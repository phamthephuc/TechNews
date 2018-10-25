package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Comment;
import model.bean.News;
import model.dao.CommentDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class AdminDelNewsController
 */
public class AdminDelNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelNewsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null )
		{
			response.sendRedirect(request.getContextPath()+"/admin/login");
			return;
		}
		else
		{
			int id = 0;
			try
			{
				id = Integer.parseInt(request.getParameter("id_news"));
			}
			catch (Exception e) {
				response.sendRedirect(request.getContextPath()+"/admin/news");
				return;
			}
			
			NewsDao newDao = new NewsDao();
			News news = newDao.getItem(id);
			
			if(!news.getPicture().isEmpty())
			{
				String filePath = request.getServletContext().getRealPath("files") + File.separator + news.getPicture();
				System.out.println(filePath);
				File file = new File(filePath);
				file.delete();
			}
			if(newDao.delItem(id)>0) {
				response.sendRedirect(request.getContextPath()+"/admin/news?msg=3");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/news?msg=0");
				return;
			}
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
