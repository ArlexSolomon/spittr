<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Spittr</title>
</head>
<body>
	<h1>Welcome to Spittr</h1>

	<a href="<c:url value='/logout' />">Logout</a>
	<a href="<c:url value='/register' />">Register</a>
</body>
</html>