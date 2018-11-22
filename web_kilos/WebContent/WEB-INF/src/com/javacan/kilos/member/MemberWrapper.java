package com.javacan.kilos.member;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.javacan.wrapper.WrapperHelperFactory;
import com.javacan.wrapper.WrapperHelper;
import com.javacan.wrapper.WrapperHelperException;

/**
 * 회원 테이블과 관련된 CRUD 처리
 * @author 최범균
 */
public class MemberWrapper {
    
    private Connection conn = null;
    private WrapperHelperFactory helperFactory = WrapperHelperFactory.getInstance();
    
    public MemberWrapper(Connection conn) {
        this.conn = conn;
    }
    
    public void insert(MemberBean member)
    throws WrapperHelperException, SQLException {
    	
    	
        /*-----------------------------------------------------------*/
        System.out.println("[Debug] insert aaaa");
        /*-----------------------------------------------------------*/

    	
        WrapperHelper helper = helperFactory.createWrapperHelper(MemberBean.class);
  
        
        /*-----------------------------------------------------------*/
        System.out.println("[Debug] " + conn.toString());
        /*-----------------------------------------------------------*/
        
                
        helper.insert(conn, member);
        
        
        
        
        /*-----------------------------------------------------------*/
        System.out.println("[Debug] member Wrapper ");
        /*-----------------------------------------------------------*/

    }
    
    public MemberBean select(String id) 
    throws WrapperHelperException, SQLException {
        WrapperHelper helper = helperFactory.createWrapperHelper(MemberBean.class);
        Object[] keys = {id};
        return (MemberBean)helper.select(conn, keys);
    }

    public int update(MemberBean member) 
    throws WrapperHelperException, SQLException {
        WrapperHelper helper = helperFactory.createWrapperHelper(MemberBean.class);
        return helper.update(conn, member);
    }
    
    public int delete(String id)
    throws WrapperHelperException, SQLException {
        WrapperHelper helper = helperFactory.createWrapperHelper(MemberBean.class);
        Object[] keys = {id};
        return helper.delete(conn, keys);
    }

    public boolean exists(String memberId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(
            "select count(*) from MEMBER where ID = ?");
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 1) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
        }
    }
    
    public String getPassword(String memberId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(
            "select PASSWORD from MEMBER where ID = ?");
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("PASSWORD");
            } else {
                return null;
            }
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
        }
    }    

    public void close() {
        if (conn != null) conn = null;
    }
}
