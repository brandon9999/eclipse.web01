package com.javacan.common.module.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * �����ͺ��̽� Ŀ�ؼ��� �����ϴ� Ŭ�������� ��ӹ޾ƾ� �ϴ� �߻� Ŭ����
 * @author �ֹ���
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
                    // ������ �߻��ϸ� �⺻ �����ͺ��̽�Ŀ�ؼǸŴ��� ���
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
