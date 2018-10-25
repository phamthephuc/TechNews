package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Comment;
import model.bean.News;
import model.bean.User;
import model.dao.CommentDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class PublicAddCommentController
 */
public class PublicAddCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicAddCommentController() {
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
		response.setContentType("text/html");
		int id_news = Integer.parseInt(request.getParameter("id_news"));
		int type = Integer.parseInt(request.getParameter("type"));
		int id_cmt = Integer.parseInt(request.getParameter("id_cmt"));
		int level = 1;
		int id_bai = id_news;
		if(type == 2) {
			level = 2;
			id_bai = id_cmt;
		}
		
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
	
		String content = request.getParameter("content");
		String avatar = "";
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null )
		{
			User objNguoiDung = (User)session.getAttribute("user");
			avatar = objNguoiDung.getPicture();
		}
		
		
		Comment objCmt = new Comment(0, type, content, id_bai, email, new Date(), name, 1, avatar);
		CommentDao cmd = new CommentDao();
		NewsDao nd = new NewsDao();
		if(!(name.contains("&lt;") || email.contains("&lt;") || content.contains("&lt;") || name.contains("<") || email.contains("<") || content.contains("<"))) {
		cmd.addItem(objCmt);
		if(level == 1) nd.increaseComment(id_news);
		}
		HashMap<Comment, ArrayList<Comment>> mapCmt = new HashMap<>();
		ArrayList<Comment> listCmt = cmd.getItemsType1ForNews(id_news);
		for (Comment comment : listCmt) {
			mapCmt.put(comment, cmd.getItemsType2ForNews(comment));
		}
		News objNews = nd.getItem(id_news);
		ArrayList<News> listNews = nd.getItemsLikeIdCat(id_news, objNews.getId_cat());
		
		String data = "";
		for(Comment objCmtLevel1 : listCmt) {
			data += "<div class='media'><div class='media-left'>";
			if(objCmtLevel1.getAvatar().equals("")) {
				data += "<a ><img style='width: 64px;height:64px' alt='64x64' class='media-object' data-src='" +request.getContextPath() + "/templates/public/img/reader_img1.jpg' src='" + request.getContextPath() + "/files/user.png' data-holder-rendered='true'></a>";
			} else
			{
				data += "<a><img style='width: 64px;height:64px' alt='64x64' class='media-object' data-src='" +request.getContextPath() + "/templates/public/img/reader_img1.jpg'  src='" + request.getContextPath() + "/files/" +objCmtLevel1.getAvatar() + "' data-holder-rendered='true'></a>";
			}
			data +="</div><div class='media-body'><h2 class='media-heading'><a >" + objCmtLevel1.getName_user() + "</a></h2>" + objCmtLevel1.getContent() + "<div class='entity_vote'><a ><i class='fa fa-thumbs-o-up' aria-hidden='true'></i></a><a ><i class='fa fa-thumbs-o-down' aria-hidden='true'></i></a>";
			data +="<a onclick='addForm("+ objCmtLevel1.getId_cmt() + "," + objNews.getId_news() + ")' href='javasript:void(0)'><span class='reply_ic'>Reply </span></a></div>";
			ArrayList<Comment> listCmtLevel2 = mapCmt.get(objCmtLevel1);
        	if(listCmtLevel2.size() > 0) {
        		for(Comment objCmtLevel2 : listCmtLevel2) {
        			data += "<div class='media'><div class='media-left'>";
        			if(objCmtLevel2.getAvatar().equals("")) {
        				data += "<a ><img style='width: 64px;height:64px' alt='64x64' class='media-object' data-src='" + request.getContextPath() + "/templates/public/img/reader_img1.jpg' src='" + request.getContextPath() + "/files/user.png' data-holder-rendered='true'></a>";
        			}
        			else 
        			{
        				data += "<a><img style='width: 64px;height:64px' alt='64x64' class='media-object' data-src='" + request.getContextPath() + "/templates/public/img/reader_img1.jpg' src='" + request.getContextPath() + "/files/" + objCmtLevel2.getAvatar() + "' data-holder-rendered='true'></a>";
        			}
        			
        			data += "</div><div class='media-body'><h2 class='media-heading'><a >" + objCmtLevel2.getName_user() + "</a></h2>" + objCmtLevel2.getContent() + "<div class='entity_vote'><a ><i class='fa fa-thumbs-o-up' aria-hidden='true'></i></a><a><i class='fa fa-thumbs-o-down' aria-hidden='true'></i></a><a onclick='addForm(" + objCmtLevel1.getId_cmt() + "," + objNews.getId_news() + ")' href='javasript:void(0)'><span class='reply_ic'>Reply </span></a></div></div></div>";
        		}
        	}
        		data += " <div id='divCmt" +objCmtLevel1.getId_cmt()+"'>";
        		if(id_cmt == objCmtLevel1.getId_cmt())
        		{
        			if(session.getAttribute("user") == null) {
        				data += "<div class='media'><div class='entity_comments'><div class='entity_inner__title header_black'><h2>Comment</h2></div><div class='entity_comment_from'><form action='javascript:addCmt("+ id_news +",2,"+id_cmt + ")'><div class='form-group'><input required maxlength='100' minlength='5' requiredmsg= 'Nhập tên theo đúng định dạng chỉ chứa chử cái, khoảng trắng, chữ số tối thiểu 5 ký tự, tối đa 100 ký tự' type='text' class='form-control' id='name" +id_cmt+ "' placeholder='Name'></div><div class='form-group'><input required= 'required' type='email' requiredmsg= 'Nhập đúng định dạng email' class='form-control' id='email" +id_cmt+ "' placeholder='Email'></div><div class='form-group comment'><textarea required maxlength='200' minlength='5' requiredmsg= 'Nhập nội dung theo đúng định dạng chỉ chứa chử cái, khoảng trắng, chữ số tối thiểu 5 ký tự, tối đa 200 ký tự' class='form-control' id='content" +id_cmt+ "' placeholder='Comment'></textarea></div><button type='submit' class='btn btn-submit red'>Submit</button></form></div></div></div>";
                		
        			}else {
        				User objUser = (User)session.getAttribute("user");
        				data += "<div class='media'><div class='entity_comments'><div class='entity_inner__title header_black'><h2>Comment</h2></div><div class='entity_comment_from'><form action='javascript:addCmt("+ id_news +",2,"+id_cmt + ")'><div class='form-group'><input readonly='readonly' value='"+ objUser.getFullname() + "' required maxlength='100' minlength='5' requiredmsg= 'Nhập tên theo đúng định dạng chỉ chứa chử cái, khoảng trắng, chữ số tối thiểu 5 ký tự, tối đa 100 ký tự' type='text' class='form-control' id='name" +id_cmt+ "' placeholder='Name'></div><div class='form-group'><input required= 'required' type='email' requiredmsg= 'Nhập đúng định dạng email' class='form-control' readonly='readonly' value='" + objUser.getEmail() + "' id='email" +id_cmt+ "' placeholder='Email'></div><div class='form-group comment'><textarea required maxlength='200' minlength='5' requiredmsg= 'Nhập nội dung theo đúng định dạng chỉ chứa chử cái, khoảng trắng, chữ số tối thiểu 5 ký tự, tối đa 200 ký tự' class='form-control' id='content" +id_cmt+ "' placeholder='Comment'></textarea></div><button type='submit' class='btn btn-submit red'>Submit</button></form></div></div></div>";
                		
        			}
        			
        			
        		}
        		
        		data +="</div></div></div>";
		}
		PrintWriter out = response.getWriter();
		out.print(data);
	}

}
