<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>login page</title>
</head>

<body>
	<p>用户登录</p>
	<%
		String message = (String) request.getAttribute("message");
		if (message != null) {
			out.print("<font color='red'>"+message+"</font>");
		}
	%>
	<form id="form1" name="form1" method="post" action="/blog/servlet/UserServlet">
	<input type="hidden" name="method" value="login" />
		<table width="331" height="84" border="1">
			<tr>
				<td>用户名：</td>
				<td><label> <input type="text" name="username"
						id="username" />
				</label></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><label> <input type="password" name="password"
						id="password" />
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
	<p>&nbsp;</p>
</body>
</html>
