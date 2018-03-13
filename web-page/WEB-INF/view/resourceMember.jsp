<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resource</title>
<jsp:include page="_banner1.jsp"></jsp:include>
<jsp:include page="_menu.jsp" />
<%-- <link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap-reboot.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap-grid.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap-grid.css" />"> --%>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta name="_csrf_parameterName" content="${_csrf.parameterName}" />

</head>

<body onload="onload_function()">
<h6 style="margin-left: 20px">${projectInfo.project_name} > Setting > Resource</h6>

	<!-- <form action='saveReSourceMemberToDB' method='POST'> -->
	<!-- <form action="saveReSourceMemberToDB" method="post" id="table_ResourceMember_Form"> -->


	<input id="id_csrf" type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />


	<div class="container">

		<div>
			Project name <input disabled="disabled" id="project_name"
				value="${projectInfo.project_name}"
				name="${projectInfo.project_id }" style="width: 30%"> <input
				type="hidden" id="project_id" value="${projectInfo.project_id}"
				name="project_id">
		</div>


		<div class="row" style="margin-top: 30px;">
			<div class="col-sm-6">


				<table class="table" style="background-color: #FFE7BA"
					id="dataTable">
					<thead>
						<tr style="background-color: #C1FFC1">
							<th scope="col"><div>#</div></th>
							<th scope="col"><div>Member</div></th>
							<th scope="col"><div>Effort(%)</div></th>
							<th scope="col"><div>Role</div></th>
							<th scope="col"><div></div></th>
							<th scope="col"><div></div></th>

						</tr>
					</thead>
					<tbody>

						<c:forEach var="listMemberOfProject"
							items="${listMemberOfProject}">

							<tr id="row_function">
								<th><div></div></th>

								<th id="id_member_project_name"><input type="hidden" id="member_project_id"value="${listMemberOfProject.member_project_id}"name="member_project_id" /><input type="hidden" id="user_id" value="${listMemberOfProject.user_id}" name="user_id" /><div>${listMemberOfProject.member_project_name}</div></th>
									
								
								<th><input type="text" id="id_input_Effort"
									value="${listMemberOfProject.member_project_effort}"
									style="width: 50px"></th>

								<th id="id_cell_role"><input type="hidden" id="role_id" value="${listMemberOfProject.role_id}" name="role_id" /><div class="class_Role">${listMemberOfProject.role_name}</div><div id="panel" style="height: 100%; display: none"><select id="id_select_Role"><c:forEach var="roleUser" items="${roleUser}"><option id="role_select" value="${roleUser.role_id}">${roleUser.role_name}</option></c:forEach></select></div></th>
									
												
						
								<!-- <th><input type="button" id="editRow" value="Edit" /></th>

								<th><input type="button" id="deleteRow" value="Delete" /></th> -->
								<!-- <th><input style="background-image: url('../resources/image/edit.png');" type="button" id="editRow" value="Edit" /></th> -->
								<th><img alt="" src="../resources/image/edit.png" width="30px;" height="30px;"  id="editRow"/></th>
								<!-- <th><input style="background-image: url('../resources/image/delete.png');" type="button" id="deleteRow" value="Delete" /></th> -->
								<th><img alt="" src="../resources/image/delete.png" width="30px;" height="30px;" id="deleteRow"/></th>
							</tr>

						</c:forEach>

					</tbody>
				</table>

			</div>
			<div class="col-sm-6">
				<div class="row">
					<div class="col-sm-6">
						<b>System member</b>
						<div class="card border-secondary"
							style="overflow: auto;position: relative; height: 300px; border: solid; border-width: 1px">
							<div class="card-body" style="padding: 5px 10px 10px 5px">
								<c:forEach var="getAllUser" items="${getAllUser}">
									<input type="checkbox" value="${getAllUser.user_fullName}"
										name="checkboxName">${getAllUser.user_fullName}
										<input type="hidden" value="${getAllUser.user_id}" name="hidden_checkboxName"> <br>
								</c:forEach>

							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<b>Roles</b>

						<div class="card border-secondary" style="border: solid; border-width: 1px">
							<div class="card-body" style="padding: 5px 10px 10px 5px">
								<c:forEach var="roleUser" items="${roleUser}">
									<input type="radio" value="${roleUser.role_name}"
										name="radioRole"> ${roleUser.role_name}
									<input type="hidden" value="${roleUser.role_id}"
										name="hidden_radioRole"><br>	
								</c:forEach>

							</div>
						</div>

					</div>
				</div>
				<br>
				<div style="text-align: end">

					<button id="id_save_button" type="submit"
						style="background-color: green; color: white; float: left">Save</button>
				</div>

				<div style="text-align: end">

					<input type="button" onclick="addRowToTable_function()"
						style="background-color: green; color: white;" value="Add" />
				</div>

			</div>
		</div>

	</div>
	<!-- </form> -->
	
	

	<script type="text/javascript">
		var token = $("meta[name='_csrf']").attr("content");

		var header = $("meta[name='_csrf_header']").attr("content");

		var table = document.getElementById("dataTable");
		var rowCount = table.rows.length;

		/*set so thu tu auto tang*/
		var hang = 1;

		function onload_function() {
			var table = document.getElementById("dataTable");
			var rowCount = table.rows.length;

			for (var i = 1; i < rowCount; i++) {
				table.rows[i].cells[0].innerHTML = hang;
				hang++;

			}
		}

		/*delete row*/
		$("#dataTable").on(
				"click",
				"#deleteRow",
				function(e) {
					e.preventDefault();
					$(this).parent('th').parent('tr').remove();

					//var $(this).parent('th').parent('tr').;

					var member_project_id = $(this).parent('th').parent('tr')
							.find("#member_project_id").val();
					//alert(member_project_id);
					//id_member_project_name

			
        					$.ajax({
        						url : "deleteOneMemberProject",

        						type : "POST",
        						data : JSON.stringify(member_project_id),
        						contentType : 'application/json;charset=UTF-8',
        						dataType : 'json',

        						beforeSend : function(xhr) {
        							// here it is
        							xhr.setRequestHeader(header, token);
        						},

        						success : function(data) {
        							alert("delete done!");
        						},
        						error : function(data) {
        							alert("error! ");
        						}
        					});
        					
        					hang = 1;
        					onload_function();
        					
					
				});

		/*add row*/
		function addRowToTable_function() {
			var checkboxName = document.getElementsByName('checkboxName');
			var radioRole = document.getElementsByName('radioRole');
			var hidden_radioRole = document.getElementsByName('hidden_radioRole');
			var hidden_checkboxName = document.getElementsByName('hidden_checkboxName');
			var name;
			var name_id;
			var role;
			var role_id;
			var array_name_Of_Member_Add = [];
			var soLuongMemberThemVao = 0;
			var string_Alert_Exists_Error = "";

			for (var i = 0; i < radioRole.length; i++) {
				if (radioRole[i].checked === true) {

					role = radioRole[i].value;
					role_id = hidden_radioRole[i].value;
					
				}
			}

			
			for (var j = 0; j < checkboxName.length; j++) {
				if (checkboxName[j].checked === true) {
					name = checkboxName[j].value;
					name_id = hidden_checkboxName[j].value;
					var check_trung_user_id = false;
				
					/* alert(table.rows[2].cells.namedItem("id_member_project_name").childNodes[1].value)  //OK;
					alert(hang); //OK */ 
					//check trùng lặp user_id
					  for( var k = 1; k < hang; k ++){
						if(name_id == table.rows[k].cells.namedItem("id_member_project_name").childNodes[1].value){
							//alert("trung");
							check_trung_user_id = true;
							break;
						}
								
					}
					  if(check_trung_user_id == true){
							string_Alert_Exists_Error = "  "+ name + string_Alert_Exists_Error;
						}
					  else{
						  var objectUser = {
									name : name,
									name_id : name_id
									
								}
							
							array_name_Of_Member_Add[soLuongMemberThemVao] = objectUser;
							
							soLuongMemberThemVao++;
					  }
					  
				}
				
				}
			
			if(string_Alert_Exists_Error!=""){
				alert(string_Alert_Exists_Error + " are already existed!");
			}
			
			if (role != null && name != null) {

				for (var i = 0; i < array_name_Of_Member_Add.length; i++) {

					document.getElementById("dataTable").insertRow(-1).innerHTML = '<tr id="row_function"><th><div>'
							+ hang
							+ '<div></th>'
							+ '<th id="id_member_project_name"><input type="hidden" id="member_project_id"value="" name="member_project_id" /><input type="hidden" id="user_id" value="'+array_name_Of_Member_Add[i].name_id+'" name="user_id" /><div>'+array_name_Of_Member_Add[i].name+'</div></th>'
							
							+ '<th><input type="text" value = "" style="width: 50px"></th>'
							+ '<th id="id_cell_role"><input type="hidden" id="role_id" value="'+role_id+'" name="role_id" /><div class="class_Role">'+role+'</div><div id="panel" style="height: 100%; display: none"><select id="id_select_Role"><c:forEach var="roleUser" items="${roleUser}"><option id="role_select" value="${roleUser.role_id}">${roleUser.role_name}</option></c:forEach></select></div></th>'
							+ '<th><img alt="" src="../resources/image/edit.png" width="30px;" height="30px;"  id="editRow"/></th>'
							+ '<th><img alt="" src="../resources/image/delete.png" width="30px;" height="30px;" id="deleteRow"/></th></tr>';
					hang++;
				}

			} else {
				alert("Name or Role is empty, Please choose Both!");
			}

		}

	

		/*Edit row*/
		$("#dataTable").on(
				"click",
				"#editRow",
				function(e) {
					e.preventDefault();
					$(this).parent('th').parent('tr').find("#panel")
							.slideToggle("fast");

					$(this).parent('th').parent('tr').find("#id_select_Role")
							.on(
									'change',
									function() {

										//get role_name at choose
										var valueOfSelectRole = $(this).find(
												"option:selected").text();

										//get role_id at choose
										var role_id_choose = $(this).val();
										
										//set role_id at
										$(this).parent('div').parent('th')
												.parent('tr').find("#role_id")
												.val(role_id_choose);

										$(this).parent('div').parent('th')
												.parent('tr').find(
														".class_Role").html(
														valueOfSelectRole);

									});

				});

		$('#id_save_button')
				.click(
						function(e) {
							var array_Infor_Member_Of_Project = new Array();
							var _project_id = $("#project_id").val();
							var _check_loi_effort = false;	
							
							for (var i = 1; i < hang; i++) {
								var _member_project_name = table.rows[i].cells
										.namedItem("id_member_project_name").childNodes[1].nextSibling.innerHTML;
								
								var _member_project_effort = table.rows[i].cells[2].childNodes[0].value;
								
								//validate effort
								if(_member_project_effort == ""){
									_member_project_effort=100;
								}
								if(_member_project_effort<0 || _member_project_effort >100 || (_member_project_effort != parseInt(_member_project_effort, 10)) ){
									
									//table.rows[i].cells[2].childNodes[0].value;
									_check_loi_effort = true;
									
								}
								//alert("_member_project_effort: "+Number.isInteger(_member_project_effort));
								
								

								var _role_name = table.rows[i].cells
										.namedItem("id_cell_role").childNodes[1].innerHTML;

								//var _role_id = table.rows[i].cells.namedItem("id_cell_role").childNodes[1].value;
								//var member_project_id = table.rows[i].cells[1].find("#user_id").val();

								var _member_project_id = table.rows[i].cells
										.namedItem("id_member_project_name").childNodes[0].value;

								var _user_id = table.rows[i].cells
										.namedItem("id_member_project_name").childNodes[1].value;
								

								var _role_id = table.rows[i].cells
										.namedItem("id_cell_role").childNodes[0].value;
								
								

								var infor_Object = {
									member_project_name : _member_project_name,
									member_project_effort : _member_project_effort,
									role_name : _role_name,
									project_id : _project_id,
									user_id : _user_id,
									member_project_id : _member_project_id,
									role_id : _role_id
								}

								array_Infor_Member_Of_Project
										.push(infor_Object);

							}
							
							if(_check_loi_effort){
								alert("Error! Effort is number(Integer) and from 0 to 100. Please reset effort!");
							} else{
								//console.log(array_Infor_Member_Of_Project);

								
										$.ajax({
											url : "actionSaveMemberToDB",

											type : "POST",
											data : JSON
													.stringify(array_Infor_Member_Of_Project),
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

							/* var csrfParameter = $(
									"meta[name='_csrf_parameter']").attr(
									"content"); */

							/* var token = $("meta[name='_csrf']").attr("content");

							var header = $("meta[name='_csrf_header']").attr(
									"content"); */

							

						});
		
	</script>



	<jsp:include page="_bottom1.jsp"></jsp:include>
</body>
</html>