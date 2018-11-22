package com.javacan.common.module.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 데이터베이스 커넥션을 관리하는 클래스들이 상속받아야 하는 추상 클래스
 * @author 최범균
 */
public abstract class DBConnectionManager {
    public static final String DEFAULT_CLASS_NAME = 
        "com.javacan.common.module.db.impl.DefaultDBConnectionManager";
    
    private static DBConnectionManager instance = null;
    
    public static DBConnectionManager getDBConnectionManager() throws SQLException {
        if (instance == null) {
            initInstance();
        }
        return instance;
    }
    
    private static void initInstance() throws SQLException {
        synchronized(DBConnectionManager.class) {
            if (instance == null) {
                String className = 
                    System.getProperty("com.javacan.common.module.db.DBConnectionManager", DEFAULT_CLASS_NAME);
                try {
                    Class managerClass = Class.forName(className);
                    instance = (DBConnectionManager)managerClass.newInstance();
                } catch(Throwable ex) {
                    // 에러가 발생하면 기본 데이터베이스커넥션매니저 사용
                    try {
                        Class managerClass = Class.forName(DEFAULT_CLASS_NAME);
                        instance = (DBConnectionManager)managerClass.newInstance();
                    } catch(Throwable ex2) {
                        throw new SQLException("Can't initiate DB Connection Manager");
                    }
                }
            }
        }
    }
    
    public abstract Connection getConnection(String dbName) throws SQLException;
    
    public abstract void releaseConnection(Connection conn) throws SQLException;
}
