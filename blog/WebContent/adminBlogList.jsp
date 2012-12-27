<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="cn.com.jobedu.blog.Blog"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<title>admin page</title>
</head>
<%
	List list = (List) request.getAttribute("list");
	Blog blog = null;
%>
<body>
	<div align="center">
		<p>博客文章管理</p>
		<table width="670" height="134" border="1" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="51">文章号</td>
				<td width="515">文章主题</td>
				<td width="82">操作</td>
			</tr>
			<%
				for (int i = 0; i < list.size(); i++) {
					blog = (Blog) list.get(i);
			%>
			<tr>
				<td><%=blog.getId()%></td>
				<td><%=blog.getTitle()%></td>
				<td><a
					href="/blog/servlet/PreEditBlogServlet?id=<%=blog.getId()%>">修改</a>|<a
					href="/blog/servlet/DeleteBlogServlet?id=<%=blog.getId()%>"
					onclick="javascript:return dele()">删除</a></td>
			</tr>
			<%
				}
			%>
		</table>
		<p>&nbsp;</p>
	</div>
</body>
</html>
