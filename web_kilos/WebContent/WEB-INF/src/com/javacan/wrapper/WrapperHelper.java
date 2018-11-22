package com.javacan.wrapper;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.javacan.beanutil.BeanUtil;
import java.lang.reflect.InvocationTargetException;

/**
 * DB 테이블과 DataBean 사이의 기본적인 CRUD 매핑을 처리해주는 헬퍼 클래스
 * 
 * @author 최범균
 */
public class WrapperHelper {
    
    private ORMapping orMapping;
    
    public WrapperHelper(ORMapping orMapping) {
        this.orMapping = orMapping;
        initWrapperHelper();
    }
    
    private String insertQuery;
    private String selectQuery;
    private String updateQuery;
    private String deleteQuery;
    
    private void initWrapperHelper() {
        // CRUD 쿼리를 생성한다.
        StringBuffer buffer = new StringBuffer(1024);
        
        List fieldList = orMapping.getFieldList();
        List pkFieldList = orMapping.getPKFieldList();
        FieldMapping mapping = null;
        
        // pk를 사용하는 where 구문 작성
        buffer.append("where ");
        for (int i = 0 ; i < pkFieldList.size() ; i++) {
            mapping = (FieldMapping) pkFieldList.get(i);
            if (i > 0) buffer.append("and ");
            buffer.append(mapping.getFieldName()).append(" = ?");
        }
        String wherePart = buffer.toString();
        
        buffer.delete(0, buffer.length());
        
        // insert 쿼리 생성
        buffer.append("insert into ").append(orMapping.getTableName())
              .append(" (");
        for (int i = 0 ; i < fieldList.size() ; i++) {
            mapping = (FieldMapping) fieldList.get(i);
            if (i > 0) buffer.append(", ");
            buffer.append(mapping.getFieldName());
        }
        buffer.append(") values (");
        for (int i = 0 ; i < fieldList.size() ; i++) {
            if (i > 0) buffer.append(", ");
            buffer.append("?");
        }
        buffer.append(")");
        
        insertQuery = buffer.toString();
        
        buffer.delete(0, buffer.length());
        
        // select 쿼리 생성
        buffer.append("select ");
        for (int i = 0 ; i < fieldList.size() ; i++) {
            mapping = (FieldMapping) fieldList.get(i);
            if (i > 0) buffer.append(", ");
            buffer.append(mapping.getFieldName());
        }
        buffer.append(" from ").append(orMapping.getTableName()).append(" ")
              .append(wherePart);
        selectQuery = buffer.toString();
        
        buffer.delete(0, buffer.length());
        
        // update 쿼리 생성
        int fieldCount = 0;
        buffer.append("update ").append(orMapping.getTableName()).append(" set ");
        for (int i = 0 ; i < fieldList.size() ; i++) {
            mapping = (FieldMapping) fieldList.get(i);
            if (!mapping.isPrimaryKey()) {
                if (fieldCount++ > 0) buffer.append(", ");
                buffer.append(mapping.getFieldName()).append(" = ?");
            }
        }
        buffer.append(" ").append(wherePart);
        updateQuery = buffer.toString();
        
        buffer.delete(0, buffer.length());
        
        // delete 쿼리 생성
        buffer.append("delete from ").append(orMapping.getTableName())
              .append(" ").append(wherePart);
        deleteQuery = buffer.toString();
    }
    
