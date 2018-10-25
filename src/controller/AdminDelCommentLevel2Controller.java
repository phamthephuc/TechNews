package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CommentDao;

/**
 * Servlet implementation class AdminDelCommentLevel2Controller
 */
public class AdminDelCommentLevel2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelCommentLevel2Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_cmt = 0;
		try
		{
			id_cmt = Integer.parseInt(request.getParameter("id_cmt"));
		}
		catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/admin/cmt_level1?msg=0");
			return;
		}
		
		CommentDao cmd = new CommentDao();
		if(cmd.delItemLevel1(id_cmt) > 0)
		{
			response.sendRedirect(request.getContextPath()+"/admin/cmt_level1?msg=1");
			return;
		}
		else
		{
			response.sendRedirect(request.getContextPath()+"/admin/cmt_level1?msg=0");
			return;
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
