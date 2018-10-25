<%@page import="model.bean.Message"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.MessageDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>SB Admin - Start Bootstrap Template</title>
  <!-- Bootstrap core CSS-->
  <link href="<%=request.getContextPath() %>/templates/admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="<%=request.getContextPath() %>/templates/admin/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Custom styles for this template-->
  <link href="<%=request.getContextPath() %>/templates/admin/css/sb-admin.css" rel="stylesheet">
  <style type="text/css">
  	th,td{text-align: center;}
  	.nav-link1{padding: .1rem .1rem;}
  </style>
</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <a class="navbar-brand" href="<%=request.getContextPath()%>/admin">Admin Web</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
          <a class="nav-link" href="<%=request.getContextPath()%>/admin">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Dashboard</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Category">
          <a class="nav-link" href="<%=request.getContextPath()%>/admin/cat">
            <i class="fa fa-fw fa-tags"></i>
            <span class="nav-link-text">Category</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="News">
          <a class="nav-link" href="<%=request.getContextPath()%>/admin/news">
            <i class="fa fa-fw fa-newspaper-o "></i>
            <span class="nav-link-text"> News</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="User">
          <a class="nav-link" href="<%=request.getContextPath()%>/admin/user">
            <i class="fa fa-fw fa-user"></i>
            <span class="nav-link-text">User</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
          <a class="nav-link" href="<%=request.getContextPath()%>/admin/cmt_level1">
            <i class="fa fa-fw fa-comment-o"></i>
            <span class="nav-link-text">Comment</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components">
          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents" data-parent="#exampleAccordion">
            <i class="fa fa-fw fa-wrench"></i>
            <span class="nav-link-text">Components</span>
          </a>
          <ul class="sidenav-second-level collapse" id="collapseComponents">
            <li>
              <a href="<%=request.getContextPath()%>/admin/navbar">Navbar</a>
            </li>
          </ul>
        </li>
      </ul>
      <ul class="navbar-nav sidenav-toggler">
        <li class="nav-item">
          <a class="nav-link text-center" id="sidenavToggler">
            <i class="fa fa-fw fa-angle-left"></i>
          </a>
        </li>
      </ul>
       <% MessageDao md = new MessageDao();
          	 ArrayList<Message> listForgot = md.getUnseenByType(2);
          	 ArrayList<Message> listSubcribe = md.getUnseenByType(1);
          	 String background = "";
       %>
      <ul class="navbar-nav ml-auto">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle mr-lg-2" id="messagesDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-fw fa-envelope"></i>
            <span class="d-lg-none">Forgot
              <span class="badge badge-pill badge-primary"><%=listForgot.size() %> New</span>
            </span>
            <span class="indicator text-primary d-none d-lg-block">
              <i class="fa fa-fw fa-circle"></i>
            </span>
          </a>
         
         <% if(listForgot.size() > 0) { %>
          <div class="dropdown-menu" aria-labelledby="messagesDropdown">
            <h6 class="dropdown-header">Forgot Password:</h6>
             <% for(Message objMessage : listForgot) {
            	 if(objMessage.getSeen() == 0)
            	 background = "background-color: #3AC6DC";
            	 else background = "";
            	 %>
            <div class="dropdown-divider"></div>
            <a style="<%=background %>" class="dropdown-item" href="<%=request.getContextPath()%>/admin/message/forgot?id_message=<%=objMessage.getId_message()%>">
              <strong><%=objMessage.getEmail() %></strong>
              <div class="dropdown-message small">Quên mật khẩu</div>
            </a>
            <% } %>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item small" href="<%=request.getContextPath()%>/admin/message?type=2">Xem thêm </a>
          </div>
          <% } %>
        </li>
        <li class="nav-item dropdown">
          <a onclick="setRead()" class="nav-link dropdown-toggle mr-lg-2" id="alertsDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-fw fa-bell"></i>
            <span class="d-lg-none">Subcribe
              <span class="badge badge-pill badge-warning"><%=listSubcribe.size() %> New</span>
            </span>
            <span class="indicator text-warning d-none d-lg-block">
              <i class="fa fa-fw fa-circle"></i>
            </span>
          </a>
          <div class="dropdown-menu" aria-labelledby="alertsDropdown">
            <h6 class="dropdown-header">Subcribe</h6>
            <% for(Message objMessage : listSubcribe) {
            	 if(objMessage.getSeen() == 0)
                	 background = "background-color: #3AC6DC";
                	 else background = "";
            	
            	%>
            <div class="dropdown-divider"></div>
            <a style="<%=background %>" class="dropdown-item" href="<%=request.getContextPath()%>/admin/message?type=1">
              <span class="text-success">
                <strong style="color:black;">
                  <%=objMessage.getEmail() %></strong>
              </span>
              <div class="dropdown-message small">Đã subcribe web của bạn</div>
            </a>
            <% } %>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item small" href="<%=request.getContextPath()%>/admin/message?type=1">Xem thêm</a>
          </div>
        </li>
        <li class="nav-item">
          <form class="form-inline my-2 my-lg-0 mr-lg-2">
            <div class="input-group">
              <input class="form-control" type="text" placeholder="Search for...">
              <span class="input-group-btn">
                <button class="btn btn-primary" type="button">
                  <i class="fa fa-search"></i>
                </button>
              </span>
            </div>
          </form>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<%=request.getContextPath() %>/admin/logout" data-toggle="modal" data-target="#exampleModal">
            <i class="fa fa-fw fa-sign-out"></i>Logout</a>
        </li>
      </ul>
    </div>
  </nav>