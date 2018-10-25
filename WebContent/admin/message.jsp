<%@page import="model.bean.User"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.bean.Message"%>
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
			if(request.getAttribute("type")!=null){
				User objUser = (User)session.getAttribute("user");
				int type = (int)(request.getAttribute("type"));
		%>
      <!-- Example DataTables Card-->
      <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i> Message Table</div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered"  id="dataTable" width="100%" cellspacing="0">
              <thead>
                <tr>
                	<th>Id</th>
                  <th>Email</th>
                  <th>Loại</th>
                  
                  <% if(type == 2) {%>
                  <th>Thay đổi</th>
                  <% } %>
                </tr>
              </thead>
              <tfoot>
                <tr>
                 <th>Id</th>
                  <th>Email</th>
                  <th>Loại</th>
                  
                  <% if(type == 2) {%>
                  <th>Thay đổi</th>
                  <% } %>
                </tr>
              </tfoot>
              <tbody>
              <%
              	
              	if(request.getAttribute("listMessage")!= null)
              	{
					ArrayList<Message> listMessage =   (ArrayList<Message>)request.getAttribute("listMessage");
					if(listMessage.size() > 0)
					{
						for(Message objMessage : listMessage)
						{
              %>
                <tr>
                  <td style="text-align: center">
                  <%=objMessage.getId_message() %>
                  </td>
                  <td style="text-align: center">
                  <%=objMessage.getEmail() %>
                  </td>
                  <td style="text-align: center">
                  <% if(type == 1) { %>
                  	Subcribe
                  <% } else { %>
                  	Quên mật khẩu
                  <% } %>
                  </td>
                  <% if(type == 2 && objUser.getId_role() == 3 ){ %>
                  <td>
			          <a style="display: inline"   class="nav-link" href="<%=request.getContextPath()%>/admin/message/forgot?id_message=<%=objMessage.getId_message()%>">
			            <i style="display: inline" class="fa fa-eye"></i>
			            <span style="display: inline" class="nav-link-text">View profile</span>
			          </a>
                  </td>
                  <% } %>
                </tr>
              <%}}} %>
                
              </tbody>
            </table>
          </div>
        </div>
        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
      </div>
      <% } %>
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
