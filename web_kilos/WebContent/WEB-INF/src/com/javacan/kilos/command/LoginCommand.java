package com.javacan.kilos.command;

import com.javacan.mvc.command.Command;
import com.javacan.mvc.configuration.ViewInfoMap;
import com.javacan.mvc.configuration.ViewInfoConfig;
import com.javacan.mvc.ana.AAManager;
import com.javacan.mvc.ana.AAManagerException;
import com.javacan.mvc.ana.AAManagerFactory;
import com.javacan.kilos.ana.UserInfoImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class LoginCommand implements Command 
{
    public ViewInfoConfig execute(ViewInfoMap viewInfoMap,
                          HttpServletRequest request, 
                          HttpServletResponse response)
    throws ServletException 
    {
      	String id = request.getParameter("id");
      	String password = request.getParameter("password");
      	
      	UserInfoImpl userInfo = new UserInfoImpl();
      	userInfo.setId(id);
      	userInfo.setPassword(password);
      	try 
      	{
	      	AAManager aaManager = AAManagerFactory.createAAManager();
	      	int result = aaManager.authenticate(userInfo, request, response);
	      	if (result == AAManager.FAIL_INVALID_ID ||
	      	    result == AAManager.FAIL_INVALID_PASSWORD) 
	      	{
	  	    	return viewInfoMap.getViewInfoConfig("loginForm");
	  		} 
	      	else 
	      	{
	  			return viewInfoMap.getViewInfoConfig("success");
			}
		} 
      	catch(AAManagerException ex) 
      	{
			throw new ServletException(ex);
		}
    }
}
