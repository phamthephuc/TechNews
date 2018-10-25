<%@page import="library.StringLibrary"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="templates/public/inc/header.jsp"%>
<section class="breadcrumb_section">
	<div class="container">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="<%=request.getContextPath()%>">Home</a></li>
				<% if(request.getAttribute("objCat2") != null) 
				{
					Category objCat2 = (Category)request.getAttribute("objCat2");
					String urlSlug =  request.getContextPath()+"/" + StringLibrary.makeSlug(objCat2.getName())+"-" + objCat2.getId_cat();
				%>
				<li><a href="<%=request.getContextPath()%>/cat?id_cat=<%=objCat2.getId_cat()%>" ><%=objCat2.getName() %></a></li>
				<% } %>
				<% if(request.getAttribute("objCat1") != null) 
				{
					Category objCat1 = (Category)request.getAttribute("objCat1");
					String urlSlug =  request.getContextPath()+"/" + StringLibrary.makeSlug(objCat1.getName())+"-" + objCat1.getId_cat();
				%>
				<li class="active"><a href="<%=request.getContextPath()%>/cat?id_cat=<%=objCat1.getId_cat()%>"><%=objCat1.getName() %></a></li>
				<% } %>
			</ol>
		</div>
	</div>
</section>

<div class="container">
	<div class="row">
		<%
			if (request.getAttribute("objCat1") != null) {
				Category objCat1 = (Category) request.getAttribute("objCat1");
				String urlSlug =  request.getContextPath()+"/" + StringLibrary.makeSlug(objCat1.getName())+"-" + objCat1.getId_cat();
				if (request.getAttribute("listNews") != null) {
					ArrayList<News> listNews = (ArrayList<News>) request.getAttribute("listNews");
		%>
		<div class="col-md-8">
			<%
				if (listNews.size() > 0) {
					String urlSlugNews = request.getContextPath()+"/" +StringLibrary.makeSlug(listNews.get(0).getName())+"-" +listNews.get(0).getId_news()+".html";
			%>
			<div class="entity_wrapper">

				<div class="entity_title header_purple">
					<h1>
						<a
							href="<%=request.getContextPath()%>/cat?id_cat=<%=objCat1.getId_cat()%>"
							><%=objCat1.getName()%></a>
					</h1>
				</div>
				<!-- entity_title -->

				<div class="entity_thumb">
					<img class="img-responsive"
						src="<%=request.getContextPath()%>/templates/public/img/category_img_top.jpg"
						alt="feature-top">
				</div>
				<!-- entity_thumb -->

				<div class="entity_title">
					<a
						href="<%=request.getContextPath()%>/detail?id_news=<%=listNews.get(0).getId_news()%>"
						target="_blank"><h3>
							<%=listNews.get(0).getName()%></h3></a>
				</div>
				<!-- entity_title -->

				<div class="entity_meta">
					<a><%=sdft.format(listNews.get(0).getDate_create())%></a>,
					by: <a><%=listNews.get(0).getNameAuthor()%></a>
				</div>
				<!-- entity_meta -->

				<div class="entity_content">
					<%=listNews.get(0).getDescription()%>
				</div>
				<!-- entity_content -->

				<div class="entity_social">
					<span><i class="fa fa-eye"></i><%=listNews.get(0).getViews()%><a
						>Views</a> </span> <span><i class="fa fa-comments-o"></i><%=listNews.get(0).getComment()%>
						<a>Comments</a> </span>
				</div>
				<!-- entity_social -->

			</div>

			<%
				}
			%>
			<!-- entity_wrapper -->
			<%
				if (listNews.size() > 1) {
							for (int i = 1; i <= (listNews.size()) / 2; i++) {
			%>

			<div class="row">
				<%
					for (int j = i * 2 - 1; j <= i * 2 && j < listNews.size(); j++) {
						String urlSlugNews = request.getContextPath()+"/" +StringLibrary.makeSlug(listNews.get(j).getName())+"-" +listNews.get(j).getId_news()+".html";
				%>
				<div class="col-md-6">
					<div class="category_article_body">
						<div class="top_article_img">
							<img style="width: 350px; height: 190px" class="img-fluid"
								src="<%=request.getContextPath()%>/files/<%=listNews.get(j).getPicture()%>"
								alt="feature-top">
						</div>
						<!-- top_article_img -->

						<div class="category_article_title">
							<h5>
								<a
									href="<%=request.getContextPath()%>/detail?id_news=<%=listNews.get(j).getId_news()%>"
									target="_blank"><%=listNews.get(j).getName()%></a>
							</h5>
						</div>
						<!-- category_article_title -->

						<div class="article_date">
							<a><%=sdft.format(listNews.get(j).getDate_create())%></a>,
							by: <a><%=listNews.get(j).getNameAuthor()%></a>
						</div>
						<!-- article_date -->

						<div class="category_article_content">
							<%=listNews.get(j).getDescription()%>
						</div>
						<!-- category_article_content -->

						<div class="article_social">
							<span><a><i class="fa fa-eye"></i><%=listNews.get(j).getViews()%></a>
								Views</span> <span><i class="fa fa-comments-o"></i><a><%=listNews.get(j).getComment()%></a>
								Comments</span>
						</div>
						<!-- article_social -->

					</div>
					<!-- category_article_body -->

				</div>
				<!-- col-md-6 -->
				<%
					}
				%>


			</div>
			<!-- row -->

			<div class="widget_advertisement">
				<img class="img-responsive"
					src="<%=request.getContextPath()%>/templates/public/img/category_advertisement.jpg"
					alt="feature-top">
			</div>
			<!-- widget_advertisement -->

			<%
				}
						}
			%>

			<nav aria-label="Page navigation" class="pagination_section">
				<ul class="pagination">
					<%
					int current_page = (int) request.getAttribute("current_page");
					int sumPage = (int) request.getAttribute("sumPage");
					if(sumPage > 1 ) {
					if(current_page > 1) { 
						String urlSlugCatPag = request.getContextPath() + "/" + StringLibrary.makeSlug(objCat1.getName()) + "-" + objCat1.getId_cat() +"/page-" + (current_page -1);
					%>
					<li><a href="<%=request.getContextPath()%>/cat?id_cat=<%=objCat1.getId_cat()%>&page=<%=current_page -1 %>" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>
					</a></li>
					<% } 
					for(int i = 1; i <= sumPage;i++)  {
						String urlSlugCatPag = request.getContextPath() + "/" + StringLibrary.makeSlug(objCat1.getName()) + "-" + objCat1.getId_cat() +"/page-" + i;
					if(i==current_page) {
					%>
					<li><a class="active" href="<%=request.getContextPath()%>/cat?id_cat=<%=objCat1.getId_cat()%>&page=<%=i %>"><%=i %></a></li>
					<% } else { %>
					<li><a href="<%=urlSlugCatPag%>"><%=i %></a></li>
					<% } } %>
					<% if(current_page < sumPage) { 
						String urlSlugCatPag = request.getContextPath() + "/" + StringLibrary.makeSlug(objCat1.getName()) + "-" + objCat1.getId_cat() +"/page-" + (current_page + 1);
					%>
					<li><a href="<%=request.getContextPath()%>/cat?id_cat=<%=objCat1.getId_cat()%>&page=<%=current_page + 1 %>" aria-label="Next"> <span
							aria-hidden="true">&raquo;</span>
					</a></li>
					<% } }%>
				</ul>
			</nav>
			<!-- navigation -->

		</div>
		<!-- col-md-8 -->
		<%
			}
			}
		%>
		<%@include file="templates/public/inc/rightSession.jsp"%>
		<!-- col-md-4 -->

	</div>
	<!-- row -->

</div>
<!-- container -->
<%@include file="templates/public/inc/footer.jsp"%>