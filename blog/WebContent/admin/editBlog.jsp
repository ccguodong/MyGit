<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="header.jsp"%>
<%@ page import="java.util.List"%>
<%@ page import="cn.com.jobedu.blog.Category"%>
<%@ page import="cn.com.jobedu.blog.Blog"%>
<%
	Blog blog = (Blog) request.getAttribute("blog");
	List categorys = (List) request.getAttribute("categorys");
%>
<h3>请您修改您的博文内容</h3>
<form id="form1" name="form1" method="post"
	action="/blog/servlet/PostEditBlogServlet">
	<input type="hidden" name="id" value="<%=blog.getId()%>" />
	<table width="629" border="0">
		<tr>
			<td width="76">主题：</td>
			<td width="543"><label> <input name="title" type="text"
					id="title" size="60" value="<%=blog.getTitle()%>" />
			</label></td>
		</tr>
		<tr>
			<td>类别：</td>
			<td><label> <select name="category" id="category">
						<%
							int oldcid = blog.getCategoryId();
							for (int i = 0; i < categorys.size(); i++) {
								Category category = (Category) categorys.get(i);
								if (category.getId().equals(oldcid)) {
						%>
						<option value="<%=category.getId()%>" selected><%=category.getName()%></option>
						<%
							} else {
						%>
						<option value="<%=category.getId()%>"><%=category.getName()%></option>
						<%
							}
							}
						%>
				</select>
			</label></td>
		</tr>
		<tr>
			<td>内容：</td>
			<td><label> <textarea name="content" cols="60" rows="8"
						id="content"><%=blog.getContent()%></textarea> <script
						type="text/javascript">
							CKEDITOR.replace('content');
						</script>
			</label></td>
		</tr>
		<tr>
			<td></td>
			<td align="right"><label> <input type="submit"
					name="button2" id="button2" value="提交" />
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
