package cn.com.jobedu.blog;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	public static final String DBURL = "jdbc:mysql://localhost:3306/blogdatabase";
	public static final String DBUSER = "root";
	public static final String DBPASS = "mysqladmin";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		if (method == null) {
			method = "";
		}
		if (method.equals("login")) {
			login(request, response);
		}
		else if(method.equals("logout"))
		{
			logout(request, response);
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		List<User> list = new ArrayList<User>();
		User user = null;
		try {
			Connection conn = null; // 定义数据库连接
			PreparedStatement pstmt = null; // 定义数据库操作对象
			Class.forName(DBDRIVER); // 加载驱动程序
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS); // 数据库连接
			String sql = "SELECT id,username,password FROM user where username=? and password=?";
			pstmt = conn.prepareStatement(sql); // 预处理sql语句
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				User us = new User();
				us.setId(rs.getInt(1));
				us.setUsername(rs.getString(2));
				us.setPassword(rs.getString(3));
				list.add(us);
			}
			rs.close();
			pstmt.close(); // 关闭操作
			conn.close(); // 数据库关闭
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (list.size() > 0) {
			user = (User) list.get(0);
			// 用request对象取得session对象
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			request.getRequestDispatcher("/admin/admin.jsp").forward(request,
					response);
		} else {
			request.setAttribute("message", "用户名或者密码不正确！");
			request.getRequestDispatcher("/admin/login.jsp").forward(request,
					response);
		}
	}

	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取session对象
		HttpSession session =request.getSession();
		//使session对象失效
		session.invalidate();
		//是页面跳转到index.jsp页面，这里不需要传递参数可以直接使用response跳转
		response.sendRedirect("/blog");
	}
}
