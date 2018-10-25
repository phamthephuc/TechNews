package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.AuthLibrary;
import model.bean.Comment;
import model.bean.News;
import model.bean.User;
import model.dao.CommentDao;
import model.dao.MessageDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class AdminIndexController
 */
@WebServlet("/AdminIndexController")
public class AdminIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexController() {
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
		CommentDao cmd = new CommentDao();
		NewsDao nd = new NewsDao();
		MessageDao md = new MessageDao();
		
		HttpSession session = request.getSession();
		User objUser = (User)session.getAttribute("user");
		int numberNews = nd.countNewNews(objUser);
		int numberComment = cmd.countNewComment(objUser);
		int numberForgot = md.countNewMessageByType(2);
		int numberSubcribe = md.countNewMessageByType(1);
		ArrayList<News> listNews = nd.getItemsPopular();
		ArrayList<Comment> listComment = cmd.getItemsLimit10();
		for (Comment comment : listComment) {
			comment.setTenBaiViet(cmd.getTenBaiViet(comment.getLevel() ,comment.getId_bai()));
			if(comment.getLevel() == 2)
			{
				comment.setId_bai(cmd.getItem(comment.getId_bai()).getId_bai());
			}
		}
		request.setAttribute("listComment", listComment);
		request.setAttribute("listNews", listNews);
		request.setAttribute("numberNews", numberNews);
		request.setAttribute("numberComment", numberComment);
		request.setAttribute("numberForgot", numberForgot);
		request.setAttribute("numberSubcribe", numberSubcribe);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/index.jsp");
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
