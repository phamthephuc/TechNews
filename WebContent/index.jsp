<%@page import="java.util.HashMap"%>
<%@page import="model.bean.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="templates/public/inc/header.jsp" %>
<section id="feature_news_section" class="feature_news_section">
	<div class="container">
		<%
		if(request.getAttribute("listTop3News")!= null) {
		ArrayList<News> listTop3News = (ArrayList<News>)request.getAttribute("listTop3News");
	%>
		<div class="feature_article_wrapper">
			<div class="feature_article_img">
				<div class="row">
					<div class="well">
						<div id="myCarousel" class="carousel slide">
							<div class="carousel-inner">
							<% 
								if(listTop3News.size() > 0) {
									for(int i=0 ;i<listTop3News.size();i++) {
										String active = "";
										if(i==1) active="active";
										else active = "";
										String urlSlugNews = request.getContextPath()+"/" +StringLibrary.makeSlug(listTop3News.get(i).getName())+"-" +listTop3News.get(i).getId_news()+".html";
							%>
								<div class="item <%=active%>">
									<div class="row">
										<div  class="thumbnail">
											<img class="img-responsive"
												style="width: 1140px; height: 490px"
												src="<%=request.getContextPath() %>/files/<%=listTop3News.get(i).getPicture() %>"
												alt="HÃ¬nh 1">
										</div>
										<div class="carousel-indicators-center">
											<h2 class="titlecss"><%=listTop3News.get(i).getName() %></h2>
											<a href="<%=request.getContextPath()%>/detail?id_news=<%=listTop3News.get(i).getId_news()%>" class="general_button_type_1">Xem thêm</a>
										</div>
									</div>
								</div>
							<% } } %>
							</div>
							<a class="left carousel-control"
								href="#myCarousel" data-slide="prev"><i
								class="fa fa-chevron-left fa-2x"></i></a> <a
								class="right carousel-control"
								href="#myCarousel" data-slide="next"><i
								class="fa fa-chevron-right fa-2x"></i></a>

							<ol class="carousel-indicators">
								<li data-target="#myCarousel" data-slide-to="0" class=""></li>
								<li data-target="#myCarousel" data-slide-to="1" class="active"></li>
								<li data-target="#myCarousel" data-slide-to="2"></li>
							</ol>
						</div>
					</div>
				</div>
			</div>
			
			<!-- feature_article_img -->


			<!-- feature_article_inner -->

		</div>
		<% } %>


	</div>
	<!-- container -->

</section>
<!-- Feature News Section -->

<section id="category_section" class="category_section">
<div class="container">
<div class="row">
<div class="col-md-8">

