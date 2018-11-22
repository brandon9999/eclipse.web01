package com.javacan.mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import com.javacan.mvc.configuration.ViewInfoMap;
import com.javacan.mvc.configuration.ViewInfoConfig;

/**
 * 모든 커맨드 클래스가 구현해야 하는 인터페이스
 * @author 최범균
 */
public interface Command {

    public ViewInfoConfig execute(
                              ViewInfoMap viewInfoMap,
                              HttpServletRequest request, 
                              HttpServletResponse response)
    throws ServletException;
}