package com.javacan.kilos.ana;

import com.javacan.mvc.ana.UserInfo;

public class UserInfoImpl implements UserInfo {
    private String id;
    private String password;
    
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
}
