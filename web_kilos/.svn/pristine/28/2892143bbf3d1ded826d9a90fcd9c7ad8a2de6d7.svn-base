package com.javacan.common.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Enumeration;

/**
 * 시스템 프로퍼티를 설정해주는 서블릿.
 * web.xml 파일의 초기화 파라미터에 지정된 파라미터의 이름을 프로퍼티의 이름으로
 * 사용하고 파라미터의 값을 프로퍼티의 값으로 지정한다.
 * @author 최범균
 */
public final class SystemPropertySetterServlet extends GenericServlet 
{
    
    public void init() throws ServletException 
    {
        System.out.println("********************************************************");            
        System.out.println("[TmaxDebug]");
        System.out.println("[SystemPropertySetterServlet/init]");
        System.out.println("********************************************************");
   	
        Enumeration paramNames = getInitParameterNames();
        while (paramNames.hasMoreElements()) 
        {
            String paramName = (String)paramNames.nextElement();
            String value = getInitParameter(paramName);
            
            System.out.println("********************************************************");            
            System.out.println("[TmaxDebug]");
            System.out.println("[VAR/paramName] : " + paramName);            
            System.out.println("********************************************************");
            
            if (value != null) System.setProperty(paramName, value);
        }
    }
    
    public void service(ServletRequest req, ServletResponse res)
    throws ServletException, java.io.IOException 
    {
        System.out.println("********************************************************");            
        System.out.println("[TmaxDebug]");
        System.out.println("[SystemPropertySetterServlet/service]");
        System.out.println("********************************************************");
    	
    }
}