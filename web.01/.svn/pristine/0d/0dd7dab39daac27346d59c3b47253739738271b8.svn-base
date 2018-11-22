<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.rmi.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.transaction.*"%>
<%@ page import="kis.ejb.guestbook.*"%>
<html>
<head>
<title>방명록 리스트</title>
</head>
<body>
	<h3>방명록 리스트</h3>
	
<%
			Context ctx = null;
			int iPageSize = 10;
			int iRecordCnt = 0;
			int iTotalPage = 0;	
			int iCurPage = 1;
			
			if(request.getParameter("reqPage") != null)
			{
		iCurPage = Integer.parseInt(request.getParameter("reqPage"));
			}
			
			try
			{
		ctx = new InitialContext();
		Object h = ctx.lookup("GuestbookBean");
		GuestbookHome home = (GuestbookHome)PortableRemoteObject.narrow(h, GuestbookHome.class);
		Guestbook guestbook = home.create();
		String cnt = guestbook.cntGuestbookBean();
		ArrayList list = guestbook.getGuestbookBean();
		
		out.println("총 레코드  갯수  : " + cnt + "<hr>");
		
		// ===========================================================
		// 페이징 처리
		// ===========================================================
		// 총 레코드 갯수
		iRecordCnt = list.size();
		// 총 페이지 갯수
		iTotalPage = ((iRecordCnt-1)/iPageSize)+1;
		out.println("총페이지 갯수 : " + iTotalPage + "<hr>");
		
		int iStartNum = 0;
		int iLastNum = 0;	
		
		if(iCurPage == 1)
		{
			iStartNum = 0;	
			iLastNum = 9;	
		}
		else
		{
			iStartNum = (iCurPage-1)*iPageSize;	
			iLastNum = (iCurPage*iPageSize)-1;	
		}
		
		out.println("iStartNum : " + iStartNum + "<br>");
		out.println("iLastNum : " + iLastNum + "<hr>");
		
		for(int i=iStartNum; i<iLastNum+1; i++)
		{
			if(i == iRecordCnt-1 || i < iRecordCnt-1)
			{
		GuestbookDataBean gdb = (GuestbookDataBean)list.get(i);
		out.println("<a href=delete.jsp?reqSeq="+gdb.getSeq()+">DEL</a>&nbsp");
		out.println("<a href=modifyform.jsp?reqSeq="+gdb.getSeq()+">MODIFY</a>&nbsp");		
		out.println("순번 : " + gdb.getSeq() + "이름 : " + gdb.getName() + "내용 : " + gdb.getContent() + "<hr>");
			}
		}		
		
		for(int i = 1; i<iTotalPage+1; i++)
		{
			if (i == iCurPage)
			{
		out.println(i);
			}
			else
			{
		out.println("<a href=list.jsp?reqPage="+i+">" +  i + "</a>&nbsp;&nbsp;");
			}
		}
			}
			catch(Exception e)
			{
		out.println(e.toString());
			}
	%>
<a href=writeform.html>방명록 글쓰기</a>
</body>
</html>
