<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="java.sql.*" %>

<%
Context ctx = null;
DataSource ds = null;
//Connection conPool = null;         // for xlog
Connection conDs = null;
Statement stmt = null;
ResultSet rs = null;

try 
{
		StringBuffer query = new StringBuffer();
		
		ctx = new InitialContext();
		ds = (DataSource) ctx.lookup("dbsource");
		conDs = ds.getConnection(); 
		//conDs = new WpConnection(ds.getConnection());    // for xlog

        stmt = conDs.createStatement();
        query.append( " select deptno, dname, loc " )
             .append( " from dept " ); 
        System.out.println( "ConnectionPool query is " + query.toString());     
        System.out.println( "con is " + conDs.toString());      
        rs = stmt.executeQuery( query.toString() );

        //Thread.sleep(20000);
        
        while ( rs.next() )
        {
%>
                DEPTNO : <%= rs.getInt(1) %><br>
                DNAME : <%= rs.getString(2) %><br>
                LOC : <%= rs.getString(3) %><br>
<%
        }
} 
catch ( Exception e ) 
{
        e.printStackTrace();
} 
finally 
{
        if ( stmt != null ) try { stmt.close(); } catch(Exception e) {}
        if ( conDs != null ) try { conDs.close(); } catch(Exception e) {}
}
%>