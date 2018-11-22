<%@ page import = "com.javacan.common.module.logging.*" %>
<%@ page import = "com.javacan.common.module.config.*" %>
<%@ page import = "com.javacan.common.module.config.impl.*" %>
<%@ page contentType = "text/html; charset=euc-kr" %>
<%
try {
    Logger logger = Logger.getLogger("kilos");
    if (logger == null) {
%>
logger null<br>
<%
    } else {
%>
logger is <%= logger.getName() %><br>
<%
        logger.fatal("치명적 에러", "/test.jsp");
    }
} catch(Exception ex) {
    ex.printStackTrace(new java.io.PrintWriter(out));
}
%>
Test
<p>
FileConfigFactory.class.getName() = <%= FileConfigFactory.class.getName() %>
<br>
<%
try {
    System.setProperty("autopost", FileConfigFactory.class.getName());
    //System.setProperty("autopost.ConfigFile", "C:\\jakarta-tomcat-4.1.24-LE-jdk14\\webapps\\kilos\\autopost.config");
    //System.setProperty("autopost.ConfigFile", "D:\\AppProject\\KisWorkspace\\work_kilos\\WebContent\\WEB-INF\\autopost.config");
    System.setProperty("autopost.ConfigFile", "D:/AppProject/KisWorkspace/work_kilos/WebContent/WEB-INF/autopost.config");
    
    
    
    
    out.println("autopost : " + System.getProperty("autopost") + "<br>");
    out.println("autopost.ConfigFile : " + System.getProperty("autopost.ConfigFile") + "<br>");    
    
    
    Config autopostConfig = Config.getConfig("autopost");
    Config kilosConfig = Config.getConfig("kilos");
%>
<br>
<br>
<br>
AutoPost:<br>
a = <%=autopostConfig.getValue("a") %>
<br>
b = <%=autopostConfig.getValue("b") %>

<p>
Kilos:<br>
a = <%=kilosConfig.getValue("a") %>
b = <%=kilosConfig.getValue("b") %>
<%
} catch(Throwable ex) {
    out.println("<pre>");
    ex.printStackTrace(new java.io.PrintWriter(out));
    out.println("</pre>");
}

%>
