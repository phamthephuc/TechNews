package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Comment;
import model.dao.CommentDao;
import model.dao.NewsDao;
import model.dao.UserDao;

/**
 * Servlet implementation class AdminCommentChangeController
 */
public class AdminCommentChangeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCommentChangeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int status_cmt = 0;
		String status = request.getParameter("status");
		if(!status.equals("Ẩn"))
		{
			status_cmt = 1;
		}
		int id_cmt = Integer.parseInt(request.getParameter("id_cmt"));
		CommentDao cmd = new CommentDao();
		NewsDao nd = new NewsDao();
		Comment cmt = cmd.getItem(id_cmt);
		if(cmd.editStatus(id_cmt,1-status_cmt)>0)
		{
			PrintWriter out = response.getWriter();
			if(status_cmt == 0) {
				if(cmt.getLevel() == 1) nd.increaseComment(cmt.getId_bai());
				out.print("<a  onclick='edit("+id_cmt+")' style='display: inline' class='nav-link' href='javascript:void(0)'>" + 
						"<i style='display: inline' class='fa fa-fw fa-check'></i>" + 
						"<span id='id_ajax' style='display: inline' class='nav-link-text'>Hiển thị</span>");
			}
			else
			{
				if(cmt.getLevel() == 1) nd.decreaseComment(cmt.getId_bai());
				out.print("<a onclick='edit("+id_cmt+")' style='display: inline' class='nav-link' href='javascript:void(0)'>" + 
						"<i style='display: inline' class='fa fa-fw fa-square-o'></i>" + 
						"<span id='id_ajax' style='display: inline' class='nav-link-text'>Ẩn</span>");
			}
			
			
		}
		else
		{
			PrintWriter out = response.getWriter();
			out.print("<h5 style='color:red;font-style:italic'>Có lỗi, không chuyển trạng thái được</h5>");
		}
		
	}

}
