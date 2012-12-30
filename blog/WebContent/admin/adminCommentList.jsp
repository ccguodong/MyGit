<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="include.jsp" %>
<%@ page import="java.util.List"%>
<%@ page import="cn.com.jobedu.blog.Comment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript">
	function dele() {
		var msg = "您真的确定要删除吗？\n\n请确认！";
		if (confirm(msg) == true) {
			return true;
		} else {
			return false;
		}
	}
</script>
</head>

<body>
	<%
		List list = (List) request.getAttribute("list");
		Comment comment = null;
	%>
	<p align="center">管理评论内容</p>
	<table width="828" height="168" border="1" align="center">
		<tr>
			<td width="113">编号</td>
			<td width="203">评论人</td>
			<td width="158">内容</td>
			<td width="158">时间</td>
			<td width="162">操作</td>
		</tr>
		<%
			for (int i = 0; i < list.size(); i++) {
				comment = (Comment) list.get(i);
		%>
		<tr>
			<td><%=comment.getId()%></td>
			<td><%=comment.getName()%></td>
			<td><%=comment.getContent()%></td>
			<td><%=comment.getCreatedtime()%></td>
			<td><a
				href="http://localhost:8080/blog/servlet/CommentServlet?method=edit&id=<%=comment.getId()%>">修改</a>|<a
				href="http://localhost:8080/blog/servlet/CommentServlet?method=delete&id=<%=comment.getId()%>"
				onclick="javascript:return dele()">删除</a></td>
		</tr>
		<%
			}
		%>
	</table>
	<p>&nbsp;</p>
</body>
</html>
