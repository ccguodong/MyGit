<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="include.jsp" %>
<%@ page import="java.util.List"%>
<%@ page import="cn.com.jobedu.blog.Category"%>
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
<title>admin Category page</title>
</head>
<%
	List list = (List) request.getAttribute("list");
	Category category = null;
%>
<body>
	<div align="center">
		<p>博客分类管理</p>
		<table width="670" height="134" border="1" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="100">分类编号</td>
				<td width="200">分类名称</td>
				<td width="100">分类显示序</td>
				<td width="100">操作</td>
			</tr>
			<%
				for (int i = 0; i < list.size(); i++) {
					category = (Category) list.get(i);
			%>
			<tr>
				<td><%=category.getId()%></td>
				<td><%=category.getName()%></td>
				<td><%=category.getLevel()%></td>
				<td><a
					href="/blog/servlet/CategoryServlet?method=edit&id=<%=category.getId()%>">修改</a>|<a
					href="/blog/servlet/CategoryServlet?method=delete&id=<%=category.getId()%>"
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
