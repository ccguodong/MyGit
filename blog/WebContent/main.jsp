<%@page import="cn.com.jobedu.blog.Comment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="cn.com.jobedu.blog.Blog"%>
<%@ page import="cn.com.jobedu.blog.Category"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
<link rel="stylesheet" type="text/css" href="/blog/admin/style/main.css" />
</head>
<body>
	<%
		List bloglist = (List) request.getAttribute("bloglist");
		Blog blog = null;
		List categorylist = (List) request.getAttribute("categorylist");
		Category category = null;
		List commentlist = (List) request.getAttribute("commentlist");
		Comment comment = null;
	%>
	<div id="container">
		<div id="banner">
			<h1>
				<a href="/blog">张国栋的博客</a>
			</h1>
		</div>

		<div id="center">
			<div class="content">
				<!-- list blog begin  -->
				<%
					for (int i = 0; i < bloglist.size(); i++) {
						blog = (Blog) bloglist.get(i);
				%>
				<div class="entry">
					<a id="6"></a>
					<h3>
						<a
							href="/blog/servlet/GetBlogListServlet?id=<%=blog.getId()%>&method=get" target="_blank"><%=blog.getTitle()%></a>
					</h3>
					<%
						String source = blog.getContent();
							int length = 100;
							if (source.length() < 100) {
								length = source.length();
							}
							String newString = source.substring(0, length);
							out.print(newString + "...");
					%>
					<p class="posted"><%=blog.getCreatedTime()%>
						<a href="<%=request.getContextPath() %>/servlet/GetBlogListServlet?cid=<%=blog.getCategoryId()%>"><%=blog.getCategory()%></a> | <a href="tm?method=e&id=6#comments">评论</a>
					</p>
				</div>
				<%
					}
				%>
				<!-- 产生分页的连接-->
				&nbsp; 1/2 &nbsp;<a href="tm?method=h&p=2">&gt;&gt;</a>
				<!-- end list -->

				<br clear="all" />
			</div>
			<!-- end content -->
		</div>
		<!-- end center -->

		<div id="right">
			<div class="sidebar">
			<ul>
			<li>张国栋的博客，欢迎大家访问！！</li>
			</ul>
				<h2>分类</h2>
				<ul>
					<li><a href="/blog">全部</a></li>
					<%
						for (int i = 0; i < categorylist.size(); i++) {
							category = (Category) categorylist.get(i);
					%>
					<li><a href="<%=request.getContextPath() %>/servlet/GetBlogListServlet?cid=<%=category.getId()%>"><%=category.getName()%></a></li>
					<%
						}
					%>
				</ul>

				<h2>最近的主题</h2>
				<ul>
					<%
						for (int i = 0; i < bloglist.size(); i++) {
							blog = (Blog) bloglist.get(i);
					%>
					<li><a href="<%=request.getContextPath() %>/servlet/GetBlogListServlet?id=<%=blog.getId()%>&method=get" target="_blank"><%=blog.getTitle() %></a></li>
					<%
						}
					%>
				</ul>
				<h2>最近的评论</h2>
				<ul>
				    <%
				        for(int i=0;i<commentlist.size();i++)
				        {
				        	comment=(Comment)commentlist.get(i);
				    %>
					<li><a href="<%=request.getContextPath() %>/servlet/GetBlogListServlet?id=<%=comment.getBlogId()%>&method=get" target="_blank"><%=comment.getContent() %></a></li>
					<%
					}
					%>
				</ul>

			</div>
			<!-- end sidebar -->
		</div>
		<!-- end right -->
	</div>
	<!-- end container -->
</body>
</html>