    public int insert(Connection conn, Object bean)
    throws SQLException, WrapperHelperException {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(insertQuery);
            List fieldList = orMapping.getFieldList();
            for (int i = 0 ; i < fieldList.size() ; i++) {
                FieldMapping mapping = (FieldMapping)fieldList.get(i);
                Object propertyValue = 
                    BeanUtil.getProperty(bean, mapping.getPropertyName());
                FieldMappingUtil.setPreparedParameter(
                    pstmt, i + 1, mapping, propertyValue);
            }
            return pstmt.executeUpdate();
        } catch(NoSuchMethodException ex) {
            throw new WrapperHelperException(ex.getMessage(), ex);
        } catch(InvocationTargetException ex) {
            throw new WrapperHelperException(ex.getMessage(), ex);
        } catch(IllegalAccessException ex) {
            throw new WrapperHelperException(ex.getMessage(), ex);
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
        }
    }
    
    /**
     * 한 행의 정보를 bean에 저장한다. 지정한 행이 존재할 경우 데이터를 저장한
     * bean을 리턴하고, 존재하지 않을 경우 null을 리턴한다.
     * 만약 bean이 null이면 새로운 객체를 생성해서 리턴한다.
     * @param pkVal 테이블의 한 행을 선택할 때 사용되는 PK
     * @param bean 데이터를 저장할 빈 객체
     */
    public Object select(Connection conn, Object[] pkVal, Object bean)
    throws SQLException, WrapperHelperException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = conn.prepareStatement(selectQuery);
            
            List pkFieldList = orMapping.getPKFieldList();
            for (int i = 0 ; i < pkFieldList.size() ; i++) {
                FieldMapping mapping = (FieldMapping)pkFieldList.get(i);
                FieldMappingUtil.setPreparedParameter(pstmt, i+1, mapping, pkVal[i]);
            }
            
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if(bean == null) bean = orMapping.getBeanClass().newInstance();
                if(bean.getClass() != orMapping.getBeanClass())
                    throw new IllegalArgumentException(
                        "Invalid Bean Type:"+bean.getClass().getName());
                
                // rs에서 bean으로 데이터 복사
                List fieldList = orMapping.getFieldList();
                for (int i = 0 ; i < fieldList.size() ; i++) {
                    FieldMapping mapping = (FieldMapping)fieldList.get(i);
                    FieldMappingUtil.copyResultSetToBean(rs, mapping, bean);
                }
                return bean;
            } else {
                return null;
            }
        } catch(InstantiationException ex) {
            throw new WrapperHelperException(ex.getMessage(), ex);
        } catch(IllegalAccessException ex) {
            throw new WrapperHelperException(ex.getMessage(), ex);
        } catch(NoSuchMethodException ex) {
            throw new WrapperHelperException(ex.getMessage(), ex);
        } catch(InvocationTargetException ex) {
            throw new WrapperHelperException(ex.getMessage(), ex);
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
        }
    }
    
    /**
     * 한 행의 정보를 저장한 빈 객체를 리턴한다. 존재하지 않을 경우 null을 리턴한다.
     * @param pkVal 테이블의 한 행을 선택할 때 사용되는 PK
     */
    public Object select(Connection conn, Object[] pkVal) 
    throws SQLException, WrapperHelperException {
        return select(conn, pkVal, null);
    }
    
    /**
     * 테이블의 한 행 데이터를 빈에 저장된 데이터로 변환한다.
     * @param bean 데이터를 저장하고 있는 빈
     */
    public int update(Connection conn, Object bean) 
    throws SQLException, WrapperHelperException {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(updateQuery);
            
            List fieldList = orMapping.getFieldList();
            int parameterIndex = 1;
            Object propertyValue = null;
            for (int i = 0 ; i < fieldList.size() ; i++) {
                FieldMapping mapping = (FieldMapping)fieldList.get(i);
                if (!mapping.isPrimaryKey()) {
                    propertyValue = BeanUtil.getProperty(bean, mapping.getPropertyName());
                    FieldMappingUtil.setPreparedParameter(
                        pstmt, parameterIndex, mapping, propertyValue);
                    parameterIndex++;
                }
            }
            List pkFieldList = orMapping.getPKFieldList();
            for (int i = 0 ; i < pkFieldList.size() ; i++) {
                FieldMapping mapping = (FieldMapping)pkFieldList.get(i);
                propertyValue = BeanUtil.getProperty(bean, mapping.getPropertyName());
                FieldMappingUtil.setPreparedParameter(
                    pstmt, parameterIndex, mapping, propertyValue);
                parameterIndex++;
            }
            return pstmt.executeUpdate();
        } catch(NoSuchMethodException ex) {
            throw new WrapperHelperException(ex.getMessage(), ex);
        } catch(InvocationTargetException ex) {
            throw new WrapperHelperException(ex.getMessage(), ex);
        } catch(IllegalAccessException ex) {
            throw new WrapperHelperException(ex.getMessage(), ex);
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
        }
    }
    
    /**
     * 지정한 PK에 해당하는 행을 삭제한다.
     * @param pkVal PK 값
     */
    public int delete(Connection conn, Object[] pkVal)
    throws SQLException {
        PreparedStatement pstmt = null;
        
        try {
            pstmt = conn.prepareStatement(deleteQuery);
            
            List pkFieldList = orMapping.getPKFieldList();
            for (int i = 0 ; i < pkFieldList.size() ; i++) {
                FieldMapping mapping = (FieldMapping)pkFieldList.get(i);
                FieldMappingUtil.setPreparedParameter(
                    pstmt, i+1, mapping, pkVal[i]);
            }
            return pstmt.executeUpdate();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
        }
    }
}
