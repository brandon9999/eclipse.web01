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
<title>장바구니 목록 보기</title>
</head>
<body>
<center>
<h3>장바구니 물건 보기</h3>

<jsp:useBean id="scart" class="kr.co.hanbitbook.ejb.examples.shop.ShoppingCart" scope="session" />

<table width="600" border="1">
<tr>
<td>아이템 이름</td><td>아이템 설명</td><td>아이템 가격</td><td>신청수량</td>
</tr>

<%
	java.util.Collection list = scart.getItems();
	if(list == null){
%>
<td colspan=3>선택한 물품이 없습니다.</td>
<%
	}else{
		java.util.Iterator iter = list.iterator();
		while(iter.hasNext()){
			ItemDataBean ibean = (ItemDataBean)iter.next();
%>
<tr><td><%=ibean.getName()%></td>
<td><%=ibean.getText()%></td>
<td><%=ibean.getPrice()%></td>
<td><%=ibean.getCount()%></td>
<%
		}
	}
%>
</table>

<a href="categorylist.jsp">상품 카테고리 보기</a>
<a href="buy.jsp">선택한 물건 구매하기</a>

</body>
</html>
