<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TimeSheet List</title>
<jsp:include page="_banner.jsp"></jsp:include>
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

<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta name="_csrf_parameterName" content="${_csrf.parameterName}" />
</head>
<body>
<h6 style="margin-left: 20px">Timesheet Management > <a href="${pageContext.request.contextPath}/timeSheetList"> List</a></h6>
	<div class="container" style="margin-top: 10px;">
		<div>
			<form method="post" action="/Login/timeSheetList/">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> Project Name <select name="projectName">
					
					<option value="0"></option>
					<c:forEach var="listProjects" items="${listProjects}">
						<option value="${listProjects.project_id}" <c:if test="${listProjects.project_id==project_searched.project_id}"> selected="selected"</c:if> >${listProjects.project_name}</option>
					</c:forEach>
				</select> <label style="margin-left: 10px"> PIC </label> <select
					name="user_id" style="margin-left: 5px">
					<option value="0"></option>
					<c:forEach var="list_PIC" items="${list_PIC}">
						<option value="${list_PIC.user_id}" <c:if test="${list_PIC.user_id==PIC_searched.user_id}"> selected="selected"</c:if>>
							${list_PIC.member_project_name}</option>
					</c:forEach>
				</select> <label style="margin-left: 10px"> Process </label> <select  id="id_select_process"
					name="process_id" style="margin-left: 5px">
					<option value="0"></option>
					<c:forEach var="process" items="${process}">
						<option value="${process.process_id}" <c:if test="${process.process_id==process_searched.process_id}"> selected="selected"</c:if>>
							${process.process_name}</option>
					</c:forEach>
				</select> <label style="margin-left: 10px"> Status </label> <select
					name="status_name" style="margin-left: 5px">
					<option value=""></option>
					<c:forEach var="timeSheetStatus" items="${timeSheetStatus}">
						<option value="${timeSheetStatus.status_name}" <c:if test="${timeSheetStatus.status_name==status_searched}"> selected="selected"</c:if>>
							${timeSheetStatus.status_name}</option>
					</c:forEach>
				</select>
				<button type="submit"
					style="background-color: green; color: white; margin-left: 30px">Search</button>
			</form>
		</div>
		
		<table style="margin-top: 30px;" class="table table-bordered"
			id="id_table">
			<thead>
				<tr>
					<th scope="col" style="background-color: #3ADF00;"><input
						type="checkbox" id="id_check_All" onclick="function_checkAll()" /></th>
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
						<th><input type="checkbox" id="id_check_One" /></th>
						<th>${listTimeSheetDetails.memberProject.member_project_name}</th>
						<th><input type="hidden" name="detail_timesheet_id" value="${listTimeSheetDetails.detail_timesheet_id}">${listTimeSheetDetails.detail_timesheet_date}</th>
						<th>${listTimeSheetDetails.hour}</th>
						<th>${listTimeSheetDetails.pre_defined_name}</th>
						<th>${listTimeSheetDetails.process_name}</th>
						<th>${listTimeSheetDetails.type_name}</th>
						<th>${listTimeSheetDetails.task_subject}</th>
						<th>${listTimeSheetDetails.workcontent}</th>
						<th><select id="select_stt" style="margin-left: 5px; border: hidden;"><option id="id_Get_status_type" value="${listTimeSheetDetails.status_type}">${listTimeSheetDetails.status_type}</option>
							<option id="id_status_type_Request" value="Request">Request</option>
							<option id="id_status_Approved" value="Approved">Approved</option>
							<option id="id_status_Reject" value="Reject">Reject</option></select></th>
					</tr>



				</c:forEach>
			</tbody>
		</table>
		<div align="right" style="padding-top: 2%; padding-bottom: 5%">
			<button id="id_buttonApprove">Approve</button>
			<button type="submit" id="id_buttonSave">Save</button>
		</div>
	</div>
		<jsp:include page="_bottom.jsp"></jsp:include>
</body>

<style>

  
</style>
<script type="text/javascript">
	 
	 $("#id_table").on(
				"change",
				"#select_stt",
				function(e) {
					e.preventDefault();
					/* alert($(this).val());	
					alert($(this).text()); */
					$(this).parent('th').find("#id_Get_status_type").text($(this).val());
					$(this).parent('th').find("#id_Get_status_type").val($(this).val());
					//	$(this).parent('th').find("#id_Get_status_tytpe").value = $(this).val();
					
					
				});
	 
	 
	 
	var table = document.getElementById("id_table");
	var len = table.rows.length;
	
	function function_checkAll() {
		
		if (document.getElementById("id_check_All").checked === true) {

			for (var i = 1; i < len; i++) {

				table.rows[i].cells[0].childNodes[0].checked = true;
				//alert("true");
			}

		}

		else if (document.getElementById("id_check_All").checked === false) {

			for (var i = 1; i < len; i++) {

				table.rows[i].cells[0].childNodes[0].checked = false;
				//alert("true");
			}

		}

	}
	 
	 $('#id_buttonApprove')
		.click(
				function() {
					 for(var i =1;i<len;i++){
							
							if(table.rows[i].cells[0].childNodes[0].checked===true){
									table.rows[i].cells[9].childNodes[0].childNodes[0].innerHTML = "Approved";
									table.rows[i].cells[9].childNodes[0].childNodes[0].value = "Approved";
							}
						}
					
				});
	 
	 $('#id_buttonSave')
		.click(
				function() {
					// create arrayList object
					var arrayList_Timesheet = new Array();
					 for(var i =1;i<len;i++){
							
						// set value of var
							var _detail_timesheet_id = table.rows[i].cells[2].childNodes[0].value;
						 	//var test = $("#select_stt").val();
							var _status_type = table.rows[i].cells[9].childNodes[0].childNodes[0].value;
							//alert(_status_type);
							//console.log(test);
							
							 var infor_Object = {
									detail_timesheet_id : _detail_timesheet_id,
									status_type : _status_type
								} 

							
							arrayList_Timesheet.push(infor_Object);
						}
					
					 if(arrayList_Timesheet.length!=0){
						 
						 var token = $("meta[name='_csrf']").attr("content");

							var header = $("meta[name='_csrf_header']").attr("content");
						 
						 $.ajax({
								url : "/Login/actionUpdateStatusTypeOfListTimesheets",

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
					 //submit with ajax 
				});
	 
				
</script>
</html>