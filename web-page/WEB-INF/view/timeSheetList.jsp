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
		<form method="post" action="/Login/timeSheetList/">
			<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
			Project Name <select name="projectName">
				<option value="0"></option>
				<c:forEach var="listProjects" items="${listProjects}">
					<option value="${listProjects.project_id}">${listProjects.project_name}</option>
				</c:forEach>
			</select> <label style="margin-left: 10px"> PIC </label> <select
				name="member_project_id" style="margin-left: 5px">
				<option value="0"></option>
				<c:forEach var="list_PIC" items="${list_PIC}">
					<option value="${list_PIC.member_project_id}">
						${list_PIC.member_project_name}</option>
				</c:forEach>
			</select> <label style="margin-left: 10px"> Process </label> <select
				name="process_id" style="margin-left: 5px">
				<option value="0"></option>
				<c:forEach var="process" items="${process}">
					<option value="${process.process_id}">
						${process.process_name}</option>
				</c:forEach>
			</select> <label style="margin-left: 10px"> Status </label> <select
				name="status_id" style="margin-left: 5px">
				<option value="0"></option>
				<c:forEach var="timeSheetStatus" items="${timeSheetStatus}">
					<option value="${timeSheetStatus.status_id}">
						${timeSheetStatus.status_name}</option>
				</c:forEach>
			</select>
			<button type="submit"  style="background-color: green; color: white; margin-left: 30px">Search</button>
				</form>
		</div>
		<table style="margin-top: 50px;" class="table table-bordered">
			<thead>
				<tr>
					<th scope="col" style="background-color: #3ADF00;">Member</th>
					<th scope="col" style="background-color: #3ADF00;">TimeSheet
						Date</th>
					<th scope="col" style="background-color: #3ADF00;">Hour</th>
					<th scope="col" style="background-color: #3ADF00;">Pre-defined
						task</th>
					<th scope="col" style="background-color: #3ADF00;">Process</th>
					<th scope="col" style="background-color: #3ADF00;">Type of
						work</th>
					<th scope="col" style="background-color: #3ADF00;">Requirement</th>
					<th scope="col" style="background-color: #3ADF00;">Work
						content</th>
					<th scope="col" style="background-color: #3ADF00;">Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="listTimeSheetDetails"
					items="${listTimeSheetDetails}">


					<tr>
						<th>${listTimeSheetDetails.memberProject.member_project_name}</th>
						<th>${listTimeSheetDetails.detail_timesheet_date}</th>
						<th>${listTimeSheetDetails.hour}</th>
						<th>${listTimeSheetDetails.pre_defined_name}</th>
						<th>${listTimeSheetDetails.process_name}</th>
						<th>${listTimeSheetDetails.type_name}</th>
						<th>${listTimeSheetDetails.task_subject}</th>
						<th>${listTimeSheetDetails.workcontent}</th>
						<th><select style="margin-left: 5px">
								<option>${listTimeSheetDetails.status_type}</option>
								<c:forEach var="timeSheetStatus" items="${timeSheetStatus}">
									<option value="${timeSheetStatus.status_id}">
										${timeSheetStatus.status_name}</option>
								</c:forEach>
						</select></th>
					</tr>


				</c:forEach>
			</tbody>
		</table>
		<div align="right" style="padding-top: 2%; padding-bottom: 5%">
			<button id="id_buttonApprove">Approve</button>
			<button type="submit" id="id_buttonSave" >Save</button>
		</div>
	</div>
</body>
</html>