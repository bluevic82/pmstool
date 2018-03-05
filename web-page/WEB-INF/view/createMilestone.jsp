<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MileStone</title>
<jsp:include page="_banner1.jsp"></jsp:include>
<jsp:include page="_menu.jsp" />
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet"
	href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" />">

<script src="https://momentjs.com/downloads/moment.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker-standalone.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker-standalone.min.css">
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
<h4 style="padding-bottom: 4%">${projectInfor.project_name } > Setting > Milestone</h4>
	<div class="container">

		<div>
			Project Name<input style="margin-left: 50px; width: 20%; "
				value="${projectInfor.project_name }" disabled="disabled">
		</div>
		<div>
			<input type="hidden"
				value="${projectInfor.project_id}" name="project_id" id="p_id">
		</div>
		
		<%-- <form:form id="id_form"> --%>
			<div>Milestone</div>
			<table id="id_table" class="table"
				style="margin-top: 2%;">
				<thead>
					<tr>
						<th scope="col"></th>
						<th scope="col"></th>
						<th scope="col"></th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody >
				<c:forEach var="ml" items="${ml}">
				<tr id="id_tr">
				<td><input type="hidden"
				value="${ml.milestone_id}" name="milestone_id" id="id_milestone_id" width="100px;"></td><td><button id="button_delete">-</button></td><td id="id_Date"><div class="input-group date datetimepicker" ><input id="d_dateTime" type="text" class="form-control"name="milestone_date"value="${ml.milestone_date}"/><div class="input-group-addon"><div class="glyphicon glyphicon-calendar"></div></div></div></td><td id="id_des"><input id="id_description" class="form-control input-sm" type="text" name="milestone_description" value="${ml.milestone_description}" /></td></tr>
				
				</c:forEach>
						<!-- <tr id="id_tr">
							<td>MileStone</td>
							<td></td>
							<td id="id_Date"><div class="input-group date datetimepicker" >
									<input id="d_dateTime" type="text" class="form-control"
										name="milestone_date"
										value="" />
									<div class="input-group-addon">
										<div class="glyphicon glyphicon-calendar"></div>
									</div>
								</div>
							</td>
							<td id="id_des">
							<input id="id_description" class="form-control input-sm" type="text" name="milestone_description"
								value="" />
							</td>
						</tr> -->
				</tbody>
				
			</table>
		<%-- </form:form> --%>
		<button id="button_add">+</button>
		<div align="right" style="padding-top: 5%">
			<button type="submit" id="id_buttonSave">Save</button>
		</div>
	</div>
	
	<input type="text"
				value="${projectInfor.project_from}" name="from" id="from">
	<input type="text"
				value="${projectInfor.project_to}" name="to" id="to">
</body>

<style>
#id_Date{
	width: 20%; 
}
#button_delete{
	
}
</style>
<script type="text/javascript">
$('.datetimepicker').datetimepicker({
	format : "YYYY-MM-DD",
});
</script>
<script type="text/javascript">
	var $TABLE = $('#id_table');
	
	/* delete row milestone */
	
	$("#id_table").on("click", "#button_delete", function(e) {
		e.preventDefault();
		var milestone_id = $(this).parent('td').parent('tr').find('#id_milestone_id').val();
		$(this).parent('td').parent('tr').remove();
		
		//submit by ajax to detele
		var token = $("meta[name='_csrf']").attr("content");

		var header = $("meta[name='_csrf_header']").attr(
			"content"); 
		//use ajax to submit
		$.ajax({
			url : "actionDeleteMileStone",

			type : "POST",
			data : JSON
					.stringify(milestone_id),
			contentType : 'application/json;charset=UTF-8',
			dataType : 'json',

			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			},

			success : function(data) {
				//alert("delete completed!");
			},
			error : function(data) {
				alert("error! ");
			}
		});	
		
		
	}); 
	
	
	/*add row milestone*/
	$('#button_add').click(function() {
						document.getElementById("id_table").insertRow(-1).innerHTML = '<tr id="id_tr"><td><input type="hidden" value="" name="milestone_id" id="id_milestone_id" width="100px;"></td><td><button id="button_delete">-</button></td><td id="id_Date"><div class="input-group date datetimepicker" ><input id="d_dateTime" type="text" class="form-control"name="milestone_date"value="" /><div class="input-group-addon"><div class="glyphicon glyphicon-calendar"></div></div></div></td><td id="id_des"><input id="id_description" class="form-control input-sm" type="text" name="milestone_description"value="" /></td></tr>';

						$('.datetimepicker').datetimepicker({
							format : "YYYY-MM-DD",
						});
					});
	
	
	//---------------------
	$('#id_buttonSave').click(		
					function() {
						//var m_id = $("#milestone_id").val();
						/* if( m_id == null ){
							var table = document.getElementById("id_table");
							var p_id = $("#p_id").val();
							var len = table.rows.length;	
							
							var arrayList_Milestone = new Array();

							for (var i = 1; i < len; i++) {
								// set value of var
								var _milestone_date = table.rows[i].cells[2].childNodes[0].childNodes[0].value;
								var _milestone_des = table.rows[i].cells[3].childNodes[0].value;
								var info_Object = {
										project_id: p_id,
										milestone_date: _milestone_date,
										milestone_description : _milestone_des,
										} 

								
								arrayList_Milestone.push(info_Object);
							}
							console.log(arrayList_Milestone);
							

						 	var token = $("meta[name='_csrf']").attr("content");

							var header = $("meta[name='_csrf_header']").attr(
								"content"); 
							//use ajax to submit
							$.ajax({
								url : "actionAddMileStone",

								type : "POST",
								data : JSON
										.stringify(arrayList_Milestone),
								contentType : 'application/json;charset=UTF-8',
								dataType : 'json',

								beforeSend : function(xhr) {
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
						} */
						//else{
							var table = document.getElementById("id_table");
							var p_id = $("#p_id").val();
							var len = table.rows.length;
							var String_alert_err = "";
			
							// create arrayList object
							var arrayList_Milestone = new Array();

							for (var i = 1; i < len; i++) {
								// set value of var
								var _milestone_id = table.rows[i].cells[0].childNodes[0].value;
								var _milestone_date = table.rows[i].cells[2].childNodes[0].childNodes[0].value;
								var _milestone_des = table.rows[i].cells[3].childNodes[0].value;
								from = $("#from").val();
								to = $("#to").val();
								if(_milestone_date < from){
									alert("milestone " + _milestone_date+ " is not smaller than start project!");
								}else if (_milestone_date > to) {
									alert("milestone " + _milestone_date+ " is not bigger than end project!");
								}else if (_milestone_date >= from && _milestone_date <= to) {
									
								
								var info_Object = {
										project_id: p_id,
										milestone_date: _milestone_date,
										milestone_description : _milestone_des,
										milestone_id : _milestone_id
									} 

								arrayList_Milestone.push(info_Object);
							}
							}
							console.log(arrayList_Milestone);
							

						 	var token = $("meta[name='_csrf']").attr("content");

							var header = $("meta[name='_csrf_header']").attr(
								"content"); 
							if(len == (arrayList_Milestone.length+1)){
									//use ajax to submit 
											$.ajax({
												url : "actionSaveMileStone",

												type : "POST",
												data : JSON
														.stringify(arrayList_Milestone),
												contentType : 'application/json;charset=UTF-8',
												dataType : 'json',

												beforeSend : function(xhr) {
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
					});
</script>
</html>
