<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="header.jsp"%>
<link rel="stylesheet" href="/blog/admin/style/style.css"
	type="text/css"></link>
<h3 align="center">修改密码</h3>
<form id="form1" name="form1" method="post" action="/blog/servlet/UserServlet">
<input type="hidden" name="method" value="changepassword" />
  <table class="tab" border="0" align="center">
    <tr>
      <td width="80">原密码：</td>
      <td width="150"><label>
        <input name="oldPassword" type="password" id="oldPassword" size="20" />
      </label></td>
    </tr>
    <tr>
      <td>新密码：</td>
      <td><label>
        <input name="newPassword" type="password" id="newPassword" size="20" />
      </label></td>
    </tr>
    <tr>
      <td>确认新密码：</td>
      <td><label>
        <input name="reNewPassword" type="password" id="reNewPassword" size="20" />
      </label></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><label>
        <input type="submit" name="button" id="button" value="提交" />
      </label></td>
    </tr>
  </table>
</form>
</div>
</body>
</html>