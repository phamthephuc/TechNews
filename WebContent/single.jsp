<%@page import="model.bean.Comment"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- header_section_wrapper -->
<%@include file="templates/public/inc/header.jsp" %>
<section id="entity_section" class="entity_section">
<div class="container">
<div class="row">
<% if(request.getAttribute("objNews")!=null) {
	News objNews = (News)request.getAttribute("objNews");
	%>
<div class="col-md-8">

<div class="entity_wrapper">
    <div class="entity_title">
        <h1><a><%=objNews.getName() %></a></h1>
    </div>
    <!-- entity_title -->

    <div class="entity_meta"><a target="_self"><%=sdft.format(objNews.getDate_create()) %></a>, by: <a target="_self"><%=objNews.getNameAuthor() %></a>
    </div>
    <!-- entity_meta -->

    <div class="entity_rating">
        <i class="fa fa-star"></i>
        <i class="fa fa-star"></i>
        <i class="fa fa-star"></i>
        <i class="fa fa-star"></i>
        <i class="fa fa-star-half-full"></i>
    </div>
    <!-- entity_rating -->

    <div class="entity_social">
        <a  class="icons-sm sh-ic">
            <i class="fa fa-views"></i>
            <b><%=objNews.getViews() %></b> <span class="share_ic">Views</span>
        </a>
        <a href="https://www.facebook.com/sharer/sharer.php?u=https://www.facebook.com/phuc.pham.5268" class="icons-sm fb-ic"><i class="fa fa-facebook"></i></a>
        <!--Twitter-->
        <a href="https://twitter.com/share?text=&url=https://www.facebook.com/phuc.pham.5268" class="icons-sm tw-ic"><i class="fa fa-twitter"></i></a>
        <!--Google +-->
        <a href="https://plus.google.com/share?url=https://www.facebook.com/phuc.pham.5268" class="icons-sm inst-ic"><i class="fa fa-google-plus"> </i></a>
        <!--Linkedin-->
        <a href="https://www.pinterest.com/pin/create/button/?url=https://www.facebook.com/phuc.pham.5268" class="icons-sm tmb-ic"><i class="fa fa-ge"> </i></a>
        <!--Pinterest-->
        <a href="https://www.pinterest.com/pin/create/button/?url=https://www.facebook.com/phuc.pham.5268" class="icons-sm rss-ic"><i class="fa fa-rss"> </i></a>
    </div>
    <!-- entity_social -->

    <div class="entity_thumb">
        <img style="width: 750px;height: 470px" class="img-responsive" src="<%=request.getContextPath() %>/files/<%=objNews.getPicture() %>" alt="feature-top">
    </div>
    <!-- entity_thumb -->
	<div>
    <div class="entity_content">
    	<blockquote class="pull-left"><%=objNews.getDescription() %>
        </blockquote>
        <p>
           <%=objNews.getDetail() %>
        </p>

        
    </div>
    </div>
    <div style="clear: both;"></div>
    <!-- entity_content -->

    <div class="entity_footer">

        <div class="entity_social">
            <span><i class="fa fa-eye"></i><%=objNews.getViews() %> <a>Views</a> </span>
            <span><i class="fa fa-comments-o"></i><%=objNews.getComment() %><a>Comments</a> </span>
        </div>
        <!-- entity_social -->

    </div>
    <!-- entity_footer -->

</div>
<!-- entity_wrapper -->

<div class="related_news">
    <div class="entity_inner__title header_purple">
        <h2><a >Tin liên quan</a></h2>
    </div>
    <!-- entity_title -->
	<% if(request.getAttribute("listNews")!=null) {
	ArrayList<News> listNews = (ArrayList<News>)request.getAttribute("listNews");
	if(request.getAttribute("objCat")!= null){
		Category objCat = (Category)request.getAttribute("objCat");
		String urlSlugCat = request.getContextPath()+"/" + StringLibrary.makeSlug(objCat.getName())+"-" + objCat.getId_cat();
	for(int i=0;i<=(listNews.size()-1)/2;i++)
	{
		
	%>
    <div class="row">
    	<% for(int j = i*2;j<=i*2+1 && j<listNews.size();j++) {
    		String urlSlugNews = request.getContextPath()+"/" +StringLibrary.makeSlug(listNews.get(j).getName())+"-" +listNews.get(j).getId_news()+".html";
    		%>
        <div class="col-md-6">
            <div class="media">
                <div class="media-left">
                    <a href="<%=request.getContextPath()%>/detail?id_news=<%=listNews.get(j).getId_news()%>"><img style="width: 120px;height: 120px" class="media-object" src="<%=request.getContextPath() %>/files/<%=listNews.get(j).getPicture() %>"
                                     alt="Generic placeholder image"></a>
                </div>
                <div class="media-body">
                    <span class="tag purple"><a href="<%=request.getContextPath()%>/cat?id_cat=<%=objCat.getId_cat()%>" target="_self"><%=listNews.get(j).getCatName() %></a></span>

                    <h3 class="media-heading"><a href="<%=request.getContextPath()%>/detail?id_news=<%=listNews.get(j).getId_news()%>" target="_self"><%=listNews.get(j).getName() %></a></h3>
                    <span class="media-date"><a><%=sdft.format(listNews.get(j).getDate_create()) %></a>,  by: <a><%=listNews.get(j).getNameAuthor() %></a></span>

                    <div class="media_social">
                        <span><a><i class="fa fa-eye"></i><%=listNews.get(j).getViews() %></a> Views</span>
                        <span><a><i class="fa fa-comments-o"></i><%=listNews.get(j).getComment() %></a> Comments</span>
                    </div>
                </div>
            </div>
        </div>
        <% } %>
        
    </div>
    
    <% } } } %>
</div>
<!-- Related news -->

<div class="widget_advertisement">
    <img class="img-responsive" src="<%=request.getContextPath() %>/templates/public/img/category_advertisement.jpg" alt="feature-top">
</div>
<!--Advertisement-->

<div id="ajax2" class="readers_comment">
    <div class="entity_inner__title header_purple">
        <h2>Bạn đọc Comment</h2>
    </div>
    <!-- entity_title -->
	<div id="ajax">
	<% if(request.getAttribute("mapCmt")!= null) { 
		HashMap<Comment,ArrayList<Comment>> mapCmt = (HashMap<Comment,ArrayList<Comment>>)request.getAttribute("mapCmt");
		if(request.getAttribute("listCmt")!=null) {
		ArrayList<Comment> listCmtLevel1 = (ArrayList<Comment>)request.getAttribute("listCmt");
		for(Comment objCmtLevel1 : listCmtLevel1) {
	%>
    <div class="media">
        <div class="media-left">
        <% if(objCmtLevel1.getAvatar().equals("")) { %>
            <a href="#">
                <img style="width: 64px;height:64px" alt="64x64" class="media-object" data-src="<%=request.getContextPath() %>/templates/public/img/reader_img1.jpg"
                     src="<%=request.getContextPath() %>/files/user.png" data-holder-rendered="true">
            </a>
         <% } else { %>
         	<a href="#">
                <img style="width: 64px;height:64px" alt="64x64" class="media-object" data-src="<%=request.getContextPath() %>/templates/public/img/reader_img1.jpg"
                     src="<%=request.getContextPath() %>/files/<%=objCmtLevel1.getAvatar() %>" data-holder-rendered="true">
            </a>
         <% } %>
        </div>
        <div class="media-body">
            <h2 class="media-heading"><a href="#"><%=objCmtLevel1.getName_user() %></a></h2>
           	<%=objCmtLevel1.getContent() %>

            <div class="entity_vote">
                <a href="#"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i></a>
                <a href="#"><i class="fa fa-thumbs-o-down" aria-hidden="true"></i></a>
                <a onclick="addForm(<%=objCmtLevel1.getId_cmt() %>,<%=objNews.getId_news() %>)" href="javasript:void(0)"><span class="reply_ic">Reply </span></a>
            </div>
            <% ArrayList<Comment> listCmtLevel2 = mapCmt.get(objCmtLevel1);
            	if(listCmtLevel2.size() > 0) {
            		for(Comment objCmtLevel2 : listCmtLevel2) {
            %>
            <div class="media">
                <div class="media-left">
                  <% if(objCmtLevel2.getAvatar().equals("")) { %>
		            <a>
		                <img style="width: 64px;height:64px" alt="64x64" class="media-object" data-src="<%=request.getContextPath() %>/templates/public/img/reader_img1.jpg"
		                     src="<%=request.getContextPath() %>/files/user.png" data-holder-rendered="true">
		            </a>
		         <% } else { %>
                    <a>
		                <img style="width: 64px;height:64px" alt="64x64" class="media-object" data-src="<%=request.getContextPath() %>/templates/public/img/reader_img1.jpg"
		                     src="<%=request.getContextPath() %>/files/<%=objCmtLevel2.getAvatar() %>" data-holder-rendered="true">
		            </a>
		         <% } %>
                </div>
                <div class="media-body">
                    <h2 class="media-heading"><a><%=objCmtLevel2.getName_user() %></a></h2>
                   <%=objCmtLevel2.getContent() %>

                    <div class="entity_vote">
                        <a href="#"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i></a>
                        <a href="#"><i class="fa fa-thumbs-o-down" aria-hidden="true"></i></a>
                        <a onclick="addForm(<%=objCmtLevel1.getId_cmt() %>,<%=objNews.getId_news() %>)" href="javasript:void(0)"><span class="reply_ic">Reply </span></a>
                    </div>
                </div>
            </div>
            
            <% } } %>
            <div id="divCmt<%=objCmtLevel1.getId_cmt()%>">
            
            </div>
        </div>

    </div>
    
    <% } } } %>
    <!-- media end -->

    <!-- media end -->
 	</div>
</div>
<!--Readers Comment-->



<div class="entity_comments">
    <div class="entity_inner__title header_black">
        <h2>Comment</h2>
    </div>
    <!--Entity Title -->
	<% if(session.getAttribute("user") != null) {
		User objUser1 = (User)session.getAttribute("user");
		%>
    <div class="entity_comment_from">
        <form action="javascript:addCmt(<%=objNews.getId_news() %>,1,0)">
            <div class="form-group">
                <input type="text" readonly="readonly" value="<%=objUser1.getFullname() %>" class="form-control" id="name0" placeholder="Name"  required pattern="[a-z Á-Ỵá-ỵA-Z0-9]{5,100}"  requiredmsg="Nhập tên theo đúng định dạng chỉ chứa chử cái, khoảng trắng, chữ số tối thiểu 5 ký tự, tối đa 100 ký tự">
            </div>
            <div class="form-group">
                <input readonly="readonly" value="<%=objUser1.getEmail() %>" onfocus="inputForm(0,<%=objUser1.getFullname() %>,<%=objUser1.getEmail() %>)" class="form-control" id="email0" placeholder="Email"  required="required" type="email" requiredmsg="Nhập đúng định dạng email">
            </div>
            <div class="form-group comment">
                <textarea onfocus="inputForm(0,<%=objUser1.getFullname() %>,<%=objUser1.getEmail() %>)" required="required" pattern="[a-z Á-Ỵá-ỵA-Z0-9]{5,200}" requiredmsg="Nhập nội dung theo đúng định dạng chỉ chứa chử cái, khoảng trắng, chữ số tối thiểu 5 ký tự, tối đa 200 ký tự" class="form-control" id="content0" placeholder="Comment"></textarea>
            </div>

            <button type="submit" class="btn btn-submit red">Submit</button>
        </form>
    </div>
    <% } else { %>
    <div class="entity_comment_from">
        <form action="javascript:addCmt(<%=objNews.getId_news() %>,1,0)">
            <div class="form-group">
                <input type="text" class="form-control" id="name0" placeholder="Name"  required pattern="[a-z Á-Ỵá-ỵA-Z0-9]{5,100}"  requiredmsg="Nhập tên theo đúng định dạng chỉ chứa chử cái, khoảng trắng, chữ số tối thiểu 5 ký tự, tối đa 100 ký tự">
            </div>
            <div class="form-group">
                <input onfocus="inputForm(0,'','')" class="form-control" id="email0" placeholder="Email"  required="required" type="email" requiredmsg="Nhập đúng định dạng email">
            </div>
            <div class="form-group comment">
                <textarea onfocus="inputForm(0,'','')" required="required" pattern="[a-z Á-Ỵá-ỵA-Z0-9]{5,200}" requiredmsg="Nhập nội dung theo đúng định dạng chỉ chứa chử cái, khoảng trắng, chữ số tối thiểu 5 ký tự, tối đa 200 ký tự" class="form-control" id="content0" placeholder="Comment"></textarea>
            </div>

            <button type="submit" class="btn btn-submit red">Submit</button>
        </form>
    </div>
    <% } %>
    <!--Entity Comments From -->

</div>
<!--Entity Comments -->

</div>
<% } %>
<!--Left Section-->

<%@include file="templates/public/inc/rightSession.jsp" %>
<!--Right Section-->

</div>
<!-- row -->

</div>
<!-- container -->

</section>
<!-- Entity Section Wrapper -->
<%@include file="templates/public/inc/footer.jsp" %>
