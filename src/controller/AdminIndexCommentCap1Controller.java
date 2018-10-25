package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
public class AdminIndexCommentCap1Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexCommentCap1Controller() {
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
		CommentDao cd = new CommentDao();
		ArrayList<Comment> listCmt1 = cd.getItemsType1();
		request.setAttribute("listCmt1", listCmt1);
		HashMap<Comment, Integer> mapCmt = new HashMap<>();
		for (Comment comment : listCmt1) {
			mapCmt.put(comment, new Integer(cd.getCmt2Relative(comment.getId_cmt())));
		}
		request.setAttribute("mapCmt", mapCmt);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/comment/indexCmt1.jsp");
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
