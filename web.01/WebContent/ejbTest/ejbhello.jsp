<%@ page contentType="text/html;charset=EUC-KR"  %>

<%@ page import = "java.rmi.*,
                   javax.naming.*,
                   javax.rmi.*,
                   java.net.*,                   
                   javax.ejb.*,
                   java.util.*,
                   hello.*"
%>

<%
//      ResourceBundle res = ResourceBundle.getBundle("WebRes", Locale.getDefault());
%>
<head>
<title>Hello World!</title>
</head>

<body bgcolor="#DCDCDC" TEXT="#000000" LINK="#0000EE" VLINK="#551A8B" ALINK="#FF0000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<h2 align="center">
<br>JEUS EJB Test</h2>
<h2 align="center">
<br>
    <%
                try{

                        InitialContext initial = new InitialContext();
                        Object objref = initial.lookup("hellotest");
                        hello.HelloHome home = (hello.HelloHome)PortableRemoteObject.narrow(objref, hello.HelloHome.class);

                        hello.Hello hello = home.create();
                        out.println(hello.sayHello());

                } catch(Exception e) {
                        out.println("Error caught : " + e.getMessage());
                        e.printStackTrace();
                }

%>
</h2>
</body>
</html>