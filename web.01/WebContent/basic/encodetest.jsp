<%@page contentType="text/html;charset=euc-kr" %>
<%
request.setCharacterEncoding("utf-8");
System.out.println(request.getParameter("TEST"));

request.setCharacterEncoding("euc-kr");
System.out.println(request.getParameter("TEST"));

%>