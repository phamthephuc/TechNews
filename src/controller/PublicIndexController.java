package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.News;
import model.dao.CatDao;
import model.dao.MessageDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class PublicIndexController
 */

public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicIndexController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		CatDao cd = new CatDao();
		NewsDao nd = new NewsDao();
		ArrayList<Category> listCat = cd.getItems();
		HashMap<Category, ArrayList<News>> mapCatNews = new HashMap<>();
		for (Category category : listCat) {
			mapCatNews.put(category, nd.getItemsByIdCatLiMit5(category.getId_cat()));
		}
		
		ArrayList<News> listTop3News = nd.getTop3();
		request.setAttribute("listTop3News", listTop3News);
		request.setAttribute("mapCatNews", mapCatNews);
		request.setAttribute("listCat", listCat);
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
