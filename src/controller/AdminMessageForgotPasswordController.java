package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.AuthLibrary;
import model.bean.User;
import model.dao.MessageDao;
import model.dao.UserDao;

/**
 * Servlet implementation class AdminMessageForgotPasswordController
 */
public class AdminMessageForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMessageForgotPasswordController() {
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
		if(userLogin.getId_role() != 3)
		{
			response.sendRedirect(request.getContextPath() + "/admin/message?type=2");
			return;
		}
		int id_message = 0;
		try {
			id_message = Integer.parseInt(request.getParameter("id_message"));
		}
		catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/message?type=2");
			return;
		}
		
		MessageDao md = new MessageDao();
		if(md.getItem(id_message) == null)
		{
			response.sendRedirect(request.getContextPath() + "/admin/message?type=2");
			return;
		}
		
		String email = md.getItem(id_message).getEmail();
		md.setSeenById(id_message);
		
		UserDao ud = new UserDao();
		if(ud.getItemByEmail(email) != null)
		{
			int id_user = ud.getItemByEmail(email).getId_user();
			response.sendRedirect(request.getContextPath() + "/admin/user/edit?id_user=" + id_user);
			return;
		}
		else
		{
			response.sendRedirect(request.getContextPath() + "/admin/user/edit?id_user=0");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
