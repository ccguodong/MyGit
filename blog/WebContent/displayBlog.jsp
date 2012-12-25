<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="cn.com.jobedu.blog.Blog"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>显示博文</title>
</head>

<body>
<% Blog blog=(Blog)request.getAttribute("blog"); %>
	<table width="781" height="404" border="0" align="center">
		<tr>
			<td height="35"><div align="center"><%=blog.getTitle() %></div></td>
		</tr>
		<tr>
			<td height="221"><%=blog.getContent() %></td>
		</tr>
		<tr>
			<td><%=blog.getCreatedTime() %></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
</body>
</html>
