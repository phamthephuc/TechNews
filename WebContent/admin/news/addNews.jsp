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
        <li class="breadcrumb-item active">Tables</li>
      </ol>
      <%
			if(request.getParameter("msg")!=null){
				int msg = Integer.parseInt(request.getParameter("msg"));
				switch(msg){
					case 5:
						out.print("<h5 style='color:red; font-style: italic'>Bạn cần nhập đủ các trường yêu cầu theo đúng định dạng</h5>");break;
					case 7:
						out.print("<h5 style='color:red; font-style: italic'>Tên bài đăng đã tồn tại</h5>");break;
					default: out.print("<h5 style='color:red; font-style: italic'>Có lỗi trong quá trình xử lý</h5>");break;
				}
			}
		%>
      <!-- Example DataTables Card-->
      <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-plus"></i> Thêm tin tức</div>
        <div class="card-body">
          <div style="width:60%;margin-left: 20%" class="table-responsive">
        <form method="post" action="<%=request.getContextPath()%>/admin/news/add" enctype="multipart/form-data">
          <div class="form-group">
            <label for="exampleInputPassword1">Tên tin tức</label>
            <input class="form-control" type="text" name="tentin" placeholder="Nhập tên tin" required="required" pattern='[^<@%#>]{5,500}' requiredmsg="Nhập tên tin theo đúng định dạng không chứa ký tự đặc biệt tối thiểu 5 ký tự, tối đa 500 ký tự">
            <label style="display: block" for="exampleInputPassword1">Loại danh mục</label>
           <select name="danhmuc">
           		  <%
	              	if(request.getAttribute("listCat")!= null)
	              	{
						ArrayList<Category> listCat =   (ArrayList<Category>)request.getAttribute("listCat");
						if(listCat.size() > 0)
						{
							for(Category objCat : listCat)
							{
	              %>
	              <option value="<%=objCat.getId_cat()%>"><%=objCat.getName() %></option>
	              <%}}} %>
           </select>
            <label  style="display: block"  for="exampleInputPassword1">Hình ảnh</label>
            <input class="form-control" type="file" name="hinhanh" required="required" requiredmsg="Bạn phải nhập hình ảnh chứ nhỉ" >
            <label style="display: block"  for="exampleInputPassword1">Mô tả</label>
            <textarea rows="6" cols="84"  name="mota" required="required" pattern='[^@%#]{20,1000}' requiredmsg="Nhập mô tả theo đúng định dạng không chứa ký tự đặc biệt tối thiểu 20 ký tự, tối đa 1000 ký tự"></textarea>
            <label style="display: block" for="exampleInputPassword1">Chi tiết</label>
             <textarea rows="6" cols="84" id="ck" name="chitiet"></textarea>
          </div>
          <input class="btn btn-primary btn-block" type="submit" value="Thêm tin">
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
    <script src="<%=request.getContextPath() %>/templates/admin/js/jquery.validate.min.js" ></script>
		
	<script src="<%=request.getContextPath() %>/libararies/ckeditor/ckeditor.js" ></script>
	<script src="<%=request.getContextPath() %>/libararies/ckfinder/ckfinder.js" ></script>
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
       		var editor = CKEDITOR.replace("ck");
       		CKFinder.setupCKEditor(editor,"libararies/ckfinder/");
     </script>
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
