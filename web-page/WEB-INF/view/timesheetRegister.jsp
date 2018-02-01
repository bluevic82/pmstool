<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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

		<form:form id="id_form" action="/Login/actionSaveTimeSheet"
			method="post" modelAttribute="list_TimeSheetOfOneProject">
			<table id="id_table" class="table table-bordered"
				style="margin-top: 2%">
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
						items="${list_TimeSheetOfOneProject}">
						<tr id="id_tr">
							<td scope="col"><input id="id_checkbox" type="checkbox"
								name="checkboxTS" /></td>
							<td id="id_ts_Date">${list_TimeSheetOfOneProject.detail_timesheet_date }</td>
							<td contenteditable="true" id="id_ts_Hour">${list_TimeSheetOfOneProject.hour}</td>
							<td id="id_ts_Definded">${list_TimeSheetOfOneProject.list_Name_Of_Id[0]}</td>
							<td id="id_ts_Process">${list_TimeSheetOfOneProject.list_Name_Of_Id[1]}</td>
							<td id="id_ts_Type">${list_TimeSheetOfOneProject.list_Name_Of_Id[2]}</td>
							<td id="id_ts_Requirement">${list_TimeSheetOfOneProject.list_Name_Of_Id[3]}</td>
							<td contenteditable="true" id="id_ts_workContent">${list_TimeSheetOfOneProject.workcontent}</td>
						</tr>
					</c:forEach>


				</tbody>

			</table>
		</form:form>
		<div>

			<button id="id_buttonAdd">+</button>


		</div>

		<div align="right" style="padding-top: 5%">
			<button id="id_buttonDelete">Delete</button>
			<button type="submit" id="id_buttonSave">Save</button>
		</div>

	</div>
</body>
<script type="text/javascript">
	var $TABLE = $('#id_table');

	/*add timesheet*/
	$('#id_buttonAdd')
			.click(
					function() {
						document.getElementById("id_table").insertRow(-1).innerHTML = '<tr id = "id_tr"><td scope="col" ><div><input id = "id_checkbox" type="checkbox" name="checkboxTS"/><div></td>'
								+ '<td id = "id_ts_Date"></td>'
								+ '<td contenteditable="true" id = "id_ts_Hour"></td>'
								+ '<td id = "id_ts_Definded"></td>'
								+ '<td id = "id_ts_Process"></td>'
								+ '<td id = "id_ts_Type"></td>'
								+ '<td id="id_ts_Requirement"></td>'
								+ '<td contenteditable="true" id="id_ts_workContent"></td>' + '</tr>';
					});
	
	/*delete timesheet*/
	$('#id_buttonDelete')
	.click(
			function() {
				var checkbox = document.getElementsByName('checkboxTS');
				for (var i = 0; i < checkbox.length; i++) {
					if (checkbox[i].checked === true) {
						var temp = i+1;
						var name = document.getElementById("id_table").rows[i].remove();
						/* var temp = i+1;
						document.getElementById("id_table").deleteRow(temp);
						temp--; */
						//checkbox[i]
						//alert("check in i = "+i);
					}
					//alert('check i = '+i);
				}
				
				//alert("delete timesheet");
			});
	
	/*save timesheet*/
	$('#id_buttonSave')
	.click(
			function() {
				
				document.getElementById("id_form").submit();
			
			});

	/*edit cols*/
	$("#id_table").on("click", "#id_ts_Date", function(e) {

		alert("edit date");
	});

	$("#id_table").on("click", "#id_ts_Definded", function(e) {

		alert("edit Definded");
	});
	$("#id_table").on("click", "#id_ts_Process", function(e) {

		alert("edit Process");
	});
	$("#id_table").on("click", "#id_ts_Type", function(e) {

		alert("edit TypeName");
	});
	$("#id_table").on("click", "#id_ts_Requirement", function(e) {

		alert("edit Requirement");
	});
	
</script>
</html>


