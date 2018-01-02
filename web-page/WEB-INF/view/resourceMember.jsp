<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resource</title>
	<jsp:include page="_menu.jsp" />
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" >
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-reboot.min.css" />" >
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-grid.min.css" />" >
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-grid.css" />" >
  
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
</head>
<body>
	<div class="container">
		<div>Project name <input disabled="disabled"></div>
		<div class="row" style="margin-top: 30px;">
			<div class="col-sm-6">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Member</th>
							<th scope="col">Effort</th>
							<th scope="col">Role</th>
							<th scope="col"></th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row">1</th>
							<th>a</th>
							<th>15</th>
							<th>Manager</th>
							<th></th>
							<th></th>
						</tr>
						<tr>
							<th scope="row">2</th>
							<th>a</th>
							<th>15</th>
							<th>Manager</th>
							<th></th>
							<th></th>
						</tr>
						<tr>
							<th scope="row">3</th>
							<th>a</th>
							<th>15</th>
							<th>Manager</th>
							<th></th>
							<th></th>
						</tr>
						<tr>
							<th scope="row">4</th>
							<th>a</th>
							<th>15</th>
							<th>Manager</th>
							<th></th>
							<th></th>
						</tr>
					</tbody>
				</table>
				
			</div>
			<div class="col-sm-6">
				<div class="row">
					<div class="col-sm-6">System member
						<div class="card border-secondary" style="overflow: scroll; height: 300px; width: 200px;">
							<div class="card-body">
								<c:forEach var="getAllUser" items="${getAllUser}">
								    <input type="checkbox" value="${getAllUser.user_id}">${getAllUser.user_fullName}<br>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="col-sm-6">Roles
						<div class="card border-secondary">
							<div class="card-body">
								<c:forEach var="roleUser" items="${roleUser}">
							    	<input type="checkbox" value="${roleUser.role_id}"> ${roleUser.role_name}<br>
							    </c:forEach>
							</div>
						</div>
					</div>
				</div>
				</br>
				<div style="text-align: end"><button type="button" style="background-color: green; color: white; float: left">Save</button></div>
				<div style="text-align: end"><button type="button" style="background-color: green; color: white;">Add</button></div>
			</div>
		</div>
	</div>
</body>
</html>