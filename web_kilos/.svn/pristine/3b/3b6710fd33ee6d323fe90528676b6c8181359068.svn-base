package com.javacan.mvc.ana;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AAManager {
	/** 잘못된 아이디를 사용하여 인증에 실패한 경우 */
    public int FAIL_INVALID_ID = 0x01;
    /** 잘못된 암호를 사용하여 인증에 실패한 경우 */
    public int FAIL_INVALID_PASSWORD = 0x02;
    /** 인증에 성공한 경우 */
    public int SUCCESS = 0x04;
    /** 
     * 올바른 사용자인지 인증하고, 올바른 사용자인 경우 세션에 사용자의 역할(Role) 정보를 저장한다.
     * 세션은 실제 시스템에 따라 알맞게 구현된다.
     * @param ui 사용자 정보
     * @param request HTTP 요청
     * @param response HTTP 응답
     * @return 인증처리 결과. FAIL_INVALID_ID, FAIL_INVALID_PASSWORD, SUCCESS 중 하나를 리턴한다.
     */
    public int authenticate(UserInfo ui, HttpServletRequest request, HttpServletResponse response) throws AAManagerException;
    /**
     * 인증된 사용자의 세션을 종료한다.
     * @param request HTTP 요청
     * @param response HTTP 응답
     */
    public void closeSession(HttpServletRequest request, HttpServletResponse response) throws AAManagerException;
    /**
     * 사용자의 역할(Role)을 구한다.
     * @param request HTTP 요청
     */
    public Role getUserRoleFromSession(HttpServletRequest request);
    /** 
     * 사용자의 역할 정보를 세션에 저장한다.
     */
    public void putUserRoleToSession(Role role, HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 새로운 역할을 추가한다.
     */
    public void addRole(Role role) throws AAManagerException, DuplicateRoleException;
    /**
     * 지정한 Role 정보를 구한다.
     */
    public Role getRole(String roleCode) throws RoleNotFoundException, AAManagerException;
    /**
     * 지정한 역할의 사용을 제한한다.
     */
    public void restrict(Role role) throws RoleNotFoundException, AAManagerException;
    /**
     * 사용이 제한된 역할의 제한을 푼다.
     */
    public void lift(Role role) throws RoleNotFoundException, AAManagerException;
    /**
     * 지정한 역할을 삭제한다.
     */
    public void remove(Role role) throws RoleNotFoundException, AAManagerException;
    /**
     * 모든 역할 목록을 구한다.
     */
    public List getRoleList() throws AAManagerException;
    
    /**
     * 새로운 서비스를 추가한다.
     */
    public void addService(Service service) throws AAManagerException, DuplicateServiceException;
    /**
     * 지정한 서비스를 구한다.
     */
    public Service getService(String serviceCode) throws ServiceNotFoundException, AAManagerException;
    /**
     * 지정한 URI에 해당하는 서비스를 구한다.
     */
    public Service getServiceByUri(String uri) throws AAManagerException;
    /**
     * 서비스의 사용을 중지시킨다.
     */
    public void stop(Service service) throws ServiceNotFoundException, AAManagerException;
    /**
     * 중지된 서비스를 다시 사용할 수 있도록 한다.
     */
    public void resume(Service service) throws ServiceNotFoundException, AAManagerException;
    /**
     * 서비스를 제거한다.
     */
    public void remove(Service service) throws ServiceNotFoundException, AAManagerException;
    /**
     * 모든 서비스의 목록을 구한다.
     */
    public List getServiceList() throws AAManagerException;
    
    /**
     * 지정한 역할을 지정한 서비스를 사용할 수 있도록 한다.
     */
    public void grant(Role role, Service service) throws AAManagerException;
    /**
     * 지정한 역할이 지정한 서비스를 사용할 수 없도록 한다.
     */
    public void deprive(Role role, Service service) throws AAManagerException;
    /**
     * 지정한 역할이 지정한 서비스를 사용할 수 있는 지 검사한다.
     * @return 사용할 수 있을 경우 true를 리턴한다.
     */
    public boolean hasAuthority(Role role, Service service) throws AAManagerException;
    /**
     * 지정한 역할이 사용할 수 있는 서비스 목록을 구한다.
     */
    public List getGrantedServiceList(Role role) throws AAManagerException;
    /**
     * 지정한 서비스를 사용할 수 있는 역할 목록을 구한다.
     */
    public List getGrantedRoleList(Service service) throws AAManagerException;
    
}
