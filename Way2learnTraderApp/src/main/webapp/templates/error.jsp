<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
<title>Error page</title>
<meta charset="utf-8" />
    <jsp:include page="fragments/header.jsp" />
</head>
<body>
	<h1>${errorCode}</h1>
	<p>${errorMessage}</p>
	<a href="index.html" href="/">Back to Home Page</a>
</body>
</html>