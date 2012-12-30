//根据adminBlogList.jsp中传过来的id，对其博文进行删除
package cn.com.jobedu.blog;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteBlogServlet extends HttpServlet {
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
			try {
				Connection conn = null; // 数据库连接
				Statement stmt = null; // 定义数据库操作对象
				Class.forName(DBDRIVER); // 加载驱动程序
				conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
				stmt = conn.createStatement(); // 创建数据库接口对象
				String sql = "DELETE FROM blog WHERE id=" + id;
				stmt.executeUpdate(sql); // 执行更新
				stmt.close(); // 关闭操作
				conn.close(); // 数据库关闭

			} catch (SQLException e) {
				System.out.println(e);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("/servlet/AdminBlogListServlet")
					.forward(request, response);
		}
	}
}
