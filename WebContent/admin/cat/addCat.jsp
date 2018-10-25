<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Category"%>
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
        <li class="breadcrumb-item active">Tables</li>
      </ol>
      
      <%
			if(request.getParameter("msg")!=null){
				int msg = Integer.parseInt(request.getParameter("msg"));
				switch(msg){
				case 5:
					out.print("<h5 style='color:red;font-style:italic'>Bạn cần nhập đủ các trường yêu cầu theo đúng định dạng</h5>");break;
				case 6:
					out.print("<h5 style='color:red;font-style:italic'>Tên danh mục đã tồn tại</h5>");break;
				default: out.print("<h5 style='color:red;font-style:italic'>Có lỗi trong quá trình xử lý</h5>");break;
					
				}
			}
		%>
      <!-- Example DataTables Card-->
      <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-plus"></i> Thêm danh mục</div>
        <div class="card-body">
          <div style="width:60%;margin-left: 20%" class="table-responsive">
        <form method="post" action="<%=request.getContextPath()%>/admin/cat/add" >
          <div class="form-group">
            <label for="exampleInputPassword1">Tên danh mục</label>
            <input class="form-control" type="text" name="tendanhmuc" placeholder="Nhập tên danh mục" required="required" required maxlength="50" required minlength="6" pattern="[a-z Á-Ỵá-ỵA-Z0-9]{6,20}" requiredmsg="Nhập trường này theo đúng định dạng chỉ chứa chử cái chữ số và khoảng trắng tối thiểu 5 ký tự, tối đa 50 ký tự" >
            <label for="exampleInputPassword1">Chọn danh mục cha</label>
            <select class="form-control" name="parent">
            	<option value="0">Là danh mục gốc</option>
            	<% if(request.getAttribute("rootCats")!=null) { 
            	ArrayList<Category> rootCats = (ArrayList<Category>)request.getAttribute("rootCats");
            	for(Category objRootCat : rootCats) {
            	%>
            	<option value="<%=objRootCat.getId_cat() %>"><%=objRootCat.getName() %></option>
            	<% } } %>
            </select>
            <label for="exampleInputPassword1">Chọn thứ tự</label>
            <select class="form-control" name="priority">
            	<% if(request.getAttribute("soLuong")!=null) { 
            	int soLuong = (int)request.getAttribute("soLuong");
            	for(int i=1;i<=soLuong + 1;i++) {
            	%>
            	<option value="<%=i %>"><%=i %></option>
            	<% } } %>
            </select>
          </div>
          <input class="btn btn-primary btn-block" type="submit" value="Thêm danh mục">
        </form>
          </div>
        </div>
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
  	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
	    var elements = $("input,textarea,select");
	    for (var i = 0; i < elements.length; i++) {
	        elements[i].oninvalid = function(e) {
	            e.target.setCustomValidity("");
	            if (!e.target.validity.valid) {
	                 e.target.setCustomValidity(e.target.getAttribute("requiredmsg"));
	            }
	        };
	        elements[i].oninput = function(e) {
	            e.target.setCustomValidity("");
	        };
	    }
	})
</script>
  </div>
</body>

</html>
