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

public class PreEditBlogServlet extends HttpServlet {
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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("/blog/admin/login.jsp");
		} else {
			String id = request.getParameter("id");
			// 每查询blog中的一条记录，就把它生成一个Blog对象
			Blog blog = new Blog();
			try {
				Connection conn = null; // 定义数据库连接
				PreparedStatement pstmt = null; // 定义数据库操作对象
				Class.forName(DBDRIVER); // 加载驱动程序
				conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS); // 数据库连接
				String sql = "SELECT id,title,content,createdtime,category_id FROM blog where id="
						+ id;
				pstmt = conn.prepareStatement(sql); // 预处理sql语句
				ResultSet rs = pstmt.executeQuery();
				// 为blog对象的属性赋值，所赋的值为数据库中的内容
				while (rs.next()) {
					blog.setId(rs.getInt(1));
					blog.setTitle(rs.getString(2));
					blog.setContent(rs.getString(3));
					blog.setCreatedTime(rs.getString(4));
					blog.setCategoryId(rs.getInt(5));
				}
				rs.close();
				pstmt.close(); // 关闭操作
				conn.close(); // 数据库关闭
			} catch (SQLException e) {
				System.out.println(e);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
						
			List<Category> categorys = new ArrayList<Category>();
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
					int cid = rs.getInt(1);
					String name = rs.getString(2);
					int level = rs.getInt(3);
					Category category = new Category();
					category.setId(cid);
					category.setName(name);
					category.setLevel(level);
					categorys.add(sqlnum, category);
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
			
			request.setAttribute("blog", blog);
			request.setAttribute("categorys", categorys);
			request.getRequestDispatcher("/admin/editBlog.jsp").forward(
					request, response);
		}
	}
}