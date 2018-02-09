<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Register</title>
</head>
<body>
	<h1>Register</h1>
	<p>UserName</p>
	<input id="username" type="text">
	<p>Name</p>
	<input id="name" type="text">
	<p>Password</p>
	<input id="password" type="text">
	<br>
	<br>
	<button id="submit" onclick="create()">Register</button>
	<script src="<c:url value='/node_modules/jquery/dist/jquery.min.js'/>"></script>
	<script>
		function create() {
			var spitter = {
				userName : $("#username").val(),
				name : $("#name").val(),
				password : $("#password").val(),
			};
			$.ajax('rest/spitter/create', {
				type : 'POST',
				data : JSON.stringify(spitter),
				contentType : 'application/json',
				success : function(data, XMLHttpRequest, jqXHR) {
					console.log(data);
				},
				error : function(XMLHttpRequest, jqXHR) {
					console.log("error");
				}
			});
		}
	</script>
</body>
</html>