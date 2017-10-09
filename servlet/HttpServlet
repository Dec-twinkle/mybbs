package servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChatDao;
import dao.ChatDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import domain.Chat;
import domain.User;

public class ChatServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

		doPost(request, response);
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
	UserDao userDao = new UserDaoImpl();
	ChatDao chatDao = new ChatDaoImpl();
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			User user = (User) request.getSession().getAttribute("user");
			String user2Str = request.getParameter("user2id");
			
			int user2id = Integer.parseInt(user2Str);
			User user2 = userDao.getUser(user2id);
			request.setAttribute("user2", user2);
			List<Chat> chats = chatDao.getChatList(user.getUserid(), user2.getUserid());
			
			request.setAttribute("chats", chats);
			request.getRequestDispatcher("chat.jsp").forward(request, response);
	}
	public void send(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			User user = (User) request.getSession().getAttribute("user");
			System.out.println(user);
			String user2Str = request.getParameter("user2id");
			int user2id = Integer.parseInt(user2Str);
			String cont = request.getParameter("cont");
			Chat chat = new Chat(0, user.getUserid(), user2id, cont);
			System.out.println(chat);
			chatDao.createChat(chat);
			//list(request,response);
			request.getRequestDispatcher("/pages/bootstrap/chatservlet?method=list&user2id="+user2id).forward(request, response);
	}
	public void chatlist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			User user = (User) request.getSession().getAttribute("user");
			List<Integer> authors=chatDao.getChatAuthorList(user.getUserid());
			int userid=user.getUserid();
			List<Chat> chats = new ArrayList<>();
			List<User> friends = new ArrayList<>();
			for(int id:authors)
			{
				Chat chat = chatDao.getLastChat(id, userid);
				User friend = userDao.getUser(id);
				friends.add(friend);
				chats.add(chat);
			}
			request.setAttribute("friends", friends);
			request.setAttribute("chats", chats);
			request.getRequestDispatcher("/pages/bootstrap/chatlist.jsp").forward(request, response);;
			
			
	}
}
