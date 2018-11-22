package com.javacan.mvc.command;

import com.javacan.mvc.configuration.Configuration;
import com.javacan.mvc.configuration.ConfigurationDigester;
import com.javacan.mvc.configuration.ConfigurationDigesterException;

/**
 * Command �ν��Ͻ��� �������ִ� Command ���丮
 * @author �ֹ���
 */
public class CommandFactory {
    
    private static CommandFactory instance = new CommandFactory();
    
    public static CommandFactory getInstance() {
        return instance;
    }
    
    private Configuration configuration;
    
    private CommandFactory() {
    }
    
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
    
    /**
     * @param command ��û Ŀ�ǵ�
     */
    public Command createCommand(String cmd) {
        Command command = configuration.createCommand(cmd);
        return command;
    }
}