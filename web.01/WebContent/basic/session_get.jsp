<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page session="true"%>    
<%
        
//HttpSession sessions = request.getSession(false); 
//out.println("session.jsp" + sessions);
%>

<br>

<%

    String name = (String)session.getAttribute("name");
    
    System.out.println("get name :" + name);
    out.println("get name :" + name);
    

%>