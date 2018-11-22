package com.javacan.common.module.config;

import java.util.Map;

/**
 * 설정 정보를 저장하고 있는 클래스들이 구현해야 하는 추상 클래스로서
 * 설정 정보를 읽어오고 지정할 때 사용되는 메소드를 선언하고 있다.
 * @author 최범균
 */
public abstract class Config 
{
    public static Config getConfig(String name) throws ConfigException 
    {
        return ConfigFactory.createConfigFactory(name).createConfig();
    }
    public static Config getConfig(Class c) throws ConfigException 
    {
        return ConfigFactory.createConfigFactory(c).createConfig();
    }
    
    public abstract String getValue(String propertyName);
    
    public abstract String getValue(String propertyName, String defaultValue);
    
    public abstract void setValue(String propertyName, String value);
    
    public abstract Map getPropertyMap();
}
