<%@page import="java.util.HashMap"%>
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
      	 <a style="background-color:#343A40;color:white;display: inline;margin: 10px;border-radius: 5px  " class="nav-link" href="<%=request.getContextPath()%>/admin/cat/add">
            <i class="fa fa-fw fa-plus"></i>
            <span class="nav-link-text">Thêm danh mục</span>
          </a>
      </div>
      <br>
      <%
			if(request.getParameter("msg")!=null){
				int msg = Integer.parseInt(request.getParameter("msg"));
				switch(msg){
				case 1:
					out.print("<h5 style='color:green;font-style:italic'>Thêm thành công</h5>");break;
				case 2:
					out.print("<h5 style='color:green;font-style:italic'>Sửa thành công</h5>");break;
				case 3:
					out.print("<h5 style='color:green;font-style:italic'>Xóa thành công</h5>");break;
				default: out.print("<h3 style='color:red;font-style:italic'>Có lỗi trong quá trình xử lý</h3>");break;
					
				}
			}
		%>
      <!-- Example DataTables Card-->
      <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i> Category Table</div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered"  id="dataTable" width="100%" cellspacing="0">
              <thead>
                <tr>
                  <th>Id Danh mục tin</th>
                  <th>Tên danh mục</th>
                  <th>Độ ưu tiên</th>
                  <th>Thay đổi</th>
                </tr>
              </thead>
              <tfoot>
                <tr>
                 <th style="width:20%">Id Danh mục tin</th>
                  <th>Tên danh mục</th>
                  <th>Độ ưu tiên</th>
                  <th style="width:20%">Thay đổi</th>
                </tr>
              </tfoot>
              <tbody>
              <%
              	if(request.getAttribute("mapCat") != null) {
              	HashMap<Category,ArrayList<Category>> mapCat = (HashMap<Category,ArrayList<Category>>)request.getAttribute("mapCat");
              	if(request.getAttribute("listCat")!= null)
              	{
					ArrayList<Category> listCat =   (ArrayList<Category>)request.getAttribute("listCat");
					if(listCat.size() > 0)
					{
						for(Category objCat : listCat)
						{
							ArrayList<Category> listSubCat = mapCat.get(objCat);
              %>
                <tr>
                  <td style="text-align: left">
                  <span style="text-align: left"><%=objCat.getId_cat() %></span>
                  <%if(listCat.size() > 0) { %>
                  <ul style="list-style: none">
                	<%  for(Category objSubCat : listSubCat)
						{	
                	  %>
                  	<li style="text-align: center"><%=objSubCat.getId_cat() %></li>
                  <% } %>
                  </ul>
                  <%} %>	
                  </td>
                  <td style="text-align: left">
                  <span style="text-align: left"><%=objCat.getName() %></span>
                  <%if(listCat.size() > 0) {%>
                	<ul style="list-style: none"> 
                	 <% for(Category objSubCat : listSubCat)
						{	
                	  %>
                  	<li style="text-align: center"><%=objSubCat.getName() %></li>
                  <% } %>
                  </ul>
                  <% } %>
                  </td>
                  <td>
                   <span style="text-align: left"><%=objCat.getPriority() %></span>
                   <%if(listCat.size() > 0) { %>
                  <ul style="list-style: none">
                	<%  for(Category objSubCat : listSubCat)
						{	
                	  %>
                  	<li style="text-align: center"><%=objSubCat.getPriority() %></li>
                  <% } %>
                  </ul>
                  <%} %>
                  </td>
                  <td>
			          <a style="display: inline"  onclick="return confirm('Bạn có chắc muốn xóa không')"  class="nav-link" href="<%=request.getContextPath()%>/admin/cat/del?id_cat=<%=objCat.getId_cat()%>">
			            <i style="display: inline" class="fa fa-fw fa-trash-o"></i>
			            <span style="display: inline" class="nav-link-text">Xóa</span>
			          </a>
			          <a style="display: inline" class="nav-link" href="<%=request.getContextPath()%>/admin/cat/edit?id_cat=<%=objCat.getId_cat()%>">
			            <i style="display: inline" class="fa fa-fw fa-pencil-square-o"></i>
			            <span style="display: inline" class="nav-link-text">Sửa</span>
			          </a>
			          <%if(listCat.size() > 0) {
                	  for(Category objSubCat : listSubCat)
						{	
                	  %>
                  	<br>
                  	<a style="display: inline"   class="nav-link" href="<%=request.getContextPath()%>/admin/cat/del?id_cat=<%=objSubCat.getId_cat()%>">
			            <i style="display: inline" class="fa fa-fw fa-trash-o"></i>
			            <span style="display: inline" class="nav-link-text">Xóa</span>
			          </a>
			          <a style="display: inline" class="nav-link" href="<%=request.getContextPath()%>/admin/cat/edit?id_cat=<%=objSubCat.getId_cat()%>">
			            <i style="display: inline" class="fa fa-fw fa-pencil-square-o"></i>
			            <span style="display: inline" class="nav-link-text">Sửa</span>
			          </a>	
			         <% }} %>
                  </td>
                </tr>
              <%}}} }%>
                
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
