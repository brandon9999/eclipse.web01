<%@ page contentType="text/html;charset=EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.rmi.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.transaction.*"%>
<%@ page import="kr.co.hanbitbook.ejb.examples.shop.*"%>
<%
	String sname = request.getParameter("name");
	if(sname == null || sname.trim().equals("")){
%>
<script language="JavaScript">
	window.alert("카테고리 이름이 전달되지 않았습니다.");
	history.go(-1);
</script>
<%
		return;
	}
%>
<html>
<head>
<title>아이템 목록 보기</title>
</head>
<body>
<center>
<h3><%=sname%> 아이템 목록 보기</h3>
<table width="600" border="1">
<tr>
<td>아이템 이름</td><td>아이템 설명</td><td>아이템 가격</td><td>장바구니담기</td>
<%
	Context ctx = null;
	CategoryManager categoryManager = null;
	try{
		ctx = new InitialContext();
		Object h = ctx.lookup("CategoryManager");
		CategoryManagerHome home = (CategoryManagerHome)PortableRemoteObject.narrow(h, CategoryManagerHome.class);
		categoryManager = home.create();
		Collection list = categoryManager.getItems(sname);
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			ItemDataBean bd = (ItemDataBean)iter.next();
%>
<tr>
<td width="300"><%=bd.getName()%></td>
<td><%=bd.getText()%></td>
<td><%=bd.getPrice()%></td>
<td><a href=additem.jsp?id=<%=bd.getId()%>>선택</a></td>
</tr>
<%
		}
		
	}catch(Exception e){
		out.println(e.toString());
	}
%>
</table>

<a href="javascript:history.go(-1)">상품 카테고리 보기</a>

</body>
</html>
