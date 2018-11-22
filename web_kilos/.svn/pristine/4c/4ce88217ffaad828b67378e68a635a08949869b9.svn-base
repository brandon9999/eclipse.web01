package com.javacan.beanutil;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
 * 자바빈과 관련된 유틸.
 * @author 최범균
 */
public class BeanUtil {
    /**
     * 빈의 특정 프로퍼티의 값을 Object 타입으로 리턴한다.
     */
    public static Object getProperty(Object bean, String propertyName)
    throws NoSuchMethodException, InvocationTargetException,
           IllegalAccessException {
        Class beanClass = bean.getClass();
        
        String methodName = null;
        
        if (propertyName.length() == 1) {
            methodName = "get"+propertyName.toUpperCase();
        } else {
            methodName = "get"+propertyName.substring(0, 1).toUpperCase()
                              +propertyName.substring(1);
        }
        Method getMethod = beanClass.getDeclaredMethod(methodName, new Class[0]);
        Object propertyValue = getMethod.invoke(bean, null);
        return propertyValue;
    }
    
    public static void setProperty(Object bean, String propertyName, Object value)
    throws NoSuchMethodException, InvocationTargetException,
           IllegalAccessException {
        Class parameterType = value.getClass();
        if (parameterType == Integer.class) {
        	parameterType = Integer.TYPE;
    	} else if (parameterType == Long.class) {
		    parameterType = Long.TYPE;
		} else if (parameterType == Short.class) {
	        parameterType = Short.TYPE;
		} else if (parameterType == Double.class) {
			parameterType = Double.TYPE;
		} else if (parameterType == Float.class) {
			parameterType = Float.TYPE;
		} else if (parameterType == Byte.class) {
			parameterType = Byte.TYPE;
		} else if (parameterType == Character.class) {
			parameterType = Character.TYPE;
		} else if (parameterType == Boolean.class) {
			parameterType = Boolean.TYPE;
		}
		
		Class[] parameterList = { parameterType };
		
    	String methodName = null;
    	
        if (propertyName.length() == 1) {
            methodName = "set"+propertyName.toUpperCase();
        } else {
            methodName = "set"+propertyName.substring(0, 1).toUpperCase()
                              +propertyName.substring(1);
        }
        Class beanClass = bean.getClass();
        Method setMethod = beanClass.getDeclaredMethod(methodName, parameterList);
        
        Object[] parameterValue = { value };
        setMethod.invoke(bean, parameterValue);
	}
}
