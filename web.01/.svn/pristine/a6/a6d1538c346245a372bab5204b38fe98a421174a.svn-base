<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="java.util.TimeZone"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
    
<%
	long start = System.currentTimeMillis();        //최초 수행 시간 기록
	System.out.println(">>>>> START");
	Thread.sleep(1000);
	out.println("test : " + System.currentTimeMillis());
	System.out.println(">>>>> STEP1 : " + (System.currentTimeMillis() - start));    //현재 시간에서 최초 수행시간을 빼면 여기까지 수행된 시간이 나옴
	Thread.sleep(1000);
	out.println("test");
	System.out.println(">>>>> STEP2 : " + (System.currentTimeMillis() - start));
	Thread.sleep(1000);
	out.println("test");
	System.out.println(">>>>> END : " + (System.currentTimeMillis() - start));
	
	
%>

<br>

====================================================================
<br>
<%	


  Date date;
  SimpleDateFormat formatter;
  String pattern = "yyyy년 M월 d일  a h시 m분";
  String result;
  
  formatter = new SimpleDateFormat(pattern, new Locale("ko","KOREA"));
  date = new Date();
  result = formatter.format(date);
  System.out.println("result : " + result);
  out.println("result : " + result);  
  
%>
<br>
====================================================================
<br>
<%	
Date today = new Date (); 
System.out.println ( today );
out.println ( today );

%>
<br>
====================================================================
<br>
<%	
	Calendar runTimeCal = Calendar.getInstance();

	System.out.println ("현재 타임존 : " + runTimeCal.getTimeZone().getDisplayName() );
	out.println ("현재 타임존 : " + runTimeCal.getTimeZone().getDisplayName() );

	TimeZone tz = TimeZone.getTimeZone("GMT+09:00");
	runTimeCal.setTimeZone(tz);
%>

<br>
====================================================================
<br>
<%	
long time1 = System.currentTimeMillis (); 
long time2 = System.currentTimeMillis ();
System.out.println ( ( time2 - time1 ) / 1000.0 );
out.println ( ( time2 - time1 ) / 1000.0 );
%>