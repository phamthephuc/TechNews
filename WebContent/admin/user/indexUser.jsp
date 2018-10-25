<%@page import="model.bean.Role"%>
<%@page import="model.bean.TrangThai"%>
<%@page import="model.bean.User"%>
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
      	 <a style="background-color:#343A40;display: inline;color:white;margin: 10px;border-radius: 5px  " class="nav-link" href="<%=request.getContextPath()%>/admin/user/add">
            <i class="fa fa-fw fa-plus"></i>
            <span class="nav-link-text">Thêm người dùng</span>
          </a>
      </div>
      <br>
		      <%
			if(request.getParameter("msg")!=null){
				int msg = Integer.parseInt(request.getParameter("msg"));
				switch(msg){
					case 1:
						out.print("<h5 style='font-style: italic; color:green'>Thêm thành công</h5>");break;
					case 2:
						out.print("<h5 style='font-style: italic; color:green'>Sửa thành công</h5>");break;
					case 3:
						out.print("<h5 style='font-style: italic; color:green'>Xóa thành công</h5>");break;
					case 4:
						out.print("<h5 style='font-style: italic; color:red'>Không có quyền sửa xóa admin</h5>");break;
					case 5:
						out.print("<h5 style='font-style: italic; color:red'>Tài khoản của bạn không có quyền thêm sửa và xóa này</h5>");break;	
					default: out.print("<h5 style='font-style: italic; color:green'>Có lỗi trong quá trình xử lý</h5>");break;
					
				}
			}
		%>
      <div id="ajax_id"></div>
      <!-- Example DataTables Card-->
      <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i> User Table</div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
              <thead>
                <tr>
                  <th style="width: 1%">Id</th>
                  <th>Username</th>
                  <th>Email</th>
                  <%
                  	User userLogin = (User)session.getAttribute("user");
                  	if(userLogin.getId_role() == 3){
                  %>
                  	<th>Role</th>
                  	<th style="width: 15%">Tình trạng</th>
                  <%
                  	} else {
                  %>
                  <th>Fullname</th>
                  <% } %>
                  <th style="width:10%">Hình ảnh</th>
                  <th style="width:20%">Thay đổi</th>
                </tr>
              </thead>
              <tfoot>
                <tr>
               	 <th style="width: 1%">Id</th>
                  <th style="width: 5%" >Username</th>
                 <th>Email</th>
                  <%
                  	if(userLogin.getId_role() == 3){
                  %>
                  	<th>Role</th>
                  	<th>Tình trạng</th>
                  <%
                  	} else {
                  %>
                  <th>Fullname</th>
                  <% } %>
                  
                  <th>Hình ảnh</th>
                  <th>Thay đổi</th>
                </tr>
              </tfoot>
              <tbody>
               <%
              	if(request.getAttribute("listUser")!= null)
              	{
					ArrayList<User> listUser =   (ArrayList<User>)request.getAttribute("listUser");
					if(listUser.size() > 0)
					{
						for(User objUser : listUser)
						{
              %>
                <tr>
                  <td><%=objUser.getId_user() %></td>
                  <td><%=objUser.getUsername() %></td>
                  <td><%=objUser.getEmail() %></td>
                  <%
                  	if(userLogin.getId_role() == 3){
                  %>
                  	 <td><select id="role<%=objUser.getId_user()%>">
                  <% if(request.getAttribute("listRole") != null)
                  {
                	  ArrayList<Role> listRole  =   (ArrayList<Role>)request.getAttribute("listRole");
  					if(listRole.size() > 0)
  					{
  						for(Role objRole : listRole)
  						{
  							String selected = "";
  							if(objRole.getId_role() == objUser.getId_role())
  								selected = "selected = 'selected'";
                	%>
	                  	<option <%=selected %> value="<%=objRole.getId_role()%>"><%=objRole.getName_role() %></option>
	                <% } } } %>
                  	</select>
                  </td>
                  
                  <td><select id="status<%=objUser.getId_user()%>">
                  <% if(request.getAttribute("listTT") != null)
                  {
                	  ArrayList<TrangThai> listTT  =   (ArrayList<TrangThai>)request.getAttribute("listTT");
  					if(listTT.size() > 0)
  					{
  						for(TrangThai objTT : listTT)
  						{
  							String selected = "";
  							if(objTT.getStatus() == objUser.getStatus())
  								selected = "selected = 'selected'";
                	%>
	                  	<option <%=selected %> value="<%=objTT.getStatus()%>"><%=objTT.getName_status() %></option>
	                <% } } } %>
                  	</select>
                  </td>
                  <%
                  	} else {
                  %>
                  	<td><%=objUser.getFullname() %></td>
                  <%} %>
                  <td>
                  <% if(objUser.getPicture().equals("")) {%>
                	 	 <img style="width:70px;height:70px;border-radius: 50%" src="<%=request.getContextPath()%>/files/user.png">
                  <% } else { %>
                  		<img style="width:70px;height:70px;border-radius: 50%" src="<%=request.getContextPath()%>/files/<%=objUser.getPicture()%>">
                  <% } %>
                  	
                  </td>
                  <td>
                  <%
                  	if(userLogin.getId_role() == 3){
                  %>
			          <a style="display: inline"   class="nav-link nav-link1" href="javascript:void(0)" onclick ="edit(<%=objUser.getId_user()%>)">
			            <i style="display: inline" class="fa fa-fw fa-save"></i>
			            <span style="display: inline" class="nav-link-text">Lưu</span>
			          </a>
			          <a style="display: inline"  onclick="return confirm('Bạn có chắc muốn xóa không')" class="nav-link nav-link1" href="<%=request.getContextPath()%>/admin/user/del?id_user=<%=objUser.getId_user()%>">
			            <i style="display: inline" class="fa fa-fw fa-trash-o"></i>
			            <span style="display: inline" class="nav-link-text">Xóa</span>
			          </a>
			      <% } %>
			      <%
                  	if(userLogin.getId_role() == 3 || userLogin.getId_user() == objUser.getId_user() ){
                  %>
			           <a style="display: inline" class="nav-link nav-link1" href="<%=request.getContextPath()%>/admin/user/edit?id_user=<%=objUser.getId_user()%>">
			            <i style="display: inline" class="fa fa-fw fa-pencil-square-o"></i>
			            <span style="display: inline" class="nav-link-text">Sửa</span>
			          </a>
			      <%} %>
			         
			          
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
    <script>
				
						
				function edit(id_tr)
				{
					var id = "#" + "status" + id_tr;
					var id_trantmp = id_tr;
					var id_statustmp = $(id).val();
					
					var id_role = "#" + "role" + id_tr;
					var role_tmp = $(id_role).val();
					$.ajax({
						url: '<%=request.getContextPath()%>/admin/user',
						type: 'POST',
						cache: false,
						data: {
								//Dữ liệu gửi đi
								id_user: id_trantmp,
								status : id_statustmp,
								role : role_tmp
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
