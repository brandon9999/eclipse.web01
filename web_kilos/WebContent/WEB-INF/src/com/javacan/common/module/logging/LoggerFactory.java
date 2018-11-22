package com.javacan.common.module.logging;

import java.util.StringTokenizer;
import java.util.Map;
import com.javacan.common.module.logging.impl.ConsoleHandler;

/**
 * 로거를 생성해주는 팩토리
 * 
 * com.javacan.common.module.logging.loggers=kilos,system
 * kilos.level=warn
 * kilos.handlers=com.javacan.common.module.logging.impl.Log4jHandler,com.javacan.common.module.logging.impl.SystemOutHandler
 * kilos.listener=com.javacan.kilos.loggin.NoticeFatalErrorListener
 * system.handlers=com.javaacn.common.module.logging.impl.SystemOutHandler
 * system.level=debug
 * 
 * @author 최범균
 */
public class LoggerFactory {
    public static final String DEFAULT_LOGGER_NAME = "default";
    
    public static LoggerFactory instance = new LoggerFactory();
    
    public static LoggerFactory getInstance() {
        return instance;
    }
    
    private Map loggerRepository = new java.util.HashMap();
    
    private LoggerFactory() {
        initialize();
    }
    
    private void initialize() {
        Logger defaultLogger = new Logger(DEFAULT_LOGGER_NAME, Level.DEBUG);
        defaultLogger.addHandler(new ConsoleHandler());
        loggerRepository.put(defaultLogger.getName(), defaultLogger);
        
        String loggerNames = System.getProperty("com.javacan.common.module.logging.loggers");
        if (loggerNames != null) {
            StringTokenizer tokenizer = new StringTokenizer(loggerNames, ",");
            while(tokenizer.hasMoreTokens()) {
                String loggerName = tokenizer.nextToken();
                String loggerLevelName = System.getProperty(loggerName+".level");
                String loggerHandlers = System.getProperty(loggerName+".handlers");
                String loggerListener = System.getProperty(loggerName+".listener");
                
                Level level = null;
                if (loggerLevelName == null || loggerLevelName.equals("debug")) {
                    level = Level.DEBUG;
                } else if (loggerLevelName.equals("info")) {
                    level = Level.INFO;
                } else if (loggerLevelName.equals("warn")) {
                    level = Level.WARN;
                } else if (loggerLevelName.equals("error")) {
                    level = Level.ERROR;
                } else if (loggerLevelName.equals("fatal")) {
                    level = Level.FATAL;
                }
                Logger logger = new Logger(loggerName, level);
                
                // 핸들러를 등록한다.
                if (loggerHandlers != null) {
                    StringTokenizer tokenizer2 = new StringTokenizer(loggerHandlers, ",");
                    while(tokenizer2.hasMoreTokens()) {
                        String handlerClassName = tokenizer2.nextToken();
                        try {
                            Class handlerClass = Class.forName(handlerClassName);
                            Handler handler = (Handler)handlerClass.newInstance();
                            logger.addHandler(handler);
                        } catch(Throwable e) {}
                    }
                }
                // 로거 리스너 등록
                if (loggerListener != null) {
                    try {
                        Class listenerClass = Class.forName(loggerListener);
                        LoggerListener listener = (LoggerListener)listenerClass.newInstance();
                        logger.setLoggerListener(listener);
                    } catch(Throwable e) {}
                }
                loggerRepository.put(logger.getName(), logger);
            }
        }
    }
    
    public Logger createLogger(String name) {
        Logger logger = (Logger)loggerRepository.get(name);
        if (logger != null) return logger;
        else return (Logger)loggerRepository.get(DEFAULT_LOGGER_NAME);
    }
}
