package com.javacan.wrapper;

import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.digester.Digester;

/**
 * 지정한 클래스 이름과 관련된 시스템 자원으로부터 
 * 데이터빈-테이블 사이의 매핑 정보를 로딩하여 그 값을 저장한
 * ORMapping 객체를 생성해주는 유틸리티 클래스
 * 
 * @author 최범균
 */
public class WrapperHelperUtil 
{
    /**
     * 파라미터로 전달한 클래스와 관련된 설정 파일로부터 ORMapping 객체를
     * 생성한다. 이때 자원의 이름은 beanClass의 클래스 이름에 ".xml"을 붙인 것이다.
     * 예를 들어, com.javacan.kilos.member.MemberBean 클래스를 전달했을 경우
     * 설정 파일은 com/javacan/kilos/member 디렉토리의 MemberBean.xml 파일이 된다.
     * 
     * @param beanClass 매핑 설정 파일과 관련된 클래스
     */
    public static ORMapping createORMapping(Class beanClass)
    throws WrapperHelperUtilException {
        InputStream is = null;
        try {
            String className = beanClass.getName();
            int lastIndexDot = className.lastIndexOf(".");
            if (lastIndexDot == -1) {
                is = beanClass.getResourceAsStream(className + ".xml");
            } else {
                is = beanClass.getResourceAsStream(
                    className.substring(lastIndexDot+1)+".xml");
            }
            
            if (is == null) 
                throw new WrapperHelperUtilException(
                    "Not Found Configuration File:"+className, null);
            
            Digester digester = new Digester();
            digester.setValidating(false);
            
            digester.addObjectCreate("or-mapping", ORMapping.class);
            digester.addBeanPropertySetter("or-mapping/bean-class", "beanClassName");
            digester.addBeanPropertySetter("or-mapping/table-name", "tableName");
            
            digester.addObjectCreate(
                "or-mapping/field-mapping", FieldMapping.class);
            digester.addBeanPropertySetter(
                "or-mapping/field-mapping/property-name", "propertyName");
            digester.addBeanPropertySetter(
                "or-mapping/field-mapping/property-type", "propertyTypeByString");
            digester.addBeanPropertySetter(
                "or-mapping/field-mapping/field-name", "fieldName");
            digester.addBeanPropertySetter(
                "or-mapping/field-mapping/field-type", "fieldTypeByString");
            digester.addBeanPropertySetter(
                "or-mapping/field-mapping/pk-field", "primaryKeyByString");
            
            digester.addSetNext("or-mapping/field-mapping", "addFieldMapping");
            
            ORMapping orMapping = (ORMapping) digester.parse(is);
            return orMapping;
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new WrapperHelperUtilException(ex.getMessage(), ex);
        } finally {
            if (is != null) try { is.close(); } catch(IOException ex) {}
        }
    }
}
