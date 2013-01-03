<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="header.jsp" %>
<%@ page import="cn.com.jobedu.blog.Category" %>
<title>修改博客分类</title>
<script type="text/javascript" src="/blog/ckeditor/ckeditor.js"></script>
<%
   Category category=(Category)request.getAttribute("category");
%>
<p>&nbsp; </p>
<h3 align="center">请您修改博客分类</h3>
<form id="form1" name="form1" method="post" action="/blog/servlet/CategoryServlet">
<input type="hidden" name="method" id="method" value="update" />
<input type="hidden" name="id" id="id" value="<%=category.getId() %>" />
  <table width="336" border="0" class="title" align="center">
    <tr>
      <td>分类的名称：</td>
      <td><label>
        <input type="text" name="name" id="name" value="<%=category.getName() %>" />
      </label></td>
    </tr>
    <tr>
      <td>分类的显示顺序</td>
      <td><label>
        <input name="level" type="text" id="level" size="2" maxlength="2" value="<%=category.getLevel() %>" />
      </label></td>
    </tr>
    <tr>
      <td><label>
        <input type="submit" name="button" id="button" value="提交" />
      </label></td>
      <td>&nbsp;</td>
    </tr>
  </table>
</form>
</div>
</body>
</html>
