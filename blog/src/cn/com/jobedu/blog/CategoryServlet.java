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

public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 定义MySQL的数据库驱动程序
	public static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	// 定义MySQL数据库的连接地址
	public static final String DBURL = "jdbc:mysql://localhost:3306/blogdatabase";
	// MySQL数据库的连接用户名
	public static final String DBUSER = "root";
	// MySQL数据库的连接密码
	public static final String DBPASS = "mysqladmin";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		if (method.equals("add")) {
			add(request, response);
		}
		else if (method.equals("list")) {
			list(request, response);
		}
		else if (method.equals("delete")) {
			delete(request, response);
		}
		else if(method.equals("edit"))
		{
			preEdit(request, response);
		}
		else if(method.equals("update"))
		{
			update(request, response);
		}
		else
		{
			list(request, response);
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		int level = Integer.parseInt(request.getParameter("level"));
		int result = 0;
		try {
			Connection conn = null; // 数据库连接
			PreparedStatement pstmt = null; // 定义数据库操作对象
			Class.forName(DBDRIVER); // 加载驱动程序
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			String sql = "insert into category (name,level) values (?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, level);
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String message = "";
		if (result == 1) {
			message = "添加博客分类成功";
		} else
			message = "添加博客分类失败";
		request.setAttribute("message", message);
		request.getRequestDispatcher("/admin/Result.jsp").forward(request, response);
	}

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> list = new ArrayList<Category>();
		try {
			Connection conn = null; // 定义数据库连接
			PreparedStatement pstmt = null; // 定义数据库操作对象
			Class.forName(DBDRIVER); // 加载驱动程序
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS); // 数据库连接
			String sql = "SELECT id,name,level FROM category order by level DESC,id DESC";
			pstmt = conn.prepareStatement(sql); // 预处理sql语句
			ResultSet rs = pstmt.executeQuery();
			// 用来计算从数据库中查询的条数
			int sqlnum = 0;
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int level = rs.getInt(3);
				Category category = new Category();
				category.setId(id);
				category.setName(name);
				category.setLevel(level);
				list.add(sqlnum, category);
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
		request.getRequestDispatcher("/admin/adminCategoryList.jsp").forward(request,
				response);
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
			String sql = "DELETE FROM category WHERE id=" + id;
			stmt.executeUpdate(sql); // 执行更新
			stmt.close(); // 关闭操作
			conn.close(); // 数据库关闭

		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/servlet/CategoryServlet?method=list")
				.forward(request, response);
	}

	public void preEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Category category=new Category();
		try {
			Connection conn = null; // 定义数据库连接
			PreparedStatement pstmt = null; // 定义数据库操作对象
			Class.forName(DBDRIVER); // 加载驱动程序
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS); // 数据库连接
			String sql = "SELECT name,level FROM category where id="
					+ id;
			pstmt = conn.prepareStatement(sql); // 预处理sql语句
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				category.setName(rs.getString(1));
				category.setLevel(rs.getInt(2));
			}
			//给category对象的id赋值
			category.setId(Integer.parseInt(id));
			rs.close();
			pstmt.close(); // 关闭操作
			conn.close(); // 数据库关闭
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		request.setAttribute("category", category);
		request.getRequestDispatcher("/admin/editCategory.jsp")
				.forward(request, response);
	}

	public void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		int level=Integer.parseInt(request.getParameter("level"));
		try {
			Connection conn = null; // 定义数据库连接
			PreparedStatement pstmt = null; // 定义数据库操作对象
			Class.forName(DBDRIVER); // 加载驱动程序
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS); // 数据库连接
			String sql = "update category set name=?,level=? where id=" + id;
			pstmt = conn.prepareStatement(sql); // 预处理sql语句
			pstmt.setString(1, name);
			pstmt.setInt(2, level);
            pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/servlet/CategoryServlet?method=list").forward(request,
				response);
	}
}
