package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.AuthLibrary;
import model.bean.Category;
import model.bean.Role;
import model.bean.TrangThai;
import model.bean.User;
import model.dao.CatDao;
import model.dao.RoleDao;
import model.dao.TrangThaiDao;
import model.dao.UserDao;

/**
 * Servlet implementation class AdminIndexCatController
 */
public class AdminIndexUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexUserController() {
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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		UserDao ud = new UserDao();
		ArrayList<User> listUser = ud.getItems();
		TrangThaiDao ttdao = new TrangThaiDao();
		ArrayList<TrangThai> listTT = ttdao.getItems();
		RoleDao rd = new RoleDao();
		ArrayList<Role> listRole = rd.getItems();
		request.setAttribute("listRole", listRole);
		request.setAttribute("listTT", listTT);
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user/indexUser.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int id_user = Integer.parseInt(request.getParameter("id_user"));
		int status = Integer.parseInt(request.getParameter("status"));
		int role = Integer.parseInt(request.getParameter("role"));
		UserDao ud = new UserDao();
		if(ud.editStatus(id_user,status,role)>0)
		{
			PrintWriter out = response.getWriter();
			out.print("<h5 style='color:green;font-style:italic'>Chuyển trạng thái thành công</h5>");
		}
		else
		{
			PrintWriter out = response.getWriter();
			out.print("<h5 style='color:red;font-style:italic'>Có lỗi, không chuyển trạng thái được</h5>");
		}
		
		
	}

}
