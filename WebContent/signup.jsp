<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<title>Daily UI - Day 1 Sign In</title>

	<!-- Google Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900' rel='stylesheet' type='text/css'>

	<link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/animate.css">
	<!-- Custom Stylesheet -->
	<link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/style1.css">
	<style type="text/css">
		body {
				background: url("templates/public/img/photo_bg.jpg") no-repeat center center fixed;
				background-size: cover;
				font-size: 16px;
				font-family: 'Lato', sans-serif;
				font-weight: 300;
				margin: 0;
				color: #666;
			}
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
</head>

<body>
	<div class="container">
		<div class="top">
			<h1 id="title" class="hidden"><span id="logo">TechNews</span></h1>
		</div>
		<div class="login-box animated fadeInUp">
		<form action="<%=request.getContextPath()%>/register" method="post">
		
			<div class="box-header">
				<h2>Đăng ký</h2>
			</div>
			<label for="username">Email</label>
			<br/>
			<input type="text" name="email" id="email">
			<br/>
			<label for="username">Fullname</label>
			<br/>
			<input type="text" name="username" id="fullname">
			<br/>
			<label for="username">Username</label>
			<br/>
			<input type="text" id="username">
			<br/>
			<label for="password">Password</label>
			<br/>
			<input type="password" id="password">
			<br/>
			<button type="submit">Đăng ký</button>
			<a href="<%=request.getContextPath()%>/login"><p style="font-family: arial" class="small">Bạn đã có tài khoản?</p></a>
			</form>
		</div>
	</div>
</body>

<script>
	$(document).ready(function () {
    	$('#logo').addClass('animated fadeInDown');
    	$("input:text:visible:first").focus();
	});
	$('#username').focus(function() {
		$('label[for="username"]').addClass('selected');
	});
	$('#username').blur(function() {
		$('label[for="username"]').removeClass('selected');
	});
	$('#password').focus(function() {
		$('label[for="password"]').addClass('selected');
	});
	$('#password').blur(function() {
		$('label[for="password"]').removeClass('selected');
	});
</script>

</html>