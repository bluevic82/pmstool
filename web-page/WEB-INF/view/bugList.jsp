<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Task/Spec/Issue</title>
<jsp:include page="_menu.jsp" />
 <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  	<link rel="stylesheet" href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" />" >
  	<script src="https://momentjs.com/downloads/moment.min.js"></script>
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker-standalone.css">
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker-standalone.min.css">
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css">
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
</head>
<body>
	<div class="container" style="margin-top: 20px;">
		<div>
			Project Name 
			<select name="projectName">
				<c:forEach var="projectName" items="${projectName}">
					<option value="${projectName.project_id}">${projectName.project_name}</option>
				</c:forEach>
			</select>
			<label style="margin-left: 10px"> Type </label> 
			<select name="type_id" style="margin-left: 5px">
				<c:forEach var="bugTypes" items="${taskTypes}">
					<option value="${bugTypes.type_id}">
						${bugTypes.type_name}</option>
				</c:forEach>
			</select> 
			<label style="margin-left: 10px"> Status </label> 
			<select name="status_id" style="margin-left: 5px">
				<c:forEach var="taskStatus" items="${taskStatus}">
					<option value="${taskStatus.status_id}">
						${taskStatus.status_name}</option>
				</c:forEach>
			</select> 
			<label style="margin-left: 10px"> PIC </label> 
			<select name="member_project_id" style="margin-left: 5px">
				<c:forEach var="pic" items="${pic}">
					<option value="${pic.member_project_id}">
						${pic.member_project_name}</option>
				</c:forEach>
			</select> 
			<label style="margin-left: 10px"> Priority </label> 
			<select name="task_priority" style="margin-left: 5px">
				<option>Highest</option>
				<option>High</option>
				<option>Medium</option>
				<option>Low</option>
			</select>
			<button name="actionSearchTask" value="actionSearchTask" style="background-color: green; color: white; margin-left: 30px">Search</button>
		</div>
		<table style="margin-top: 50px;" class="table table-bordered">
			<thead>
				<tr>
					<th scope="col" style="background-color: #3ADF00;">ID</th>
					<th scope="col" style="background-color: #3ADF00;">Subject</th>
					<th scope="col" style="background-color: #3ADF00;">PIC</th>
					<th scope="col" style="background-color: #3ADF00;">Priority</th>
					<th scope="col" style="background-color: #3ADF00;">Deadline</th>
					<th scope="col" style="background-color: #3ADF00;">Status</th>
					<th scope="col" style="background-color: #3ADF00;">Done</th>
					<th scope="col" style="background-color: #3ADF00;">Description</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${list}">
					<tr>
						<th scope="row"><a href="editBug/${list.bug_id}">${list.bug_id}</a></th>
						<th><a href="editBug/${list.bug_id}">${list.bug_subject}</a></th>
						<th>${list.member_project_id}</th>
						<th>${list.bug_priority}</th>
						<th>${list.bug_to}</th>
						<th>${list.status_id}</th>
						<th>${list.bug_done}</th>
						<th>${list.bug_description}</th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>