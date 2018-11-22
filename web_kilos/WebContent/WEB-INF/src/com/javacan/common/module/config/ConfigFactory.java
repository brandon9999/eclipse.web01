package com.javacan.common.module.config;

import java.util.Map;

/**
 * Config를 생성해주는 팩토리가 상속받아야 하는 추상 클래스.
 * createConfigFactory() 메소드를 사용하여 알맞은 사용할 ConfigFactory를 생성하는데
 * 이때 전달되는 파라미터를 시스템 프로퍼티의 이름으로 사용하여
 * 생성할 ConfigFactory의 클래스이름을 읽어온다.
 * 
 * @author 최범균
 */
public abstract class ConfigFactory {
    
    private static Map factoryRepository = new java.util.HashMap();
    
    private static Object syncObj = new Object();
    
    /**
     * name과 관련된 config 팩토리를 리턴한다.
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
     * 클래스의 이름에 ".ConfigFactory"를 덧붙인 문자열을 이름으로 사용하여
     * 그에 해당하는 ConfigFactory 인스턴스를 리턴한다.
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
