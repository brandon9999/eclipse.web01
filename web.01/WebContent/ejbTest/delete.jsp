<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.rmi.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.transaction.*"%>
<%@ page import="kis.ejb.guestbook.*"%>
<html>
<head>
<title>방명록 삭제</title>
</head>
<body>
<%
	int reqSeq = Integer.parseInt(request.getParameter("reqSeq"));
	GuestbookDataBean gdb = new GuestbookDataBean();

	Context ctx = null;

	try
	{
		ctx = new InitialContext();
		Object h = ctx.lookup("GuestbookBean");
		GuestbookHome home = (GuestbookHome)PortableRemoteObject.narrow(h, GuestbookHome.class);
		Guestbook guestbook = home.create();
		guestbook.deleteGuestbookBean(reqSeq);
	}
	catch(Exception e)
	{
		out.println(e.toString());
	}
%>
	<h3>삭제 하였습니다.</h3><br>

<a href=list.jsp>방명록 리스트 보기</a>
</body>
</html>
