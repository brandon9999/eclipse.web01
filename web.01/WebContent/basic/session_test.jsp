<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page session="false"%>
<%@ page language="java" import="java.io.*,java.util.*,java.net.*"%>

<%
String hosturl = request.getHeader("Host"); 
HttpSession sessions = request.getSession(false); 
String mb_id="";
String mb_name="";
String mb_level="";
String ci_email="";

try
{
	 out.println("session.jsp" + sessions);
}
catch(Exception e)
{
	out.println("session.jsp" + e.toString());
}


%> 