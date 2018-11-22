package com.javacan.kilos.command;

import com.javacan.mvc.command.Command;
import com.javacan.mvc.configuration.ViewInfoMap;
import com.javacan.mvc.configuration.ViewInfoConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterFormCommand implements Command {
    public ViewInfoConfig execute(ViewInfoMap viewInfoMap,
                          HttpServletRequest request, 
                          HttpServletResponse response) {
        return viewInfoMap.getViewInfoConfig("registerForm");
    }
} 

