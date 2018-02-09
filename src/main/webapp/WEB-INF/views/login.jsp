<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<form action="<c:url value='/login.action'/>" method="post">
		<h1>Login</h1>
		<p>UserName</p>
		<input type="text" name="username">
		<p>Password</p>
		<input type="password" name="password"> <br> <br>
		<button type="submit">登录</button>
	</form>
	<script src="<c:url value='/node_modules/jquery/dist/jquery.min.js'/>"></script>
	<script>
		
	</script>
</body>
</html>