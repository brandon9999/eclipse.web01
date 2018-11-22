package com.javacan.kilos.command;

import com.javacan.mvc.command.Command;
import com.javacan.mvc.configuration.ViewInfoMap;
import com.javacan.mvc.configuration.ViewInfoConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javacan.kilos.member.MemberBean;
import com.javacan.kilos.member.MemberManager;
import com.javacan.kilos.member.MemberManagerException;
import com.javacan.kilos.member.DuplicateMemberIdException;

public class RegisterCommand implements Command {
    public ViewInfoConfig execute(ViewInfoMap viewInfoMap,
                          HttpServletRequest request, 
                          HttpServletResponse response) {
        try {
            MemberBean memberBean = new MemberBean();
            memberBean.setId(request.getParameter("id"));
            memberBean.setPassword(request.getParameter("password"));
            memberBean.setName(request.getParameter("name"));
            memberBean.setEmail(request.getParameter("email"));
            memberBean.setModel(request.getParameter("model"));
            memberBean.setAddress(request.getParameter("address"));
            memberBean.setPhone(request.getParameter("phone"));
            memberBean.setRoleCode("UR20030620090000");
            
            /*-----------------------------------------------------------*/
            System.out.println("[Debug]---Start---");
            System.out.println("[Debug] request parameter : " + request.getParameter("id"));
            System.out.println("[Debug]---End---");
            /*-----------------------------------------------------------*/
            
            MemberManager memberMgr = MemberManager.getInstance();
            memberMgr.register(memberBean);
            
            return viewInfoMap.getViewInfoConfig("success");
        } catch(DuplicateMemberIdException ex) {
            return viewInfoMap.getViewInfoConfig("registerForm");
        } catch(MemberManagerException ex) {
            return viewInfoMap.getViewInfoConfig("error");
        }
    }
} 
