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

<body>
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
							<th scope="col">#</th>
							<th scope="col">Member</th>
							<th scope="col">Effort</th>
							<th scope="col">Role</th>
							<th scope="col"></th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
					
						<c:forEach var="listMemberOfProject"
							items="${listMemberOfProject}">

							<tr id="row_function">
								<th scope="row"  id="stt"></th>

								<th>${listMemberOfProject.member_project_name}</th>

								<th><input type="text"
									value="${listMemberOfProject.member_project_effort}"
									style="width: 50px"></th>

								<th>${listMemberOfProject.role_name }</th>

								<th><input type = "button" id="edit" onclick = "edit_function()"value = "Edit"/></th>
								
								<th><input type = "button" id="deleteRow" onclick = "delete_function(this)" value = "Delete"/></th>
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
				<div style="text-align: end">
					<input type="button" onclick="saveToDB_function()"
						style="background-color: green; color: white; float: left" value = "Save"/>
				</div>
				<div style="text-align: end">

					<input type="button" onclick="addRowToTable_function()"
						style="background-color: green; color: white;" value="Add"/>
				</div>
			
			</div>
		</div>
	</div>
 
	<script type="text/javascript">
	/*add row to table */
		function addRowToTable_function() {
			var table = document.getElementById("dataTable");
			var rowCount = table.rows.length;

			var checkboxName = document.getElementsByName('checkboxName');
			var radioRole = document.getElementsByName('radioRole');
			var name;
			var role;
			for (var i = 0; i < radioRole.length; i++) {
				if (radioRole[i].checked === true) {
					role = radioRole[i].value;
				}
			}

			for (var j = 0; j < checkboxName.length; j++) {
				if (checkboxName[j].checked === true) {
					name = checkboxName[j].value
				}
			}
			
			var row = table.insertRow(rowCount);
			var cell1 = row.insertCell(0);
		    var cell2 = row.insertCell(1);
		    var cell3 = row.insertCell(2);
		    var cell4 = row.insertCell(3);
		    var cell5 = row.insertCell(4);
		    var cell6 = row.insertCell(5);
		    cell1.innerHTML = rowCount;
		    cell2.innerHTML = name;
		    cell3.innerHTML = '<input type="text" style="width: 50px">';	
		    cell4.innerHTML = role;
		    cell5.innerHTML = '<input type = "button" id="edit" onclick = "edit_function()"value = "Edit"/>';
		    cell6.innerHTML = '<input type = "button" id="deleteRow" onclick = "delete_function()" value = "Delete"/>';
			
			
		}
		
		
	</script> 
	
	
	<script>
	/* set stt auto tang cho table load du lieu tu DB*/
		var table = document.getElementById('dataTable');
		var rowCount = table.rows.length;
		var j =1;
		/*set so thu tu auto tang*/
		for(var i=0; i<rowCount; i++){
			  var x = document.getElementById("dataTable").rows[j].cells;
			    x[0].innerHTML = j; 
			    
			    j++;
    
		}
		
		
	</script> 
	
	<script type="text/javascript">
		/*function delete row*/
		function delete_function(){
			
			var elem = $('#deleteRow');
			alert(elem.parent()[0].sectionRowIndex);
		}
	
	</script>




</body>
</html>