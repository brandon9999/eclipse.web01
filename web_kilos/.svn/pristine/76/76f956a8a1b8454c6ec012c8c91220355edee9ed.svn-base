package com.javacan.kilos.ana;

import com.javacan.mvc.ana.Service;

public class ServiceImpl implements Service {
    private String code;
    private String name;
    private String uri;
    private String description;
    private boolean stopped;
    private int grantedType;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean isStopped() {
        return stopped;
    }
    public void setStopped(boolean b) {
        this.stopped = b;
    }

    public int getGrantedType() {
        return grantedType;
    }
    
    public void setGrantedType(int type) {
        if (type == GRANTED_ALL || type == GRANTED_ALL_ROLE || type == GRANTED_LIMITED_ROLE) {
            this.grantedType = type;
        } else {
            throw new IllegalArgumentException("Invalid Granted Type="+type);
        }
    }
}
