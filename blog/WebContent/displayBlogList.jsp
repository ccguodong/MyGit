<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="cn.com.jobedu.blog.Blog"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>显示博客列表</title>
</head>

<body>
<%
    List list=(List)request.getAttribute("list");
    Blog blog=null;
%>
<%
    for(int i=0;i<list.size();i++){
    	blog=(Blog)list.get(i);
%>
	<table width="543" height="125" border="0">
		<tr>
			<td><%=blog.getCreatedTime() %></td>
		</tr>
		<tr>
			<td><a href="http://localhost:8080/blog/servlet/GetBlogServlet?id=<%=blog.getId()%>" target="_blank"><%=blog.getTitle() %></a></td>
		</tr>
		<tr>
			<td>
			<%
			     String source=blog.getContent();
			     int length=100;
			     if(source.length()<100)
			     {
			    	 length=source.length();
			     }
			     String newString=source.substring(0, length);
			     out.print(newString+"...");
			%>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
	<%
    }
	%>
</body>
</html>
