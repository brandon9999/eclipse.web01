package com.javacan.kilos.ana;

import com.javacan.wrapper.WrapperHelperFactory;
import com.javacan.wrapper.WrapperHelper;
import com.javacan.wrapper.WrapperHelperException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

public class RoleImplWrapper {
    
    private Connection conn = null;
    private WrapperHelperFactory helperFactory = WrapperHelperFactory.getInstance();
    
    public RoleImplWrapper(Connection conn) {
        this.conn = conn;
    }
    
    public void insert(RoleImpl role)
    throws WrapperHelperException, SQLException {
        WrapperHelper helper = helperFactory.createWrapperHelper(RoleImpl.class);
        helper.insert(conn, role);
    }
    
    public RoleImpl select(String roleCode) 
    throws WrapperHelperException, SQLException {
        WrapperHelper helper = helperFactory.createWrapperHelper(RoleImpl.class);
        Object[] keys = {roleCode};
        return (RoleImpl)helper.select(conn, keys);
    }

    public int update(RoleImpl role) 
    throws WrapperHelperException, SQLException {
        WrapperHelper helper = helperFactory.createWrapperHelper(RoleImpl.class);
        return helper.update(conn, role);
    }
    
    public int delete(String roleCode)
    throws WrapperHelperException, SQLException {
        WrapperHelper helper = helperFactory.createWrapperHelper(RoleImpl.class);
        Object[] keys = {roleCode};
        return helper.delete(conn, keys);
    }
    
    public List selectAll() 
    throws WrapperHelperException, SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select CODE from ROLE");
            
            List roleList = new java.util.ArrayList(8);
            
            while (rs.next()) {
                roleList.add(this.select(rs.getString("CODE")));
            }
            
            return roleList;
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (stmt != null) try { stmt.close(); } catch(SQLException ex) {}
        }
    }
    
    public void close() {
        conn = null;
    }
    
}
