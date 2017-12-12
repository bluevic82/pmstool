<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Project</title>
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

 	<script>
		$(document)
				.ready(
				function() {
				$(".datepicker")
				.datepicker(
				{
				showOn : "button",
				buttonImage : "${pageContext.request.contextPath}/resources/image/Date-32.png",
				buttonImageOnly : true
				});
				});
	</script> 
  
</head>
<body>
	<div class="container" style="margin-top: 10px;">
		<form name="task" action="actionCreateTask" >
			<div class="row">
				<div class="col-sm-4">
					<div>
						Project Name <input value="" name="project_id" size="30">
					</div>
				</div>
			</div>
			<br>
			<div>
				Type <select name="type_id" style="margin-left: 63px">
					<c:forEach var="taskTypes" items="${taskTypes}">
						<option value="${taskTypes.type_id}">
							${taskTypes.type_name}</option>
					</c:forEach>
				</select>
			</div>
			<br>
			
			<div>
				<label> Status </label>
				 <select name="status_id" style="margin-left: 52px">
					<c:forEach var="taskStatus" items="${taskStatus}">
						<option value="${taskStatus.status_id}">
							${taskStatus.status_name}</option>
					</c:forEach>
				</select>
				<label style="margin-left: 40px ">Done</label> <input value="" name="done">(%)
			</div>
			<br>
			
			<div>
				<div>
				     From <input value="" name="task_from" class="datepicker" size="10"  style="margin-left: 60px"> 
				     To <input value="" name="task_to" class="datepicker" size="10">
				</div>	
			</div>
			<br>
			
			<div>
			  <div>
			  	   Subject<input value="" name="task_subject" size="58" style="margin-left: 49px">
			  </div>
			</div>
			<br>
			
			<div>
				<label for="Description">Description</label>
				<textarea name="task_description" style="margin-left: 15px" cols="60" rows="3"></textarea>
			</div>
			<br>
			
			<div>
				<label> PIC </label>
				 <select name="member_project_id" style="margin-left: 70px">
					<c:forEach var="pic" items="${pic}">
						<option value="${pic.member_project_id}">
							${pic.member_project_name}</option>
					</c:forEach>
				</select>	
				<label style="margin-left: 50px"> Priority </label>
				 	<select name="task_priority">
						<option>Highest</option>
						<option>High</option>
						<option>Medium</option>
						<option>Low</option>
					</select>
			</div>
			<br>
			
			<div>
				<label> Category </label>
				 <select name="category_id" style="margin-left: 30px">
					<c:forEach var="category" items="${category}">
						<option value="${category.category_id}">
							${category.category_name}</option>
					</c:forEach>
				</select>
			</div>
			<br>
			<div style="text-align: end;">
				<button name="actionCreateTask" value="actionCreateTask" style="background-color: green; color: white;">Create</button>
			</div>
		</form>
	</div>

</body>
</html>