<%@page import="library.StringLibrary"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="templates/public/inc/header.jsp"%>

<% if(request.getAttribute("key") != null) 
				{
					String key = (String)request.getAttribute("key");
				%>
<section class="breadcrumb_section">
	<div class="container">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="<%=request.getContextPath()%>">Home</a></li>
				<li><a>Search</a></li>
				
				<li class="active"><a><%=key %></a></li>
				
			</ol>
		</div>
	</div>
</section>

<div class="container">
	<div class="row">
		
		<div class="col-md-8">
			<div class="related_news">
				    <div class="entity_inner__title header_purple">
				        <h2><a >Kết quả tìm kiếm</a></h2>
				    </div>
				    <!-- entity_title -->
					<% if(request.getAttribute("listNews")!=null) {
					ArrayList<News> listNews = (ArrayList<News>)request.getAttribute("listNews");
					for(int i=0;i<=(listNews.size()-1)/2;i++)
					{
						
					%>
				    <div class="row">
				    	<% for(int j = i*2;j<=i*2+1 && j<listNews.size();j++) { 
				    		String urlSlugCat =  request.getContextPath()+"/" + StringLibrary.makeSlug(listNews.get(j).getCatName())+"-" + listNews.get(j).getId_cat();
				    		String urlSlugNews = request.getContextPath()+"/" +StringLibrary.makeSlug(listNews.get(j).getName())+"-" +listNews.get(j).getId_news()+".html";
				    	%>
				        <div class="col-md-6">
				            <div class="media">
				                <div class="media-left">
				                    <a href="<%=request.getContextPath()%>/detail?id_news=<%=listNews.get(j).getId_news()%>"><img style="width: 120px;height: 120px" class="media-object" src="<%=request.getContextPath() %>/files/<%=listNews.get(j).getPicture() %>"
				                                     alt="Generic placeholder image"></a>
				                </div>
				                <div class="media-body">
				                    <span class="tag purple"><a href="<%=request.getContextPath()%>/cat?id_cat=<%=listNews.get(j).getId_cat()%>" target="_self"><%=listNews.get(j).getCatName() %></a></span>
				
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
				    
				    <% } } %>
				</div>

			<nav aria-label="Page navigation" class="pagination_section">
				<ul class="pagination">
					<%
					int current_page = (int) request.getAttribute("current_page");
					int sumPage = (int) request.getAttribute("sumPage");
					if(sumPage > 1 ) {
					if(current_page > 1) { %>
					<li><a href="<%=request.getContextPath() %>/search?key=<%=key %>&page=<%=current_page -1 %>" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>
					</a></li>
					<% } 
					for(int i = 1; i <= sumPage;i++)  {
					if(i==current_page) {
					%>
					<li><a class="active" href="<%=request.getContextPath() %>/search?key=<%=key %>&page=<%=i%>"><%=i%></a></li>
					<% } else { %>
					<li><a href="<%=request.getContextPath() %>/search?key=<%=key %>&page=<%=i%>"><%=i%></a></li>
					<% } } %>
					<% if(current_page < sumPage) { %>
					<li><a href="<%=request.getContextPath() %>/search?key=<%=key %>&page=<%=current_page +1 %>" aria-label="Next"> <span
							aria-hidden="true">&raquo;</span>
					</a></li>
					<% } }%>
				</ul>
			</nav>
			<!-- navigation -->

		</div>
		<!-- col-md-8 -->
		
		<%@include file="templates/public/inc/rightSession.jsp"%>
		<!-- col-md-4 -->

	</div>
	<!-- row -->

</div>
<% } %>
<!-- container -->
<%@include file="templates/public/inc/footer.jsp"%>