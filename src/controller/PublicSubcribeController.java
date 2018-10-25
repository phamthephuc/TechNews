package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MessageDao;

/**
 * Servlet implementation class PublicSubcribeController
 */

public class PublicSubcribeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicSubcribeController() {
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
		response.setContentType("html/text");
		Pattern pt1 = Pattern.compile("[a-zÁ-Ỵá-ỵA-Z]{1,1}[a-z Á-Ỵá-ỵA-Z0-9-_]*@[a-zÁ-Ỵá-ỵA-Z]+.com");
		String email = request.getParameter("email");
		Matcher mc = pt1.matcher(email);
		System.out.println(email);
		PrintWriter out = response.getWriter();
		if(mc.matches())
		{
			MessageDao md = new MessageDao();
			if(md.getItemSubcribeByEmail(email) != null) 
			{
				out.print("<h5 style='font-style: italic; color:red;text-align:center'>Tài khoản của bạn đã subcribe web rồi</h5>");
				return;
			}
			if(md.addItem(email, 1)>0) {
				out.print("<h5 style='font-style: italic; color:green;text-align:center'>Bạn đã gửi thành công</h5>");
				return;
			}
			else
			{
				out.println("<h5 style='font-style: italic; color:red;text-align:center'>Có lỗi trong quá trình xử lý</h5>");
				return;
			}
			
		}
		else
		{
			out.print("<h5 style='font-style: italic; color:red;text-align:center'>Bạn cần nhập đủ các trường yêu cầu theo đúng định dạng</h5>");
			return;
		}
	}

}
