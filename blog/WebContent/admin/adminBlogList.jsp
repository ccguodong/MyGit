<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="header.jsp"%>
<%@ page import="java.util.List"%>
<%@ page import="cn.com.jobedu.blog.Blog"%>
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
<title>admin page</title>
<%
	List list = (List) request.getAttribute("list");
	Blog blog = null;
%>
	<h2 align="center">博文管理</h2>

	<table id="tab" align="center">
		<tr>
			<th width="60px">编号</th>
			<th width="200px">主题</th>
			<th width="100px">分类</th>
			<th width="200px">日期</th>
			<th width="60px">操作</th>
		</tr>
		<%
			for (int i = 0; i < list.size(); i++) {
				blog = (Blog) list.get(i);
		%>
		<tr>
			<td><%=blog.getId()%></td>
			<td><%=blog.getTitle()%></td>
			<td>java web</td>
			<td><%=blog.getCreatedTime()%></td>
			<td align="center"><a
				href="/blog/servlet/PreEditBlogServlet?id=<%=blog.getId()%> "><img
					src="/blog/admin/images/edit.gif" border="0" alt="编辑"></img></a><a
				href="/blog/servlet/DeleteBlogServlet?id=<%=blog.getId()%>"
				onclick="javascript:return dele()"><img
					src="/blog/admin/images/delete.gif" border="0" alt="删除"></img></a></td>
		</tr>
		<%
			}
		%>
	</table>
</div>
</body>
</html>

