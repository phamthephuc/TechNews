<%@page import="library.StringLibrary"%>
<%@page import="model.bean.User"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.CatDao"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TechNews - HTML and CSS Template</title>

    <!-- favicon -->
    <link href="<%=request.getContextPath() %>/templates/public/img/favicon.png" rel=icon>

    <!-- web-fonts -->
    <link href='https://fonts.googleapis.com/css?family=Roboto:100,300,400,700,500' rel='stylesheet' type='text/css'>

    <!-- Bootstrap -->
    <link href="<%=request.getContextPath() %>/templates/public/css/bootstrap.min.css" rel="stylesheet">

    <!-- font-awesome -->
    <link href="<%=request.getContextPath() %>/templates/public/fonts/font-awesome/font-awesome.min.css" rel="stylesheet">
    <!-- Mobile Menu Style -->
    <link href="<%=request.getContextPath() %>/templates/public/css/mobile-menu.css" rel="stylesheet">

    <!-- Owl carousel -->
    <link href="<%=request.getContextPath() %>/templates/public/css/owl.carousel.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/templates/public/css/owl.theme.default.min.css" rel="stylesheet">
    <!-- Theme Style -->
    <link href="<%=request.getContextPath() %>/templates/public/css/style.css" rel="stylesheet">
	<style type="text/css">
		ul.pagination li a.active 
		{
			background-color : #3AC6DC;
		}
	</style>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body id="page-top" data-spy="scroll" data-target=".navbar">
<div id="main-wrapper">
<!-- Page Preloader -->
<div id="preloader">
    <div id="status">
        <div class="status-mes"></div>
    </div>
</div>
<!-- preloader -->

<div class="uc-mobile-menu-pusher">
<div class="content-wrapper">
<% SimpleDateFormat sdft = new SimpleDateFormat("ddMMM-yyyy");
	
%>
<section id="header_section_wrapper" class="header_section_wrapper">
    <div class="container">
        <div class="header-section">
            <div class="row">
                <div class="col-md-4">
                    <div class="left_section">
                    <% SimpleDateFormat sdf = new SimpleDateFormat("EEEEE . dd MMMMM . yyyy");
                    	Date date = new Date();
                    	String dateString = sdf.format(date);
                    %>
                                            <span class="date">
                                            <%=dateString %>
                                            </span>
                        <!-- Date -->
                        <!-- Time -->
                        <div class="social">
                            <a target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=https://www.facebook.com/phuc.pham.5268" class="icons-sm fb-ic"><i class="fa fa-facebook"></i></a>
                            <!--Twitter-->
                            <a target="_blank" href="https://twitter.com/share?text=&url=https://www.facebook.com/phuc.pham.5268" class="icons-sm tw-ic"><i class="fa fa-twitter"></i></a>
                            <!--Google +-->
                            <a target="_blank" href="https://plus.google.com/share?url=https://www.facebook.com/phuc.pham.5268" class="icons-sm inst-ic"><i class="fa fa-instagram"> </i></a>
                            <!--Linkedin-->
                            <a target="_blank" href="https://www.pinterest.com/pin/create/button/?url=https://www.facebook.com/phuc.pham.5268" class="icons-sm tmb-ic"><i class="fa fa-tumblr"> </i></a>
                            <!--Pinterest-->
                            <a target="_blank" href="https://www.pinterest.com/pin/create/button/?url=https://www.facebook.com/phuc.pham.5268" class="icons-sm rss-ic"><i class="fa fa-rss"> </i></a>
                        </div>
                        <!-- Top Social Section -->
                    </div>
                    <!-- Left Header Section -->
                </div>
                <div class="col-md-4">
                    <div class="logo">
                        <a href="<%=request.getContextPath()%>"><img src="<%=request.getContextPath() %>/templates/public/img/logo.png" alt="Tech NewsLogo"></a>
                    </div>
                    <!-- Logo Section -->
                </div>
                <div class="col-md-4">
                    <div class="right_section">
                        
                     <ul class="nav navbar-nav">
                            <li class="dropdown"><a href="#" data-toggle="dropdown" class="dropdown-toggle"><i
                                    class="fa fa-search"></i></a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <div class="head-search">
                                            <form role="form" action="<%=request.getContextPath() %>/search" method="get">
                                                <!-- Input Group -->
                                                <div class="input-group">
                                                    <input name="key" onmouseup="search()" onkeyup="search()" id="idkey_tmp" type="text" class="form-control"
                                                           placeholder="Type Something"> <span class="input-group-btn">
                                                                            <button type="submit"
                                                                                    class="btn btn-primary">Search
                                                                            </button>
                                                                        </span></div>
                                            </form>
                                        </div>
                                        <div class="head-search" id="ajax_search">
                                    		
                                    	</div>
                                    </li>
                                    
                                </ul>
                            </li>
                        </ul>
                        <!-- Search Section -->
                    </div>
                    <!-- Right Header Section -->
                </div>
            </div>
        </div>
        <!-- Header Section -->

        <div class="navigation-section">
            <nav class="navbar m-menu navbar-default">
                <div class="container">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                                data-target="#navbar-collapse-1"><span class="sr-only">Toggle navigation</span> <span
                                class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
                        </button>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="#navbar-collapse-1">
                        <ul class="nav navbar-nav main-nav">
                            <li><a href="<%=request.getContextPath()%>">News</a></li>
                            <% CatDao cd = new CatDao();
                    			ArrayList<Category> listRootCat = cd.getRootCat();
                    			if(listRootCat.size()>0){
                    				for(Category objRootCat : listRootCat) {
                    					ArrayList<Category> listSubCat = cd.getItemsByIdParent(objRootCat.getId_cat());
                    					if(listSubCat.size() > 0) {
                    						String urlSlug =  request.getContextPath()+"/" + StringLibrary.makeSlug(objRootCat.getName())+"-" + objRootCat.getId_cat();
                    		%>
                            <li style="border:none;background-color:white" class="dropdown">
								<a href="<%=request.getContextPath()%>/cat?id_cat=<%=objRootCat.getId_cat()%>" style="border:none;background-color:white" class="btn btn-primary dropdown-toggle" >
								<%=objRootCat.getName() %>
								<span class="caret"></span></a>
								<ul class="dropdown-menu">
								<% for(Category objSubCat : listSubCat) { 
									urlSlug =  request.getContextPath()+"/" + StringLibrary.makeSlug(objSubCat.getName())+"-" + objSubCat.getId_cat();
								%>
								  <li><a href="<%=request.getContextPath()%>/cat?id_cat=<%=objSubCat.getId_cat()%>"><%=objSubCat.getName() %></a></li>
								 <% } %>
								</ul>
							</li>
                           <% } else { 
                        	   String urlSlug =  request.getContextPath()+"/" + StringLibrary.makeSlug(objRootCat.getName())+"-" + objRootCat.getId_cat();
                           %>
                            <li><a href="<%=request.getContextPath()%>/cat?id_cat=<%=objRootCat.getId_cat()%>"><%=objRootCat.getName() %></a></li>
                            <% } } } %>
                            
                        </ul>
                    </div>
                    <!-- .navbar-collapse -->
                </div>
                <!-- .container -->
            </nav>
            <!-- .nav -->
        </div>
        <!-- .navigation-section -->
    </div>
    <!-- .container -->
</section>
<!-- header_section_wrapper -->
