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
import model.bean.Category;
import model.bean.Comment;
import model.dao.CatDao;
import model.dao.CommentDao;

/**
 * Servlet implementation class AdminIndexCommentCap1Controller
 */
public class AdminIndexCommentCap2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexCommentCap2Controller() {
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
		int id_cmt = 0;
		try
		{
			id_cmt = Integer.parseInt(request.getParameter("id_cmt"));
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		CommentDao cd = new CommentDao();
		Comment objCmt = cd.getItem(id_cmt);
		System.out.println(objCmt);
		request.setAttribute("objCmt", objCmt);
		ArrayList<Comment> listCmt2 = cd.getItemsType2(objCmt);
		request.setAttribute("listCmt2", listCmt2);
		System.out.println(listCmt2.size());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/comment/indexCmt2.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
