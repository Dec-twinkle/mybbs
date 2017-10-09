package servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.Page;
import dao.ItemDao;
import dao.ItemDaoImpl;
import domain.Item;
import domain.User;

public class TiebaServlet extends HttpServlet {

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

		request.setCharacterEncoding("UTF-8");
		String pageNumberStr = request.getParameter("pageNumber");
		int pageNumber=-1;
		pageNumber = Integer.parseInt(pageNumberStr);
		System.out.println(pageNumber);
		ItemDao dao = new ItemDaoImpl(); 
		Page<Item> page = dao.getPage(pageNumber);
		pageNumber=page.getPageNo();
		page.setPageNo(page.getPageNo());
		int totalPageNumber = page.getTotalPageNumber();
		request.setAttribute("totalPageNumber", totalPageNumber);
		List<User> authors = dao.getAuthor(pageNumber);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("authorList", authors);
		request.setAttribute("itemPage", page);
		request.getRequestDispatcher("/pages/bootstrap/tieba.jsp").forward(request, response);
		
	}

}
