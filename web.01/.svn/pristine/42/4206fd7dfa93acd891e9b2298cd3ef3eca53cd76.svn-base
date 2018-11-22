<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="java.sql.*" %>

<%@ page import="java.sql.DatabaseMetaData" %>
<%@ page import="oracle.sql.CLOB" %>
<%@ page import="oracle.jdbc.OracleConnection" %>
<%@ page import="oracle.xdb.XMLType" %>
<%@ page import="oracle.jdbc.OraclePreparedStatement" %>

<%
Context ctx = null;
DataSource ds = null;
Connection conDs = null;
Statement stmt = null;
ResultSet rs = null;
oracle.sql.CLOB clob = null;        

OracleConnection oraConn = null;
OraclePreparedStatement pstmt = null;

try 
{
		ctx = new InitialContext();
		ds = (DataSource) ctx.lookup("ora_dbsource");
		conDs = ds.getConnection(); 
		DatabaseMetaData metaData = conDs.getMetaData();
		
		oraConn = (OracleConnection)metaData.getConnection();
		out.println(":::oraConn:::"+oraConn.getClass().getName().toString() + "<br><br><br>");		
		
		/////////////////////////////////////////////////////////////////////////
        StringBuffer sql = new StringBuffer();
        StringBuffer xmlData = new StringBuffer();
       
        //저장될 샘플 XML Data
        xmlData.append("<PO>");
        xmlData.append("    <PONO>1</PONO>");
        xmlData.append("    <PNAME>Po_1</PNAME>");
        xmlData.append("    <CUSTNAME>John</CUSTNAME>");
        xmlData.append("    <SHIPADDR>");
        xmlData.append("        <STREET>1033, Main Street</STREET>");
        xmlData.append("        <CITY>Sunnyvalue</CITY>");
        xmlData.append("        <STATE>CA</STATE>");
        xmlData.append("   </SHIPADDR>");
        xmlData.append("</PO>");

        // INSERT 문장
        sql.append(" INSERT INTO CLOB_TEST (test1, test2, review) ");
        sql.append(" VALUES (90, 'aaa', ?) ");
                    
        pstmt = (OraclePreparedStatement)oraConn.prepareStatement(sql.toString());
        XMLType poXML = XMLType.createXML(oraConn, xmlData.toString());
        pstmt.setObject(1, poXML.getClobVal());
        pstmt.executeUpdate();        
		/////////////////////////////////////////////////////////////////////////
} 
catch ( Exception e ) 
{
        e.printStackTrace();
} 
finally 
{
        if ( pstmt != null ) try { pstmt.close(); } catch(Exception e) {}
        if ( oraConn != null ) try { oraConn.close(); } catch(Exception e) {}
}
%>