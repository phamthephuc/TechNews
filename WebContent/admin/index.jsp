<%@page import="model.bean.Comment"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.bean.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../../templates/admin/inc/header.jsp" %>
  <div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="<%=request.getContextPath()%>/admin">Dashboard</a>
        </li>
        <li class="breadcrumb-item active">My Dashboard</li>
      </ol>
      <% if(request.getAttribute("numberNews")!= null && request.getAttribute("numberComment")!= null && request.getAttribute("numberForgot")!= null && request.getAttribute("numberSubcribe")!= null) 
      {
    	 	int numberNews = (int)request.getAttribute("numberNews");
    	 	int numberComment = (int) request.getAttribute("numberComment");
    	 	int numberForgot = (int)request.getAttribute("numberForgot");
    	 	int numberSubcribe =(int)request.getAttribute("numberSubcribe");
      %>
      <!-- Icon Cards-->
      <div class="row">
        <div class="col-xl-3 col-sm-6 mb-3">
          <div class="card text-white bg-primary o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-comments"></i>
              </div>
              <div class="mr-5"><%=numberComment %> Bình luận mới!</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="<%=request.getContextPath()%>/admin/cmt_level1">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        <div class="col-xl-3 col-sm-6 mb-3">
          <div class="card text-white bg-warning o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-list"></i>
              </div>
              <div class="mr-5"><%=numberNews %> Tin mới!</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="<%=request.getContextPath()%>/admin/news">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        <div class="col-xl-3 col-sm-6 mb-3">
          <div class="card text-white bg-success o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-address-card-o"></i>
              </div>
              <div class="mr-5"><%=numberForgot %> Tin lấy mật khẩu!</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="<%=request.getContextPath()%>/admin/message?type=2">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        <div class="col-xl-3 col-sm-6 mb-3">
          <div class="card text-white bg-danger o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-address-card-o"></i>
              </div>
              <div class="mr-5"><%=numberSubcribe %> Người dùng đăng ký web!</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="<%=request.getContextPath()%>/admin/message?type=1">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
      </div>
      
      <% } %>
      <!-- Area Chart Example-->
      <div class="row">
        <div class="col-lg-8">
          <!-- Example Bar Chart Card-->
          
          <!-- Card Columns Example Social Feed-->
          <div class="mb-0 mt-4">
            <i class="fa fa-newspaper-o"></i> News Feed</div>
          <hr class="mt-2">
          <div class="card-columns">
            <!-- Example Social Card-->
            
            <% if(request.getAttribute("listNews") != null)
            {
				ArrayList<News> listNews = (ArrayList<News>)request.getAttribute("listNews");    
				for(News objNews : listNews) {
            	%>
            <!-- Example Social Card-->
            <div class="card mb-3">
              <a href="#">
                <img class="card-img-top img-fluid w-100" src="<%=request.getContextPath() %>/files/<%=objNews.getPicture() %>" alt="">
              </a>
              <div class="card-body">
                <h6 class="card-title mb-1"><a><%=objNews.getNameAuthor() %></a></h6>
                <p class="card-text small">
                  <a href="<%=request.getContextPath()%>/detail?id_news=<%=objNews.getId_news()%>"><%=objNews.getName() %></a>
                </p>
              </div>
              <hr class="my-0">
              <div class="card-body py-2 small">
                <a class="mr-3 d-inline-block">
                  <i class="fa fa-fw fa-eye"></i><%=objNews.getViews() %> Views</a>
                <a class="mr-3 d-inline-block">
                  <i class="fa fa-fw fa-comment"></i><%=objNews.getComment() %> Comment</a>
              </div>
              <div class="card-footer small text-muted">Posted at <%=(new SimpleDateFormat("ddMMM- yyyy hh:mm:ss")).format(objNews.getDate_create()) %></div>
            </div>
          <% } } %>  
            
            
            <!-- Example Social Card-->
           
          </div>
          <!-- /Card Columns-->
        </div>
        <div style="padding-top: 30px" class="col-lg-4">
          <!-- Example Notifications Card-->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fa fa-bell-o"></i> Các comment gần đây</div>
            <div class="list-group list-group-flush small">
            <%if(request.getAttribute("listComment") != null) { 
            	ArrayList<Comment> listComment = (ArrayList<Comment>)request.getAttribute("listComment");
            	for(Comment objCmt : listComment) {
            		if(objCmt.getLevel() == 1) {
            	%>
               <a class="list-group-item list-group-item-action" href="<%=request.getContextPath()%>/detail?id_news=<%=objCmt.getId_bai() %>">
                <div class="media">
                <% if(objCmt.getAvatar().equals("")) { %>
                  <img style="width: 50px;height: 50px;border-radius: 50% " class="d-flex mr-3 rounded-circle" src="<%=request.getContextPath() %>/files/user.png" alt="">
                <% } else { %>
                <img style="width: 50px;height: 50px;border-radius: 50% " class="d-flex mr-3 rounded-circle" src="<%=request.getContextPath() %>/files/<%=objCmt.getAvatar() %>" alt="">
                <% } %>
                  <div class="media-body">
                    <strong><%=objCmt.getName_user() %></strong> đã comment vào bài viết 
                    <strong> <%=objCmt.getTenBaiViet() %></strong>.
                    <div class="text-muted smaller"> vào lúc <%=(new SimpleDateFormat("ddMMM- yyyy hh:mm:ss")).format(objCmt.getDate_creat())%> </div>
                  </div>
                </div>
              </a> 
              <% } else  { %>
              <a class="list-group-item list-group-item-action" href="<%=request.getContextPath()%>/detail?id_news=<%=objCmt.getId_bai() %>">
                <div class="media">
                <% if(objCmt.getAvatar().equals("")) { %>
                  <img style="width: 50px;height: 50px;border-radius: 50% " class="d-flex mr-3 rounded-circle" src="<%=request.getContextPath() %>/files/user.png" alt="">
                <% } else { %>
                <img style="width: 50px;height: 50px;border-radius: 50% " class="d-flex mr-3 rounded-circle" src="<%=request.getContextPath() %>/files/<%=objCmt.getAvatar() %>" alt="">
                <% } %>
                  <div class="media-body">
                    <strong><%=objCmt.getName_user() %></strong> đã trả lời comment
                    <strong> <%=objCmt.getTenBaiViet() %></strong>.
                    <div class="text-muted smaller"> vào lúc <%=(new SimpleDateFormat("ddMMM- yyyy hh:mm:ss")).format(objCmt.getDate_creat())%> </div>
                  </div>
                </div>
              </a> 
              <% } }%>
              <% } %>
              <a class="list-group-item list-group-item-action" href="<%=request.getContextPath()%>/admin/cmt_level1">Xem tất cả...</a>
            </div>
          </div>
        </div>
      </div>
      <!-- Example DataTables Card-->
      
    </div>
    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
    <%@include file="../../templates/admin/inc/footer.jsp" %>
    <!-- Bootstrap core JavaScript-->
    <script src="<%=request.getContextPath() %>/templates/admin/vendor/jquery/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="<%=request.getContextPath() %>/templates/admin/vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Page level plugin JavaScript-->
    <script src="<%=request.getContextPath() %>/templates/admin/vendor/chart.js/Chart.min.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/vendor/datatables/jquery.dataTables.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/vendor/datatables/dataTables.bootstrap4.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="<%=request.getContextPath() %>/templates/admin/js/sb-admin.min.js"></script>
    <!-- Custom scripts for this page-->
    <script src="<%=request.getContextPath() %>/templates/admin/js/sb-admin-datatables.min.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/js/sb-admin-charts.min.js"></script>
  </div>
</body>

</html>
