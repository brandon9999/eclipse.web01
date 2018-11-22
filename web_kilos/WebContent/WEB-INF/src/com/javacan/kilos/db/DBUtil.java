package com.javacan.kilos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.javacan.common.module.db.DBConnectionManager;

public class DBUtil {
    
    public static Connection getConnection(String dbName) throws SQLException {
        DBConnectionManager dbMgr = DBConnectionManager.getDBConnectionManager();
        return dbMgr.getConnection(dbName);
    }
    
    public static void releaseConnection(Connection conn) throws SQLException {
        DBConnectionManager dbMgr = DBConnectionManager.getDBConnectionManager();
        dbMgr.releaseConnection(conn);
    }
}
