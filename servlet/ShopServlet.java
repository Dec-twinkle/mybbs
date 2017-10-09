package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import web.Page;
import dao.ShopDao;
import dao.ShopDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import domain.User;
import domain.shop;

public class ShopServlet extends HttpServlet {

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
	public void listPerson(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		List<shop> shops = shopDao.getPersonShopList(user.getUserid());
		request.setAttribute("shops", shops);
		request.getRequestDispatcher("/pages/bootstrap/shoplist.jsp").forward(request, response);
	}
	private ShopDao shopDao = new ShopDaoImpl();
	public void listAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageStr = request.getParameter("pageNumber");
		int pageNumber = Integer.parseInt(pageStr);
		System.out.println(pageNumber);
		Page<shop> shoppage = shopDao.getShopPage(pageNumber);
		shoppage.setPageSize(9);
		pageNumber=shoppage.getPageNo();
		System.out.println(pageNumber);
		shoppage.setPageNo(shoppage.getPageNo());
		System.out.println(shoppage.getTotalPageNumber());
		List<User> shoppers = shopDao.getShopper(pageNumber);
		request.setAttribute("page", shoppage);
		request.setAttribute("shoppers", shoppers);
		request.setAttribute("totalPageNumber", shoppage.getTotalPageNumber());
		request.setAttribute("pageNumber", pageNumber);
		request.getRequestDispatcher("/pages/bootstrap/shop.jsp").forward(request, response);
	}
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idstr = request.getParameter("shopid");
		int shopid=Integer.parseInt(idstr);
		shop Shop = shopDao.getShop(shopid);
		String filepath="/pages/bootstrap/shop";
		String fileName = getServletContext().getRealPath(filepath)+"\\"+Shop.getShopid()+".jpg";
		File pic = new File(fileName);
		pic.delete();
		shopDao.deleteShop(shopid);
		request.getRequestDispatcher("/pages/bootstrap/shopservlet?method=listPerson").forward(request, response);;
	}
	private shop Shop;
	public void create1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String name = request.getParameter("name");
		String prizeStr = request.getParameter("prize");
		int prize = Integer.parseInt(prizeStr);
		Random random = new Random();
		String picture ="shop/"+System.currentTimeMillis() + random.nextInt(100000)+".jpg";
		Shop = new shop(0,user.getUserid(),prize,name,picture);
		request.getRequestDispatcher("/pages/bootstrap/shopcreate2.jsp").forward(request, response);
	}
	public void create2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		shopDao.createShop(Shop);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 500);
		File tempDirectory = new File("d:\\tempDirectory");
		factory.setRepository(tempDirectory);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		upload.setSizeMax(1024 * 1024 *1024 *5);
		try {
			List<FileItem> /* FileItem */items = upload.parseRequest(request);

			// 2. 遍历 items:
			for (FileItem item : items) {
				// 若是一个一般的表单域, 打印信息
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString();

					System.out.println(name + ": " + value);
				}
				// 若是文件域则把文件保存到 d:\\files 目录下.
				else {
					String fieldName = item.getFieldName();
					String fileName = item.getName();
					String contentType = item.getContentType();
					long sizeInBytes = item.getSize();
					User user = (User) request.getSession().getAttribute("user");
					System.out.println(fieldName);
					System.out.println(fileName);
					System.out.println(contentType);
					System.out.println(sizeInBytes);

					InputStream in = item.getInputStream();
					byte[] buffer = new byte[1024];
					int len = 0;
					String filepath="/pages/bootstrap";
					fileName = getServletContext().getRealPath(filepath)+"\\"+Shop.getPicture();
					
					System.out.println(fileName);

					OutputStream out = new FileOutputStream(fileName);

					while ((len = in.read(buffer)) != -1) {
						out.write(buffer, 0, len);
					}

					out.close();
					in.close();
					request.getRequestDispatcher("/pages/bootstrap/shopservlet?method=listAll&pageNumber=1").forward(request, response);
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		}

	}
}
