<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="javax.rmi.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="kis.ejb.guestbook.*"%>
<html>
<head>
<title>방명록 글쓰기</title>
</head>
<body>
	<h3>저장되었습니다.</h3><br>
<%
	GuestbookDataBean gdb = new GuestbookDataBean();
	gdb.setName(request.getParameter("name"));
	gdb.setContent(request.getParameter("content"));

	Context ctx = null;

	try
	{
		ctx = new InitialContext();
		Object h = ctx.lookup("GuestbookBean");
		GuestbookHome home = (GuestbookHome)PortableRemoteObject.narrow(h, GuestbookHome.class);
		Guestbook guestbook = home.create();
		guestbook.addGuestbookBean(gdb);
	}
	catch(Exception e)
	{
		out.println(e.toString());
	}
%>
<a href=list.jsp>방명록 리스트 보기</a>
</body>
</html>
