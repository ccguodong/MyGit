<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="header.jsp"%>
<%@ page import="java.util.List"%>
<%@ page import="cn.com.jobedu.blog.Category"%>
<link rel="stylesheet" href="/blog/admin/style/style.css"
	type="text/css"></link>
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
<%
	List list = (List) request.getAttribute("list");
	Category category = null;
%>
<h3>博客分类管理</h3>
<table id="tab" align="center">
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
			href="/blog/servlet/CategoryServlet?method=edit&id=<%=category.getId()%>"><img
				src="/blog/admin/images/edit.gif" border="0" alt="编辑"></img></a>|<a
			href="/blog/servlet/CategoryServlet?method=delete&id=<%=category.getId()%>"
			onclick="javascript:return dele()"><img
				src="/blog/admin/images/delete.gif" border="0" alt="删除"></img></a></td>
	</tr>
	<%
		}
	%>
</table>
<p>&nbsp;</p>
</div>
</body>
</html>
