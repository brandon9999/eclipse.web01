package com.javacan.common.module.config;

/**
 * Config�� ���õ� ������ �߻��� ��� �߻��ϴ� ����.
 * @author �ֹ���
 */
public class ConfigException extends Exception {
    public ConfigException(String msg) {
        super(msg);
    }
    
    public ConfigException(String msg, Throwable e) {
        super(msg, e);
    }
}
