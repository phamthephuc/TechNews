package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Category;
import model.bean.News;
import model.dao.CatDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class AdminIndexCatController
 */
public class AdminDelCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelCatController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null )
		{
			response.sendRedirect(request.getContextPath()+"/admin/login");
			return;
		}
		else
		{
			try
			{
				int id = Integer.parseInt(request.getParameter("id_cat"));
				
				CatDao catDao = new CatDao();
				Category objCat = catDao.getItem(id);
				if(catDao.delItem(id)>0) {
					catDao.decreasePriority(objCat.getPriority());
					response.sendRedirect(request.getContextPath()+"/admin/cat?msg=3");
					return;
				}else {
					response.sendRedirect(request.getContextPath()+"/admin/cat?msg=0");
					return;
				}
			}
			catch (Exception e) {
				response.sendRedirect(request.getContextPath()+"/admin/cat");
				return;
			}
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
