package com.javacan.mvc.configuration;

/**
 * @author ÃÖ¹ü±Õ
 */
public class MappingConfig {
    private String uri;
    private String name;
    
    public MappingConfig() {}
    
    public void setUri(String uri) {
        this.uri = uri;
    }
    public String getUri() {
        return uri;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}