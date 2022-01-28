<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
	<h1>Hello From Home Page :) :)</h1>
	
	<hr>

		<!-- display user and roles -->
		<p>
			User : <security:authentication property="principal.username"/>
			<br><br>
			role(s) : <security:authentication property="principal.authorities"/>
		</p>
	 
	<hr>
	
	
	
		<!-- link to leaders -->
		<security:authorize access="hasRole('manager')">
			<p>
				<a href="${pageContext.request.contextPath}/leaders" >leaderShip Meeting</a>
				( for only manager )
			</p>
			<hr>
		</security:authorize>
	 
	
	
	
	<!-- link to admin -->
		<security:authorize access="hasRole('admin')">
			<p>
				<a href="${pageContext.request.contextPath}/systems" >CS Systems Meeting</a>
				( for only admin )
			</p>
			<hr>
		</security:authorize>
	
	
	

    <!--
    <h1><a href="${pageContext.request.contextPath}/loginUser">go to login</a></h1>
      -->
<form:form action="${pageContext.request.contextPath}/logout" method="POST">
	<input type="submit" value="logout" />
</form:form>



</body>
</html>