package com.javacan.mvc.configuration;

/**
 * ConfigurationDigester�� XML ������ �Ľ��ϴ� ���ȿ�
 * ������ ���� �� �߻��ϴ� ����
 * 
 * @author �ֹ���
 */
public class ConfigurationDigesterException extends Exception {
    public ConfigurationDigesterException(Throwable ex) {
        super(ex.getMessage(), ex);
    }
}