<% 
	if(request.getAttribute("mapCatNews")!=null) {
	HashMap<Category,ArrayList<News>> mapCatNews = (HashMap<Category,ArrayList<News>>)request.getAttribute("mapCatNews");
	
	if(request.getAttribute("listCat")!=null) {
	ArrayList<Category> listCat = (ArrayList<Category>)request.getAttribute("listCat");
	for(Category objCat : listCat) {
		String urlSlug =  request.getContextPath()+"/" + StringLibrary.makeSlug(objCat.getName())+"-" + objCat.getId_cat();
%>
<div class="category_section mobile">
    <div class="article_title header_purple">
        <h2><a href="<%=request.getContextPath()%>/cat?id_cat=<%=objCat.getId_cat()%>" target="_self"><%=objCat.getName() %></a></h2>
    </div>
    <!----article_title------>
    
   <% ArrayList<News> listNews = mapCatNews.get(objCat);
   		if(listNews.size() > 0) {
   			String urlSlugNews = request.getContextPath()+"/" +StringLibrary.makeSlug(listNews.get(0).getName())+"-" +listNews.get(0).getId_news()+".html";
   %>
    <div class="category_article_wrapper">
        <div class="row">
            <div class="col-md-6">
                <div class="top_article_img">
                    <a href="<%=request.getContextPath()%>/detail?id_news=<%=listNews.get(0).getId_news()%>" target="_self"><img style="width:360px;height:245px" class="img-responsive" src="<%=request.getContextPath() %>/files/<%=listNews.get(0).getPicture() %>" alt="feature-top">
                    </a>
                </div>
                <!----top_article_img------>
            </div>
            <div class="col-md-6">
                <span class="tag purple"><a href="<%=request.getContextPath()%>/cat?id_cat=<%=objCat.getId_cat()%>"><%=objCat.getName() %></a></span>

                <div class="category_article_title">
                    <h2><a href="<%=request.getContextPath()%>/detail?id_news=<%=listNews.get(0).getId_news()%>" target="_self">
                    <%=listNews.get(0).getName() %>
                    </a></h2>
                </div>
                <!----category_article_title------>
                <% SimpleDateFormat sdfx = new SimpleDateFormat("ddMMM- yyyy"); %>
                <div class="category_article_date"><a ><%=sdfx.format(listNews.get(0).getDate_create()) %></a>, by: <a ><%=listNews.get(0).getNameAuthor() %></a></div>
                <!----category_article_date------>
                <div class="category_article_content">
                   	<%=listNews.get(0).getDescription() %>
                </div>
                <!----category_article_content------>
                <div class="media_social">
                    <span><a><i class="fa fa-eye"></i><%=listNews.get(0).getViews() %> </a> Views</span>
                    <span><i class="fa fa-comments-o"></i><a ><%=listNews.get(0).getComment() %></a> Comments</span>
                </div>
                <!----media_social------>
            </div>
        </div>
    </div>

    <% if(listNews.size() > 1) { %>
    <div class="category_article_wrapper">
    <% for(int i=1;i<=(listNews.size())/2;i++) { %>
        <div style="margin-top: 20px" class="row">
        <% for(int j=i*2-1;j<=i*2 && j<listNews.size();j++) { 
        	urlSlugNews = request.getContextPath()+"/" +StringLibrary.makeSlug(listNews.get(j).getName())+"-" +listNews.get(j).getId_news()+".html";
        %>
            <div class="col-md-6">
                <div class="media">
                    <div class="media-left">
                        <a href="<%=request.getContextPath()%>/detail?id_news=<%=listNews.get(j).getId_news()%>"><img style="width:122px;height:122px" class="media-object" src="<%=request.getContextPath() %>/files/<%=listNews.get(j).getPicture() %>"
                                         alt="Generic placeholder image"></a>
                    </div>
                    <div class="media-body">
                        <span class="tag purple"><a href="<%=request.getContextPath()%>/cat?id_cat=<%=listNews.get(j).getId_cat()%>"><%=objCat.getName() %></a></span>

                        <h3 class="media-heading"><a style="font-family: 'Roboto', serif;" href="<%=request.getContextPath()%>/detail?id_news=<%=listNews.get(j).getId_news()%>" target="_self">
                        <%=listNews.get(j).getName() %></a></h3>
                        <span class="media-date"><a><%=sdfx.format(listNews.get(j).getDate_create()) %></a>,  by: <a><%=listNews.get(j).getNameAuthor() %></a></span>

                        <div class="media_social">
                            <span><a><i class="fa fa-eye"></i><%=listNews.get(j).getViews() %></a> Views</span>
                            <span><a><i class="fa fa-comments-o"></i><%=listNews.get(j).getComment() %></a> Comments</span>
                        </div>
                    </div>
                </div>
              
            </div>
            
            <%} %>
        </div>
        <%} %>
        
    </div>
    <% }} %>
    <p class="divider"><a href="<%=request.getContextPath()%>/cat?id_cat=<%=objCat.getId_cat()%>">More News&nbsp;&raquo;</a></p>
</div>
<!-- Mobile News Section -->




<% } } }%>




</div>
<!-- Left Section -->

<%@include file="templates/public/inc/rightSession.jsp" %>
<!-- Right Section -->

</div>
<!-- Row -->

</div>
<!-- Container -->

</section>
<!-- Category News Section -->

<section id="video_section" class="video_section">
    <div class="container">
        <div class="well">
            <div class="row">
                <div class="col-md-6">
                    <div class="embed-responsive embed-responsive-4by3">
                        <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/MJ-jbFdUPns"
                                frameborder="0" allowfullscreen></iframe>
                    </div>
                    <!-- embed-responsive -->

                </div>
                <!-- col-md-6 -->

                <div class="col-md-3">
                    <div class="embed-responsive embed-responsive-4by3">
                        <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/h5Jni-vy_5M"></iframe>
                    </div>
                    <!-- embed-responsive -->

                    <div class="embed-responsive embed-responsive-4by3 m16">
                        <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/wQ5Gj0UB_R8"></iframe>
                    </div>
                    <!-- embed-responsive -->

                </div>
                <!-- col-md-3 -->

                <div class="col-md-3">
                    <div class="embed-responsive embed-responsive-4by3">
                        <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/UPvJXBI_3V4"></iframe>
                    </div>
                    <!-- embed-responsive -->

                    <div class="embed-responsive embed-responsive-4by3 m16">
                        <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/DTCtj5Wz6BM"></iframe>
                    </div>
                    <!-- embed-responsive -->

                </div>
                <!-- col-md-3 -->

            </div>
            <!-- row -->

        </div>
        <!-- well -->

    </div>
    <!-- Container -->

</section>
<!-- Video News Section -->

<%@include file="templates/public/inc/footer.jsp" %>