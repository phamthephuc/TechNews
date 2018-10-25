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
public class PublicIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicIndexCatController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_cat = 0;
		try{
			id_cat = Integer.parseInt(request.getParameter("id_cat"));
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
				response.sendRedirect(request.getContextPath()+"/cat?id_cat="+id_cat);
				return;
			}
		}
		
		NewsDao nd = new NewsDao();
		CatDao cd = new CatDao();
		int offset = (current_page - 1)*(Define.Row_Count_Public);
		int sumNews = nd.countNewsByIdCat(id_cat);
		int sumPage = (int)Math.ceil((float)sumNews/Define.Row_Count_Public);
		
		
		Category objCat1 = cd.getItem(id_cat);
		request.setAttribute("objCat1", objCat1);
		if(objCat1.getId_parent() != 0)
		{
			Category objCat2 = cd.getItem(objCat1.getId_parent());
			request.setAttribute("objCat2", objCat2);
		}
		request.setAttribute("current_page", current_page);
		request.setAttribute("sumPage", sumPage);
		
		ArrayList<News> listNews = nd.getItemsByIdCatPagination(id_cat,offset);
		request.setAttribute("listNews", listNews);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/category.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("html/text");
		
		NewsDao nd = new NewsDao();

		String key = request.getParameter("key");
		
		ArrayList<News> listNews = nd.searchByNameLimit10(key);
		
		String data = "";
		for (News news : listNews) {
			data += "<div class='media'><div class='media-left'><a href='" + request.getContextPath() + "/detail?id_news=" + news.getId_news() +"'><img style='width:50px;height:50px' class='media-object' src='" + request.getContextPath() +"/files/" + news.getPicture()+"' alt='Generic placeholder image'></a></div><div class='media-body'><h3 class='media-heading'><a style='font-family: 'Roboto', serif;' href='" + request.getContextPath() + "/detail?id_news="+ news.getId_news() +"' target='_self'>" + news.getName() + "</a></h3></div></div>";
		}
		PrintWriter out = response.getWriter();
		if(key.equals(""))
			out.print("");
		else
			out.print(data);
	}

}
