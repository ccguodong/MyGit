<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="include.jsp" %>
<%@ page import="cn.com.jobedu.blog.Comment" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改评论</title>
</head>
<body>
<%
     Comment comment=(Comment)request.getAttribute("comment");
%>
	<form action="/blog/servlet/CommentServlet" method="post">
	<input type="hidden" name="method" value="update"></input>
	<input type="hidden" name="id" value="<%=comment.getId()%>"></input>
		<table width="650" border="1">
			<tr>
				<td width="150">评论人：</td>
				<td width="500"><label> <input name="name" value="<%=comment.getName() %>" type="text"
						id="name" size="20" />
				</label></td>
			</tr>
			<tr>
				<td>内容：</td>
				<td><label> <textarea name="content" cols="60"
							rows="10" id="content"><%=comment.getContent() %></textarea>
				</label></td>
			</tr>
			<tr>
				<td><label> <input type="submit" name="button"
						id="button" value="提交" />
				</label></td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form>
</body>
</html>