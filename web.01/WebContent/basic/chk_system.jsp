<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

    
<%@ page import="java.io.*, java.util.*" %>
<%
        Properties sysprop = new Properties(System.getProperties());
        String path = application.getRealPath("/") + File.separator + "sys.txt";
        OutputStream os = new FileOutputStream(path);
        PrintStream ps = new PrintStream(os);
        sysprop.list(ps);
        os.close();
%>
<HTML>
<HEAD>
        <TITLE>
                System Locale
        </TITLE>
</HEAD>
<BODY>
<TABLE>
        <TR>
                <TD>current WAS char encoding : </TD><TD><%=response.getCharacterEncoding()%></TD>
        </TR>
        <TR>
                <TD>Locale : </TD><TD><%=response.getLocale().toString()%></TD>
        </TR>
        <TR>
                <TD>Locale Language : </TD><TD><%=response.getLocale().getLanguage()%></TD>
        </TR>
        <TR>
                <TD>Locale Country : </TD><TD><%=response.getLocale().getCountry()%></TD>
        </TR>
        <TR>
                <TD>Locale Language : </TD><TD><%=System.getProperty("system.language")%></TD>
        </TR>
        <TR>
                <TD>Locale Country : </TD><TD></TD>
        </TR>
        <TR>
                <TD>System user language : </TD><TD><%=System.getProperty("user.language")%></TD>
        </TR>
        <TR>
                <TD>System user region : </TD><TD><%=System.getProperty("user.region")%></TD>
        </TR>
        <TR>
                <TD>System os name : </TD><TD><%=System.getProperty("os.name")%></TD>
        </TR>
        <TR>
                <TD>System encoding : </TD><TD><%=System.getProperty("encoding")%></TD>
        </TR>
        <TR>
                <TD>System file encoding : </TD><TD><%=System.getProperty("file.encoding")%></TD>
        </TR>
        <TR>
                <TD>System default.client.encoding : </TD><TD><%=System.getProperty("default.client.encoding")%></TD>
        </TR>
        <TR>
                <TD>Servlet Name</TD><TD><%=getServletConfig().getServletName()%></TD>
        </TR>
        <TR>
                <TD>Servlet Info</TD><TD><%=getServletInfo()%></TD>
        </TR>
        <TR>
                <TD>Server Info</TD><TD><%=getServletConfig().getServletContext().getServerInfo()%></TD>
        </TR>
        <TR>
                <TD>System properties</TD><TD><a href="sys.txt"><%=path%></a></TD>
        </TR>
</TABLE>
</BODY>
</HTML>    