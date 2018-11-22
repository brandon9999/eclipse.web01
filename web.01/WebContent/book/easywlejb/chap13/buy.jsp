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
<title>물건 구매하기</title>
</head>
<body>
<center>
<h3>장바구니 물건 구매하기</h3>

<jsp:useBean id="userd" class="kr.co.hanbitbook.ejb.examples.shop.UserDataBean" scope="session" />
<%
if(userd.getId() == null || userd.getId().equals("")){
%>
<meta http-equiv="refresh" content="0;url=loginform.html" /> 
<%
	return;
}
%>

<jsp:useBean id="scart" class="kr.co.hanbitbook.ejb.examples.shop.ShoppingCart" scope="session" />

<%
	Context ctx = null;
	UserManager userManager = null;
	java.util.Collection list = scart.getItems();
	if(list == null){
%>
<h3>선택한 물품이 없습니다.</h3>
<%
	}else{
		try{
			ctx = new InitialContext();
			Object h = ctx.lookup("UserManager");
			UserManagerHome home = (UserManagerHome)PortableRemoteObject.narrow(h, UserManagerHome.class);
			userManager = home.create();
			userManager.addBuyItems(userd.getId(), list);
			session.removeAttribute("scart");
		}catch(Exception e){}
	} // else

%>
</table>

<a href="categorylist.jsp">상품 카테고리 보기</a>
<a href="cartlist.jsp">장바구니 보기</a>

</body>
</html>
