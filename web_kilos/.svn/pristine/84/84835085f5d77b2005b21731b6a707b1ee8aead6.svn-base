package com.javacan.common.module.logging;

import java.util.List;

/**
 * 로그 메시지를 Handler에 전달해주는 로거.
 * @author 최범균
 */
public class Logger {
    private List handlerList = new java.util.ArrayList();
    
    private String name;
    private Level level;
    private LoggerListener listener;
    
    public Logger(String name, Level level) {
        this.name = name;
        this.level = level;
    }
    
    public Level getLevel() {
        return level;
    }
    
    public void setLevel(Level level) {
        this.level = level;
    }
    
    public static Logger getLogger(String name) {
        return LoggerFactory.getInstance().createLogger(name);
    }
    
    public void debug(String msg, String source, Throwable e) {
        logging(Level.DEBUG, msg, source, e);
    }
    
    public void debug(String msg, String source) {
        logging(Level.DEBUG, msg, source, null);
    }
    
    public void info(String msg, String source, Throwable e) {
        logging(Level.INFO, msg, source, e);
    }
    
    public void info(String msg, String source) {
        logging(Level.INFO, msg, source, null);
    }
    
    public void warn(String msg, String source, Throwable e) {
        logging(Level.WARN, msg, source, e);
    }
    
    public void warn(String msg, String source) {
        logging(Level.WARN, msg, source, null);
    }
    
    public void error(String msg, String source, Throwable e) {
        logging(Level.ERROR, msg, source, e);
    }
    
    public void error(String msg, String source) {
        logging(Level.ERROR, msg, source, null);
    }
    
    public void fatal(String msg, String source, Throwable e) {
        logging(Level.FATAL, msg, source, e);
    }
    
    public void fatal(String msg, String source) {
        logging(Level.FATAL, msg, source, null);
    }
    
    public void addHandler(Handler handler) {
        handlerList.add(handler);
    }
    
    public void removeHandler(Handler handler) {
        handlerList.remove(handler);
    }
    
    public String getName() {
        return name;
    }
    
    public void setLoggerListener(LoggerListener listener) {
        this.listener = listener;
    }
    
    public void logging(Level level, String msg, String source, Throwable e) {
        if (listener != null) listener.process(level, msg, source, e);
        if (this.level.getValue() <= level.getValue()) {
            if (handlerList.size() > 0) {
                for (int i = 0 ; i < handlerList.size() ; i++) {
                    Handler handler = (Handler)handlerList.get(i);
                    handler.logging(level, msg, source, e);
                }
            }
        }
    }
}
