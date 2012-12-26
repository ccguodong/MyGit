<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=request.getAttribute("message") %><br><br>
<a href="http://localhost:8080/blog/servlet/AdminBlogListServlet">博客管理首页</a><br>
<a href="http://localhost:8080/blog/addBlog.jsp">写新的博文</a><br>
<a href="http://localhost:8080/blog/servlet/GetBlogListServlet">查看博文</a><br>
</body>
</html>