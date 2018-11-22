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

public class LogoutCommand implements Command {
    public ViewInfoConfig execute(ViewInfoMap viewInfoMap,
                          HttpServletRequest request, 
                          HttpServletResponse response)
    throws ServletException {
      	try {
	      	AAManager aaManager = AAManagerFactory.createAAManager();
	      	aaManager.closeSession(request, response);
  			return viewInfoMap.getViewInfoConfig("main");
		} catch(AAManagerException ex) {
			throw new ServletException(ex);
		}
    }
}
