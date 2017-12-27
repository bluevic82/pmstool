<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TimeSheet List</title>
<jsp:include page="_menu.jsp" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap-reboot.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap-grid.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap-grid.css" />">
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
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
			<label style="margin-left: 10px"> PIC </label> 
			<select name="member_project_id" style="margin-left: 5px">
				<c:forEach var="pic" items="${pic}">
					<option value="${pic.member_project_id}">
						${pic.member_project_name}</option>
				</c:forEach>
			</select> 
			<label style="margin-left: 10px"> Process </label> 
			<select name="process_id" style="margin-left: 5px">
				<c:forEach var="process" items="${process}">
					<option value="${process.process_id}">
						${process.process_name}</option>
				</c:forEach>
			</select> 
			<label style="margin-left: 10px"> Status </label> 
			<select name="status_id" style="margin-left: 5px">
				<c:forEach var="timeSheetStatus" items="${timeSheetStatus}">
					<option value="${timeSheetStatus.status_id}">
						${timeSheetStatus.status_name}</option>
				</c:forEach>
			</select> 
			<button name="actionSearchTask" value="actionSearchTask" style="background-color: green; color: white; margin-left: 30px">Search</button>
		</div>
		<table style="margin-top: 50px;" class="table table-bordered">
			<thead>
				<tr>
					<th scope="col" style="background-color: #3ADF00;">Member</th>
					<th scope="col" style="background-color: #3ADF00;">TimeSheet Date</th>
					<th scope="col" style="background-color: #3ADF00;">Hour</th>
					<th scope="col" style="background-color: #3ADF00;">Pre-defined task</th>
					<th scope="col" style="background-color: #3ADF00;">Process</th>
					<th scope="col" style="background-color: #3ADF00;">Type of work</th>
					<th scope="col" style="background-color: #3ADF00;">Requirement</th>
					<th scope="col" style="background-color: #3ADF00;">Work content</th>
					<th scope="col" style="background-color: #3ADF00;">Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${list}">
					<tr>
						<th scope="row"><a href="editTask/${list.task_id}">${list.task_id}</a></th>
						<th><a href="editTask/${list.task_id}">${list.task_subject}</a></th>
						<th>${list.member_project_id}</th>
						<th>${list.task_priority}</th>
						<th>${list.task_to}</th>
						<th>${list.status_id}</th>
						<th>${list.task_done}</th>
						<th>${list.task_description}</th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>