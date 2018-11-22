package com.javacan.common.module.db.impl;

import javax.naming.*;
import javax.sql.*;
import java.sql.*;

import com.javacan.common.module.db.DBConnectionManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DefaultDBConnectionManager extends DBConnectionManager {
    
    public DefaultDBConnectionManager() throws Throwable 
    {
        //Class.forName("org.gjt.mm.mysql.Driver");
    }
    
    public Connection getConnection(String dbName) throws SQLException 
    {
        //return java.sql.DriverManager.getConnection(
        //    "jdbc:mysql://localhost/kilos?useUnicode=true&characterEncoding=euc-kr",
        //    "kilos", "kilos");
        Context ctx = null;
        DataSource ds = null;
        Connection conDs = null;

        try
        {
                ctx = new InitialContext();
                ds = (DataSource) ctx.lookup("dbsource");
                conDs = ds.getConnection();
        //        return conDs;
        }
        catch ( Exception e )
        {
                e.printStackTrace();
        }

        return conDs;    	
    }
    
    public void releaseConnection(Connection conn) throws SQLException {
        conn.close();
    }

}
