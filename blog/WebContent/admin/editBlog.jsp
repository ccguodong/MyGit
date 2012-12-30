<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="cn.com.jobedu.blog.Blog" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>edit Blog content</title>
<script type="text/javascript" src="/blog/admin/ckeditor/ckeditor.js"></script>
</head>
<%
   Blog blog=(Blog)request.getAttribute("blog");
%>
<body>
<p>请您修改您的博文内容</p>
<p><a href="http://localhost:8080/blog/servlet/GetBlogListServlet">查看博客内容</a></p>
<form id="form1" name="form1" method="post" action="/blog/servlet/PostEditBlogServlet">
  <input type="hidden" name="id" value="<%=blog.getId()%>"/>
  <table width="629" border="0">
    <tr>
      <td width="76">主题：</td>
      <td width="543"><label>
        <input name="title" type="text" id="title" size="60" value="<%=blog.getTitle()%>"/>
      </label></td>
    </tr>
    <tr>
      <td>类别：</td>
      <td><label>
        <select name="category" id="category">
          <option value="2">心情故事</option>
          <option value="3">旅游故事</option>
        </select>
      </label></td>
    </tr>
    <tr>
      <td>内容：</td>
      <td><label>
        <textarea name="content" cols="60" rows="8" id="content"><%=blog.getContent() %></textarea>
        <script type="text/javascript">
                CKEDITOR.replace( 'content' );
        </script>
      </label></td>
    </tr>
    <tr>
      <td><label>
        <input type="reset" name="button" id="button" value="重置" />
      </label></td>
      <td><label>
        <input type="submit" name="button2" id="button2" value="提交" />
      </label></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </table>
</form>
<p>&nbsp; </p>
</body>
</html>
