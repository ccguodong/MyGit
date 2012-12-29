<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="cn.com.jobedu.blog.Blog"%>
<%@ page import="cn.com.jobedu.blog.Comment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>显示博文</title>
</head>

<body>
	<%
		Blog blog = (Blog) request.getAttribute("blog");
		List list = (List) request.getAttribute("list");
	%>
	<table width="781" height="404" border="0" align="center">
		<tr>
			<td height="35"><div align="center"><%=blog.getTitle()%></div></td>
		</tr>
		<tr>
			<td height="221"><%=blog.getContent()%></td>
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
			   for(int i=0;i<list.size();i++)
			   {
				   Comment comment=(Comment)list.get(i);
			%>
			<table width="675" border="1">
					<tr>
						<td><%=comment.getName() %>的评论</td>
					</tr>
					<tr>
						<td>评论内容：<%=comment.getContent() %></td>
					</tr>
					<tr>
						<td>评论时间：<%=comment.getCreatedtime() %></td>
					</tr>
				</table> <br /> <br />
				<%
				}
				%>
				<p>&nbsp;</p></td>
		</tr>
		<tr>
			<td><form id="form1" name="form1" method="post"
					action="/blog/servlet/CommentServlet">
					<input type="hidden" name="method" value="add"></input> <input
						type="hidden" name="blog_id" value=<%=blog.getId()%>></input>
					<table width="650" border="1">
						<tr>
							<td width="150">评论人：</td>
							<td width="500"><label> <input name="name"
									type="text" id="name" size="20" />
							</label></td>
						</tr>
						<tr>
							<td>内容：</td>
							<td><label> <textarea name="content" cols="60"
										rows="10" id="content"></textarea>
							</label></td>
						</tr>
						<tr>
							<td><label> <input type="submit" name="button"
									id="button" value="提交" />
							</label></td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</form></td>
		</tr>
	</table>
</body>
</html>
