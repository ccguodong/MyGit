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

public class PreAddBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	public static final String DBURL = "jdbc:mysql://localhost:3306/blogdatabase";
	public static final String DBUSER = "root";
	public static final String DBPASS = "mysqladmin";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		list(request, response);
	}

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 对session进行验证，以实现只有管理员才能发博客
		// 首先获取session对象
		HttpSession session = request.getSession();
		// 获取用session传过来的user对象
		User user = (User) session.getAttribute("user");
		// 如果user是空，即不是管理员登录，使其转到登录页面
		if (user == null) {
			response.sendRedirect("/blog/admin/login.jsp");
		} else {
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
			request.getRequestDispatcher("/admin/addBlog.jsp").forward(request,
					response);
		}
	}
}
