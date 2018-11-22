<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import="java.util.*"%>
<%@ page import="jeus.servlet.engine.*"%>
<%@ page import="jeus.servlet.loader.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="java.lang.reflect.*"%>

<%

    String className = request.getParameter("classname");

    if(className == null || className.length() == 0) {
%>
        <html>
        <body onLoad='javascript:document.theform.classname.focus()'>
        <h3>JAVA Class/Method/Field Information</h3>
        <form name='theform' method='POST' action='ClassInfo.jsp'>
        Class name specified has extension? <input type='radio' name='has-extension' value='true'>true <input type='radio' name='has-extension' value='false' checked='true'>false<br><br>
        Enter name of class included package : <input type='text' name='classname' size=50><br>
        </form>
        </body>
        </html>
<%
        return;
    }


    String save_classname = className;
    String strhasext = request.getParameter("has-extension");
    boolean hasext = false;

    if("true".equalsIgnoreCase(strhasext))
        hasext = true;

    if(!className.startsWith("/"))
        className = "/" + className;
    className = className.replace('.', '/');

    if(!hasext) {
        className += ".class";
    } else {
        int pos = className.lastIndexOf('/');
        if(pos != -1)
            className = className.substring(0, pos) + '.' + className.substring(pos+1);
    }

    java.net.URL classUrl = getClass().getResource(className);
%>


    <h3>JAVA Class/Method/Field Information Result</h3>

<%
    if(classUrl == null) {
        out.println("Class '<font color=red>" + className + "</font>' <strong>not found</strong>  in CLASSPATH.");
    } else {
%>
        <menu>
<%
        out.println("<li>Class '<font color=red>" + className + "</font>' found in \n'<font color=blue>" + classUrl.getFile() + "</font>'");

        if(className.endsWith(".class")) {

            className = save_classname.replace('/', '.');
            if(className.indexOf(".class") != -1)
                className = className.substring(0, className.indexOf(".class"));

            Class cls = Class.forName(className);
            ClassLoader cl = cls.getClassLoader();

            if(cl != null) {

                Class[] ifs = cls.getInterfaces();
                Constructor[] dcons = cls.getDeclaredConstructors();
                Field[] dfls = cls.getDeclaredFields();
                Method[] dmtds = cls.getDeclaredMethods();
                Class[] dcls = cls.getDeclaredClasses();
%>

				<br><br><br>

				<li>Summary</li><br><br>
					
                <table border=1 cellspacing=0 width="60%">
                <thead>
                    <tr bgcolor="#CCCCFF">
                        <th width=150>Category</th>
                        <th>Value</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td align=center>Name</td>
                        <td align=center><%=className%></td>
                    </tr>
                    <tr>
                        <td align=center>ClassLoader</td>
                        <td align=center><%=cl%></td>
                    </tr>
                    <tr>
                        <td align=center>SuperClass</td>
                        <td align=center><%=(cls.getSuperclass()==null)?"&nbsp;":cls.getSuperclass().getName()%></td>
                    </tr>
                    <tr>
                        <td align=center>Interface</td>
                        <td align=center><%=cls.isInterface()?"Yes":"No"%></td>
                    </tr>
                    <tr>
                        <td align=center>Primitive</td>
                        <td align=center><%=cls.isPrimitive()?"Yes":"No"%></td>
                    </tr>
                </tbody>
                </table>


				<br><br>


				<li>Detail</li><br><br>

                <table border=1 cellspacing=0 width="80%">
                <thead>
                    <tr bgcolor="#CCCCFF">
                        <th width=150>Category</th>
                        <th width=250>Type</th>
                        <th>Detail</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td align=center rowspan=<%=ifs.length%>>Implemented</td>
                        <td align=center rowspan=<%=ifs.length%>>N/A</td>
                        <%
                            for(int i=0; i<ifs.length; i++) {
                                if(i > 0)
                                    out.println("</tr><tr>");
                                out.println("<td align=center>" + ifs[i].getName() + "</td>");
                            }
                            if(ifs.length == 0)
                                out.println("<td align=center>&nbsp;</td>");
                        %>
                    </tr>
                    <tr>
                        <td align=center rowspan=<%=dcons.length%>>Constructor</td>
                        <td align=center rowspan=<%=dcons.length%>>N/A</td>
                        <%
                            for(int i=0; i<dcons.length; i++) {
                                String conStr = dcons[i].getName();
                                if(i > 0)
                                    out.println("</tr><tr>");
                                out.println("<td align=center>" + conStr.substring(conStr.lastIndexOf(".")+1) + "(");
                                Class[] params = dcons[i].getParameterTypes();
                                for(int j=0; j<params.length; j++) {
                                    if(j!=0) out.println(", ");
                                    out.println(params[j].getName());
                                }
                                out.println(")</td>");
                            }
                            if(dcons.length == 0)
                                out.println("<td align=center>&nbsp;</td>");
                        %>
                    </tr>
                    <tr>
                        <td align=center rowspan=<%=dfls.length%>>Field</td>
                        <%
                            for(int i=0; i<dfls.length; i++) {
                                Class type = dfls[i].getType();
                                if(i > 0)
                                    out.println("</tr><tr>");
                                out.println("<td align=center>" + type.getName() + "</td>");
                                out.println("<td align=center>" + dfls[i].getName() + "</td>");
                            }
                            if(dfls.length == 0) {
                                out.println("<td align=center>&nbsp;</td>");
                                out.println("<td align=center>&nbsp;</td>");
                            }
                        %>
                    </tr>
                    <tr>
                        <td align=center rowspan=<%=dmtds.length%>>Method</td>
                        <%
                            for(int i=0; i<dmtds.length; i++) {
                                Class type = dmtds[i].getReturnType();
                                if(i > 0)
                                    out.println("</tr><tr>");
                                out.println("<td align=center>" + type.getName() + "</td>");
                                out.println("<td align=center>" + dmtds[i].getName() + "(");
                                Class[] params = dmtds[i].getParameterTypes();
                                for(int j=0; j<params.length; j++) {
                                    if(j!=0) out.println(", ");
                                    out.println(params[j].getName());
                                }
                                out.println(")</tr>");
                            }
                            if(dmtds.length == 0) {
                                out.println("<td align=center>&nbsp;</td>");
                                out.println("<td align=center>&nbsp;</td>");
                            }
                        %>
                    </tr>
                    <tr>
                        <td align=center rowspan=<%=dcls.length%>>InnerClass</td>
                        <td align=center rowspan=<%=dcls.length%>>N/A</td>
                        <%
                            for(int i=0; i<dcls.length; i++) {
                                if(i > 0)
                                    out.println("</tr><tr>");
                                out.println("<td align=center>" + dcls[i].getName() + "</td>");
                            }
                            if(dcls.length == 0)
                                out.println("<td align=center>&nbsp;</td>");
                        %>
                    </tr>
                </tbody>
                </table>
<%
            }
        }
%>
        </menu>
<%
    }
%>
