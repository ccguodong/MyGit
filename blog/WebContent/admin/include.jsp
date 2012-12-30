<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.com.jobedu.blog.User"%>
<%
	User user = (User) session.getAttribute("user");
	if (user == null) {
		request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
	}
%>