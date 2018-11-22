<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page import = "org.apache.log4j.Logger" %>

<%! static Logger logger = Logger.getLogger("log4jtest.jsp");%>

<%
logger.fatal("fatal!!");
logger.fatal("fatal2!!", new NullPointerException("널입니다요"));
logger.error("error!", new NumberFormatException());
logger.error("error!2");
logger.warn("warn");
logger.info("info");
logger.debug("debug");
%>

Log4J 테스트 페이지