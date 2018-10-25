<%@page import="model.bean.News"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../../templates/admin/inc/header.jsp" %>
  <div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="<%=request.getContextPath()%>/admin">Dashboard</a>
        </li>
      </ol>
      <div>
      	 <a style="background-color:#343A40;color:white;display: inline;margin: 10px;border-radius: 5px  " class="nav-link" href="<%=request.getContextPath()%>/admin/news/add">
            <i class="fa fa-fw fa-plus"></i>
            <span class="nav-link-text">Thêm tin tức</span>
          </a>
      </div>
      <br>
      <div id="ajax_id"></div>
      
      <%
		if(request.getParameter("msg")!=null){
			int msg = Integer.parseInt(request.getParameter("msg"));
			switch(msg){
			case 1:
				out.print("<h5 style='color:green; font-style: italic'>Thêm thành công</h5>");break;
			case 2:
				out.print("<h5 style='color:green; font-style: italic'>Sửa thành công</h5>");break;
			case 3:
				out.print("<h5 style='color:green; font-style: italic'>Xóa thành công</h5>");break;
			case 4:
				out.print("<h5 style='color:red; font-style: italic'>Chỉ có author của bài hoặc admin được phép sửa bài</h5>");break;
			default: out.print("<h5 style='color:green; font-style: italic'>Có lỗi trong quá trình xử lý</h5>");break;
	
			}
		}
	%>			
      <!-- Example DataTables Card-->
      <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i> News Table</div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
              <thead>
                <tr>
                  <th>Id Tin tức</th>
                  <th style="width:25%">Tên tin</th>
                  <th>Thuộc danh mục</th>
                  <th>Hình ảnh</th>
                  <th>Tác giả</th>
                  <th>Lượt view</th>
                  <th>Thay đổi</th>
                </tr>
              </thead>
              <tfoot>
                <tr>
               	  <th>Id Tin tức</th>
                  <th>Tên tin</th>
                  <th>Thuộc danh mục</th>
                  <th>Hình ảnh</th>
                  <th>Tác giả</th>
                  <th>Lượt view</th>
                  <th>Thay đổi</th>
              </tfoot>
              <tbody>
              <%
              	if(request.getAttribute("listNews")!= null)
              	{
					ArrayList<News> listNews =   (ArrayList<News>)request.getAttribute("listNews");
					if(listNews.size() > 0)
					{
						for(News objNews : listNews)
						{
              %>
                <tr>
                  <td><%=objNews.getId_news() %></td>
                  <td><%=objNews.getName() %></td>
                  <td><%=objNews.getCatName() %></td>
                  <td>
                  	<img style="width: 100px;height: 70px" src="<%=request.getContextPath()%>/files/<%=objNews.getPicture()%>">
                  </td>
                  <td>
                  	<img style="width:70px;height:70px;border-radius: 50%" src="<%=request.getContextPath()%>/files/<%=objNews.getAvatar()%>">
                  	<br>
                  	<span style="font-weight: bold;"><%=objNews.getNameAuthor() %></span>
                  </td>
                  <td><%=objNews.getViews() %></td>
                  <td>
			          <a style="display: inline" onclick="return confirm('Bạn có chắc muốn xóa không')"  class="nav-link" href="<%=request.getContextPath()%>/admin/news/del?id_news=<%=objNews.getId_news()%>">
			            <i style="display: inline" class="fa fa-fw fa-trash-o"></i>
			            <span style="display: inline" class="nav-link-text">Xóa</span>
			          </a>
			          <a style="display: inline" class="nav-link" href="<%=request.getContextPath()%>/admin/news/edit?id_news=<%=objNews.getId_news()%>">
			            <i style="display: inline" class="fa fa-fw fa-pencil-square-o"></i>
			            <span style="display: inline" class="nav-link-text">Sửa</span>
			          </a>
                  </td>
                </tr>
              <%}}} %>
              </tbody>
            </table>
          </div>
        </div>
        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
      </div>
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
    <script src="<%=request.getContextPath() %>/templates/admin/vendor/datatables/jquery.dataTables.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/vendor/datatables/dataTables.bootstrap4.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="<%=request.getContextPath() %>/templates/admin/js/sb-admin.min.js"></script>
    <!-- Custom scripts for this page-->
    <script src="<%=request.getContextPath() %>/templates/admin/js/sb-admin-datatables.min.js"></script>
  </div>
</body>

</html>
