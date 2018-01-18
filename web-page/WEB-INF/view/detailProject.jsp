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



	<div class="container">

		
		<div class="row" style="margin-top: 30px;">
			<div>Project information</div>
		<!-- <div class="row">
					<div class="col-sm-6" >
							<div class="row">
								<div class="col-sm-6">Name<input><br>
							From<input><br>
							PM<input><br></div>
								<div class="col-sm-6">Status<input>
							To <input>
							Technical <input></div>
							</div>
					</div>
					<div class="col-sm-6">
						<div>
							<label for="description">Description</label>
					 		<textarea name="description"  cols="40" rows="3"></textarea>
				 		</div>
					</div>
				</div>
			<div style="margin-top: 50px;">Spec / Task list</div> -->
		<div class="div_head">
			<div  class="col-sm-6" style="float: left;" >
				<table class="table_head">
					<tr >
						<td class="col-sm-2">Name</td>
						<td class="col-sm-2"><input type="text" disabled
							value="Namellllllllllllllllllllllllllllllllhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhlllllllllllllllllllllllllllllllllllllllllll" /></td>
						<td class="col-sm-2">Status</td>
						<td class="col-sm-2"><input type="text" disabled value="Status" /></td>
					</tr>
					<tr>
						<td class="col-sm-2">From</td>
						<td class="col-sm-2"><input type="text" disabled value="From" /></td>
						<td class="col-sm-2">To</td>
						<td class="col-sm-2"><input type="text" disabled value="To" /></td>
					</tr>
					<tr>
						<td class="col-sm-2">PM</td>
						<td class="col-sm-2"><input type="text" disabled value="PM" /></td>
						<td class="col-sm-2">Technical</td>
						<td class="col-sm-2"><input type="text" disabled value="Technical" /></td>
					</tr>
				</table>

			</div>
			<div class="col-sm-6" >
				<div>
					<label for="description" style="">Description</label>
					<textarea name="description" cols="60" rows="3"></textarea>
				</div>
			</div>
		</div>
		<div class = "div_table">
			<table class="table">
				<thead class="thead-light">
					<tr>
						<th scope="col">#</th>
						<th scope="col">Spec / Task</th>
						<th scope="col">start</th>
						<th scope="col">end</th>
						<th scope="col">flow chart</th>
						<th scope="col">Status</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row">1</th>
						<td>Function 1</td>
						<td>10/01</td>
						<td>10/20</td>
						<td>-------------------------------------</td>
						<td>close</td>
					</tr>
					<tr>
						<th scope="row">2</th>
						<td>Function 2</td>
						<td>10/01</td>
						<td>10/20</td>
						<td>-------------------------------------</td>
						<td>open</td>
					</tr>
					<tr>
						<th scope="row">3</th>
						<td>Function 3</td>
						<td>10/01</td>
						<td>10/20</td>
						<td>-------------------------------------</td>
						<td>on-going</td>
					</tr>
					<tr>
						<th scope="row">4</th>
						<td>Function 4</td>
						<td>10/01</td>
						<td>10/20</td>
						<td>-------------------------------------</td>
						<td>close</td>
					</tr>
					<tr>
						<th scope="row">5</th>
						<td>Function 5</td>
						<td>10/01</td>
						<td>10/20</td>
						<td>-------------------------------------</td>
						<td>open</td>
					</tr>
				</tbody>
			</table>
		</div>
		</div>

	</div>

	<!-- </form> -->





</body>
</html>