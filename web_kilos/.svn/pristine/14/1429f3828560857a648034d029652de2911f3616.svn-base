<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import = "com.javacan.kilos.member.MemberBean" %>
<%
    MemberBean mb = (MemberBean)session.getAttribute(MemberBean.class.getName());
%>
<table width="100%" bgcolor="##6E64CC" border="0" cellspacing="0" cellpadding="3">
<tr>
    <td style="color: #FFFFFF">
    &nbsp;&nbsp;&nbsp;
    <a href="<%= request.getContextPath() %>/main.do" class="menulink">홈</a> |
    <a href="<%= request.getContextPath() %>/board/list.do" class="menulink">게시판</a> |
    <a href="<%= request.getContextPath() %>/notice/list.do" class="menulink">공지사항</a> |
<%  if (mb == null) { %>
    <a href="<%= request.getContextPath() %>/registerForm.do" class="menulink">회원가입</a> | 
    <a href="<%= request.getContextPath() %>/loginForm.do" class="menulink">로그인</a>
<%  } else {    %>
    <a href="<%= request.getContextPath() %>/logout.do" class="menulink"><%= mb.getId() %> 로그아웃</a>
<%  }   %>
    </td>
</tr>
</table>


