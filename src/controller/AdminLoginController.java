package controller;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.StringLibrary;
import model.bean.User;
import model.dao.UserDao;

/**
 * Servlet implementation class AdminLoginController
 */
public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null )
		{
			response.sendRedirect(request.getContextPath()+"/admin");
			return;
		}
		else
		{
			RequestDispatcher rd = request.getRequestDispatcher("/admin/login.jsp");
			rd.forward(request, response);
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserDao ud = new UserDao();
		User objUser = ud.checkUser(name);
		if(objUser != null)
		{
			if(objUser.getId_role()>=2)
			{   
				Date date1 = objUser.getDate_lock();
				long miligiay1 = date1.getTime();
				long hour1 = TimeUnit.HOURS.convert(miligiay1, TimeUnit.MILLISECONDS);
				Date date2 = new Date();
				long miligiay2 = date2.getTime();
				long hour2 = TimeUnit.HOURS.convert(miligiay2, TimeUnit.MILLISECONDS);
				if(objUser.getStatus() == 1 || ((hour2 - hour1) >= 2 && objUser.getStatus() == 2) )
				{
					
					if(StringLibrary.md5(password).equals(objUser.getPassword()))
					{
						Cookie user = new Cookie("user", name);
						Cookie pass = new Cookie("pass", password);
						if (request.getParameter("check") != null) {
						   user.setMaxAge(60 * 60 * 24);
						    pass.setMaxAge(60 * 60 * 24);
						 } else {
						     user.setMaxAge(0);
						     pass.setMaxAge(0);
						 }
						response.addCookie(user);
						response.addCookie(pass);
						ud.setActive(objUser);
						HttpSession session = request.getSession();
						session.setAttribute("user", objUser);
						response.sendRedirect(request.getContextPath()+"/admin");
						return;
					}
					else
					{
						System.out.println("theem looi");
						ud.addFault(objUser);
						if(objUser.getFault() > 4)
						{
							ud.setUnActive(objUser);
						}
						request.setAttribute("msg", "Mật khẩu cho tên đăng nhập này không đúng");
						RequestDispatcher rd = request.getRequestDispatcher("/admin/login.jsp");
						rd.forward(request, response);
						return;
					}
				}
				else
				if(objUser.getStatus() == 3)
				{
					request.setAttribute("msg", "Tài khoản hiện tại của bạn đang bị khóa vĩnh viễn");
					RequestDispatcher rd = request.getRequestDispatcher("/admin/login.jsp");
					rd.forward(request, response);
					return;
				}
				else
				{
					request.setAttribute("msg", "Tài khoản hiện tại của bạn đang bị khóa. Vui lòng đăng nhập sau " + (2-hour2 + hour1) + " tiếng sau");
					RequestDispatcher rd = request.getRequestDispatcher("/admin/login.jsp");
					rd.forward(request, response);
					return;
				}
				
			}
			else
			{
				request.setAttribute("msg", "Đọc giả không có quyền đăng nhập vào trang này");
				RequestDispatcher rd = request.getRequestDispatcher("/admin/login.jsp");
				rd.forward(request, response);
				return;
			}
		}
		
		request.setAttribute("msg", "Tên đăng nhập không đúng");
		RequestDispatcher rd = request.getRequestDispatcher("/admin/login.jsp");
		rd.forward(request, response);
		return;

	}

}
