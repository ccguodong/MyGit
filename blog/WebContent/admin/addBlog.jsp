<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="cn.com.jobedu.blog.Category"%>
<%@include file="include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>add Blog content</title>
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
					out.print("欢迎," + user.getUsername() + "登录");
			%>
			| <a href="">发博文</a> | <a href="">博文管理</a> | <a href="">分类管理</a> | <a
				href="">评论管理</a> | <a href="">修改密码</a> | <a href="">退出</a>
		</div>
		<div class="text">发博文</div>
		<form id="form1" name="form1" method="post"
			action="/blog/servlet/AddBlogServlet">
			<table width="629" border="0">
				<tr>
					<td width="76" class="title">主题：</td>
					<td width="543"><label> <input name="title"
							type="text" id="title" size="60" />
					</label></td>
				</tr>
				<tr>
					<td class="title">类别：</td>
					<td><label> <select name="category" id="category">
								<%
									List list = (List) request.getAttribute("list");
									for (int i = 0; i < list.size(); i++) {
										Category cate = (Category) list.get(i);
								%>
								<option value="<%=cate.getId()%>"><%=cate.getName()%></option>
								<%
									}
								%>
						</select>
					</label></td>
				</tr>
				<tr>
					<td class="title">内容：</td>
					<td><label> <textarea name="content" cols="60"
								rows="8" id="content"></textarea> <script type="text/javascript">
									CKEDITOR.replace('content');
								</script>
					</label></td>
				</tr>
				<tr>
					<td></td>
					<td><label> <input type="submit" name="button2"
							id="button2" value="创建" />
					</label></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</form>
		<p>&nbsp;</p>
	</div>
</body>
</html>
