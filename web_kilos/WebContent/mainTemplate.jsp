<%@ taglib uri="/WEB-INF/tlds/javacan-mvc-template.tld" prefix="javacan" %>
<%@ page import = "com.javacan.mvc.template.TemplateConstant" %>
<%@ page contentType = "text/html; charset=euc-kr" %>
<html>
<head><title>KILOS Ä¿¹Â´ÏÆ¼</title></head>
<style>
TD { font-family: ±¼¸²; font-size: 10pt }
A.menulink { color: #FFFFFF; font-family: ±¼¸², ±¼¸²Ã¼; font-size: 9pt; text-decoration: none }
A.menulink:hover { color: #FFFFFF; font-family: ±¼¸², ±¼¸²Ã¼; font-size: 9pt; text-decoration: underline }
A.menulink:visited { color: #FFFFFF; font-family: ±¼¸², ±¼¸²Ã¼; font-size: 9pt; text-decoration: none }

A.submenulink { color: #302B63; font-family: ±¼¸², ±¼¸²Ã¼; font-size: 10pt; text-decoration: none }
A.submenulink:hover { color: #302B63; font-family: ±¼¸², ±¼¸²Ã¼; font-size: 10pt; text-decoration: underline }
A.submenulink:visited { color: #302B63; font-family: ±¼¸², ±¼¸²Ã¼; font-size: 10pt; text-decoration: none }
</style>
<body>

<table width="600" cellpadding="0" cellspacing="0" border="0">
<tr>
    <td colspan="2"><javacan:get name="header" /></td>
</tr>
<tr>
    <td colspan="2"><javacan:get name="menu" /></td>
</tr>
<tr>
    <td width="100" bgcolor="#C6C6C6" valign="top"><javacan:get name="submenu" /></td>
    <td width="500" valign="top">
	    <table width="100%" cellpadding="2" border="0" cellspacing="0">
	    <tr><td><javacan:get name="<%= TemplateConstant.CONTENT_PART_NAME %>" /></td></tr>
	    </table>
    </td>
</tr>
<tr>
    <td colspan="2"><javacan:get name="footer" /></td>
</tr>
</table>

</body>
</html>
