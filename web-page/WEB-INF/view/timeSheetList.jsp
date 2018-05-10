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
			<form method="post" action="timeSheetList">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <b>Project Name</b>  <select id="id_select_Project" name="projectName">
					
					<option value="0"></option>
					<c:forEach var="listProjects" items="${listProjects}">
						<option value="${listProjects.project_id}" <c:if test="${listProjects.project_id==project_searched.project_id}"> selected="selected"</c:if> >${listProjects.project_name}</option>
					</c:forEach>
				</select> <label style="margin-left: 2%"> PIC </label> <select id="id_select_User"
					name="user_id" style="margin-left: 5px; width: 15%">
					<option value="0"></option>
					<c:forEach var="list_PIC" items="${list_PIC}">
						<option value="${list_PIC.member_project_name}" <c:if test="${list_PIC.user_id==PIC_searched.user_id}"> selected="selected"</c:if>>
							${list_PIC.member_project_name}</option>
					</c:forEach>
				</select> <label style="margin-left: 2%"> Process </label> <select  id="id_select_process"
					name="process_id" style="margin-left: 5px; width: 20%">
					<option value="0"></option>
					<c:forEach var="process" items="${process}">
						<option value="${process.process_name}" <c:if test="${process.process_id==process_searched.process_id}"> selected="selected"</c:if>>
							${process.process_name}</option>
					</c:forEach>
				</select> <label style="margin-left: 2%"> Status </label> <select id="id_status_search"
					name="status_name" style="margin-left: 5px">
					<option value=""></option>
					<c:forEach var="timeSheetStatus" items="${timeSheetStatus}">
						<option value="${timeSheetStatus.status_name}" <c:if test="${timeSheetStatus.status_name==status_searched}"> selected="selected"</c:if>>
							${timeSheetStatus.status_name}</option>
					</c:forEach>
				</select>
				<button id="id_button_search" type="button"
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
						<th><input type="checkbox" id="id_check_One" /><input type="hidden"  id="td_id_project_Name" value="${listTimeSheetDetails.memberProject.project_id}"></th>
						<th>${listTimeSheetDetails.memberProject.member_project_name}</th>
						<th><input type="hidden" name="detail_timesheet_id" value="${listTimeSheetDetails.detail_timesheet_id}">${listTimeSheetDetails.detail_timesheet_date}</th>
						<th>${listTimeSheetDetails.hour}</th>
						<th>${listTimeSheetDetails.pre_defined_name}</th>
						<th>${listTimeSheetDetails.process_name}</th>
						<th>${listTimeSheetDetails.type_name}</th>
						<th>${listTimeSheetDetails.task_subject}</th>
						<th>${listTimeSheetDetails.workcontent}</th>
						<th style="width: 9%;"><select id="select_stt" style="width:100%; border: hidden;"><option id="id_Get_status_type" value="${listTimeSheetDetails.status_type}">${listTimeSheetDetails.status_type}</option>
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
		
</body>
<jsp:include page="_bottom.jsp"></jsp:include>
<style>
	#id_status_search{
		/* padding-right: 10%; */
	}
	#id_button_search{
		float: right;
		
	}
	#id_buttonApprove{
		margin-right: 20%;
		width: 10%;
		color: white;
		background-color: green;
	}
	#id_buttonSave{
		width: 10%;
		color: white;
		background-color: green;
	}
	thead tr th{
	 text-align: center;
	}
  
