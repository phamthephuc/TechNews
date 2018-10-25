package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.AuthLibrary;
import model.bean.Message;
import model.dao.MessageDao;

/**
 * Servlet implementation class AdminIndexMessageController
 */
public class AdminIndexMessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexMessageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("html/text");
		if(!AuthLibrary.checkLogin(request, response))
		{
			return;
		}
		int type = 0;
		try
		{
			type = Integer.parseInt(request.getParameter("type"));
			if(type!= 1 && type != 2) throw new Exception();
		}
		catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin");
			return;
		}
		
		MessageDao md = new MessageDao();
		ArrayList<Message> listMessage = md.getSubcribe(type);
		
		request.setAttribute("type", type);
		request.setAttribute("listMessage", listMessage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/message.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Đã vô set");
		MessageDao md = new MessageDao();
		md.setSeenForSubcribe();
	}

}
