//接收用户传过来的博文的id，然后从数据库中取出该博文.并且把查询出的每条博文，都创建一个Blog实例，以调用Blog这个javaBean
package cn.com.jobedu.blog;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetBlogServlet extends HttpServlet {
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
		String id = request.getParameter("id");
		// System.out.println(id);测试
		try {
			Connection conn = null; // 定义数据库连接
			PreparedStatement pstmt = null; // 定义数据库操作对象
			Class.forName(DBDRIVER); // 加载驱动程序
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS); // 数据库连接
			String sql = "SELECT id,title,content,createdtime FROM blog where id="
					+ id;
			pstmt = conn.prepareStatement(sql); // 预处理sql语句
			ResultSet rs = pstmt.executeQuery();
			//每查询blog中的一条记录，就把它生成一个Blog对象
			Blog blog=new Blog();
			
			while (rs.next()) {
				blog.setId(rs.getInt(1));
				blog.setTitle(rs.getString(2));
				blog.setContent(rs.getString(3));
				blog.setCreatedTime(rs.getString(4));
			}
			System.out.println(blog.getId());
			System.out.println(blog.getContent());
			rs.close();
			pstmt.close(); // 关闭操作
			conn.close(); // 数据库关闭
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}