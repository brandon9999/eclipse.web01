<%@ page contentType = "text/html; charset=euc-kr" %>

<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.Statement" %>
<%@ page import = "java.sql.SQLException" %>

<%
    request.setCharacterEncoding("euc-kr");
    
    Class.forName("oracle.jdbc.driver.OracleDriver");
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    try {
// for MySQL    	
//        String jdbcDriver = "jdbc:mysql://localhost:3306/chap11?" +
//                            "useUnicode=true&characterEncoding=euc-kr";

// for ORACLE   	
        String jdbcDriver = "jdbc:oracle:thin:@61.35.57.5:1521:ORCL";

		String dbUser = "scott";
        String dbPass = "tiger";
        
        String query = "SELECT 'RESULT' FROM DUAL";
        
        conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        while(rs.next()) 
        {          // 쿼리 결과 보여주기
            String rs_str = rs.getString(1);
            out.println(rs_str);
        }        
        
    } 
    catch(Exception e)
    {
    	 out.println("[CONTENT]에러 : Exception ");
    	e.printStackTrace();
    } 
    finally 
    {
        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
    	if (stmt != null) try { stmt.close(); } catch(SQLException ex) {}
        if (conn != null) try { conn.close(); } catch(SQLException ex) {}
    }
%>
