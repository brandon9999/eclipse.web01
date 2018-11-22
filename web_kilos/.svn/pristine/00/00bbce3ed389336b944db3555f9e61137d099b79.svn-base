package com.javacan.wrapper;

import java.util.Map;
import java.util.List;

/**
 * 테이블과 빈 사이의 매핑 정보를 저장하는 클래스로서
 * 테이블래퍼헬퍼 클래스에서 테이블 및 데이터 빈 정보를 추출할 때 사용된다.
 * 
 * @author 최범균
 */
public class ORMapping {
    private Class beanClass;
    private String tableName;
    
    private Map fieldMapping    = new java.util.HashMap();
    private Map propertyMapping = new java.util.HashMap();
    
    private List fieldList   = new java.util.ArrayList();
    private List pkFieldList = new java.util.ArrayList(1);
    
    private Map pkFieldMap = new java.util.HashMap();
    
    public ORMapping() {
    }
    
    public void setBeanClassName(String beanClassName) throws ClassNotFoundException {
        beanClass = Class.forName(beanClassName);
    }
    
    public Class getBeanClass() {
        return beanClass;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getTableName() {
        return tableName;
    }
    
    public void addFieldMapping(FieldMapping mapping) {
        fieldMapping.put(mapping.getFieldName(), mapping);
        propertyMapping.put(mapping.getPropertyName(), mapping);
        fieldList.add(mapping);
        if (mapping.isPrimaryKey()) {
            pkFieldList.add(mapping);
            pkFieldMap.put(mapping.getFieldName(), new Boolean(true));
        } else {
            pkFieldMap.put(mapping.getFieldName(), new Boolean(false));
        }
    }
    
    public FieldMapping getFieldMappingByField(String fieldName) {
        FieldMapping val = (FieldMapping)fieldMapping.get(fieldName);
        if (val == null) 
            throw new IllegalArgumentException("Invalid Field Name:"+fieldName);
        return val;
    }

    public FieldMapping getFieldMappingByProperty(String propertyName) {
        FieldMapping val =  (FieldMapping)propertyMapping.get(propertyName);
        if (val == null) 
            throw new IllegalArgumentException("Invalid Property Name:"+propertyName);
        return val;
    }
    
    public List getFieldList() {
        return fieldList;
    }
    
    public List getPKFieldList() {
        return pkFieldList;
    }
    
    public boolean isPKField(String fieldName) {
        Boolean val = (Boolean)pkFieldMap.get(fieldName);
        if (val == null) 
            throw new IllegalArgumentException("Invalid Field Name:"+fieldName);
        return val.booleanValue();
    }
}
