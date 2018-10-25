package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import library.AuthLibrary;
import library.FileLibary;
import model.bean.Category;
import model.bean.News;
import model.bean.User;
import model.dao.CatDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class AdminIndexCatController
 */
@MultipartConfig
public class AdminEditNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditNewsController() {
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
		int id_news = 0;
		try
		{
			id_news = Integer.parseInt(request.getParameter("id_news"));
		}
		catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/admin/news?msg=0");
			return;
		}
		
		NewsDao nd = new NewsDao();
		News objNews = nd.getItem(id_news);
		request.setAttribute("objNews", objNews);
		CatDao cd = new CatDao();
		ArrayList<Category> listCat = cd.getItems();
		request.setAttribute("listCat", listCat);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/news/editNews.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("html/text");
		int id_news = 0;
		try
		{
			id_news = Integer.parseInt(request.getParameter("id_news"));
		}
		catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/admin/news?msg=0");
			return;
		}
		String name = request.getParameter("tentin");
		String mota = request.getParameter("mota");
		String chitiet = request.getParameter("chitiet");
		Part part = request.getPart("hinhanh");
		int id_cat = Integer.parseInt(request.getParameter("danhmuc"));
		
		System.out.println("Mô tả " + mota);
		System.out.println("Chi tiết " + chitiet);
		HttpSession session = request.getSession();
		User objLogin = (User)session.getAttribute("user");
		NewsDao nd = new NewsDao();
		News objOldNews = nd.getItem(id_news);
		if(!(objLogin.getId_role() == 3 || objLogin.getId_user() == objOldNews.getId_author()))
		{
			response.sendRedirect(request.getContextPath()+"/admin/news?msg=4");
			return;
		}
		Pattern pt = Pattern.compile("[^@%#]{5,500}");
		Pattern pt1 = Pattern.compile("[^@%#]{20,1000}");
		Pattern pt2 = Pattern.compile("[^@%#]{20,2500}");
		
		Matcher mc = pt.matcher(name);
		Matcher mc1 = pt1.matcher(mota);
		Matcher mc2 = pt2.matcher(chitiet);
		
		System.out.println(chitiet + " " + mc2.matches());
		System.out.println(mota + " " + mc1.matches());
		System.out.println(name + " " + mc.matches());
		if (!(mc.matches() && mc1.matches() && mc2.matches()) || chitiet.contains("&lt;")|| mota.contains("&lt;") || name.contains("&lt;"))
		{
			System.out.println(chitiet + " " + mc2.matches() + chitiet.length());
			response.sendRedirect(request.getContextPath()+"/admin/news/edit?msg=5");
		
			return;
		}	
		String filename = FileLibary.getName(part);
		if(!filename.equals(""))
		{
			String []mang = filename.split("\\.");
			String typeFile = mang[mang.length-1];
			if(typeFile.equalsIgnoreCase("jpg")||typeFile.equalsIgnoreCase("jpge")||typeFile.equalsIgnoreCase("gif")||typeFile.equalsIgnoreCase("png")||typeFile.equalsIgnoreCase("bmp"))
			{
				Date now = new Date();
				String newfilename = mang[0] + now.getTime() + "." + mang[1];
				String dirPath = request.getServletContext().getRealPath("files");
				File file = new File(dirPath);
				if(!file.exists())
				{
					file.mkdirs();
				}
				
				String oldfilePath = dirPath + File.separator + filename;
				String filePath = dirPath + File.separator + newfilename;
				System.out.println(filePath);
				if(!objOldNews.getPicture().equals(""))
				{
					String oldPicture =  dirPath + File.separator + objOldNews.getPicture();
					File oldF = new File(oldPicture);
					oldF.delete();
				}
				File oldFile = new File(oldfilePath);
				File newFile = new File(filePath);
				oldFile.renameTo(newFile);
				part.write(filePath);
				filename = newfilename;
			}
			else
			{
				filename = objOldNews.getPicture();
			}
		}
		else
		{
			filename = objOldNews.getPicture();
		}
		
		
		News objNews = new  News(id_news,name,mota,chitiet,id_cat,filename,objOldNews.getId_author()) ;
		if(nd.editItem(objNews) > 0)
		{
			response.sendRedirect(request.getContextPath()+"/admin/news?msg=2");
			return;
		}
		else
		{
			response.sendRedirect(request.getContextPath()+"/admin/news?msg=0");
			return;
		}
	}

}
