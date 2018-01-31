<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Timesheet register/Update</title>

<jsp:include page="_menu.jsp" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

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
		<div>
			Project Name<input style="margin-left: 50px"
				value="${projectInfo.project_name }" disabled="disabled">
		</div>


		<table class="table table-bordered" style="margin-top: 2%">
			<thead>
				<tr>
					<th scope="col">Choose</th>
					<th scope="col">Date *</th>
					<th scope="col">Hour *</th>
					<th scope="col">Pre-defined task</th>
					<th scope="col">Process *</th>
					<th scope="col">Type of work *</th>
					<th scope="col">Requirement</th>
					<th scope="col">Work content</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list_TimeSheetOfOneProject"
					items="${list_TimeSheetOfOneProject}" >
					<tr>
						<th scope="col"><input type = "checkbox"/></th>
						<th>${list_TimeSheetOfOneProject.detail_timesheet_date }</th>
						<th>${list_TimeSheetOfOneProject.hour}</th>
						<th>${list_TimeSheetOfOneProject.pre_defined_name}</th>
						<th>${list_TimeSheetOfOneProject.process_name}</th>
						<th>${list_TimeSheetOfOneProject.type_name}</th>
						<th></th>
						<th>${list_TimeSheetOfOneProject.workcontent}</th>
					</tr>
				</c:forEach>
				

			</tbody>
		</table>

		<div align="right">
			<button>Delete</button>
			<button>Save</button>
		</div>
	</div>
</body>
</html>