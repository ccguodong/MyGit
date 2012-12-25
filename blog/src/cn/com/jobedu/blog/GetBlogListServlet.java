//查询出数据库中的全部博文，并把数据返回给一个jsp页面，使其以列表的形式把博文全部显示出
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

public class GetBlogListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 定义MySQL的数据库驱动程序
	public static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	// 定义MySQL数据库的连接地址
	public static final String DBURL = "jdbc:mysql://localhost:3306/blogdatabase";
	// MySQL数据库的连接用户名
	public static final String DBUSER = "root";
	// MySQL数据库的连接密码
	public static final String DBPASS = "mysqladmin";
	//声明一个List对象，以存放从数据库中的结果
	List<Blog> list=new ArrayList<Blog>();
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = null; // 定义数据库连接
			PreparedStatement pstmt = null; // 定义数据库操作对象
			Class.forName(DBDRIVER); // 加载驱动程序
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS); // 数据库连接
			//查询blog表中的全部数据并按降序排列
			String sql = "SELECT id,title,content,createdtime FROM blog order by id desc";
			pstmt = conn.prepareStatement(sql); // 预处理sql语句
			ResultSet rs = pstmt.executeQuery();
			//用来计算从数据库中查询的条数
			int sqlnum=0;
			//把从数据库中查询的每条语句实例为一个Blog对象，然后添加到list中
			while (rs.next()) {
				int id=rs.getInt(1);
				String title=rs.getString(2);
				String content=rs.getString(3);
				String createdtime=rs.getString(4);
				Blog blog=new Blog();
				blog.setId(id);
				blog.setTitle(title);
				blog.setContent(content);
				blog.setCreatedTime(createdtime);
				list.add(sqlnum, blog);
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
        request.getRequestDispatcher("/displayBlogList.jsp").forward(request, response);
	}

}
