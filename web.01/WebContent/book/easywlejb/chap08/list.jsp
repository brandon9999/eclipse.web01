<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.rmi.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="kis.ejb.guestbook.*"%>
<html>
<head>
<title>���� ����Ʈ</title>
</head>
<body>
	<h3>���� ����Ʈ</h3><br>
<%
	Context ctx = null;

	try{
		ctx = new InitialContext();
		Object h = ctx.lookup("GuestbookBean");
		GuestbookHome home = (GuestbookHome)PortableRemoteObject.narrow(h, GuestbookHome.class);
		Guestbook guestbook = home.create();
		ArrayList list = guestbook.getGuestbookBean();
		for(int i = 0; i < list.size(); i++){
			GuestbookDataBean gdb = (GuestbookDataBean)list.get(i);
%>
		���� : <%=gdb.getSeq()%><br>
		�̸� : <%=gdb.getName()%><br>
		���� : <pre> <%=gdb.getContent()%> </pre><br>
		<hr><br>
<%
		}
	}catch(Exception e){
		out.println(e.toString());
	}
%>
<a href=writeform.html>���� �۾���</a>
</body>
</html>
