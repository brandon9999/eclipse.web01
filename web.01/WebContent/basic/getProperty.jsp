<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<table sytel=font-size:9pt>
<%
java.util.Enumeration e= System.getProperties().propertyNames();
while(e.hasMoreElements())
{
	String obj = (String)e.nextElement();
%>
<tr>
<td bgcoloer=eeeeee><%=obj%></td>
<td><%=System.getProperty(obj)%></td>
</tr>
<%
}
%>
</table>
<br>
<br>
<%=System.getProperties().getProperty("conf_path")%><br>

</body>
</html>