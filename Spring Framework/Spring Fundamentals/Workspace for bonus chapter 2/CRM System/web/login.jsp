<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<title>Login to CRM</title>
		<link href="<c:url value="/css/styles.css"/>" rel="Stylesheet" type="text/css"/>
	</head>		

	<body>
	    <jsp:include page="/header.jsp"/>
	
		<h1><strong>CRM Login</strong></h1>
		
		<div id="blurb">
			<p>Welcome to the unimaginatively named CRM System, bringing you Contacts, Relationships and Managements since 2008.</p> 

			<p><strong>You need to be "rac/secret" to login</strong></p>
			
			<p>Coming soon! We will be allowing third parties to access your data. It will be secure. <span class="small">Hopefully</span></p> 
		</div>
				
		<c:url value="/login" var="loginUrl"/>
		
		<c:if test="${param.error != null}">
			<p>Bad username/password</p>
		</c:if>
		
		<form:form action="${loginUrl}" method="post">
			<label>CRM username:</label> <input type="text" name="username" value="rac"/>
			<label>CRM Password:</label> <input type="text" name="password" value="secret"/>
			<input type="submit"/>
		</form:form>
	</body>
</html>