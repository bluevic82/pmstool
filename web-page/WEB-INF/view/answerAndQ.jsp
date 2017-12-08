<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QA List</title>
	<jsp:include page="_menu.jsp" />
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" >
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-reboot.min.css" />" >
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-grid.min.css" />" >
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-grid.css" />" >
  	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
</head>
<body>
	<div class="container" style="margin-top: 20px;">
		<div class="row">
			<div class="col-sm-9">
				<div class="row">
					<div class="col-sm-5"><div>Project Name
						<input value=""></div>
					</div>
					<div class="col-sm-4"><div>Status
						<input value="" size="15"></div>
					</div>
					<div class="col-sm-3"><div>PIC
						<input value="" size="15" ></div>
					</div>
				</div>
				
				<table style="margin-top: 50px;" class="table table-bordered">
				  <thead>
				    <tr>
				      <th scope="col" style="background-color: #3ADF00;">ID</th>
				      <th scope="col" style="background-color: #3ADF00;">Title</th>
				      <th scope="col" style="background-color: #3ADF00;">PIC</th>
				      <th scope="col" style="background-color: #3ADF00;">Deadline</th>
				      <th scope="col" style="background-color: #3ADF00;">Status</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <th scope="row">1</th>
				      <td>A</td>
				      <td>Question1</td>
				      <td>ThangNN</td>
				      <td>08/12/2017</td>
				    </tr>
				    <tr>
				      <th scope="row">2</th>
				      <td>B</td>
				      <td>Question2</td>
				      <td>ThangNN</td>
				      <td>09/12/2017</td>
				    </tr>
				    <tr>
				      <th scope="row">3</th>
				      <td>C</td>
				      <td>Question3</td>
				      <td>ManhNV</td>
				      <td>10/12/2017</td>
				    </tr>
				  </tbody>
				</table>
				
				
			</div>
			<div class="col-sm-3">
				<div style="text-align: end;">
					<button style="background-color: #3ADF00">Search</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>