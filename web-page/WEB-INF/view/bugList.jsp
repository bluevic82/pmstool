<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-UTF-8">
<title>List Bug</title>
<jsp:include page="_banner.jsp"></jsp:include>
<jsp:include page="_menu.jsp" />
 <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  	<link rel="stylesheet" href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" />" >
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
<h6 style="margin-left: 20px">Bug Management > <a href="${pageContext.request.contextPath}/bugList"> List</a></h6>
	<div class="container" style="margin-top: 10px;">
		<div>
			<form method="post" action="/pms/bugList/">
			<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
			<label style="margin-left: 10px"> Project Name</label> 
			<select name="projectName">
				<option value="0"></option>
				<c:forEach var="projectName" items="${projectName}">
					<option value="${projectName.project_id}"<c:if test="${projectName.project_id==pn}">selected="selected"</c:if>>${projectName.project_name}</option>
				</c:forEach>
			</select>
			<label style="margin-left: 10px"> Type </label> 
			<select name="type_id" style="margin-left: 5px">
				<option value="0"></option>
				<c:forEach var="bugTypes" items="${bugTypes}">
					<option value="${bugTypes.type_id}"<c:if test="${bugTypes.type_id==ti}">selected="selected"</c:if>>
						${bugTypes.type_name}</option>
				</c:forEach>
			</select> 
			<label style="margin-left: 10px"> Status </label> 
			<select name="status_id" style="margin-left: 5px">
					<option value="0"></option>
				<c:forEach var="bugStatus" items="${bugStatus}">
					<option value="${bugStatus.status_id}" <c:if test="${bugStatus.status_id==si}">selected="selected"</c:if> >
						${bugStatus.status_name}</option>
				</c:forEach>
			</select> 
			<label style="margin-left: 10px"> PIC </label> 
			<select name="member_project_id" style="margin-left: 5px">
					<option value="0"></option>
				<c:forEach var="pic" items="${pic}">
					<option value="${pic.member_project_id}" <c:if test="${pic.member_project_id==mp}">selected="selected"</c:if>>
						${pic.member_project_name}</option>
				</c:forEach>
			</select> 
			<label style="margin-left: 10px"> Priority </label> 
			<select name="bug_priority" style="margin-left: 5px">
				<option value="" ></option>
				<option value="Highest" <c:if test="${bp=='Highest'}">selected="selected"</c:if>>Highest</option>
				<option value="High" <c:if test="${bp=='High'}">selected="selected"</c:if>>High</option>
				<option value="Medium" <c:if test="${bp=='Medium'}">selected="selected"</c:if>>Medium</option>
				<option value="Low" <c:if test="${bp=='Low'}">selected="selected"</c:if>>Low</option>
			</select>
			<button type="submit"  style="background-color: green; color: white; margin-left: 30px">Search</button>
			</form>
		</div>
		<table style="margin-top: 30px;" class="table table-bordered">
			<thead>
				<tr>
					<th scope="col" style="background-color: #3ADF00;">ID</th>
					<th scope="col" style="background-color: #3ADF00;">Subject</th>
					<th scope="col" style="background-color: #3ADF00;">PIC</th>
					<th scope="col" style="background-color: #3ADF00;">Priority</th>
					<th scope="col" style="background-color: #3ADF00;">Deadline</th>
					<th scope="col" style="background-color: #3ADF00;">Status</th>
					<th scope="col" style="background-color: #3ADF00;">Done</th>
					<th scope="col" style="background-color: #3ADF00;">Description</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${list}">
					<tr>
						<th scope="row">
						<a href="/pms/bugList/${list.bug_id}/editBug/${list.project_id}">${list.bug_id}</a></th>
						<th><a href="/pms/bugList/${list.bug_id}/editBug/${list.project_id}">${list.bug_subject}</a></th>
						<th>${list.mb}</th>
						<th>${list.bug_priority}</th>
						<th>${list.bug_to}</th>
						<th>${list.status}</th>
						<th>${list.bug_done}</th>
						<th>${list.bug_description}</th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
			<jsp:include page="_bottom.jsp"></jsp:include>
</body>
<script type="text/javascript">

</script>
</html>