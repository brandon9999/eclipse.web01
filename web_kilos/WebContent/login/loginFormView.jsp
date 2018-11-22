<%@ page contentType = "text/html; charset=euc-kr" %>
<form action="<%= request.getContextPath() %>/login.do" method="post">
<br><br><br>
<table width="100%" bgcolor="#e9e9e9" border="0" cellpadding="10" cellspacing="0">
<tr>
    <td align="center">
    아이디<input type="text" name="id" size="10">
    암호<input type="password" name="password" size="10">
    <input type="submit" value="로그인">
    </td>
</tr>
</table>
</form>
<br><br><br>
