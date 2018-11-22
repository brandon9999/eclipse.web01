 <%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="oracle.sql.BLOB" %>
<%@ page import="oracle.jdbc.*" %>

<%
// ����Ŭ ����̹� ������ ���� �Ʒ� ���߿� �ϳ��� import�ؾ���
// 1) oracle.jdbc.driver.OracleResultSet
// 2) oracle.jdbc.*>
%>

<%
//
// TEST�� ����  DB TABLE 
//
//	CREATE TABLE BLOB_TEST
//	(
//			test1 number(3),
//			test2 varchar(20),		
//			test3 blob
//	)
%>

 <%
 Context ctx = null;
 DataSource ds = null;
 Connection con = null;
 ResultSet rs = null;
 ResultSet rs2 = null;
 PreparedStatement ps = null;
 PreparedStatement ps2 = null;
 String query = null;
 String query2 = null;

 int test1 = 18;     		// test1 �÷��� insert �� values
 String test2 = "test18";   // test2 �÷��� insert �� values
 
ctx = new InitialContext();
ds = (DataSource) ctx.lookup("ora_dbsource");
con = ds.getConnection();

// blob �÷��� ���ε��� ����
File file = new File("c:\\temp\\UploadFile\\aaa.xls");

try 
{ 
	 // BLOB column�� ������Ʈ �ϴ� ����, setAutoCommit(false)�� �ݵ�� �����ؾ� ��.   
	con.setAutoCommit(false);  
    
	//  empty_clob()�� �̿��Ͽ� blob������ Ȯ���ϸ鼭 insert ������ �����.
    query = "INSERT INTO blob_test VALUES (?,"; 
    query = query + "?,empty_blob())"; 
    ps = con.prepareStatement(query); 
    ps.setInt(1, test1); 
    ps.setString(2, test2); 
    
	if(ps.executeUpdate() > 0)
	{
		System.out.println("Insert�� ����");		
	}
	else
	{
		System.out.println("Insert�� ����");		
	}

	//������ Insert�� �����͸� �ٽ� �������� ���� ���� // �ݵ�� for update �߰��� ��
	query2 = "select review from blob_test where test1 = ? for update"; 
	ps2 = con.prepareStatement(query2); 
    ps2.setInt(1, test1); 	
	rs2 = ps2.executeQuery(); 

	if (rs2.next())
	{
		BLOB blob = ((OracleResultSet)rs2).getBLOB("review"); 
		BufferedOutputStream bops = new BufferedOutputStream(blob.getBinaryOutputStream()); 
        FileInputStream fis = new FileInputStream(file); 
        BufferedInputStream bis = new BufferedInputStream(fis); 

        int size = blob.getBufferSize(); 
        byte[] buffer = new byte[size]; 
         
        int len = -1; 
        while ( (len = bis.read(buffer)) != -1) { 
        	bops.write(buffer, 0, len); 
            System.out.println("...[���ε� ��]..." ); 
        } 
        System.out.println("flush(); len : "+len); 
        bops.flush(); 
         
        bis.close(); 
        fis.close(); 
        bops.close();         
	}
	
	// commit
	con.commit(); 
	con.setAutoCommit(true); 
}
catch (Exception e)
{
    throw(e);
} 
finally 
{
	if ( ps != null ) try { ps.close(); } catch(Exception e) {}
	if ( ps2 != null ) try { ps2.close(); } catch(Exception e) {}
	if ( rs != null ) try { rs.close(); } catch(Exception e) {}
	if ( rs2 != null ) try { rs2.close(); } catch(Exception e) {}
	if ( con != null ) try { con.close(); } catch(Exception e) {}

}
%>  