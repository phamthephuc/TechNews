package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.AuthLibrary;
import model.bean.Category;
import model.dao.CatDao;

/**
 * Servlet implementation class AdminAddCatController
 */
@MultipartConfig
public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddCatController() {
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
		CatDao cd = new CatDao();
		int soLuong = 0;
		soLuong = cd.countCat();
		ArrayList<Category> rootCats = cd.getRootCat();
		request.setAttribute("rootCats", rootCats);
		request.setAttribute("soLuong", soLuong);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/cat/addCat.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("tendanhmuc");
		Pattern pt = Pattern.compile("[a-z Á-Ỵá-ỵA-Z0-9]{3,20}");
		Matcher mc = pt.matcher(name);
		int parent = Integer.parseInt(request.getParameter("parent"));
		int priority = Integer.parseInt(request.getParameter("priority"));
		CatDao catDao = new CatDao();
		
		catDao.increasePriority(priority);
		if (mc.matches())
		{
			Category objCat = new Category(0, name,parent,priority);
			
			if(catDao.getItemByName(name) == null)
			{
				if(catDao.addItem(objCat)>0) {
					response.sendRedirect(request.getContextPath()+"/admin/cat?msg=1");
					return;
				}else {
					response.sendRedirect(request.getContextPath()+"/admin/cat?msg=0");
					return;
				}
			}
			else
			{
				response.sendRedirect(request.getContextPath()+"/admin/cat/add?msg=6");
				return;
			}
		}
		else
		{
			response.sendRedirect(request.getContextPath()+"/admin/cat/add?msg=5");
			return;
		}
	}

}
