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
	
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
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
						<th scope="col"></th>
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

							<td id="id_ts_Date"><div
									class="input-group date datetimepicker">
									<input type="text" class="form-control"
										name="name_timesheet_date"
										value="${list_TimeSheetOfOneProject.detail_timesheet_date }" />
									<div class="input-group-addon">
										<div class="glyphicon glyphicon-calendar"></div>
									</div>

								</div></td>
							<td id="id_ts_Hour"><input id="id_input_HOUR" type="text"
								value="${list_TimeSheetOfOneProject.hour}" /></td>
							<td id="id_ts_Definded">
								<div class="class_DefindedName">
									<div id="panel" style="height: 100%;">

										<select id="">
											<option value="none" id="id_chooseOption">${list_TimeSheetOfOneProject.list_Name_Of_Id[0]}</option>
											<c:forEach var="pre_defined" items="${pre_defined}">
												<option value="${pre_defined.pre_defined_id}">${pre_defined.pre_defined_name}</option>
											</c:forEach>
										</select>
									</div>
								</div>


							</td>
							<td id="id_ts_Process">
								<div class="class_DefindedName">
									<div id="panel" style="height: 100%;">
										<select id="">
											<option value="none" id="id_chooseOption">${list_TimeSheetOfOneProject.list_Name_Of_Id[1]}</option>
											<c:forEach var="process" items="${process}">
												<option value="${process.process_id}">${process.process_name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</td>
							<td id="id_ts_Type">
								<div class="class_DefindedName">
									<div id="panel" style="height: 100%;">
										<select id="">
											<option value="none" id="id_chooseOption">${list_TimeSheetOfOneProject.list_Name_Of_Id[2]}</option>
											<c:forEach var="timsheetTypes" items="${timsheetTypes}">
												<option value="${timsheetTypes.type_id}">${timsheetTypes.type_name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</td>

							<td id="id_ts_Requirement">
								<div class="class_DefindedName">
									<div id="panel" style="height: 100%;">
										<select id="">
											<option value="none" id="id_chooseOption">${list_TimeSheetOfOneProject.list_Name_Of_Id[3]}</option>
											<c:forEach var="Tasks" items="${Tasks}">
												<option value="${Tasks.task_id}">${Tasks.task_subject}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</td>
							<td id="id_ts_workContent"><input id="id_input_WorkContent"
								type="text" value="${list_TimeSheetOfOneProject.workcontent}" />

							</td>
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

<style>
#id_input_HOUR {
	width: 50px;
}

#id_table {
	border-style: solid;
}
</style>


<script type="text/javascript">
	var $TABLE = $('#id_table');

	/*add timesheet*/
	$('#id_buttonAdd')
			.click(
					function() {

						document.getElementById("id_table").insertRow(-1).innerHTML = '<tr id = "id_tr"><td scope="col" >'
								+ '<div><input id = "id_checkbox" type="checkbox" name="checkboxTS"/><div></td>'

								+ '<td id = "id_ts_Date"><div class="input-group date datetimepicker">'
								+ '<input type="text" class="form-control" name="name_timesheet_date"'
								+ 'value="" /><div class="input-group-addon"><div class="glyphicon glyphicon-calendar"></div></div></div</td>'

								+ '<td id = "id_ts_Hour"><input id = "id_input_HOUR" type = "text" value = ""/></td>'

								+ '<td id = "id_ts_Definded"><div class="class_DefindedName">'
								+ '<div id="panel" style="height: 100%;"><select id="">'
								+ ' <option value="none" id = "id_chooseOption"></option>'
								+ ' <c:forEach var="pre_defined" items="${pre_defined}">'
								+ '<option value="${pre_defined.pre_defined_id}">${pre_defined.pre_defined_name}</option>'
								+ '</c:forEach></select></div></div></td>'

								+ '<td id = "id_ts_Process"><div class="class_DefindedName"><div id="panel" style="height: 100%;">'
								+ '<select id=""><option value="none" id = "id_chooseOption"></option>'
								+ '<c:forEach var="process" items="${process}"><option value="${process.process_id}">${process.process_name}</option>'
								+ '</c:forEach></select></div></div></td>'

								+ '<td id = "id_ts_Type"><div class="class_DefindedName"><div id="panel" style="height: 100%;">'
								+ '<select id=""><option value="none" id = "id_chooseOption"></option>'
								+ '<c:forEach var="timsheetTypes" items="${timsheetTypes}">'
								+ '<option value="${timsheetTypes.type_id}">${timsheetTypes.type_name}</option></c:forEach></select></div></div></td>'

								+ '<td id="id_ts_Requirement"><div class="class_DefindedName"><div id="panel" style="height: 100%;"><select id="">'
								+ '<option value="none" id = "id_chooseOption"></option><c:forEach var="Tasks" items="${Tasks}">'
								+ '<option value="${Tasks.task_id}">${Tasks.task_subject}</option></c:forEach></select></div></div></td>'

								+ '<td id="id_ts_workContent"><input id = "id_input_WorkContent" type = "text" value = "" /></td>'
								+ '</tr>';

					});

	/*delete timesheet*/
	$('#id_buttonDelete').click(

	function() {
		var checkbox = document.getElementsByName('checkboxTS');
		for (var i = 0; i < checkbox.length; i++) {

			if (checkbox[i].checked === true) {
				var temp = i + 1;
				document.getElementById("id_table").rows[temp].remove();
				temp--;
				i--;

			}

		}

	});

	/*save timesheet*/
	
	var list = [];

	var infor_Object = {
		detail_timesheet_date : "date",
		workcontent : "work content" 
	}

	list[0] = infor_Object;
	list[1] = infor_Object;
	alert(list[0].detail_timesheet_date);
	
	
	
	$('#id_buttonSave')
			.click(
					function() {
						alert("save");
						var table = document.getElementById("id_table");
						var len = table.rows.length;
						//alert(table.rows.length);

						//alert(table.rows[1].find("#id_ts_Date").html);

						for (var i = 1; i < len; i++) {

							

							//alert(table.rows[i].cells[1].childNodes[0].innerHTML);
							if (table.rows[i].cells[1].childNodes[0].value == "") {
								alert(" Date is empty!");

							}
							if (isNaN(table.rows[i].cells[2].childNodes[0].value)
									|| table.rows[i].cells[2].childNodes[0].value == "") {

								alert(" HOUR is empty or not number!");

							}
							
							
						}


						var Object = {
							detail_timesheet_date : "date",
							workcontent : "work content"
	
						}
						    var myList = new Array();
						     myList.push(Object);
						     //myList.push('bye');
						     
						      //alert(myList.length);
						    alert(myList[0].detail_timesheet_date);

				
						    
						
					}); 

	//document.getElementById("id_form").submit();

	/*edit cols*/

	$("#id_table").on("click", ".datetimepicker", function(e) {

		e.preventDefault();
		$('.datetimepicker').datetimepicker({
			format : "YYYY-MM-DD",
		});
	});

	$("#id_table").on("click", "#id_ts_Definded", function(e) {

	});
	$("#id_table").on("click", "#id_ts_Process", function(e) {

	});
	$("#id_table").on("click", "#id_ts_Type", function(e) {

	});
	$("#id_table").on("click", "#id_ts_Requirement", function(e) {

	});
</script>
</html>


