package com.javacan.common.module.config;

import java.util.Map;

/**
 * Config�� �������ִ� ���丮�� ��ӹ޾ƾ� �ϴ� �߻� Ŭ����.
 * createConfigFactory() �޼ҵ带 ����Ͽ� �˸��� ����� ConfigFactory�� �����ϴµ�
 * �̶� ���޵Ǵ� �Ķ���͸� �ý��� ������Ƽ�� �̸����� ����Ͽ�
 * ������ ConfigFactory�� Ŭ�����̸��� �о�´�.
 * 
 * @author �ֹ���
 */
public abstract class ConfigFactory {
    
    private static Map factoryRepository = new java.util.HashMap();
    
    private static Object syncObj = new Object();
    
    /**
     * name�� ���õ� config ���丮�� �����Ѵ�.
     */
    public static ConfigFactory createConfigFactory(String name) throws ConfigException {
        ConfigFactory factory = (ConfigFactory)factoryRepository.get(name);
        if (factory == null) {
            synchronized(syncObj) {
                factory = (ConfigFactory)factoryRepository.get(name);
                if (factory == null) {
                    try {
                        String className = System.getProperty(name);
                        Class factoryClass = Class.forName(className);
                        factory = (ConfigFactory) factoryClass.newInstance();
                        factory.setName(name);
                        factoryRepository.put(name, factory);
                    } catch(Throwable e) {
                        throw new ConfigException(e.getMessage(), e);
                    }
                }
            }
        }
        return factory;
    }
    
    /**
     * Ŭ������ �̸��� ".ConfigFactory"�� ������ ���ڿ��� �̸����� ����Ͽ�
     * �׿� �ش��ϴ� ConfigFactory �ν��Ͻ��� �����Ѵ�.
     */
    public static ConfigFactory createConfigFactory(Class c) throws ConfigException {
        return ConfigFactory.createConfigFactory(c.getName()+".ConfigFactory");
    }
    
    public ConfigFactory() {}
    
    private String name;
    
    void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public abstract Config createConfig() throws ConfigException;
}
