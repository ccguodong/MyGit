<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="cn.com.jobedu.blog.Blog"%>
<%@ page import="cn.com.jobedu.blog.Comment"%>
<%@ page import="cn.com.jobedu.blog.Category"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/blog/admin/style/main.css" />
<title>显示博文</title>
</head>

<body>
	<%
		//所有博客列表
		List bloglist = (List) request.getAttribute("bloglist");
		//所有分类的列表
		List categorylist = (List) request.getAttribute("categorylist");
		//所有的评论列表 
		List commentlist = (List) request.getAttribute("commentlist");
		//取出用户所点击的博客，只有一篇
		Blog blog = (Blog) request.getAttribute("blog");
		//该篇博客评论的列表
		List list = (List) request.getAttribute("list");
	%>
	<div id="container">
		<div id="banner">
			<h1>
				<a href="/blog">张国栋的博客</a>
			</h1>
		</div>

		<div id="center">
			<div class="content">
				<table>
					<tr>
						<td><h2><%=blog.getTitle()%></h2></td>
					</tr>
					<tr>
						<td><%=blog.getContent()%></td>
					</tr>
					<tr>
						<td><%=blog.getCreatedTime()%></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td height="79">
							<%
								for (int i = 0; i < list.size(); i++) {
									Comment thiscomment = (Comment) list.get(i);
							%>
							<table>
								<tr>
									<td><%=thiscomment.getName()%>的评论</td>
								</tr>
								<tr>
									<td>评论内容：<%=thiscomment.getContent()%></td>
								</tr>
								<tr>
									<td>评论时间：<%=thiscomment.getCreatedtime()%></td>
								</tr>
							</table> <br /> <br /> <%
 	}
 %>
							<p>&nbsp;</p>
						</td>
					</tr>
					<tr>
						<td><form id="form1" name="form1" method="post"
								action="/blog/servlet/CommentServlet">
								<input type="hidden" name="method" value="add"></input> <input
									type="hidden" name="blog_id" value=<%=blog.getId()%>></input>
								<table>
									<tr>
										<td>评论人：</td>
										<td><label> <input name="name" type="text"
												id="name" size="20" />
										</label></td>
									</tr>
									<tr>
										<td>内容：</td>
										<td><label> <textarea name="content" cols="35"
													rows="10" id="content"></textarea>
										</label></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><label> <input type="submit" name="button"
												id="button" value="提交" />
										</label></td>
									</tr>
								</table>
							</form></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="right">
			<div class="sidebar">
				<ul>
					<li class="ul"><a href="/blog/admin/login.jsp">登录</a></li>
					<li class="ul">&nbsp;&nbsp;</li>
					<li class="ul"><a href="/blog/admin/regist.jsp?method=add">注册</a></li>
					<li class="ul">&nbsp;&nbsp;</li>
					<li class="ul"><a href="/blog/admin/admin.jsp">博客管理首页</a></li>
				</ul>
				<ul>
					<li>张国栋的博客，欢迎大家访问！！</li>
				</ul>
				<h2>分类</h2>
				<ul>
					<li><a href="/blog">全部</a></li>
					<%
						for (int i = 0; i < categorylist.size(); i++) {
							Category category = (Category) categorylist.get(i);
					%>
					<li><a
						href="<%=request.getContextPath()%>/servlet/GetBlogListServlet?cid=<%=category.getId()%>"><%=category.getName()%></a></li>
					<%
						}
					%>
				</ul>

				<h2>最近的主题</h2>
				<ul>
					<%
						for (int i = 0; i < bloglist.size(); i++) {
							Blog recentblog = (Blog) bloglist.get(i);
					%>
					<li><a
						href="/blog/servlet/GetBlogListServlet?id=<%=recentblog.getId()%>&method=get"><%=recentblog.getTitle()%></a></li>
					<%
						}
					%>
				</ul>
				<h2>最近的评论</h2>
				<ul>
					<%
						for (int i = 0; i < commentlist.size(); i++) {
							Comment comment = (Comment) commentlist.get(i);
					%>
					<li><a
						href="<%=request.getContextPath()%>/servlet/GetBlogListServlet?id=<%=comment.getBlogId()%>&method=get"><%=comment.getContent()%></a></li>
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
