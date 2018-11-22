package com.javacan.mvc.command;

import com.javacan.mvc.configuration.Configuration;
import com.javacan.mvc.configuration.ConfigurationDigester;
import com.javacan.mvc.configuration.ConfigurationDigesterException;

/**
 * Command 인스턴스를 생성해주는 Command 팩토리
 * @author 최범균
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
     * @param command 요청 커맨드
     */
    public Command createCommand(String cmd) {
        Command command = configuration.createCommand(cmd);
        return command;
    }
}