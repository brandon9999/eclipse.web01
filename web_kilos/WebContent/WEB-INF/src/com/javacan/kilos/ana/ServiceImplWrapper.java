package com.javacan.kilos.ana;

import com.javacan.wrapper.WrapperHelperFactory;
import com.javacan.wrapper.WrapperHelper;
import com.javacan.wrapper.WrapperHelperException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

public class ServiceImplWrapper {
	
    private Connection conn = null;
    private WrapperHelperFactory helperFactory = WrapperHelperFactory.getInstance();
    
    public ServiceImplWrapper(Connection conn) {
        this.conn = conn;
    }
    
    public void insert(ServiceImpl service)
    throws WrapperHelperException, SQLException {
        WrapperHelper helper = helperFactory.createWrapperHelper(ServiceImpl.class);
        helper.insert(conn, service);
    }
    
    public ServiceImpl select(String serviceCode) 
    throws WrapperHelperException, SQLException {
        WrapperHelper helper = helperFactory.createWrapperHelper(ServiceImpl.class);
        Object[] keys = {serviceCode};
        return (ServiceImpl)helper.select(conn, keys);
    }

    public int update(ServiceImpl service) 
    throws WrapperHelperException, SQLException {
        WrapperHelper helper = helperFactory.createWrapperHelper(ServiceImpl.class);
        return helper.update(conn, service);
    }
    
    public int delete(String serviceCode)
    throws WrapperHelperException, SQLException {
        WrapperHelper helper = helperFactory.createWrapperHelper(ServiceImpl.class);
        Object[] keys = {serviceCode};
        return helper.delete(conn, keys);
    }
    
    public List selectAll() 
    throws WrapperHelperException, SQLException {
    	Statement stmt = null;
    	ResultSet rs = null;
    	
    	try {
    		stmt = conn.createStatement();
    		rs = stmt.executeQuery("select CODE from SERVICE");
    		
    		List serviceList = new java.util.ArrayList(64);
    		
    		while (rs.next()) {
    			serviceList.add(this.select(rs.getString("CODE")));
			}
			
			return serviceList;
		} finally {
	        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	        if (stmt != null) try { stmt.close(); } catch(SQLException ex) {}
		}
	}
	
	public void close() {
		conn = null;
	}
}
