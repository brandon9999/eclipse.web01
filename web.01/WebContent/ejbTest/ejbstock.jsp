<%@ page contentType="text/html;charset=EUC-KR"  %>

<%@ page import = "java.rmi.*,
                   javax.naming.*,
                   javax.rmi.*,
                   java.net.*,                   
                   javax.ejb.*,
                   java.util.*,
                   samples.stateless.*"
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
                        Object objref = initial.lookup("stock");
                        samples.stateless.StockHome home = (samples.stateless.StockHome)PortableRemoteObject.narrow(objref, samples.stateless.StockHome.class);

                        samples.stateless.Stock stock = home.create();
                        out.println(stock.getValue("aaa"));

                } catch(Exception e) {
                        out.println("Error caught : " + e.getMessage());
                        e.printStackTrace();
                }

%>
</h2></p>
</body>
</html>