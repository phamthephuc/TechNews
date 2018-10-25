package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.Define;
import model.bean.News;
import model.dao.CatDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class PublicIndexCatController
 */
public class PublicSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicSearchController() {
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
		String key = "";
		try{
			key = request.getParameter("key");
		}
		catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/error");
			return;
		}
		int current_page = 1;
		if(request.getParameter("page") != null)
		{
			try {
				current_page = Integer.parseInt(request.getParameter("page"));
			}
			catch (Exception e) {
				response.sendRedirect(request.getContextPath()+"/search?key="+key);
				return;
			}
		}
		
		NewsDao nd = new NewsDao();
		CatDao cd = new CatDao();
		int offset = (current_page - 1)*(Define.Row_Count_Public*2);
		int sumNews = nd.countNewsByName(key);
		System.out.println("Sum news " + sumNews);
		int sumPage = (int)Math.ceil((float)sumNews/(Define.Row_Count_Public*2));
		
		
		request.setAttribute("current_page", current_page);
		request.setAttribute("sumPage", sumPage);
		
		ArrayList<News> listNews = nd.getItemsByNamePagination(key,offset);
		request.setAttribute("listNews", listNews);
		request.setAttribute("key", key);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/search.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
