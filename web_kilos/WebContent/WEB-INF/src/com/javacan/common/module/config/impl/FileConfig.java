package com.javacan.common.module.config.impl;

import com.javacan.common.module.config.Config;
import java.util.Properties;
import java.util.Map;

public class FileConfig extends Config {
    
    private Properties properties;
    
    FileConfig(Properties properties) {
        this.properties = properties;
    }
    
    public String getValue(String propertyName) {
        return properties.getProperty(propertyName);
    }
    
    public String getValue(String propertyName, String defaultValue) {
        return properties.getProperty(propertyName, defaultValue);
    }
    
    public void setValue(String propertyName, String value) {
        properties.setProperty(propertyName, value);
    }
    
    public Map getPropertyMap() {
        return properties;
    }
}
