<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detail project</title>
<jsp:include page="_menu.jsp" />
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" >
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-reboot.min.css" />" >
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-grid.min.css" />" >
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-grid.css" />" >
</head>
<body>
	<div class="container">
		<div>Project information</div>
				<div class="row">
					<div class="col-sm-6" >
							<!-- <div>Name<input><br>
							From<input><br>
							PM<input><br>
							Status<input>
							To <input>
							Technical <input></div> -->
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
			<div style="margin-top: 50px;">Spec / Task list</div>
			<table class="table">
				  <thead class="thead-light">
				    <tr>
				      <th scope="col" >#</th>
				      <th scope="col" >Spec / Task</th>
				      <th scope="col" >start</th>
				      <th scope="col" >end</th>
				      <th scope="col" >flow chart</th>
				      <th scope="col" >Status</th>
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
</body>
</html>