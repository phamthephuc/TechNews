package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Category;
import model.bean.Comment;
import model.bean.News;
import model.bean.User;
import model.dao.CatDao;
import model.dao.CommentDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class PublicIndexNewsController
 */
public class PublicIndexNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicIndexNewsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_news=0;
		try
		{
			id_news = Integer.parseInt(request.getParameter("id_news"));
			NewsDao nd = new NewsDao();
			CommentDao cmd = new CommentDao();
			HashMap<Comment, ArrayList<Comment>> mapCmt = new HashMap<>();
			ArrayList<Comment> listCmt = cmd.getItemsType1ForNews(id_news);
			for (Comment comment : listCmt) {
				mapCmt.put(comment, cmd.getItemsType2ForNews(comment));
			}
			News objNews = nd.getItem(id_news);
			nd.increaseView(objNews.getId_news());
			ArrayList<News> listNews = nd.getItemsLikeIdCat(id_news, objNews.getId_cat());
			CatDao cd = new CatDao();
			if(objNews != null) {
			Category objCat = cd.getItem(objNews.getId_cat());
			request.setAttribute("objCat", objCat);
			}
			request.setAttribute("mapCmt", mapCmt);
			request.setAttribute("listCmt", listCmt);
			request.setAttribute("objNews", objNews);
			request.setAttribute("listNews", listNews);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/single.jsp");
			dispatcher.forward(request, response);
		}
		catch (Exception e) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_cmt = Integer.parseInt(request.getParameter("id_cmt"));
		int id_news = Integer.parseInt(request.getParameter("id_news"));
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
		PrintWriter out = response.getWriter();
		String data = "<div class='media'><div class='entity_comments'><div class='entity_inner__title header_black'><h2>Comment</h2></div><div class='entity_comment_from'><form action='javascript:addCmt("+ id_news +",2,"+id_cmt + ")'><div class='form-group'><input type='text' required maxlength='100' minlength='5' requiredmsg='Nhập tên theo đúng định dạng chỉ chứa chử cái, khoảng trắng, chữ số tối thiểu 5 ký tự, tối đa 100 ký tự' class='form-control' id='name" +id_cmt+ "' placeholder='Name'></div><div class='form-group'><input required type='email' requiredmsg= 'Nhập đúng định dạng email' class='form-control' id='email" +id_cmt+ "' placeholder='Email'></div><div class='form-group comment'><textarea required maxlength='200' minlength='5' requiredmsg= 'Nhập nội dung theo đúng định dạng chỉ chứa chử cái, khoảng trắng, chữ số tối thiểu 5 ký tự, tối đa 200 ký tự' class='form-control' id='content" +id_cmt+ "' placeholder='Comment'></textarea></div><button type='submit' class='btn btn-submit red'>Submit</button></form></div></div></div>";
		out.print(data);
		}
		else
		{
			User objUser = (User)session.getAttribute("user");
			PrintWriter out = response.getWriter();
			
			String data = "<div class='media'><div class='entity_comments'><div class='entity_inner__title header_black'><h2>Comment</h2></div><div class='entity_comment_from'><form action='javascript:addCmt("+ id_news +",2,"+id_cmt + ")'><div class='form-group'><input type='text' readonly='readonly' value='" + objUser.getFullname() +"' required maxlength='100' minlength='5' requiredmsg='Nhập tên theo đúng định dạng chỉ chứa chử cái, khoảng trắng, chữ số tối thiểu 5 ký tự, tối đa 100 ký tự' class='form-control' id='name" +id_cmt+ "' placeholder='Name'></div><div class='form-group'><input required type='email' readonly='readonly' value='" + objUser.getEmail() +"' requiredmsg= 'Nhập đúng định dạng email' class='form-control' id='email" +id_cmt+ "' placeholder='Email'></div><div class='form-group comment'><textarea required maxlength='200' minlength='5' requiredmsg= 'Nhập nội dung theo đúng định dạng chỉ chứa chử cái, khoảng trắng, chữ số tối thiểu 5 ký tự, tối đa 200 ký tự' class='form-control' id='content" +id_cmt+ "' placeholder='Comment'></textarea></div><button type='submit' class='btn btn-submit red'>Submit</button></form></div></div></div>";
			out.print(data);
		}
	}

}
