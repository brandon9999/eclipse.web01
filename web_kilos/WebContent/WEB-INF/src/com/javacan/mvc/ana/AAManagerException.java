package com.javacan.mvc.ana;

public class AAManagerException extends Exception {
    
    public AAManagerException(String msg) {
        super(msg);
    }
    
    public AAManagerException(String msg, Throwable e) {
        super(msg, e);
    }
}
