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
					case 0:
						out.print("<h5 style='font-style: italic; color:red'>Tên đăng nhập đã tôn tại</h5>");break;
					case 5:
						out.print("<h5 style='font-style: italic; color:red'>Bạn cần nhập đủ các trường yêu cầu theo đúng định dạng</h5>");break;
					case 6:
						out.print("<h5 style='font-style: italic; color:red'>Tên danh mục đã tồn tại</h5>");break;
					default: out.print("<h5 style='font-style: italic; color:red'>Có lỗi trong quá trình xử lý</h5>");break;
				}
			}
		%>
      <!-- Example DataTables Card-->
      <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-plus"></i> Thêm User</div>
        <div class="card-body">
          <div style="width:60%;margin-left: 20%" class="table-responsive">
        <form id="formAdd" method="post" action="<%=request.getContextPath()%>/admin/user/add" enctype="multipart/form-data">
          <div class="form-group">
            <label for="exampleInputPassword1">Username</label>
            <input class="form-control" type="text" name="username" placeholder="Nhập Username" required="required" pattern="[a-zÁ-Ỵá-ỵA-Z0-9]{5,20}" requiredmsg="Nhập username theo đúng định dạng chỉ chứa chử cái chữ số tối thiểu 5 ký tự, tối đa 20 ký tự" >
             <label for="exampleInputPassword1">Fullname</label>
            <input class="form-control" type="text" name="fullname" value="" placeholder="Nhập Fullname" required="required" pattern="[a-z Á-Ỵá-ỵA-Z0-9]{5,20}" requiredmsg="Nhập fullname theo đúng định dạng chỉ chứa chử cái chữ số,khoảng trắng tối thiểu 5 ký tự, tối đa 20 ký tự">
            <label for="exampleInputPassword1">Password</label>
            <input class="form-control" type="password" name="password" placeholder="Nhập Password" required="required" pattern="[a-zÁ-Ỵá-ỵA-Z0-9]{5,20}" requiredmsg="Nhập mật khẩu theo đúng định dạng chỉ chứa chử cái chữ số tối thiểu 5 ký tự, tối đa 20 ký tự">
            <label for="exampleInputPassword1">Email</label>
            <input class="form-control" type="text" name="email" placeholder="Nhập Email" required="required" required title="email" pattern="[a-zÁ-Ỵá-ỵA-Z]{1,1}[a-z Á-Ỵá-ỵA-Z0-9]*@[a-zÁ-Ỵá-ỵA-Z]+.com" requiredmsg="Nhập đúng định dạng email" >
            <label for="exampleInputPassword1">Hình ảnh</label>
            <input class="form-control" type="file" name="picture">
          </div>
          <input class="btn btn-primary btn-block" type="submit" value="Thêm User">
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
