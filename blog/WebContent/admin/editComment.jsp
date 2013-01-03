<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="header.jsp"%>
<%@ page import="cn.com.jobedu.blog.Comment"%>
<link rel="stylesheet" href="/blog/admin/style/addBlog.css"
	type="text/css"></link>
<title>修改评论</title>
<%
	Comment comment = (Comment) request.getAttribute("comment");
%>
<h3>修改评论</h3>
<form action="/blog/servlet/CommentServlet" method="post">
	<input type="hidden" name="method" value="update"></input> <input
		type="hidden" name="id" value="<%=comment.getId()%>"></input>
	<table width="650" border="0" class="title">
		<tr>
			<td width="50">评论人：</td>
			<td width="500"><label> <input name="name"
					value="<%=comment.getName()%>" type="text" id="name" size="20" />
			</label></td>
		</tr>
		<tr>
			<td>内容：</td>
			<td><label> <textarea name="content" cols="60" rows="10"
						id="content"><%=comment.getContent()%></textarea>
			</label></td>
		</tr>
		<tr>
		    <td>&nbsp;</td>
			<td><label> <input type="submit" name="button"
					id="button" value="提交" />
			</label></td>
		</tr>
	</table>
</form>
</div>
</body>
</html>