<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Register</title>
<style type="text/css">
.error {
	color: red;
}
</style>
<jsp:include page="_menu.jsp" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap-reboot.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap-grid.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap-grid.css" />">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet"
	href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" />">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

<script src="https://momentjs.com/downloads/moment.min.js"></script>
<script src="/resources/js/bootstrap.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker-standalone.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker-standalone.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker-standalone.min.css.map">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container">
		<form action="actionCreateUser" style="margin-top: 20px;"
			method="post">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div class="row">
				<div class="col-sm-2">Full name</div>
				<div class="col-sm-10">
					<input size="30" name="user_fullName">
					<errors cssClass="error" name="user_fullName">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-sm-2">Email</div>
				<div class="col-sm-10">
					<input size="30" name="user_mail">
					<errors cssClass="error" name="user_mail">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-sm-2">Password</div>
				<div class="col-sm-10">
					<input size="30" type="password" name="user_passWord">
					<errors cssClass="error" name="user_passWord">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-sm-2">Role</div>
				<div class="col-sm-10">
					<div class="col-sm-11">
						<c:forEach var="projectRole" items="${projectRole}">
							<input type="checkbox" value="${projectRole.role_id}"
								style="margin-left: 10px;" name="role_id">${projectRole.role_name} <br>
						</c:forEach>
						<errors cssClass="error" name="role_id">
					</div>
				</div>

			</div>
			<br>
			<div class="row">
				<div class="col-sm-2">Batch register</div>
				<div class="col-sm-10">
					<input type="file" name="fileName" accept=".csv"/>
				</div>

			</div>
			<br>
			<div align="right">
				<button style="background-color: green; color: white;">Save</button>
			</div>
		</form>
	</div>
</body>
</html>