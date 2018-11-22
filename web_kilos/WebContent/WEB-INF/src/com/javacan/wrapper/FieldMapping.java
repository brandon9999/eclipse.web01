package com.javacan.wrapper;

import java.sql.Types;

/**
 * �ڹٺ� ������Ƽ�� ���̺� �ʵ� ������ ���� ������ �����Ѵ�.
 * @author �ֹ���
 */
public class FieldMapping {
    private String propertyName;
    private Class propertyType;
    private String fieldName;
    private int fieldType;
    private boolean primaryKey;
    
    public FieldMapping() {}
    
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    public String getPropertyName() {
        return propertyName;
    }
    
    /**
     * beanPropertyTypeName�� �� �� �ִ� ����
     * "int", "long", "short", "float", "double", "char", "boolean" �̰ų�
     * �Ǵ� ������ Ŭ���� �̸��� ���� �ȴ�. �⺻ Ÿ���� ��� Ÿ�� ���� Ŭ������
     * TYPE �ʵ尡 Ÿ��Ŭ������ �����ȴ�. ���� ���, "int"�� ������ ���
     * beanPropertyType�� Integer.TYPE�� �����ȴ�.
     * �̵� Ÿ�� �ܿ��� java.lang.String, java.util.Date, java.sql.Timestamp,
     * java.sql.Date, java.sql.Time Ŭ������ �����Ѵ�.
     */
    public void setPropertyTypeByString(String propertyTypeName)
    throws ClassNotFoundException {
        if (propertyTypeName.compareTo("int") == 0) {
            propertyType = Integer.TYPE;
        } else if (propertyTypeName.compareTo("long") == 0) {
            propertyType = Long.TYPE;
        } else if (propertyTypeName.compareTo("short") == 0) {
            propertyType = Short.TYPE;
        } else if (propertyTypeName.compareTo("float") == 0) {
            propertyType = Float.TYPE;
        } else if (propertyTypeName.compareTo("double") == 0) {
            propertyType = Float.TYPE;
        } else if (propertyTypeName.compareTo("char") == 0) {
            propertyType = Character.TYPE;
        } else if (propertyTypeName.compareTo("boolean") == 0) {
            propertyType = Boolean.TYPE;
        } else if (propertyTypeName.compareTo("byte") == 0) {
            propertyType = Byte.TYPE;
        } else if (propertyTypeName.compareTo("java.lang.String") == 0 ||
                   propertyTypeName.compareTo("String") == 0) {
            propertyType = Class.forName("java.lang.String");
        } else if (propertyTypeName.compareTo("java.sql.Timestamp") == 0 ||
                   propertyTypeName.compareTo("java.sql.Date") == 0 ||
                   propertyTypeName.compareTo("java.sql.Time") == 0 ||
                   propertyTypeName.compareTo("java.util.Date") == 0 ) {
            propertyType = Class.forName(propertyTypeName);
        } else {
            throw new IllegalArgumentException(
                "Not Supported Type: "+propertyTypeName);
        }
    }
    
    public Class getPropertyType() {
        return propertyType;
    }
    
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldName() {
        return fieldName;
    }
    
    /**
     * fieldTypeName�� �� �� �ִ� ���� CHAR, VARCHAR, LONGVARCHAR, BIGINT,
     * DECIMAL, NUMERIC, DOUBLE, FLOAT, INTEGER, SMALLINT, TINYINT, TIMESTAMP,
     * TIME, DATE �̴�.
     * @exception IllegalArgumentException �������� Ÿ�԰��� ������ ���
     */
    public void setFieldTypeByString(String fieldTypeName) {
        fieldTypeName = fieldTypeName.toUpperCase();
        
        if (fieldTypeName.equals("CHAR")) {
            fieldType = Types.CHAR;
        } else if (fieldTypeName.equals("VARCHAR")) {
            fieldType = Types.VARCHAR;
        } else if (fieldTypeName.equals("LONGVARCHAR")) {
            fieldType = Types.LONGVARCHAR;
        } else if (fieldTypeName.equals("BIGINT")) {
            fieldType = Types.BIGINT;
        } else if (fieldTypeName.equals("DECIMAL")) {
            fieldType = Types.DECIMAL;
        } else if (fieldTypeName.equals("NUMERIC")) {
            fieldType = Types.NUMERIC;
        } else if (fieldTypeName.equals("DOUBLE")) {
            fieldType = Types.DOUBLE;
        } else if (fieldTypeName.equals("FLOAT")) {
            fieldType = Types.FLOAT;
        } else if (fieldTypeName.equals("INTEGER")) {
            fieldType = Types.INTEGER;
        } else if (fieldTypeName.equals("SMALLINT")) {
            fieldType = Types.SMALLINT;
        } else if (fieldTypeName.equals("TINYINT")) {
            fieldType = Types.TINYINT;
        } else if (fieldTypeName.equals("TIMESTAMP")) {
            fieldType = Types.TIMESTAMP;
        } else if (fieldTypeName.equals("TIME")) {
            fieldType = Types.TIME;
        } else if (fieldTypeName.equals("DATE")) {
            fieldType = Types.DATE;
        } else {
            // �������� �ʴ� Ÿ���� ���
            throw new IllegalArgumentException("Not Supported Type:"+fieldTypeName);
        }
    }
    public int getFieldType() {
        return fieldType;
    }
    
    public void setPrimaryKeyByString(String isPk) {
        if (isPk != null && isPk.equals("true")) {
            this.primaryKey = true;
        } else {
            this.primaryKey = false;
        }
    }
    
    public boolean isPrimaryKey() {
        return primaryKey;
    }
}
