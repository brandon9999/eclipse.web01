<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="java.sql.*" %>

<%
Context ctx = null;
DataSource ds = null;
Connection con = null;
Statement stmt = null;
ResultSet rs = null;
String query = null;

ctx = new InitialContext();
ds = (DataSource) ctx.lookup("ora_dbsource");
con = ds.getConnection(); 

try {  
    query = "select review from blob_test where test1 = 18"; 
    stmt = con.createStatement();  
    rs = stmt.executeQuery(query); 
    
    rs.next(); 
    
    byte buffer[] = null; 
    buffer = rs.getBytes("review"); 

	//Content-Disposition 헤더에 파일 이름 세팅. 
    //response.setHeader("Content-Disposition", "filename=imsi.txt;"); 
	response.setHeader("Content-Disposition", "attachment;filename=aaa.xls;");

    ServletOutputStream sop = response.getOutputStream(); 

    sop.write(buffer); 
    sop.close(); 
} 
catch(Exception e)
{
	
}
finally
{
    stmt.close();
    rs.close();
    con.close();
}

 %>