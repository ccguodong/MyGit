package cn.com.jobedu.blog;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		request.setCharacterEncoding("UTF-8");
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("/blog/admin/login.jsp");
		} else {
			request.setCharacterEncoding("UTF-8");
			String method = request.getParameter("method");
			if (method.equals("add")) {
				add(request, response);
			} else if (method.equals("list")) {
				list(request, response);
			} else if (method.equals("delete")) {
				delete(request, response);
			} else if (method.equals("edit")) {
				preEdit(request, response);
			} else if (method.equals("update")) {
				upDate(request, response);
			}
		}
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

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Comment> list = new ArrayList<Comment>();
		try {
			Connection conn = null; // 定义数据库连接
			PreparedStatement pstmt = null; // 定义数据库操作对象
			Class.forName(DBDRIVER); // 加载驱动程序
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS); // 数据库连接
			String sql = "SELECT id,name,content,createdtime FROM comment order by id desc";
			pstmt = conn.prepareStatement(sql); // 预处理sql语句
			ResultSet rs = pstmt.executeQuery();
			// 用来计算从数据库中查询的条数
			int sqlnum = 0;
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setId(rs.getInt(1));
				comment.setName(rs.getString(2));
				comment.setContent(rs.getString(3));
				comment.setCreatedtime(rs.getTimestamp(4));
				list.add(sqlnum, comment);
				sqlnum++;
			}
			rs.close();
			pstmt.close(); // 关闭操作
			conn.close(); // 数据库关闭
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		request.setAttribute("list", list);
		request.getRequestDispatcher("/admin/adminCommentList.jsp").forward(
				request, response);
	}

	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			Connection conn = null; // 数据库连接
			Statement stmt = null; // 定义数据库操作对象
			Class.forName(DBDRIVER); // 加载驱动程序
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			stmt = conn.createStatement(); // 创建数据库接口对象
			String sql = "DELETE FROM comment WHERE id=" + id;
			stmt.executeUpdate(sql); // 执行更新
			stmt.close(); // 关闭操作
			conn.close(); // 数据库关闭

		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/servlet/CommentServlet?method=list")
				.forward(request, response);
	}

	public void preEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Comment comment = new Comment();
		try {
			Connection conn = null; // 定义数据库连接
			PreparedStatement pstmt = null; // 定义数据库操作对象
			Class.forName(DBDRIVER); // 加载驱动程序
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS); // 数据库连接
			String sql = "SELECT name,content FROM comment where id=" + id;
			pstmt = conn.prepareStatement(sql); // 预处理sql语句
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				comment.setName(rs.getString(1));
				comment.setContent(rs.getString(2));
			}
			comment.setId(Integer.parseInt(id));
			rs.close();
			pstmt.close(); // 关闭操作
			conn.close(); // 数据库关闭
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		request.setAttribute("comment", comment);
		request.getRequestDispatcher("/admin/editComment.jsp").forward(request,
				response);
	}

	public void upDate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		if (name == null || name.equals(""))
			name = "匿名";
		try {
			Connection conn = null; // 定义数据库连接
			PreparedStatement pstmt = null; // 定义数据库操作对象
			Class.forName(DBDRIVER); // 加载驱动程序
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS); // 数据库连接
			String sql = "update comment set name=?,content=? where id=" + id;
			pstmt = conn.prepareStatement(sql); // 预处理sql语句
			pstmt.setString(1, name);
			pstmt.setString(2, content);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/servlet/CommentServlet?method=list")
				.forward(request, response);
	}
}
