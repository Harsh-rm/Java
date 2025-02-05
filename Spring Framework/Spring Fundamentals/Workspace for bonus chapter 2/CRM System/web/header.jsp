<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="header">
	<em>This is the CRM System</em>
	<div id="links">
			<sec:authorize access="isAuthenticated()">				
				<li><a href='<c:url value="all-customers.html"/>'>View Customers</a></li>
			</sec:authorize>
		
			<sec:authorize access="isAnonymous()">									
				<li><a href='<c:url value="login.jsp"/>'>Login</a></li>
			</sec:authorize>
			
			<sec:authorize access="isAuthenticated()">
				<!-- a post form is required to prevent CSRF attacks. -->									
				<li><c:url value="/logout" var="logoutUrl"/>
					<div id="logout">
						<form:form method="post" action="${logoutUrl}">
							<input type="submit" value="Logout"/>
							<sec:csrfInput />	   					
						</form:form>
					</div>
				</li>
			</sec:authorize>
	</div>			
</div>