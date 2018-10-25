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
</head>

<body class="bg-dark">
  <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">Login</div>
      <div class="card-body">
      <% if(request.getAttribute("msg") != null)
    	  {
    	%>
    	<div><h5 style="font-style:italic;color:red"><%=(String)request.getAttribute("msg") %></h5></div>
    	<%} %>
    	<%
	     Cookie[] listCookie = request.getCookies();
	     String user = "";
	     String pass = "";
	     int co = 0;
	     if(listCookie != null){
	        while(co < listCookie.length){
	          if(listCookie[co].getName().equals("user")){
	            user = listCookie[co].getValue();
	           }
	          if(listCookie[co].getName().equals("pass")){
	            pass = listCookie[co].getValue();
	           }
	          co++;
	        }
	  
	      }
 		  %>
        <form method="post" action="<%=request.getContextPath()%>/admin/login" >
          <div class="form-group">
            <label for="exampleInputEmail1">User name</label>
            <input class="form-control" id="exampleInputEmail1" value="<%out.print(user); %>" type="text" name="username" aria-describedby="emailHelp" placeholder="Enter email">
          </div>
          <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input class="form-control" id="exampleInputPassword1" value="<%out.print(pass); %>" type="password" name="password" placeholder="Password">
          </div>
          <div class="form-group">
            <div class="form-check">
              <label class="form-check-label">
                <input class="form-check-input" name="check" type="checkbox"> Remember Password</label>
            </div>
          </div>
          <input class="btn btn-primary btn-block" type="submit" value="Đăng nhập">
        </form>
        <div class="text-center">
          <a class="d-block small" href="<%=request.getContextPath() %>/admin/forgot">Forgot Password?</a>
        </div>
      </div>
    </div>
  </div>
  <!-- Bootstrap core JavaScript-->
  <script src="<%=request.getContextPath() %>/templates/admin/vendor/jquery/jquery.min.js"></script>
  <script src="<%=request.getContextPath() %>/templates/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- Core plugin JavaScript-->
  <script src="<%=request.getContextPath() %>/templates/admin/vendor/jquery-easing/jquery.easing.min.js"></script>
</body>

</html>
