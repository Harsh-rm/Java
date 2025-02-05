<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

	<head>
		<title>All Books</title>
	</head>

	<body>
		<h1>All Books</h1>
		
		<em>This is a very poor layout - see the SpringMVC course for a much better one!</em>
		
		<table>
			<tr>
				<th>Title</th>
				<th>Author</th>
			</tr>
			
			<c:forEach items="${books}" var="book">
				<tr>
					<td>${book.title}</td>
					<td>${book.author}</td>
				</tr>
			</c:forEach>
	 	<table>
	</body>
</html>