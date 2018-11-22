package com.javacan.common.module.db.impl;

import com.javacan.common.module.db.DBConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;

public class DBCPConnectionManager extends DBConnectionManager {
    
    public DBCPConnectionManager() throws Exception {
    	Class.forName("org.gjt.mm.mysql.Driver");
        setupDriver("jdbc:mysql://localhost/kilos?useUnicode=true&characterEncoding=euc-kr");
    }
    
    public Connection getConnection(String dbName) throws SQLException {
        return DriverManager.getConnection("jdbc:apache:commons:dbcp:kilos", "kilos", "kilos");
    }
    
    public void releaseConnection(Connection conn) throws SQLException {
        conn.close();
    }

    public void setupDriver(String connectURI) throws Exception {
        ObjectPool connectionPool = new GenericObjectPool(null);
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(connectURI,null);
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory,connectionPool,null,null,false,true);
        PoolingDriver driver = new PoolingDriver();
        driver.registerPool("kilos",connectionPool);
    }
}

