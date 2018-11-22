<%@ page contentType="text/html;charset=EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.rmi.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.transaction.*"%>
<%@ page import="kr.co.hanbitbook.ejb.examples.shop.*"%>
<%
	String sid = request.getParameter("id");
	if(sid == null || sid.trim().equals("")){
%>
<script language="JavaScript">
	window.alert("아이템 아이디가 전달되지 않았습니다.");
	history.go(-1);
</script>
<%
		return;
	}
%>
<html>
<head>
<title>아이템 물품담기</title>
</head>
<body>
<center>
<h3>선택한 아이템을 장바구니에 담았습니다.</h3>

<jsp:useBean id="scart" class="kr.co.hanbitbook.ejb.examples.shop.ShoppingCart" scope="session" />

<%
	Context ctx = null;
	CategoryManager categoryManager = null;
	try{
		ctx = new InitialContext();
		Object h = ctx.lookup("CategoryManager");
		CategoryManagerHome home = (CategoryManagerHome)PortableRemoteObject.narrow(h, CategoryManagerHome.class);
		categoryManager = home.create();
		ItemDataBean ibean = categoryManager.getItem(sid);
		ibean.setCount(1);
		scart.addItem(ibean);
	}catch(Exception e){
		out.println(e.toString());
	}
%>

<a href="categorylist.jsp">상품 카테고리 보기</a>&nbsp;&nbsp;&nbsp;
<a href="cartlist.jsp">선택한 물품 보기</a>

</body>
</html>
