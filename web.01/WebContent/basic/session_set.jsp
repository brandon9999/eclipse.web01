<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page session="true"%>    
<%
    session.setAttribute("name", "TEST1234");
    
    String name = (String)session.getAttribute("name");
    
    System.out.println("name :" + name);
    out.println("name :" + name);
%>