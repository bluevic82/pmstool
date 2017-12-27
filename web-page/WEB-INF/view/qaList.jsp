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
				<div>
					Project Name
						<select name="projectName">
					    	<c:forEach var="projectName" items="${projectName}">   
					   			<option value="${projectName.project_id}" >${projectName.project_name}</option>
					     	</c:forEach>  
			    		</select>
					<label style="margin-left: 50px">Status</label>
						<select name="status">
					    	<c:forEach var="qaStatus" items="${qaStatus}">   
					   			<option value="${qaStatus.status_id}" >${qaStatus.status_type}</option>
					     	</c:forEach>  
			    		</select>
					<label style="margin-left: 50px">PIC</label>
						<select name="member_project_id">
					    	<c:forEach var="pic" items="${pic}">   
					   			<option value="${pic.member_project_id}" >${pic.member_project_name}</option>
					     	</c:forEach>  
			    		</select>
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
					 <c:forEach var="allQA" items="${allQA}">
					  	<tr>   
						  	<th scope="row"><a href="editQA/${allQA.q_a_id}">${allQA.q_a_id}</a></th>
						  	<th scope="row"><a href="editQA/${allQA.q_a_id}">${allQA.q_a_title}</a></th>
						  	<th>${allQA.member_project_id}</th>
						  	<th>${allQA.q_a_dealine}</th>
						  	<th>${allQA.status_id}</th>
					  	</tr>
				  	</c:forEach>
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