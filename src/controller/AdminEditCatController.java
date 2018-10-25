package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * Servlet implementation class AdminEditCatController
 */
public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditCatController() {
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
		int id_cat = Integer.parseInt(request.getParameter("id_cat"));
		CatDao cd = new CatDao();
		Category objCat = (cd.getItem(id_cat));
		request.setAttribute("objCat", objCat);
		int soLuong = 0;
		soLuong = cd.countCat();
		System.out.println(objCat.getId_parent());
		ArrayList<Category> rootCats = cd.getRootCat();
		request.setAttribute("rootCats", rootCats);
		request.setAttribute("soLuong", soLuong);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/cat/editCat.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int cid = Integer.parseInt(request.getParameter("id_cat"));
		String name = request.getParameter("tendanhmuc");
		Pattern pt = Pattern.compile("[a-z Á-Ỵá-ỵA-Z0-9]{3,20}");
		Matcher mc = pt.matcher(name);
		int id_parent = Integer.parseInt(request.getParameter("parent"));
		int priority = Integer.parseInt(request.getParameter("priority"));
		CatDao catDao = new CatDao();
		Category oldobjCat = (catDao.getItem(cid));
		int oldpriority = oldobjCat.getPriority();
		
		if(priority > oldpriority)
		{
			catDao.decreasePriority(priority, oldpriority);
		}
		
		if(oldpriority > priority)
		{
			catDao.increasePriority(priority, oldpriority);
		}
		
		if (mc.matches())
		{
			Category objCat = new Category(cid, name,id_parent,priority);
			
			if(catDao.getItemByNameAndId(name,cid) == null)
			{
				if(catDao.editItem(objCat)>0) {
					response.sendRedirect(request.getContextPath()+"/admin/cat?msg=2");
					return;
				}else {
					response.sendRedirect(request.getContextPath()+"/admin/cat?msg=0");
					return;
				}
			}
			else
			{
				response.sendRedirect(request.getContextPath()+"/admin/cat/edit?id_cat=" + cid + "&msg=6");
				return;
			}
		}
		else
		{
			response.sendRedirect(request.getContextPath()+"/admin/cat/edit?id_cat=" + cid + "&msg=5");
			return;
		}
	}

}
