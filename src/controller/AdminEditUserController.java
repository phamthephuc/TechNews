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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import library.AuthLibrary;
import library.FileLibary;
import library.StringLibrary;
import model.bean.Category;
import model.bean.User;
import model.dao.CatDao;
import model.dao.UserDao;

/**
 * Servlet implementation class AdminEditCatController
 */
@MultipartConfig 
public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditUserController() {
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
		HttpSession session = request.getSession();
		User userLogin = (User)session.getAttribute("user");
		
		int id_user = 0;
		try
		{
			id_user = Integer.parseInt(request.getParameter("id_user"));
		}
		catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/admin/user?msg=6");
			return;
		}
		
		if(userLogin.getId_role() == 3 || userLogin.getId_user() == id_user)
		{
			User objUser = (new UserDao()).getItem(id_user);
			if(objUser != null) {
			if(objUser.getId_role() == 3 && userLogin.getId_user() != id_user)
			{
				response.sendRedirect(request.getContextPath()+"/admin/user?msg=4");
				return;
			}
			}
			request.setAttribute("objUser", objUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user/editUser.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			response.sendRedirect(request.getContextPath()+"/admin/user?msg=5");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		UserDao ud = new UserDao();
		int id_user = 0;
		User objUser = null;
		try
		{
			id_user = Integer.parseInt(request.getParameter("id_user"));
			objUser = ud.getItem(id_user);
		}
		catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/admin/user?msg=0");
			return;
		}
		
		
			String email = request.getParameter("email");
			String password_tmp = request.getParameter("password");
			String fullname = request.getParameter("fullname");
			String password = "";
			Pattern pt = Pattern.compile("[a-z Á-Ỵá-ỵA-Z0-9]{3,20}");
			Pattern pt1 = Pattern.compile("[a-zÁ-Ỵá-ỵA-Z]{1,1}[a-z Á-Ỵá-ỵA-Z0-9]*@[a-zÁ-Ỵá-ỵA-Z]+.com");
			Matcher mc1 = pt.matcher(password_tmp);
			Matcher mc2 = pt.matcher(fullname);
			Matcher mc3 = pt1.matcher(email);
			if(!password_tmp.equals(""))
			{
				if (!(mc1.matches() && mc2.matches() && mc3.matches()))
				{
					response.sendRedirect(request.getContextPath()+"/admin/user/edit?msg=5");
					return;
				}	
				password = StringLibrary.md5(password_tmp);
			}
			else
			{
				password = objUser.getPassword();
			}
			Part part = request.getPart("picture");
			
			
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
					if(!objUser.getPicture().equals(""))
					{
						String oldPicture =  dirPath + File.separator + objUser.getPicture();
						File oldF = new File(oldPicture);
						oldF.delete();
					}
					File oldFile = new File(oldfilePath);
					File newFile = new File(filePath);
					oldFile.renameTo(newFile);
					part.write(filePath);
					filename = newfilename;
				}
				else
				{
					filename = objUser.getPicture();
				}
			}
			else
			{
				filename = objUser.getPicture();
			}
			
			User user = new User(id_user, objUser.getUsername(), password, email, filename, fullname);
			UserDao userDao = new UserDao();
			
			if(userDao.editUser(user)>0) {
				response.sendRedirect(request.getContextPath()+"/admin/user?msg=2");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/user?msg=0");
				return;
			}
		}
		

}
