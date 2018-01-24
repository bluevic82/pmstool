<%@page session="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>${title}</title>
<jsp:include page="_menu.jsp" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  	<script src="https://momentjs.com/downloads/moment.min.js"></script>
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker-standalone.css">
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker-standalone.min.css">
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css">
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
</head>
<body>
	<h1>Message : ${message}</h1>
	<div class="container">
		<div class="row">
			<div class="col-sm-3">Name <input></div>
			<div class="col-sm-2">PM <input size="10"></div>
			<div class="col-sm-3">From <input ></div>
			<div class="col-sm-3">To <input></div>
			<div class="col-sm-1"><button style="background-color: green;">Search</button></div>
		</div>
		<table style="margin-top: 50px;" class="table table-bordered">
			<thead>
				<tr>
				   <th scope="col" >#</th>
				   <th scope="col" >project name</th>
				   <th scope="col" >PM</th>
				   <th scope="col" >View</th>
				</tr>
			</thead>
				<tbody>
				<c:forEach var="list" items="${list}">
				<tr>   
					<th scope="row"><a href="detailProject/${list.project_id}">${list.project_id}</a></th>
					<th>${list.project_name}</th>
					<th></th>
					<th scope="row"><a href="detalproject/${list.project_id}">Detail</a></th>
					</tr>
				 </c:forEach>
			 </tbody>
		</table>
					
	</div>
	
</body>
</html>