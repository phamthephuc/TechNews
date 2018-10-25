package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDao;

/**
 * Servlet implementation class AdminIndexCatController
 */
public class AdminDelUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null )
		{
			response.sendRedirect(request.getContextPath()+"/admin/login");
			return;
		}
		else
		{
			User userLogin = (User)session.getAttribute("user");
			int idUser = 0;
			try
			{
				idUser = Integer.parseInt(request.getParameter("id_user"));
			}
			catch (Exception e) {
				response.sendRedirect(request.getContextPath()+"/admin/user");
				return;
			}
			UserDao userDao = new UserDao();
			User objUser = userDao.getItem(idUser);
			if(objUser.getId_role() == 3){
				response.sendRedirect(request.getContextPath()+"/admin/user?msg=4");
				return;
			}else {
				if(userLogin.getId_role() == 3)
				{
					if(userDao.delItem(idUser)>0) {
						response.sendRedirect(request.getContextPath()+"/admin/user?msg=3");
						return;
					}else {
						response.sendRedirect(request.getContextPath()+"/admin/user?msg=0");
						return;
					}
				}
				else
				{
					response.sendRedirect(request.getContextPath()+"/admin/user?msg=5");
					return;
				}
			}
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
