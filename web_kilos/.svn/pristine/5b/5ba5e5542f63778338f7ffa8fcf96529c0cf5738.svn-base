package com.javacan.kilos.ana;

import java.util.List;
import com.javacan.mvc.ana.AAManager;
import com.javacan.mvc.ana.AAManagerException;
import com.javacan.mvc.ana.UserInfo;
import com.javacan.mvc.ana.Role;
import com.javacan.mvc.ana.RoleNotFoundException;
import com.javacan.mvc.ana.Service;
import com.javacan.mvc.ana.ServiceNotFoundException;
import com.javacan.mvc.ana.DuplicateRoleException;
import com.javacan.mvc.ana.DuplicateServiceException;

import com.javacan.wrapper.WrapperHelperException;
import com.javacan.kilos.db.DBUtil;

import com.javacan.kilos.member.MemberBean;
import com.javacan.kilos.member.MemberManager;
import com.javacan.kilos.member.MemberNotFoundException;
import com.javacan.kilos.member.MemberManagerException;

import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AAManagerImpl implements AAManager {
    /** 
     * <roleCode, RoleImpl> 매핑 정보 저장
     */
    private Map roleCodeMap = new java.util.TreeMap();
	//private Map<String,String> roleCodeMap = new java.util.TreeMap<String,String>();
    /**
     * <serviceCode, ServiceImpl> 매핑 정보 저장
     */
    private Map serviceCodeMap = new java.util.TreeMap();
	//private Map<String,ServiceImpl> serviceCodeMap = new java.util.TreeMap<String,ServiceImpl>();
    /**
     * <uri, ServiceImpl> 매핑 정보 저장
     */
    private Map serviceUriMap = new java.util.HashMap();
	//private Map<String,ServiceImpl> serviceUriMap = new java.util.HashMap<String,ServiceImpl>();
    /**
     * <serviceCode, Map> 매핑 저장. Map에는 <roleCode, RoleImpl> 매핑 목록 저장
     * 지정한 서비스를 사용할 수 있는 역할 목록을 쉽게 구하기 위해 사용
     */
    private Map serviceToRoleMap = new java.util.TreeMap();
	//private Map<String,String> serviceToRoleMap = new java.util.TreeMap<String,String>();
	
    /**
     * <roleCode, Map> 매핑 저장. Map에는 <serviceCode, ServiceImpl> 매핑 목록 저장
     * 지정한 역할을 사용할 수 있는 서비스 목록을 쉽게 구하기 이해 사용
     */
    private Map roleToServiceMap = new java.util.TreeMap();
    //private Map<String,String> roleToServiceMap = new java.util.TreeMap<String,String>();
    
    public AAManagerImpl() throws AAManagerException {
        init();
    }
    /**
     * DB로부터 정보를 읽어온다.
     */
    private void init() throws AAManagerException {
        RoleImplWrapper roleWrapper = null;
        ServiceImplWrapper serviceWrapper = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            roleWrapper = new RoleImplWrapper(conn);
            serviceWrapper = new ServiceImplWrapper(conn);
            
            List roleList = roleWrapper.selectAll();
            for (int i = 0 ; i < roleList.size() ; i++) {
                RoleImpl role = (RoleImpl)roleList.get(i);
                roleCodeMap.put(role.getCode(), role);
            }

            List serviceList = serviceWrapper.selectAll();
            for (int i = 0 ; i < serviceList.size() ; i++) {
                ServiceImpl service = (ServiceImpl)serviceList.get(i);
                serviceCodeMap.put(service.getCode(), service);
                serviceUriMap.put(service.getUri(), service);
            }
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select ROLECODE, SERVICECODE from AUTHORITY");
            while (rs.next()) {
                String roleCode = rs.getString("ROLECODE");
                String serviceCode = rs.getString("SERVICECODE");
                
                /*--------------------------------------------------*/
                System.out.println("[DEBUG] rolecode = "+roleCode);
                System.out.println("[DEBUG] serviceCode = "+serviceCode);
                /*--------------------------------------------------*/
                
                RoleImpl role = (RoleImpl)roleCodeMap.get(roleCode);
                ServiceImpl service = (ServiceImpl) serviceCodeMap.get(serviceCode);
                
                Map roleMap = (Map)serviceToRoleMap.get(serviceCode);
                
                if (roleMap == null) {
                    roleMap = new java.util.TreeMap();
                    serviceToRoleMap.put(serviceCode, roleMap);
                }
                roleMap.put(roleCode, role);
                
                Map serviceMap = (Map)roleToServiceMap.get(roleCode);
                
                if (serviceMap == null) {
                    serviceMap = new java.util.TreeMap();
                    roleToServiceMap.put(roleCode, serviceMap);
                }
                serviceMap.put(serviceCode, service);
            }
        } catch(Exception ex) {
            throw new AAManagerException("can't create AAManagerImpl instance!", ex);
        } finally {
            if (roleWrapper != null) roleWrapper.close();
            if (serviceWrapper != null) serviceWrapper.close();
            if (conn != null) try { releaseConnection(conn); } catch(SQLException ex) {}
        }
    }

    public int authenticate(UserInfo ui, HttpServletRequest request, HttpServletResponse response) throws AAManagerException {
        try {
            MemberManager memberMgr = MemberManager.getInstance();
            MemberBean mb = memberMgr.getMemberBean(ui.getId());
            if (mb.getPassword().compareTo(ui.getPassword()) == 0) {
            	
            	
                /*--------------------------------------------------*/
                System.out.println("[DEBUG] getRoleCode = "+mb.getRoleCode());
                /*--------------------------------------------------*/
            	
            	
                Role userRole = getRole(mb.getRoleCode());
                putUserRoleToSession(userRole, request, response);
                request.getSession().setAttribute(MemberBean.class.getName(), mb);
                
                return SUCCESS;
            } else {
                return FAIL_INVALID_PASSWORD;
            }
        } catch(MemberNotFoundException ex) {
            return FAIL_INVALID_ID;
        } catch(Exception ex) {
            throw new AAManagerException(ex.getMessage(), ex);
        }
    }
    
    public void closeSession(HttpServletRequest request, HttpServletResponse response) {
        this.removeUserRoleFromSession(request);
        request.getSession().removeAttribute(MemberBean.class.getName());
    }
    
    public Role getUserRoleFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Role)session.getAttribute(RoleImpl.SESSION_ID);
    }
    
    public void putUserRoleToSession(Role role, HttpServletRequest request, HttpServletResponse response) {
        if (role instanceof RoleImpl) {
            HttpSession session = request.getSession();
            session.setAttribute(RoleImpl.SESSION_ID, role);
        }
    }
    
    private void removeUserRoleFromSession(HttpServletRequest request) {
        request.getSession().removeAttribute(RoleImpl.SESSION_ID);
    }
    
    public void addRole(Role role) throws AAManagerException, DuplicateRoleException {
        if (roleCodeMap.containsKey(role.getCode())) {
            throw new DuplicateRoleException("Duplicate Role Code: "+role.getCode());
        }
        RoleImplWrapper roleWrapper = null;
        Connection conn = null;
        try {
            conn = getConnection();
            roleWrapper.insert((RoleImpl)role);
            roleCodeMap.put(role.getCode(), role);
        } catch(Exception ex) {
            throw new AAManagerException(ex.getMessage(), ex);
        } finally {
            if (roleWrapper != null) roleWrapper.close();
            if (conn != null) try { releaseConnection(conn); } catch(SQLException ex) {}
        }
    }
    
    public Role getRole(String roleCode) throws RoleNotFoundException {

    	/*--------------------------------------------------*/
        System.out.println("[DEBUG] asdfasdfdasf");
        /*--------------------------------------------------*/
    	
    	
        RoleImpl role = (RoleImpl)roleCodeMap.get(roleCode);
        
    	/*--------------------------------------------------*/
        System.out.println("[DEBUG]" );

        System.out.println("[DEBUG] bbbbbb");
        /*--------------------------------------------------*/
    		
        
        if (role == null)
            throw new RoleNotFoundException("role code="+roleCode);
        return role;
    }
    
    public void restrict(Role role)
    throws RoleNotFoundException, AAManagerException {
        if (!role.isRestricted()) {
            RoleImplWrapper roleWrapper = null;
            Connection conn = null;
            try {
                conn = getConnection();
                roleWrapper = new RoleImplWrapper(conn);
                
                role.setRestricted(true);
                int updatedCount = roleWrapper.update((RoleImpl)role);
                if (updatedCount == 0) {
                    throw new RoleNotFoundException("role code="+role.getCode());
                }
            } catch(Exception ex) {
            	role.setRestricted(false);
                throw new AAManagerException(ex.getMessage(), ex);
            } finally {
                if (roleWrapper != null) roleWrapper.close();
                if (conn != null) try { releaseConnection(conn); } catch(SQLException ex) {}
            }
        }
    }
    
    public void lift(Role role) throws RoleNotFoundException, AAManagerException {
        if (role.isRestricted()) {
            RoleImplWrapper roleWrapper = null;
            Connection conn = null;
            try {
                conn = getConnection();
                roleWrapper = new RoleImplWrapper(conn);
                
                role.setRestricted(false);
                int updatedCount = roleWrapper.update((RoleImpl)role);
                if (updatedCount == 0) {
                    throw new RoleNotFoundException("role code="+role.getCode());
                }
            } catch(Exception ex) {
            	role.setRestricted(true);
                throw new AAManagerException(ex.getMessage(), ex);
            } finally {
                if (roleWrapper != null) roleWrapper.close();
                if (conn != null) try { releaseConnection(conn); } catch(SQLException ex) {}
            }
        }
    }
    
    public void remove(Role role)
    throws RoleNotFoundException, AAManagerException {
        RoleImplWrapper roleWrapper = null;
        Connection conn = null;
        try {
            conn = getConnection();
            roleWrapper = new RoleImplWrapper(conn);
            
            int deletedCount = roleWrapper.delete(role.getCode());
            if (deletedCount == 0) {
                throw new RoleNotFoundException("role code="+role.getCode());
            }
            roleCodeMap.remove(role.getCode());
            // role이 사용가능한 서비스 목록을 저장하고 있는 맵
            Map services = (Map)roleToServiceMap.get(role.getCode());
            Iterator serviceIter = services.values().iterator();
            while(serviceIter.hasNext()) {
                ServiceImpl service = (ServiceImpl)serviceIter.next();
                Map roles = (Map)serviceToRoleMap.get(service.getCode());
                roles.remove(role.getCode());
            }
            roleToServiceMap.remove(role.getCode());
        } catch(Exception ex) {
            throw new AAManagerException(ex.getMessage(), ex);
        } finally {
            if (roleWrapper != null) roleWrapper.close();
            if (conn != null) try { releaseConnection(conn); } catch(SQLException ex) {}
        }
    }
    
    public List getRoleList() {
        return new java.util.ArrayList(roleCodeMap.values());
    }
    
    public void addService(Service service)
    throws DuplicateServiceException, AAManagerException {
        if (serviceCodeMap.containsKey(service.getCode())) {
            throw new DuplicateServiceException("Duplicate Service Code: "+service.getCode());
        }
        ServiceImplWrapper serviceWrapper = null;
        Connection conn = null;
        try {
            conn = getConnection();
            serviceWrapper.insert((ServiceImpl)service);
            serviceCodeMap.put(service.getCode(), service);
        } catch(Exception ex) {
            throw new AAManagerException(ex.getMessage(), ex);
        } finally {
            if (serviceWrapper != null) serviceWrapper.close();
            if (conn != null) try { releaseConnection(conn); } catch(SQLException ex) {}
        }
    }
    
    public Service getService(String serviceCode) throws ServiceNotFoundException {
        ServiceImpl service = (ServiceImpl)serviceCodeMap.get(serviceCode);
        if (service == null)
            throw new ServiceNotFoundException("service code="+serviceCode);
        return service;
    }
    
    public Service getServiceByUri(String uri) {
        ServiceImpl service = (ServiceImpl)serviceUriMap.get(uri);
        return service;
    }
    
    public void stop(Service service) throws ServiceNotFoundException, AAManagerException {
        if (!service.isStopped()) {
            ServiceImplWrapper serviceWrapper = null;
            Connection conn = null;
            try {
                conn = getConnection();
                serviceWrapper = new ServiceImplWrapper(conn);
                
                service.setStopped(true);
                int updatedCount = serviceWrapper.update((ServiceImpl)service);
                if (updatedCount == 0) {
                    throw new ServiceNotFoundException("service code="+service.getCode());
                }
            } catch(Exception ex) {
            	service.setStopped(false);
                throw new AAManagerException(ex.getMessage(), ex);
            } finally {
                if (serviceWrapper != null) serviceWrapper.close();
                if (conn != null) try { releaseConnection(conn); } catch(SQLException ex) {}
            }
        }
    }
    
    public void resume(Service service) throws ServiceNotFoundException, AAManagerException {
        if (service.isStopped()) {
            ServiceImplWrapper serviceWrapper = null;
            Connection conn = null;
            try {
                conn = getConnection();
                serviceWrapper = new ServiceImplWrapper(conn);
                
                service.setStopped(false);
                int updatedCount = serviceWrapper.update((ServiceImpl)service);
                if (updatedCount == 0) {
                    throw new ServiceNotFoundException("service code="+service.getCode());
                }
            } catch(Exception ex) {
            	service.setStopped(true);
                throw new AAManagerException(ex.getMessage(), ex);
            } finally {
                if (serviceWrapper != null) serviceWrapper.close();
                if (conn != null) try { releaseConnection(conn); } catch(SQLException ex) {}
            }
        }
    }
    
    public void remove(Service service)
    throws ServiceNotFoundException, AAManagerException {
        ServiceImplWrapper serviceWrapper = null;
        Connection conn = null;
        try {
            conn = getConnection();
            serviceWrapper = new ServiceImplWrapper(conn);
            
            int deletedCount = serviceWrapper.delete(service.getCode());
            if (deletedCount == 0) {
                throw new ServiceNotFoundException("service code="+service.getCode());
            }
            serviceCodeMap.remove(service.getCode());
            // 서비스와 관련된 역할 목록을 저장하고 있는 맵
            Map roles = (Map)serviceToRoleMap.get(service.getCode());
            Iterator roleIter = roles.values().iterator();
            while(roleIter.hasNext()) {
                RoleImpl role = (RoleImpl)roleIter.next();
                Map services = (Map)roleToServiceMap.get(role.getCode());
                services.remove(service.getCode());
            }
            serviceToRoleMap.remove(service.getCode());
        } catch(Exception ex) {
            throw new AAManagerException(ex.getMessage(), ex);
        } finally {
            if (serviceWrapper != null) serviceWrapper.close();
            if (conn != null) try { releaseConnection(conn); } catch(SQLException ex) {}
        }
    }
    
    public List getServiceList() {
        return new java.util.ArrayList(serviceCodeMap.values());
    }
    
    public void grant(Role role, Service service) throws AAManagerException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("insert into AUTHORITY (ROLECODE, SERVICECODE) values (?, ?)");
            pstmt.setString(1, role.getCode());
            pstmt.setString(2, service.getCode());
            pstmt.executeUpdate();
            
            // 역할이 사용 가능한 서비스 목록에 새롭게 추가
            Map services = (Map)roleToServiceMap.get(role.getCode());
            if (services == null) {
                services = new java.util.TreeMap();
                roleToServiceMap.put(role.getCode(), services);
            }
            services.put(service.getCode(), service);
            
            Map roles = (Map)serviceToRoleMap.get(service.getCode());
            if (roles == null) {
                roles = new java.util.TreeMap();
                serviceToRoleMap.put(service.getCode(), roles);
            }
            roles.put(role.getCode(), role);
        } catch(SQLException ex) {
            throw new AAManagerException(ex.getMessage(), ex);
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { releaseConnection(conn); } catch(SQLException ex) {}
        }
    }
    
    public void deprive(Role role, Service service)
    throws AAManagerException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("delete from AUTHORITY where ROLECODE = ? and SERVICECODE = ?");
            pstmt.setString(1, role.getCode());
            pstmt.setString(2, service.getCode());
            int deletedCount = pstmt.executeUpdate();
            
            // 역할이 사용 가능한 서비스 목록에 새롭게 추가
            Map services = (Map)roleToServiceMap.get(role.getCode());
            services.remove(service.getCode());
            
            Map roles = (Map)serviceToRoleMap.get(service.getCode());
            roles.remove(role.getCode());
        } catch(SQLException ex) {
            throw new AAManagerException(ex.getMessage(), ex);
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { releaseConnection(conn); } catch(SQLException ex) {}
        }
    }
    
    public boolean hasAuthority(Role role, Service service) {
        Map services = (Map)roleToServiceMap.get(role.getCode());
        if (services != null && services.containsKey(service.getCode())) {
            return true;
        }
        return false;
    }
    
    public List getGrantedServiceList(Role role) {
        Map services = (Map)roleToServiceMap.get(role.getCode());
        return new java.util.ArrayList(services.values());
    }
    
    public List getGrantedRoleList(Service service) {
        Map roles = (Map)serviceToRoleMap.get(service.getCode());
        return new java.util.ArrayList(roles.values());
    }
    
    private Connection getConnection() throws SQLException {
        return DBUtil.getConnection("kilos");
    }
    private void releaseConnection(Connection conn) throws SQLException {
        DBUtil.releaseConnection(conn);
    }
}
