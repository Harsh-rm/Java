<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

	<head>
		<title>All Customers</title>
		<link href="<c:url value="/css/styles.css"/>" rel="Stylesheet" type="text/css"/>
	</head>

	<body>

	    <jsp:include page="/header.jsp"/>
		
		<h1>CRM System - All Customers</h1>
				
		<table>
			<tr>
				<th>Id</th>
				<th>Company</th>
				<th>Email</th>
				<th>Phone</th>
			</tr>
			
			<c:forEach items="${customers}" var="customer">
				<tr>
					<td>${customer.customerId}</td>
					<td>${customer.companyName}</td>
					<td>${customer.email}</td>
					<td>${customer.telephone}</td>
				</tr>
			</c:forEach>
	 	<table>
	 	
		<!-- to do footer -->
	 	
	</body>
	
</html>