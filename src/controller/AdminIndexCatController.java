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
import model.dao.CatDao;

/**
 * Servlet implementation class AdminIndexCatController
 */
public class AdminIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexCatController() {
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
		CatDao cd = new CatDao();
		ArrayList<Category> listCat = cd.getRootCat();
		request.setAttribute("listCat", listCat);
		HashMap<Category, ArrayList<Category>> mapCat = new HashMap<>();
		for (Category category : listCat) {
			mapCat.put(category, cd.getItemsByIdParent(category.getId_cat()));
		}
		System.out.println(mapCat.toString());
		request.setAttribute("mapCat", mapCat);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/cat/indexCat.jsp");
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
