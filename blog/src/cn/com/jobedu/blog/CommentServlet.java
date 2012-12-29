package cn.com.jobedu.blog;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 定义MySQL的数据库驱动程序
	public static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	// 定义MySQL数据库的连接地址
	public static final String DBURL = "jdbc:mysql://localhost:3306/blogdatabase";
	// MySQL数据库的连接用户名
	public static final String DBUSER = "root";
	// MySQL数据库的连接密码
	public static final String DBPASS = "mysqladmin";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		if (method.equals("add"))
			add(request, response);
	}

	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String id = request.getParameter("blog_id");
		int blog_id = Integer.parseInt(id);
		if (name == null || name.equals(""))
			name = "匿名";
		try {
			Connection conn = null; // 数据库连接
			PreparedStatement pstmt = null; // 定义数据库操作对象
			Class.forName(DBDRIVER); // 加载驱动程序
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			String sql = "insert into `comment` (blog_id,name,content,createdtime) values (?,?,?,now() )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, blog_id);
			pstmt.setString(2, name);
			pstmt.setString(3, content);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 注意这里用request的跳转方法不行
		response.sendRedirect("/blog/servlet/GetBlogServlet?id=" + blog_id);
	}
}
