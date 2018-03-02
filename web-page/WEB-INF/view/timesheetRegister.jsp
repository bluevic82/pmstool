<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Timesheet register/Update</title>
<jsp:include page="_banner1.jsp"></jsp:include>
<jsp:include page="_menu.jsp" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap-reboot.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap-grid.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap-grid.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />

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
	
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta name="_csrf_parameterName" content="${_csrf.parameterName}" />
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
				style="margin-top: 2%;">
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
				<tbody >
					<c:forEach var="list_TimeSheetOfOneProject"
						items="${list_TimeSheetOfOneProject}">
						<tr id="id_tr">
							<td scope="col"><input id="id_checkbox" type="checkbox" name="checkboxTS" /><input type="hidden" value="${list_TimeSheetOfOneProject.ts_id}" name="ts_id"/></td>
								
							<td id="id_ts_Date">${list_TimeSheetOfOneProject.detail_timesheet_date }</td>	
							<td id="id_ts_Hour"><input id="id_input_HOUR" type="text"
								value="${list_TimeSheetOfOneProject.hour}" /></td>
								
							<td id="id_ts_Definded"><select id="id_selectDefinded"><option
										value="${list_TimeSheetOfOneProject.pre_defined_id}"
										id="id_chooseOption">${list_TimeSheetOfOneProject.pre_defined_name}</option>
									<c:forEach var="pre_defined" items="${pre_defined}">
										<option value="${pre_defined.pre_defined_id}">${pre_defined.pre_defined_name}</option>
									</c:forEach></select></td>
									
							<td id="id_ts_Process"><select id="id_selectProcess"><option
										value="${list_TimeSheetOfOneProject.process_id}"
										id="id_chooseOption">${list_TimeSheetOfOneProject.process_name}</option>
									<c:forEach var="process" items="${process}">
										<option value="${process.process_id}">${process.process_name}</option>
									</c:forEach></select></td>

							<td id="id_ts_Type"><select id="id_setType"><option
										value="${list_TimeSheetOfOneProject.type_id}"
										id="id_chooseOption">${list_TimeSheetOfOneProject.type_name}</option>
									<c:forEach var="timsheetTypes" items="${timsheetTypes}">
										<option value="${timsheetTypes.type_id}">${timsheetTypes.type_name}</option>
									</c:forEach></select></td>

							<td id="id_ts_Requirement"><select id="id_selectRequirement"><option
										value="${list_TimeSheetOfOneProject.task_id }"
										id="id_chooseOption">${list_TimeSheetOfOneProject.task_subject}</option>
									<c:forEach var="Tasks" items="${Tasks}">
										<option value="${Tasks.task_id}">${Tasks.task_subject}</option>
									</c:forEach></select></td>

							<td id="id_ts_workContent"><input id="id_input_WorkContent"
								type="text" value="${list_TimeSheetOfOneProject.workcontent}" /><input type="hidden" value="${ list_TimeSheetOfOneProject.detail_timesheet_id}" name="detail_timesheet_id"/></td>
								
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
						
						var date=new Date();
						var day="";
						var month="";
						var year="";
						if(date.getDate()<10){
							day+=0+""+date.getDate();
						}
						if(date.getMonth()<10){
							month += month+=0+""+date.getMonth();
						}
						
						var today = date.getFullYear()+"-"+month+"-"+day;

						
						document.getElementById("id_table").insertRow(-1).innerHTML = '<tr id = "id_tr"><td scope="col"><input id="id_checkbox" type="checkbox" name="checkboxTS" /><input type="hidden" value="" name="ts_id"/></td>'

								+ '<td id="id_ts_Date">'+today+'</td>'

								+ '<td id = "id_ts_Hour"><input id = "id_input_HOUR" type = "text" value = ""/></td>'

								+ '<td id="id_ts_Definded"><select id="id_selectDefinded"><option value="" id="id_chooseOption"></option><c:forEach var="pre_defined" items="${pre_defined}"><option value="${pre_defined.pre_defined_id}">${pre_defined.pre_defined_name}</option></c:forEach></select></td>'

								+ '<td id="id_ts_Process"><select id="id_selectProcess"><option value="" id="id_chooseOption"></option><c:forEach var="process" items="${process}"><option value="${process.process_id}">${process.process_name}</option></c:forEach></select></td>'

								+ '<td id="id_ts_Type"><select id="id_setType"><option value="" id="id_chooseOption"></option>'
								+ '<c:forEach var="timsheetTypes" items="${timsheetTypes}">'
								+ '<option value="${timsheetTypes.type_id}">${timsheetTypes.type_name}</option>'
								+ '</c:forEach></select></td>'

								+ '<td id="id_ts_Requirement"><select id="id_selectRequirement"><option value="" id="id_chooseOption"></option><c:forEach var="Tasks" items="${Tasks}"><option value="${Tasks.task_id}">${Tasks.task_subject}</option></c:forEach></select></td>'
								
								+ '<td id="id_ts_workContent"><input id="id_input_WorkContent" type="text" value="" /><input type="hidden" value="" name="detail_timesheet_id"/></td>'
								
								+ '</tr>';
					});
	

	/*delete timesheet*/
	$('#id_buttonDelete').click(

	function() {
		var checkbox = document.getElementsByName('checkboxTS');
		var list_timesheet_id_delete = new Array();
		var table = document.getElementById("id_table");
		
		for (var i = 0; i < checkbox.length; i++) {

			if (checkbox[i].checked === true) {
				var temp = i + 1;
				var _detail_timesheet_id = table.rows[temp].cells[7].childNodes[1].value;
				var _ts_id = table.rows[temp].cells[0].childNodes[1].value;
				
				table.rows[temp].remove();
				//chi push nhung detail_timesheet_id da ton tai => da co trong database
				if(_detail_timesheet_id!=""){
					var infor_Object_Delete = {
							detail_timesheet_id : _detail_timesheet_id,
							ts_id : _ts_id
						} 
					list_timesheet_id_delete.push(infor_Object_Delete);
				}
				
				temp--;
				i--;

			}

		}
		
		if(list_timesheet_id_delete.length!=0){
			
			var token = $("meta[name='_csrf']").attr("content");

			var header = $("meta[name='_csrf_header']").attr(
				"content"); 
				
				//use ajax to submit
				$.ajax({
					url : "actionDeleteListTimeSheet",

					type : "POST",
					data : JSON
							.stringify(list_timesheet_id_delete),
					contentType : 'application/json;charset=UTF-8',
					dataType : 'json',

					beforeSend : function(xhr) {
						// here it is
						xhr.setRequestHeader(header, token);
					},

					success : function(data) {
						alert("delete completed!");
					},
					error : function(data) {
						alert("error! ");
					}
				});
			
		}
		
		
		//console.log(list_timesheet_id_delete);

	});


	/* function check_hour(p1, p2) {
	   return p1 * p2;
	} */
	//document.getElementById("demo").innerHTML = myFunction(4, 3);
	
	/* save timesheet */
	$('#id_buttonSave')
			.click(
					function() {
						//alert("save");
						var table = document.getElementById("id_table");
						var len = table.rows.length;
						var check_hour = false;
						var check_process = false;
						var check_type = false;
						var String_alert_err = "";
						var check_hour_sum = false;
						var rows_hour_error = "";
						
						var _detail_timesheet_date_of_check_hour = table.rows[1].cells[1].innerHTML;
						var hour_check_sum = 0;
						
						
						
						
						// create arrayList object
						var arrayList_Timesheet = new Array();

						
						for (var i = 1; i < len; i++) {
							
							
							// set value of var
							var _detail_timesheet_id = table.rows[i].cells[7].childNodes[1].value;
							var _detail_timesheet_date = table.rows[i].cells[1].innerHTML;
							//alert(_detail_timesheet_date);
							var _hour = Number(table.rows[i].cells[2].childNodes[0].value);
							var _pre_defined_id = table.rows[i].cells[3].childNodes[0].value;
							var _process_id = table.rows[i].cells[4].childNodes[0].value;
							var _type_id = table.rows[i].cells[5].childNodes[0].value;
							var _task_id = table.rows[i].cells[6].childNodes[0].value;
							var _workcontent = table.rows[i].cells[7].childNodes[0].value;
							var _ts_id = table.rows[i].cells[0].childNodes[1].value;
							
							var infor_Object = {
									detail_timesheet_id : _detail_timesheet_id,
									detail_timesheet_date : _detail_timesheet_date,
									hour : _hour,
									pre_defined_id : _pre_defined_id,
									process_id : _process_id,
									type_id : _type_id,
									task_id : _task_id,
									workcontent : _workcontent,
									ts_id : _ts_id
								} 

							
							arrayList_Timesheet.push(infor_Object);
							
							
							//var hour=table.rows[i].cells[2].childNodes[0].value;
							
							if (isNaN(_hour)|| _hour == 0) {
								check_hour = true;

							}
							if(check_hour==false){
								if(_detail_timesheet_date == _detail_timesheet_date_of_check_hour){
							
									hour_check_sum += _hour;
									//alert("current hour = "+hour_check_sum);
									if(hour_check_sum > 8){
										//alert("loi");
										check_hour_sum = true;
									}
								}
								else{
									hour_check_sum = 0;
									_detail_timesheet_date_of_check_hour = _detail_timesheet_date;
									
								}
							}
							

							if (_process_id == 0) {
								check_process = true;
							}
							if (_type_id == 0) {
								check_type = true;
							}
							/* if (table.rows[i].cells[1].childNodes[0].childNodes[0].value == ""){
								check_date = true;
							} */
							
							
						}
						console.log(arrayList_Timesheet);
						
						if(!check_hour_sum && !check_hour && !check_process && !check_type){
							
							/* var csrfParameter = $(
							"meta[name='_csrf_parameter']").attr(
							"content"); */

					 	var token = $("meta[name='_csrf']").attr("content");

						var header = $("meta[name='_csrf_header']").attr(
							"content"); 
							
							//use ajax to submit
							$.ajax({
								url : "actionSaveTimeSheet",

								type : "POST",
								data : JSON
										.stringify(arrayList_Timesheet),
								contentType : 'application/json;charset=UTF-8',
								dataType : 'json',

								beforeSend : function(xhr) {
									// here it is
									xhr.setRequestHeader(header, token);
								},

								success : function(data) {
									alert("save completed!");
									location.reload();
								},
								error : function(data) {
									alert("error! ");
								}
							});
							
						}
						else{
							//alert invalid
							if( check_hour_sum ==true){
								if(check_hour_sum) {
									alert("SUM HOUR of one day must <= 8(hour)!");
								}
							}
							if (check_hour || check_process || check_type) {

								 if (check_hour==true) {
									String_alert_err += "  HOUR(is number) ";
								} 
								
								if (check_process) {
									String_alert_err += "  ,Process ";
								}
								if (check_type) {
									String_alert_err += "  ,Type Of Word ";
								}
								
								alert(String_alert_err + "is not empty!");
								
								
							}
							
						}
						 

					});

	/* $("#id_table").on("click", "#id_ts_Definded", function(e) {

	});
	$("#id_table").on("click", "#id_ts_Process", function(e) {

	});
	$("#id_table").on("click", "#id_ts_Type", function(e) {

	});
	$("#id_table").on("click", "#id_ts_Requirement", function(e) {

	}); */
</script>
</html>
