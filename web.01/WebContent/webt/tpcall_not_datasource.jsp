<%@ page language="java" session="true" contentType="text/html; charset=euc-kr" %>
<%@ page import="tmax.webt.*,tmax.webt.io.*,tmax.webt.jeus.*" %>
<%@ page import="javax.naming.*" %>
<%
 String sType = request.getParameter("iType");
 String sXA = request.getParameter("iXA");
 String iService = request.getParameter("iService");
 String iData = request.getParameter("iData");
 int iType = Integer.parseInt(sType);
 
 out.println("버퍼타입 = ");
 
 switch (iType) {
  case 1:
   out.println("STRING");
   break;
  case 2:
   out.println("CARRAY");
   break;
  case 3:
   out.println("FIELD");
   break;
 }
 
 int iXA = Integer.parseInt(sXA);
 
 out.println("<br>트랜잭션타입 = ");
 
 switch (iXA) {
  case 1:
   out.println("XA");
   break;
  case 2:
   out.println("Non XA");
   break;
 }
 
 out.println("<br>서비스명 = [" + iService + "]<br>");
 out.println("<br>입력데이터 = [" + iData + "]<br><br><br>");
%>
 
<%
		WebtDataSource ds = null;
        WebtConnection conn = null;
        WebtRemoteService service = null;
        WebtTransaction trans = null;
        WebtBuffer sndbuf = null;
        WebtBuffer rcvbuf = null;
        int sndlen, rcvlen;
        String errmsg = null;
        WebtSystem.setDefaultCharset("euc-kr");
        try 
        {
            
        /*****************************************
        * 커넥션 얻어오기 webt.properties
        *****************************************/  
        // conn = new WebtConnection("192.168.190.128", "8888")
        conn = WebtConnectionPool.getConnection("tmax1");            
     
        /*****************************************
        * 커넥션 얻어오기 WebtDataSource
        *****************************************/  
		/*
		final String TMAXSOURCE = "tmax1";
		try 
		{
			InitialContext ic = new InitialContext();
			ds = (WebtDataSource)ic.lookup(TMAXSOURCE);
			conn = ds.getConnection();
		} 
		catch (Exception ex) 
		{
		 ex.printStackTrace();
		}
		
		//System.out.println("getConnection");
		*/

        /*****************************************  
                  글로벌 트랜잭션 시작                      
        ****************************************/  
		if (iXA == 1) 
		{
		 trans = new WebtTransaction(conn);
		 trans.begin();
		}
     
		switch (iType) 
		{
			case 1:
			     /*****************************************
			     * 스트링버퍼 테스트시
			     *****************************************/  
			     service = new WebtRemoteService(iService, conn);
			   
			            sndbuf = service.createStringBuffer();
			            sndbuf.setString(iData, "euc-kr"); 
			 break;
			 
 			 case 2:
			            /*****************************************
			     * CARRAY버퍼 테스트시
			     *****************************************/         
			     service = new WebtRemoteService(iService, conn);
			   
			            sndbuf = service.createCarrayBuffer();
			            sndbuf.setString(iData, "euc-kr"); 
			 break;
			 case 3: 
			     /*****************************************
			     * 필드버퍼 테스트시
			     *****************************************/  
			     service = new WebtRemoteService(iService, conn);
			            
			            WebtFieldSet sndset = new WebtFieldSet(service.createFieldBuffer());
			     
			     if (iService.equals("FDLTOUPPER")||iService.equals("FDLTOLOWER")) {       
			      sndset.add("INPUT", iData);
			            }
			            else if (iService.equals("FDLSEL")) {
			            String id = iData;
			             sndset.add("FDL_ID", id);
			            }
			     
			     sndbuf = sndset.getFieldBuffer();
			     
			 break;
 		}
  
     
     
     /*****************************************
     * tpcall
     *****************************************/  
            rcvbuf = service.tpcall(sndbuf);
   
     String ret = null;
            switch(rcvbuf.getBufferType()) {
             case WebtBuffer.BT_STRING:   // String 타입의 데이타를 리턴 받았을 경우
              out.println("Receive Buffer type is BT_STRING<br>");
              //out.println("String");
                 ret = rcvbuf.getString();
              out.println("출력데이터 = [" + ret + "]");
              break;
              
             case WebtBuffer.BT_CARRAY:   // carray 타입의 데이타를 리턴 받았을 경우
              out.println("Receive Buffer type is BT_CARRAY<br>");
              //out.println("Carray");
              byte[] cadata = rcvbuf.getBytes();
              ret = new String(cadata);
              out.println("출력데이터 = [" + ret + "]");
          break;
          
             case WebtBuffer.BT_FIELD:   // field buffer 타입의 데이타를 리턴 받았을 경우
              
                 out.println("Receive Buffer type is BT_FIELD<br>");              
              
              
                 if (iService.equals("FDLTOUPPER")||iService.equals("FDLTOLOWER")) {
              
              WebtFieldSet rcvset = new WebtFieldSet(rcvbuf);               
              ret = rcvset.getString("OUTPUT");               
              out.println("출력데이터 = [" + ret + "]");
              
             }
             
             else if (iService.equals("FDLSEL")) {
             
          
                /*****************************************
              * 필드데이터 꺼내는 방법 1 
              *****************************************/              
              
              WebtFieldSet rcvset = new WebtFieldSet(rcvbuf);
              
              int count = rcvset.count("FDL_ID");
              
              out.println("<p>count=" + count);              
              out.println("<p><table border=1><tr><td>FDL_ID</td><td>FDL_NAME</td><td>FDL_AGE</td></tr>");              
               for (int i=1; i <= count; i++) {               
          
                  out.println("<tr>" + 
                              "<td>" + rcvset.getString("FDL_ID") + "</td>" +
                              "<td>" + rcvset.getString("FDL_NAME") + "</td>" +
                              "<td>" + rcvset.getString("FDL_AGE") + "</td>" +
                              "</tr>" );
              }              
              out.println("</table>");
              
              
              
              /*****************************************
              * 필드데이터 꺼내는 방법 2
              *****************************************/
              /*
              Vector fieldList = rcvbuf.getFields();          
          for(Enumeration e = fieldList.elements(); e.hasMoreElements(); ) {
               
               WebtField fields = (WebtField)e.nextElement();
               out.println("[Field Key] : " + fields.getFieldKey() );
               
               int fieldtype = fields.getFieldType();
               Vector elements = fields.getFieldVector();
               
               //out.println("element.size=" + elements.size() + "<br>");
               //out.println("fieldtype=" + fieldtype + "<br>");
               
               for(int j=0 ; j < elements.size(); j++) {
                  
                  switch(fieldtype) {
                     case WebtField.FB_CARRAY:
                        out.println("[Value"+j+"] : " + new String(fields.get(j).bytesValue()) + "<br>");
                        break;
                     
                     case WebtField.FB_STRING:
                        out.println("[Value"+j+"] : " + fields.get(j).stringValue() + "<br>");
                        break;
                     
                     case WebtField.FB_DOUBLE:
                        out.println("[Value"+j+"] : " + fields.get(j).doubleValue() + "<br>");
                        break;
                     
                     case WebtField.FB_FLOAT:
                        out.println("[Value"+j+"] : " + fields.get(j).floatValue() + "<br>");
                        break;
                     
                     case WebtField.FB_INT:
                        out.println("[Value"+j+"] : " + fields.get(j).intValue() + "<br>");
                        break;
                     }
                  }
              }
              */ 
              
             }
              
                    break;
                 
      default:
       out.println("Error!! Undefined receive data type");
       ret = rcvbuf.getString();
       out.println(ret);        
         }
         
         
         if (iXA == 1) {
          trans.commit();
         }
         
         
        } catch (WebtServiceFailException e) {
        
         if (iXA == 1) {
          out.println("Rollback Svc...<br><br>");
          try {
           trans.rollback();
          } catch (WebtTXException te) {
           out.println("rollback fail<br>");
          }
         }
         
      e.printStackTrace(); 
      
      errmsg = "WebtServiceFailException Catched<br><br>";
      errmsg += "tperrno =" + e.getTPError() + "<br>";
      errmsg += "tperrmsg =" + e.getTPErrorMessage() + "<br>";
      errmsg += "errmsg =" + e.getMessage() + "<br><br>";     
       
 } catch (WebtServiceException e) {    
  
  if (iXA == 1) {
          out.println("Rollback Svc...<br><br>");
          try {
           trans.rollback();
          } catch (WebtTXException te) {
           out.println("rollback fail<br>");
          }
         }
         
      e.printStackTrace(); 
      
      errmsg = "WebtServiceException Catched<br><br>";
      errmsg += "tperrno =" + e.getTPError() + "<br>";
      errmsg += "tperrmsg =" + e.getTPErrorMessage() + "<br>";
      errmsg += "errmsg =" + e.getMessage() + "<br><br>";    
         
     } catch (WebtIOException e) {
     
      if (iXA == 1) {
          out.println("Rollback Svc...<br><br>");
          try {
           trans.rollback();
          } catch (WebtTXException te) {
           out.println("rollback fail<br>");
          }
         }
         
      e.printStackTrace(); 
      
      errmsg = "WebtServiceException Catched<br><br>";
      errmsg += "tperrno =" + e.getTPError() + "<br>";
      errmsg += "tperrmsg =" + e.getTPErrorMessage() + "<br>";
      errmsg += "errmsg =" + e.getMessage() + "<br><br>";   
     } catch (WebtException e) {      
         
         if (iXA == 1) {
          out.println("Rollback Svc...<br><br>");
          try {
           trans.rollback();
          } catch (WebtTXException te) {
           out.println("rollback fail<br>");
          }
         }
         
      e.printStackTrace(); 
      
      errmsg = "WebtServiceException Catched<br><br>";
      errmsg += "tperrno =" + e.getTPError() + "<br>";
      errmsg += "tperrmsg =" + e.getTPErrorMessage() + "<br>";
      errmsg += "errmsg =" + e.getMessage() + "<br><br>";    
      
     } finally {
      
      try {
       conn.close();
   //WebtConnectionPool.putConnection(conn);
      } catch (WebtException we) {
       we.printStackTrace();
       errmsg = "[" + we.getTPError() + "]" + we.getTPErrorMessage() + " | " + we.getMessage();
      }
      
     }
     
     out.println(errmsg);
     
%>