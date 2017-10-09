package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDao;
import dao.ItemDaoImpl;
import dao.ReplyDao;
import dao.ReplyDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import domain.Item;
import domain.Reply;
import domain.User;

public class ReplyServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		System.out.println(methodName);
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	ReplyDao replyDao = new ReplyDaoImpl();
	ItemDao itemDao = new ItemDaoImpl();
	UserDao userDao = new UserDaoImpl();
	public void browse(HttpServletRequest request, HttpServletResponse response)
	{
		String itemIdStr=request.getParameter("itemId");
		int itemId=Integer.parseInt(itemIdStr);
		List<Reply> replys = replyDao.getReplyList(itemId);
		Item item = itemDao.getItem(itemId);
		String itemAuthor = userDao.getUser(item.getAuthorId()).getName();
		List<User> replyAuthors=replyDao.getAuthor(itemId);
		request.setAttribute("replys", replys);
		request.setAttribute("item", item);
		request.setAttribute("itemAuthor", itemAuthor);
		request.setAttribute("replyAuthors", replyAuthors);
		
		try {
			request.getRequestDispatcher("/pages/bootstrap/reply.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void comment(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println(2);
		String itemIdstr =request.getParameter("itemId");
		System.out.println(itemIdstr);
		int itemId = Integer.parseInt(itemIdstr);
		User user =  (User) request.getSession().getAttribute("user");
		System.out.println(user.getName());
		int authorId=user.getUserid();
		String cont = request.getParameter("cont");
		System.out.println(cont);
		Reply reply = new Reply(0, authorId, itemId, cont);
		replyDao.createReply(reply);
		try {
			request.getRequestDispatcher("/pages/bootstrap/replyservlet?method=browse&itemId="+itemId).forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
