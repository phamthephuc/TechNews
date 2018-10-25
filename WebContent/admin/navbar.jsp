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
          <a href="#">Dashboard</a>
        </li>
        <li class="breadcrumb-item active">Navbar</li>
      </ol>
      <h1>Navbar</h1>
      <hr>
      <p>The SB Admin navbar can be either fixed or static, and it supports the navbar-light and navbar-dark Bootstrap 4 classes.</p>
      <a class="btn btn-primary" href="#" id="toggleNavPosition">Toggle Fixed/Static Navbar</a>
      <a class="btn btn-primary" href="#" id="toggleNavColor">Toggle Navbar Color</a>
      <!-- Blank div to give the page height to preview the fixed vs. static navbar-->
      <div style="height: 1000px;"></div>
    </div>
    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
    
    
    
    
    
    <%@include file="../../templates/admin/inc/footer.jsp" %>
    <!-- Bootstrap core JavaScript-->
    <script src="<%=request.getContextPath() %>/templates/admin/vendor/jquery/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="<%=request.getContextPath() %>/templates/admin/vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="<%=request.getContextPath() %>/templates/admin/js/sb-admin.min.js"></script>
    <!-- Custom scripts for this page-->
    <!-- Toggle between fixed and static navbar-->
    <script>
    $('#toggleNavPosition').click(function() {
      $('body').toggleClass('fixed-nav');
      $('nav').toggleClass('fixed-top static-top');
    });

    </script>
    <!-- Toggle between dark and light navbar-->
    <script>
    $('#toggleNavColor').click(function() {
      $('nav').toggleClass('navbar-dark navbar-light');
      $('nav').toggleClass('bg-dark bg-light');
      $('body').toggleClass('bg-dark bg-light');
    });

    </script>
  </div>
</body>

</html>
