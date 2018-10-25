package controller;

import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.SendMail;
import library.StringLibrary;
import model.bean.Message;
import model.bean.User;
import model.dao.MessageDao;
import model.dao.UserDao;

/**
 * Servlet implementation class AdminForgotPasswordController
 */
public class AdminForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminForgotPasswordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/forgot-password.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Pattern pt1 = Pattern.compile("[a-zÁ-Ỵá-ỵA-Z]{1,1}[a-z Á-Ỵá-ỵA-Z0-9-_]*@[a-zÁ-Ỵá-ỵA-Z]+.com");
		String email = request.getParameter("email");
		Matcher mc = pt1.matcher(email);
		if(mc.matches())
		{
			MessageDao md = new MessageDao();
			UserDao ud = new UserDao();
			if(ud.getItemByEmail(email)!= null)
			{
				User objUser = ud.getItemByEmail(email);
				if(objUser.getId_role() != 3) {
				Random rnd = new Random();
				String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				 StringBuilder newPassWord = new StringBuilder(12);
			       for( int i = 0; i < 12; i++ ) 
			    	   newPassWord.append( AB.charAt( rnd.nextInt(AB.length()) ) );
			     ud.setPassword(StringLibrary.md5(newPassWord.toString()),objUser.getId_user());
			     SendMail.send(email, "RESET YOUR PASSWORD", "YOUR NEWS PASSWORD : " + newPassWord, "phucpham9649@gmail.com", "phamthephuc");
				}
			}
			if(md.getItemForgotByEmail(email) != null) 
			{
				
				response.sendRedirect(request.getContextPath()+"/admin/forgot?msg=1");
				return;
			}
			if(md.addItem(email, 2)>0) {
				response.sendRedirect(request.getContextPath()+"/admin/forgot?msg=1");
				return;
			}
			else
			{
				response.sendRedirect(request.getContextPath()+"/admin/forgot?msg=2");
				return;
			}
			
		}
		else
		{
			response.sendRedirect(request.getContextPath()+"/admin/forgot?msg=0");
			return;
		}
	}

}
