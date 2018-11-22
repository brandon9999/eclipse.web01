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
<title>카테고리 보기</title>
</head>
<body>
<center>
<h3>카테고리 리스트</h3>
<table width="600" border="1">
<tr>
<td>카테고리 이름</td><td>카테고리 설명</td>
<%
	Context ctx = null;
	CategoryManager categoryManager = null;
	try{
		ctx = new InitialContext();
		Object h = ctx.lookup("CategoryManager");
		CategoryManagerHome home = (CategoryManagerHome)PortableRemoteObject.narrow(h, CategoryManagerHome.class);
		categoryManager = home.create();
		Collection list = categoryManager.getCategory();
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			CategoryDataBean bd = (CategoryDataBean)iter.next();
%>
<tr>
<td width="300"><a href="read.jsp?name=<%=java.net.URLEncoder.encode(bd.getName())%>"><%=bd.getName()%></a></td>
<td><%=bd.getText()%></td>
</tr>
<%
		}
		
	}catch(Exception e){
		out.println(e.toString());
	}
%>
</table>
<a href=cartlist.jsp>장바구니 보기</a>&nbsp;&nbsp;
<%
	Object obj = session.getAttribute("userd");
	if(obj != null){
%>
<a href=logout.jsp>로그아웃</a>
<%
	}else{
%>
<a href=loginform.html>로그인</a>
<%
	}
%>
</body>
</html>
