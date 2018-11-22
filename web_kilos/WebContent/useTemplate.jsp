<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ taglib uri="/WEB-INF/tlds/javacan-mvc-template.tld" prefix="javacan" %>
<javacan:insert template="/template.jsp">
    <javacan:put name="header" uri="/header.jsp" />
    <javacan:put name="content" uri="/content.jsp" />
    <javacan:put name="footer" uri="/footer.jsp" />
</javacan:insert>