</style>
<script type="text/javascript">

	var table = document.getElementById("id_table");
	var len = table.rows.length;
	var arrayList_TimesheetResultSearched = new Array();
	 
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
	
	
	$('#id_button_search')
	.click(
			function() {
		//alert("ầdsfasdf");
		var status_select_search = $('#id_status_search :selected').val().toUpperCase();
		//get name
		var project_Name_search = $('#id_select_Project :selected').text().toUpperCase(); //ok
		
		//get id
		var project_id_search = $('#id_select_Project :selected').val();
		//get name
		var user_search = $('#id_select_User :selected').val().toUpperCase(); //ok
		//get name
		var process_search =  $('#id_select_process :selected').val().toUpperCase(); //ok
		
		
		 var conditionSearch;
		 
		 
		 
			
		 	
		 
		//set condition search
		
		var flag_conditionBefor = 0;
		 	var string_condition = "";
		 
		 	if(status_select_search !=""){ //ok
		 		
		 		string_condition += status_select_search;
		 	} 
		 	
		 	if(user_search !=0){ //ok
		 		
		 		
		 			string_condition += user_search;
		 		
		 		}
		 	
	 		if(process_search !=0){ //ok
	 			string_condition += process_search;
	 		}
	 		
	 		
	 		//create a new array to contain result of searching
			  arrayList_TimesheetResultSearched = new Array();
					
			
	 		
	 	if(project_id_search != 0){// projectNAme condition is not null
			 for (var i = 1; i < len; i++) {
				 
				 
				 tdStatus = table.rows[i].cells[9].childNodes[0].childNodes[0].value; //ok
				 	tdPic = table.rows[i].cells[1].innerHTML; //ok
				 	tdProcessName = table.rows[i].cells[5].innerHTML;
				 	tdProjectId = table.rows[i].cells[0].childNodes[1].value;
				 	
				 	
				 	
				 	var string_td = tdStatus.toUpperCase()+tdPic.toUpperCase()+tdProcessName.toUpperCase();
				 	
	 				if(string_td.includes(string_condition) && (tdProjectId==project_id_search)){
					 table.rows[i].style.display = "";  
					 
					 var detail_timesheet_id = table.rows[i].cells[2].childNodes[0].value;
	 				 var _status_type = table.rows[i].cells[9].childNodes[0].childNodes[0].value;
	 				 console.log(detail_timesheet_id); //write
	 				 var infor_Object = {
	 							detail_timesheet_id : detail_timesheet_id,
	 							status_type : _status_type
	 						}
	 				 
	 				 arrayList_TimesheetResultSearched.push(infor_Object);					 
					 
					 } 
	 				else{
	 					table.rows[i].style.display = "none";
	 				}
	 				
	 				
	 		
	 		}
			 
			 
	 	}
	 	
	 	else{ //projectNAme condition is  null
	 		
	 		console.log("string condition = "+string_condition);
			 for (var i = 1; i < len; i++) {
				 
				 
				 tdStatus = table.rows[i].cells[9].childNodes[0].childNodes[0].value; //ok
				 	tdPic = table.rows[i].cells[1].innerHTML; //ok
				 	tdProcessName = table.rows[i].cells[5].innerHTML;
				 	
				 	var string_td = tdStatus.toUpperCase()+tdPic.toUpperCase()+tdProcessName.toUpperCase();
				 	
			//	 	console.log("td searched = "+s);
				 	
			//	 	var s2 = tdStatus.toUpperCase().indexOf(status_select_search)>-1 && tdPic.toUpperCase().indexOf(user_search)>-1 && tdProcessName.toUpperCase().indexOf(process_search)>-1;
				 	
	 				if(string_td.includes(string_condition)){
					 table.rows[i].style.display = "";  
					 
					 var detail_timesheet_id = table.rows[i].cells[2].childNodes[0].value;
	 				 var _status_type = table.rows[i].cells[9].childNodes[0].childNodes[0].value;
	 				 console.log(detail_timesheet_id); //write
	 				 var infor_Object = {
	 							detail_timesheet_id : detail_timesheet_id,
	 							status_type : _status_type
	 						}
	 				 
	 				 arrayList_TimesheetResultSearched.push(infor_Object);
					 
				 } 
	 				else{
	 					table.rows[i].style.display = "none";
	 				}
	 				
	 				
	 				
			}
			
			 
	 		
	 	}
		
	 	
		 
	})
	
	
	//function find rows by detail_timesheet_id
	
	
	 
	 $('#id_buttonApprove')
		.click(
				function() {
					
					//if have condition search => only approve on list searched
					if(arrayList_TimesheetResultSearched.length != 0){
						console.log("have condition len = "+arrayList_TimesheetResultSearched.length)
						 for(var i = 0;i<arrayList_TimesheetResultSearched.length;i++){
							 
							 for(var j = 1; j < len; j ++){
								 var detail_timesheet_id_of_tableAll = table.rows[j].cells[2].childNodes[0].value;
							//	 var detail_timesheet_id_of_tableAll = table.rows[j].cells[2].childNodes[0].value;
								 console.log("ts_id = "+detail_timesheet_id_of_tableAll);
								 if(arrayList_TimesheetResultSearched[i].detail_timesheet_id === detail_timesheet_id_of_tableAll ){
									 if(table.rows[j].cells[0].childNodes[0].checked===true){
											table.rows[j].cells[9].childNodes[0].childNodes[0].innerHTML = "Approved";
											table.rows[j].cells[9].childNodes[0].childNodes[0].value = "Approved";
									}
								 }
							 }
								
								
							}
						
					}
					
					//if no condition searched => approve on all
					else{
						console.log("no condition len = "+len)
						 for(var i =1;i<len;i++){
								
								if(table.rows[i].cells[0].childNodes[0].checked===true){
										table.rows[i].cells[9].childNodes[0].childNodes[0].innerHTML = "Approved";
										table.rows[i].cells[9].childNodes[0].childNodes[0].value = "Approved";
								}
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
								url : "actionUpdateStatusTypeOfListTimesheets",

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