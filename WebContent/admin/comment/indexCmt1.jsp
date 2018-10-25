<%@page import="java.util.HashMap"%>
<%@page import="model.bean.Comment"%>
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
      <!-- Example DataTables Card-->
      <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i> Comment Level 1 Table</div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
              <thead>
                <tr>
                  <th>Id Comment</th>
                  <th>Tên tin</th>
                  <th>Nội dung cmt</th>
                  <th>Người Comment</th>
                  <th>Thay đổi</th>
                </tr>
              </thead>
              <tfoot>
                <tr>
               	  <th>Id Comment</th>
                  <th>Tên tin</th>
                  <th>Nội dung cmt</th>
                  <th>Người Comment</th>
                  <th>Thay đổi</th>
              </tfoot>
              <tbody>
              <%
              	if(request.getAttribute("mapCmt")!= null)
              	{
					HashMap<Comment,Integer> mapCmt = (HashMap<Comment,Integer>)request.getAttribute("mapCmt");         		
              	if(request.getAttribute("listCmt1")!= null)
              	{
					ArrayList<Comment> listCmt =   (ArrayList<Comment>)request.getAttribute("listCmt1");
					if(listCmt.size() > 0)
					{
						for(Comment objCmt : listCmt)
						{
              %>
                <tr>
                  <td><%=objCmt.getId_cmt() %></td>
                  <td><%=objCmt.getTenBaiViet() %></td>
                  <td><%=objCmt.getContent() %></td>
                  <td>
                  <% if(objCmt.getAvatar() != null) { %>
                  	<img style="width:70px;height:70px;border-radius: 50%" src="<%=request.getContextPath()%>/files/<%=objCmt.getAvatar()%>">
                  	<br>
                  <%} else { %>
                  	<img style="width:70px;height:70px;border-radius: 50%" src="<%=request.getContextPath()%>/files/notfound.jpg">
                  	<br>
                  	<%} %>
                  	<span style="font-weight: bold;"><%=objCmt.getName_user() %></span>
                  </td>
                  <td>
			          <a style="display: inline"  onclick="return confirm('Bạn có chắc muốn xóa không')" class="nav-link" href="<%=request.getContextPath()%>/admin/cmt_level1/del?id_cmt=<%=objCmt.getId_cmt()%>">
			            <i style="display: inline" class="fa fa-fw fa-trash-o"></i>
			            <span style="display: inline" class="nav-link-text">Xóa</span>
			          </a>
			          <% if(mapCmt.get(objCmt).intValue() > 0) {%>
			          <a style="display: inline" class="nav-link" href="<%=request.getContextPath()%>/admin/cmt_level2?id_cmt=<%=objCmt.getId_cmt()%>">
			            <i style="display: inline" class="fa fa-fw fa-eye"></i>
			            <span style="display: inline" class="nav-link-text">Views</span>
			          </a>
			          <% } %>
			          <% if(objCmt.getStatus() == 1) {
			          %>
			          <div id="ajax_id" style="display: inline">
			          <a style="display: inline" onclick ="edit(<%=objCmt.getId_cmt()%>)" class="nav-link" href="javascript:void(0)">
			            <i style="display: inline" class="fa fa-fw fa-check"></i>
			            <span id="id_ajax" style="display: inline" class="nav-link-text">Hiển thị</span>
			          </a>
			          </div>
			          <%} else { %>
			          <div id="ajax_id" style="display: inline">
			           <a  style="display: inline"  onclick ="edit(<%=objCmt.getId_cmt()%>)" class="nav-link" href="javascript:void(0)">
			            <i style="display: inline" class="fa fa-fw fa-square-o"></i>
			            <span id="id_ajax" style="display: inline" class="nav-link-text">Ẩn</span>
			          </a>
			          </div>
			          <%} %>
                  </td>
                </tr>
              <%}}}} %>
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
    <script>
				
						
				function edit(id_cmt_tmp)
				{
					var id = "#id_ajax";
					var status_tmp = $(id).text();
					$.ajax({
						url: '<%=request.getContextPath()%>/admin/cmt/change',
						type: 'POST',
						cache: false,
						data: {
								id_cmt : id_cmt_tmp,
								status : status_tmp,
								},
						success: function(data){
							// Xử lý thành công
							$('#ajax_id').html(data);
								
						},
						error: function (){
						// Xử lý nếu có lỗi
								alert('lỗi rồi');
						}
					});
				}
				
				
	</script>
  </div>
</body>

</html>
