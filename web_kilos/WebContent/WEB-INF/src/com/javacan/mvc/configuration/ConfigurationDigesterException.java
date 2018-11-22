package com.javacan.mvc.configuration;

/**
 * ConfigurationDigester가 XML 문서를 파싱하는 동안에
 * 문제가 있을 때 발생하는 예외
 * 
 * @author 최범균
 */
public class ConfigurationDigesterException extends Exception {
    public ConfigurationDigesterException(Throwable ex) {
        super(ex.getMessage(), ex);
    }
}