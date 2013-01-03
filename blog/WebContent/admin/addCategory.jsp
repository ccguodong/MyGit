<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="header.jsp"%>
<title>添加博客分类信息</title>
<p>&nbsp;</p>
<h3 align="center">输入博客的分类</h3>
<form id="form1" name="form1" method="post"
	action="/blog/servlet/CategoryServlet">
	<input type="hidden" name="method" id="method" value="add" />
	<table width="336" border="0" align="center" class="title">
		<tr>
			<td>分类的名称：</td>
			<td><label> <input type="text" name="name" id="name" />
			</label></td>
		</tr>
		<tr>
			<td>分类的显示顺序：</td>
			<td><label> <input name="level" type="text" id="level"
					size="2" maxlength="2" />
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
