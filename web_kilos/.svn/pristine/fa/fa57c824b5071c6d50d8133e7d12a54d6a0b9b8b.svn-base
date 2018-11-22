package com.javacan.common.module.config.impl;

import com.javacan.common.module.config.ConfigFactory;
import com.javacan.common.module.config.Config;
import com.javacan.common.module.config.ConfigException;

import java.util.Properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 파일로부터 설정 정보를 로딩하는 ConfigFactory. 설정 파일이 존재하지 않거나 
 * 설정 파일을 읽어올 수 없는 경우 ConfigException을 발생시킨다.
 * 설정 정보를 저장하는 파일은 "[Config Factory 이름].ConfigFile" 시스템 프로퍼티를
 * 사용하여 지정한다.
 * 
 * @author 최범균
 */
public class FileConfigFactory extends ConfigFactory {
    
    private FileConfig fileConfig;
    
    public FileConfigFactory() {
    }
    
    public Config createConfig() throws ConfigException {
        if (fileConfig == null) {
        	createFileConfig();
    	}
    	return fileConfig;
    }
    
    private synchronized void createFileConfig() throws ConfigException {
    	if (fileConfig != null) return;
    	
        String systemPropertyName = getName()+".ConfigFile";
        String configFileName = System.getProperty(systemPropertyName);
        
        File configFile = new File(configFileName);
        if (configFile.exists() && configFile.canRead()) {
            Properties properties = new Properties();
            FileInputStream is = null;
            try {
            	is = new FileInputStream(configFile);
                properties.load(is);
                fileConfig = new FileConfig(properties);
            } catch(IOException ex) {
            	throw new ConfigException(ex.getMessage(), ex);
        	} finally {
        		if (is != null) try { is.close(); } catch(IOException ex) {}
    		}
        } else {
            throw new ConfigException("Config file does not exists or can't be read");
        }
    }
}
