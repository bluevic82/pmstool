<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resource</title>
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

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
</head>

<body onload="onload_function()">
	<!-- <form action='saveReSourceMemberToDB' method='POST'> -->
	<form action="saveReSourceMemberToDB" method="POST">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />


		<div class="container">

			<div>
				Project name <input disabled="disabled" value="${nameOfProject}"
					style="width: 300px">
			</div>
			<div class="row" style="margin-top: 30px;">
				<div class="col-sm-6">


					<table class="table" style="background-color: #FFE7BA"
						id="dataTable">
						<thead>
							<tr style="background-color: #C1FFC1">
								<th scope="col"><div>#</div></th>
								<th scope="col"><div>Member</div></th>
								<th scope="col"><div>Effort</div></th>
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

									<th>${listMemberOfProject.member_project_name}</th>

									<th><input type="text" id="id_input_Effort"
										value="${listMemberOfProject.member_project_effort}"
										style="width: 50px"></th>



									<th><div class="class_Role">${listMemberOfProject.role_name }
											<div id="panel" style="height: 100%; display: none">
												<select id="id_select_Role">
													<option value="none">-----------</option>
													<c:forEach var="roleUser" items="${roleUser}">
														<option value="${roleUser.role_name}">${roleUser.role_name}</option>
													</c:forEach>
												</select>
											</div>
										</div></th>

									<th><input type="button" id="editRow"
										onclick="edit_function()" value="Edit" /></th>

									<th><input type="button" id="deleteRow"
										onclick="delete_function()" value="Delete" /></th>
								</tr>

							</c:forEach>

						</tbody>
					</table>

				</div>
				<div class="col-sm-6">
					<div class="row">
						<div class="col-sm-6">
							System member
							<div class="card border-secondary"
								style="overflow: scroll; height: 300px; width: 200px;">
								<div class="card-body">
									<c:forEach var="getAllUser" items="${getAllUser}">
										<input type="checkbox" value="${getAllUser.user_fullName}"
											name="checkboxName">${getAllUser.user_fullName}<br>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							Roles

							<div class="card border-secondary">
								<div class="card-body">
									<c:forEach var="roleUser" items="${roleUser}">
										<input type="radio" value="${roleUser.role_name}"
											name="radioRole"> ${roleUser.role_name}<br>
									</c:forEach>
								</div>
							</div>

						</div>
					</div>
					<br>
					<!-- <div style="text-align: end">
					<input type="button" id="id_save_button"
						onclick="saveToDB_function()"
						style="background-color: green; color: white; float: left"
						value="Save" />
				</div> -->
					<div style="text-align: end">
						<input type="submit"
							style="background-color: green; color: white; float: left"
							value="Save" />
					</div>
					<div style="text-align: end">

						<input type="button" onclick="addRowToTable_function()"
							style="background-color: green; color: white;" value="Add" />
					</div>

				</div>
			</div>

		</div>
	</form>
	<!-- </form> -->

	<script type="text/javascript">
		var table = document.getElementById("dataTable");
		var rowCount = table.rows.length;

		/*set so thu tu auto tang*/
		var hang = 1;
		function onload_function() {
			for (var i = 0; i < rowCount; i++) {
				document.getElementById("dataTable").rows[hang].cells[0].innerHTML = hang;
				hang++;

			}
		}

		/*delete row*/
		$("#dataTable").on("click", "#deleteRow", function(e) {
			e.preventDefault();
			$(this).parent('th').parent('tr').remove();
			hang = 1;
			onload_function();
		});

		/*add row*/
		function addRowToTable_function() {
			var checkboxName = document.getElementsByName('checkboxName');
			var radioRole = document.getElementsByName('radioRole');
			var name;
			var role;
			var array_name_Of_Member_Add = [];
			var soLuongMemberThemVao = 0;

			for (var i = 0; i < radioRole.length; i++) {
				if (radioRole[i].checked === true) {

					role = radioRole[i].value;
				}
			}

			for (var j = 0; j < checkboxName.length; j++) {
				if (checkboxName[j].checked === true) {
					name = checkboxName[j].value;
					array_name_Of_Member_Add[soLuongMemberThemVao] = name;
					soLuongMemberThemVao++;
				}
			}
			if (role != null && name != null) {

				for (var i = 0; i < array_name_Of_Member_Add.length; i++) {

					document.getElementById("dataTable").insertRow(-1).innerHTML = '<tr id="row_function"><th><div>'
							+ hang
							+ '<div></th>'
							+ '<th>'
							+ array_name_Of_Member_Add[i]
							+ '</th>'
							+ '<th><input type="text" value = "" style="width: 50px"></th>'
							+ '<th><div class="class_Role">'
							+ role
							+ '<div id="panel" style="height: 100%; display: none">'
							+ '<select id="id_select_Role">'
							+ '<option value="none">-----------</option>'
							+ '<c:forEach var="roleUser" items="${roleUser}">'
							+ '<option value="${roleUser.role_name}">${roleUser.role_name}</option></c:forEach></select></div>'
							+ '</div></th>'
							+ '<th><input type="button" id="editRow" onclick="edit_function()"value="Edit" /></th>'
							+ '<th><input type="button" id="deleteRow" onclick="delete_function()" value="Delete" /></th></tr>';
					hang++;
				}

			} else {
				alert("Name or Role is empty, Please choose Both!");
			}

		}

		/*Edit row*/
		$("#dataTable")
				.on(
						"click",
						"#editRow",
						function(e) {
							e.preventDefault();
							$(this).parent('th').parent('tr').find("#panel")
									.slideToggle("fast");
							var role;

							$(this)
									.parent('th')
									.parent('tr')
									.find("#id_select_Role")
									.on(
											'change',
											function() {
												var valueOfSelectRole = $(this)
														.val();
												var n = $(this).html();
												var p = $(this)
														.parent('div')
														.parent('div')
														.parent('th')
														.parent('tr')
														.find(".class_Role")
														.html(
																''
																		+ valueOfSelectRole
																		+ '<div id="panel" style="height: 100%; display: none">'
																		+ '<select id="id_select_Role"><option value="none">-----------</option>'
																		+ '<c:forEach var="roleUser" items="${roleUser}">'
																		+ '<option value="${roleUser.role_name}">${roleUser.role_name}</option></c:forEach>'
																		+ '</select></div>');
											});

						});

		/* $(document).ready(function() {
			$('#id_save_button').click(function() {

				for (var i = 1; i < hang; i++) {

					var array_Infor_Member_Of_Project = [];
					var name = table.rows[i].cells[1].innerHTML;

					var effort = table.rows[i].cells[2].childNodes[0].value;
					//var role = table.rows[1].cells[3].val(); 
					alert(name);

				}

			});
		}); */
	</script>




</body>
</html>