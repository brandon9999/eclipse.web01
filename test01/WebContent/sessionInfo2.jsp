<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page session = "true" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%
    Date time = new Date();
    SimpleDateFormat formatter = 
       new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<html>
<head><title>��������</title></head>
<body>
����ID: <%= session.getId() %> <br>
<%
    time.setTime(session.getCreationTime());
%>
���ǻ����ð�: <%= formatter.format(time) %> <br>
<%
    time.setTime(session.getLastAccessedTime());
%>
�ֱ����ٽð�: <%= formatter.format(time) %>


 <br>
���� getMaxInactiveInterval : <%= session.getMaxInactiveInterval() %> <br>

���� setMaxInactiveInterval : <% session.setMaxInactiveInterval(2400); %> <br>

���� getMaxInactiveInterval : <%= session.getMaxInactiveInterval() %> <br>


</body>
</html>