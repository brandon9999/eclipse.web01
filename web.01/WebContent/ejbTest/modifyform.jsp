<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.rmi.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.transaction.*"%>
<%@ page import="kis.ejb.guestbook.*"%>
<%@ page import="java.util.*"%>

<html>
<head><title>방명록 글 수정 </title></head>
<body>

<%
	int reqSeq = Integer.parseInt(request.getParameter("reqSeq"));
	Context ctx = null;
	int iSeq = 0;
	String sName = "";
	String sContent = "";
	
	try
	{
		ctx = new InitialContext();
		Object h = ctx.lookup("GuestbookBean");
		GuestbookHome home = (GuestbookHome)PortableRemoteObject.narrow(h, GuestbookHome.class);
		Guestbook guestbook = home.create();
		ArrayList list = guestbook.getGuestbookBean();
		
		for(int i=0; i<list.size(); i++)
		{
	GuestbookDataBean gdb = (GuestbookDataBean)list.get(i);

	if(gdb.getSeq() == reqSeq)
	{
		iSeq = gdb.getSeq();
		sName = gdb.getName();
		sContent = gdb.getContent();
		
		// exit for ??????
	}
		}	

	}
	catch(Exception e)
	{
		out.println(e.toString());
	}
%>

<form method=post action=modify.jsp>
이름 : <input type=text name=name value=<%=sName%>><br>
내용 : <textarea name=content cols=50 rows=4><%=sContent%></textarea><br>
<input type=submit value=확인>&nbsp;&nbsp;&nbsp;<a href=list.jsp>리스트로 돌아가기</a>
</form>
</body>
</html>