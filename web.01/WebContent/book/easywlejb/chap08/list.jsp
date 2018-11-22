<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.rmi.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="kis.ejb.guestbook.*"%>
<html>
<head>
<title>방명록 리스트</title>
</head>
<body>
	<h3>방명록 리스트</h3><br>
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
		순번 : <%=gdb.getSeq()%><br>
		이름 : <%=gdb.getName()%><br>
		내용 : <pre> <%=gdb.getContent()%> </pre><br>
		<hr><br>
<%
		}
	}catch(Exception e){
		out.println(e.toString());
	}
%>
<a href=writeform.html>방명록 글쓰기</a>
</body>
</html>
