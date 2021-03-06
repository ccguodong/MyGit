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

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		if (method == null) {
			main(request, response);
			request.getRequestDispatcher("/main.jsp")
					.forward(request, response);
		}else if(method.equals("get"))
		{
			main(request, response);
			get(request, response);
			request.getRequestDispatcher("/displayBlog.jsp").forward(request, response);
		}
	}

	protected void main(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid=request.getParameter("cid");
		String blogsql=null;
		//如果cid为空则查询全部的blog，如果cid不为空查询指定category_id的博客
		if(cid==null)
		{
			blogsql="SELECT b.id as id,title,content,createdtime,name as category,c.id as categoryId from blog b,category c WHERE category_id=c.id ORDER BY b.id DESC";
		}
		else
		{
			blogsql="SELECT b.id as id,title,content,createdtime,name as category,c.id as categoryId from blog b,category c WHERE category_id=c.id and category_id="+cid+" ORDER BY b.id DESC";
		}
		// 声明一个List对象，以存放从数据库中的结果。特别注意！！此变量要声明为局部变量
		List<Blog> bloglist = new ArrayList<Blog>();
		try {
			Connection conn = null; // 定义数据库连接
			PreparedStatement pstmt = null; // 定义数据库操作对象
			Class.forName(DBDRIVER); // 加载驱动程序
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS); // 数据库连接
			// 查询blog表中的全部数据并按降序排列
			pstmt = conn.prepareStatement(blogsql); // 预处理sql语句
			ResultSet rs = pstmt.executeQuery();
			// 用来计算从数据库中查询的条数
			int sqlnum = 0;
			// 把从数据库中查询的每条语句实例为一个Blog对象，然后添加到list中
			while (rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String createdtime = rs.getString(4);
				String category = rs.getString(5);
				int categoryId = rs.getInt(6);
				Blog blog = new Blog();
				blog.setId(id);
				blog.setTitle(title);
				blog.setContent(content);
				blog.setCreatedTime(createdtime);
				blog.setCategory(category);
				blog.setCategoryId(categoryId);
				bloglist.add(sqlnum, blog);
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

		List<Category> categorylist = new ArrayList<Category>();
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			Class.forName(DBDRIVER);
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS); // 数据库连接
			String sql = "SELECT id,name FROM category order by level desc,id desc";
			pstmt = conn.prepareStatement(sql); // 预处理sql语句
			ResultSet rs = pstmt.executeQuery();
			// 用来计算从数据库中查询的条数
			int sqlnum = 0;
			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt(1));
				category.setName(rs.getString(2));
				categorylist.add(sqlnum, category);
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

		List<Comment> commentlist = new ArrayList<Comment>();
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			Class.forName(DBDRIVER);
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS); // 数据库连接
			String sql = "SELECT id,name,content,blog_id FROM comment order by id desc limit 0,2";
			pstmt = conn.prepareStatement(sql); // 预处理sql语句
			ResultSet rs = pstmt.executeQuery();
			// 用来计算从数据库中查询的条数
			int sqlnum = 0;
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setId(rs.getInt(1));
				comment.setName(rs.getString(2));
				comment.setContent(rs.getString(3));
				comment.setBlogId(rs.getInt(4));
				commentlist.add(sqlnum, comment);
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
		request.setAttribute("bloglist", bloglist);
		request.setAttribute("categorylist", categorylist);
		request.setAttribute("commentlist", commentlist);
	}
	
	protected void get(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//此时的id是取得链接上的id
		String id = request.getParameter("id");
		// System.out.println(id);测试
		//每查询blog中的一条记录，就把它生成一个Blog对象
		Blog blog=new Blog();
		try {
			Connection conn = null; // 定义数据库连接
			PreparedStatement pstmt = null; // 定义数据库操作对象
			Class.forName(DBDRIVER); // 加载驱动程序
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS); // 数据库连接
			String sql = "SELECT id,title,content,createdtime FROM blog where id="
					+ id;
			pstmt = conn.prepareStatement(sql); // 预处理sql语句
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				blog.setId(rs.getInt(1));
				blog.setTitle(rs.getString(2));
				blog.setContent(rs.getString(3));
				blog.setCreatedTime(rs.getString(4));
			}
			rs.close();
			pstmt.close(); // 关闭操作
			conn.close(); // 数据库关闭
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		List<Comment> list=new ArrayList<Comment>();
		int sqlnum=0;
		try {
			Connection conn = null; // 定义数据库连接
			PreparedStatement pstmt = null; // 定义数据库操作对象
			Class.forName(DBDRIVER); // 加载驱动程序
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS); // 数据库连接
			String sql = "SELECT id,name,content,createdtime FROM comment where blog_id= "
					+ id+" order by id desc";
			pstmt = conn.prepareStatement(sql); // 预处理sql语句
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Comment comment=new Comment();
				comment.setId(rs.getInt(1));
				comment.setName(rs.getString(2));
				comment.setContent(rs.getString(3));
				comment.setCreatedtime(rs.getTimestamp(4));
				list.add(sqlnum, comment);
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
		request.setAttribute("list", list);
	}

}
