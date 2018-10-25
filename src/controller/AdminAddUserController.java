package controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import library.AuthLibrary;
import library.FileLibary;
import library.StringLibrary;
import model.bean.User;
import model.dao.UserDao;

/**
 * Servlet implementation class AdminAddCatController
 */
@MultipartConfig
public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthLibrary.checkLogin(request, response))
		{
			return;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user/addUser.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		System.out.println("email " + email);
		String username = request.getParameter("username");
		System.out.println("user " + username);
		String password_tmp = request.getParameter("password");
		System.out.println("password = " + password_tmp);
		String fullname = request.getParameter("fullname");
		String password = StringLibrary.md5(password_tmp);
		
		Part part = request.getPart("picture");
		Pattern pt = Pattern.compile("[a-z Á-Ỵá-ỵA-Z0-9]{3,20}");
		Pattern pt1 = Pattern.compile("[a-zÁ-Ỵá-ỵA-Z]{1,1}[a-z Á-Ỵá-ỵA-Z0-9]*@[a-zÁ-Ỵá-ỵA-Z]+.com");
		Matcher mc = pt.matcher(username);
		Matcher mc1 = pt.matcher(password_tmp);
		Matcher mc2 = pt.matcher(fullname);
		Matcher mc3 = pt1.matcher(email);
		if (!(mc.matches() && mc1.matches() && mc2.matches() && mc3.matches()))
		{
			response.sendRedirect(request.getContextPath()+"/admin/user/add?msg=5");
			return;
		}	
		String filename = FileLibary.getName(part);
		if(!filename.equals(""))
		{
			String []mang = filename.split("\\.");
			String typeFile = mang[mang.length-1];
			if(typeFile.equalsIgnoreCase("jpg")||typeFile.equalsIgnoreCase("jpge")||typeFile.equalsIgnoreCase("gif")||typeFile.equalsIgnoreCase("png")||typeFile.equalsIgnoreCase("bmp"))
			{
				Date now = new Date();
				String newfilename = mang[0] + now.getTime() + "." + mang[1];
				String dirPath = request.getServletContext().getRealPath("files");
				File file = new File(dirPath);
				if(!file.exists())
				{
					file.mkdirs();
				}
				String oldfilePath = dirPath + File.separator + filename;
				String filePath = dirPath + File.separator + newfilename;
				File oldFile = new File(oldfilePath);
				File newFile = new File(filePath);
				oldFile.renameTo(newFile);
				part.write(filePath);
				filename = newfilename;
			}
			else
			{
				filename ="";
			}
		}
		
		User user = new User(0, username, password, email, filename, fullname);
		UserDao userDao = new UserDao();
		if(userDao.checkUser(username)!=null || userDao.checkUserByMail(email) != null) {
			response.sendRedirect(request.getContextPath()+"/admin/user/add?msg=0");
			return;
		}else {
			if(userDao.addUser(user)>0) {
				response.sendRedirect(request.getContextPath()+"/admin/user?msg=1");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/user?msg=0");
				return;
			}
		}
	}
}
