<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="cn.com.jobedu.blog.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>admin page</title>
</head>

<body>
	<%
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("/blog/admin/login.jsp");
		}
		else
		{
			out.print("欢迎" + user.getUsername());
		}
	%>
	<table width="200" border="1" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td><a
				href="http://localhost:8080/blog/servlet/UserServlet?method=logout">退出系统</a></td>
		</tr>
		<tr>
			<td><a
				href="http://localhost:8080/blog/servlet/PreAddBlogServlet">发博文</a></td>
		</tr>
		<tr>
			<td><a
				href="http://localhost:8080/blog/servlet/AdminBlogListServlet">管理博文</a></td>
		</tr>
		<tr>
			<td><a href="http://localhost:8080/blog/admin/addCategory.jsp">添加分类</a></td>
		</tr>
		<tr>
			<td><a
				href="http://localhost:8080/blog/servlet/CategoryServlet?method=list">管理分类</a></td>
		</tr>
		<tr>
			<td><a
				href="http://localhost:8080/blog/servlet/GetBlogListServlet">查看博文</a></td>
		</tr>
		<tr>
			<td><a
				href="http://localhost:8080/blog/servlet/CommentServlet?method=list">管理评论</a></td>
		</tr>
	</table>
</body>
</html>
