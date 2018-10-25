
<%@page import="library.StringLibrary"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.dao.NewsDao"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="col-md-4">
<%
SimpleDateFormat sdfxs = new SimpleDateFormat("ddMMM- yyyy");
NewsDao nd = new NewsDao();
ArrayList<News> listPopularNews = nd.getItemsPopular();
%>
<div class="widget">
    <div class="widget_title widget_black">
        <h2><a >Phổ biến nhất</a></h2>
    </div>
    <% for(News objPopularNews : listPopularNews) {
    	String urlSlugNews = request.getContextPath()+"/" +StringLibrary.makeSlug(objPopularNews.getName())+"-" +objPopularNews.getId_news()+".html";
    	%>
    <div class="media">
        <div class="media-left">
            <a href="<%=request.getContextPath()%>/detail?id_news=<%=objPopularNews.getId_news()%>"><img style="width:100px;height:100px" class="media-object" src="<%=request.getContextPath() %>/files/<%=objPopularNews.getPicture() %>" alt="Generic placeholder image"></a>
        </div>
        <div class="media-body">
            <h3 class="media-heading">
                <a style="font-family: 'Roboto', serif;" href="<%=request.getContextPath()%>/detail?id_news=<%=objPopularNews.getId_news()%>" target="_self"><%=objPopularNews.getName() %></a>
            </h3> <span class="media-date"><a><%=sdfxs.format(objPopularNews.getDate_create() )%></a>,  by: <a><%=objPopularNews.getNameAuthor() %></a></span>

            <div class="widget_article_social">
                <span>
                    <a target="_self"> <i class="fa fa-eye"></i><%=objPopularNews.getViews() %></a> Views
                </span> 
            </div>
        </div>
    </div>
	<% } %>
</div>
<!-- Popular News -->

<div class="widget hidden-xs m30">
    <img class="img-responsive adv_img" src="<%=request.getContextPath() %>/templates/public/img/right_add1.jpg" alt="add_one">
    <img class="img-responsive adv_img" src="<%=request.getContextPath() %>/templates/public/img/right_add2.jpg" alt="add_one">
    <img class="img-responsive adv_img" src="<%=request.getContextPath() %>/templates/public/img/right_add3.jpg" alt="add_one">
    <img class="img-responsive adv_img" src="<%=request.getContextPath() %>/templates/public/img/right_add4.jpg" alt="add_one">
</div>
<!-- Advertisement -->

<% ArrayList<News> listCmtNews = nd.getMostComment(); %>
<div class="widget m30">
    <div class="widget_title widget_black">
        <h2><a>Quan tâm nhất</a></h2>
    </div>
    <% for(News objPopularNews : listCmtNews) {
    	String urlSlugNews = request.getContextPath()+"/" +StringLibrary.makeSlug(objPopularNews.getName())+"-" +objPopularNews.getId_news()+".html";
    	%>
    <div class="media">
        <div class="media-left">
            <a href="<%=request.getContextPath()%>/detail?id_news=<%=objPopularNews.getId_news()%>"><img style="width:100px;height:100px" class="media-object" src="<%=request.getContextPath() %>/files/<%=objPopularNews.getPicture() %>" alt="Generic placeholder image"></a>
        </div>
        <div class="media-body">
            <h3 class="media-heading">
                <a style="font-family: 'Roboto', serif" href="<%=request.getContextPath()%>/detail?id_news=<%=objPopularNews.getId_news()%>" target="_self"><%=objPopularNews.getName() %></a>
            </h3> <span class="media-date"><a><%=sdfxs.format(objPopularNews.getDate_create() )%></a>,  by: <a><%=objPopularNews.getNameAuthor() %></a></span>

            <div class="widget_article_social">
                <span>
                    <a target="_self"><i class="fa fa-comments-o"></i><%=objPopularNews.getComment() %></a> Comments
                </span>
            </div>
        </div>
    </div>
    <% } %>
</div>
<!-- Most Commented News -->

<div class="widget m30">
    <div class="widget_title widget_black">
        <h2><a>Góc tác giả</a></h2>
    </div>
    <div class="widget_body"><img style="width: 120px;" class="img-responsive left" src="<%=request.getContextPath() %>/templates/public/img/phuc.jpg"
                                  alt="Generic placeholder image">
		<span>Tôi là : </span><h5 class="red-class">Phạm Thế Phúc</h5>
        <p>Tôi xây dựng website trên cơ sở chia sẻ các kiến thức lập trình, các kiến thức về khoa học công nghệ đến những bạn có cùng đam mê.</p>

        <p>Tôi biết kiến thức của con người là vô hạn nên với tầm kiến thức hạn hẹp của mình, tôi rất mong các đóng góp từ các bạn</p>
    </div>
</div>
<!-- Editor News -->
</div>