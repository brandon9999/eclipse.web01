package com.javacan.common.module.config;

/**
 * Config와 관련된 에러가 발생할 경우 발생하는 예외.
 * @author 최범균
 */
public class ConfigException extends Exception {
    public ConfigException(String msg) {
        super(msg);
    }
    
    public ConfigException(String msg, Throwable e) {
        super(msg, e);
    }
}
