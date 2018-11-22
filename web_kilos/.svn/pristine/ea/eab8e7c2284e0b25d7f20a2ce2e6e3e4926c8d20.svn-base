package com.javacan.mvc.configuration;

import java.util.Map;

/**
 * @author ÃÖ¹ü±Õ
 */
public class CommandConfig {
    
    private String name;
    private String type;
    
    private Map commandViewMap = new java.util.HashMap();
    
    public CommandConfig() {
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void addViewInfoConfig(ViewInfoConfig viewInfo) {
        commandViewMap.put(viewInfo.getName(), viewInfo);
    }
    
    public Map getCommandViewMap() {
        return commandViewMap;
    }
}