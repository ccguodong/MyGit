<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="/blog/admin/style/addBlogStyle.css"
	type="text/css"></link>
<script type="text/javascript" src="/blog/admin/ckeditor/ckeditor.js"></script>
</head>

<body>
	<div class="main">
		<div class="head">
			<h1>张国栋的博客</h1>
		</div>
		<div class="menu">
			<%
				if (user != null)
					out.print("欢迎," + user.getUsername() + "用户 ");
			%>
			| <a href="/blog/servlet/PreAddBlogServlet">发博文</a>
			| <a href="/blog/servlet/GetBlogListServlet">查看博文</a> 
			| <a href="/blog/servlet/AdminBlogListServlet">博文管理</a>
			| <a href="/blog/admin/addCategory.jsp">添加分类</a> 
			| <a href="/blog/servlet/CategoryServlet?method=list">分类管理</a>
			| <a href="/blog/servlet/CommentServlet?method=list">评论管理</a>
			| <a href="/blog/admin/changePassword.jsp">修改密码</a> 
			| <a href="/blog/servlet/UserServlet?method=logout">退出</a>
		</div>