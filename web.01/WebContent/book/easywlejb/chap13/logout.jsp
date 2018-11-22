<%@ page contentType="text/html;charset=EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.rmi.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.transaction.*"%>
<%@ page import="kr.co.hanbitbook.ejb.examples.shop.*"%>
<html>
<head>
<title>로그아웃하기</title>
</head>
<body>
<center>
<h3>로그아웃하기</h3>
<%
	Object obj = session.getAttribute("userd");
	if(obj != null){
		session.removeAttribute("userd");
	}
%>
<a href="categorylist.jsp">상품 카테고리 보기</a>
</body>
</html>
