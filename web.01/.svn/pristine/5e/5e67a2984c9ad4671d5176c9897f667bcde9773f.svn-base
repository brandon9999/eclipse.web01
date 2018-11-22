<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>

<%@ page import="oracle.sql.CLOB" %>
<%@ page import="oracle.sql.BLOB" %>
<%@ page import="oracle.jdbc.driver.OracleResultSet" %>

<%
Context ctx = null;
DataSource ds = null;
Connection con = null;
PreparedStatement ps = null;
ResultSet rs = null;

//File            file       = (File)param.get("test3"); // 등록할 File
File file = new File("c:\\temp\\UploadFile", "Tail.exe");

Blob            emptyBlob  = null;            
OutputStream    outstream  = null;
FileInputStream finstream  = null;
String sql = null;

try
{
	con.setAutoCommit(false);

    // connection
	ctx = new InitialContext();
	ds = (DataSource) ctx.lookup("dbsource");
	con = ds.getConnection(); 

    // EMPTY_BLOB() 처리
    sql = "update blob_test set test3=EMPTY_BLOB() where test1=?";
    ps = con.prepareStatement(sql);
    ps.setString(1, request.getParameter("test1"));
    if ( ps.executeUpdate() < 0 ) throw new Exception();

    // 저장할 sajin Column 가져온다.
    sql = "select test3 from blob_test where test1=? for update";
    ps = con.prepareStatement(sql);
    ps.setString(1, request.getParameter("test1"));
    rs = ps.executeQuery();
    //if ( rs.next() ) emptyBlob = rs.getBlob(1);
	if ( rs.next() ) 
    {
		emptyBlob = rs.getBlob(1);
		BLOB blob = ((OracleResultSet)rs).getBLOB(1);
	}

    // db blob output stream
    oracle.sql.BLOB bol = (oracle.sql.BLOB) emptyBlob;
    outstream = bol.getBinaryOutputStream();
    int size = bol.getBufferSize();

    // 파일 input stream
    finstream = new FileInputStream(file);
    
    // 파일 읽어서 db에 넣기
    byte[] buffer = new byte[size];
    int length = -1;
    while ((length = finstream.read(buffer)) != -1) 
	{
        outstream.write(buffer, 0, length);
    }

    con.setAutoCommit(true);
} 
catch (Exception e)
{
    throw(e);
} 
finally 
{
    if( rs          != null ) rs.close();
    if( finstream   != null ) finstream.close();               
    if( outstream   != null ) outstream.close(); 
	if ( ps != null ) try { ps.close(); } catch(Exception e) {}
	if ( con != null ) try { con.close(); } catch(Exception e) {}

}

%>