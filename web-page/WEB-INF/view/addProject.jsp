<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Project</title>
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
	<div class="container" style="margin-top: 10px;">
		<form id="addproject">
			<div class="row">
				<div class="col-sm-4">
					<div>Project Name <input value="" name="" size="30"></div>
				</div>
				<div class="col-sm-2">
					<!-- <div>Type <input value="" name="" size="10"></div> -->
					<label for="type">Type</label>
           				      <select name="type" >
							       <c:forEach var="projectTypes" items="${projectTypes}">   
							   			<option value="${projectTypes.type_id}" >${projectTypes.type_name}</option>
							      </c:forEach>  
						      </select>
				</div>
				<div class="col-sm-3">
					<div>From <input value="" class="datepicker" size="15"></div>	
				</div>
				<div class="col-sm-3">
						<div>To <input value="" class="datepicker" size="15"> </div>
					</div>
				</div><br>
				<div>Technical
					<!-- <input value="" name="" style="margin-left: 30px"> -->
           				<select name="Technical" style="margin-left: 30px">
						  <option value="PHP">PHP</option>
						  <option value="Java">Java</option>
						  <option value="IOS">IOS</option>
						  <option value="Android">Android</option>
						</select>
				</div><br>
				<div>
					<label for="scope">Scope</label> 
						 <input type="checkbox" name="BD" value="BD" style="margin-left: 52px;"> BD
						 <input type="checkbox" name="DD" value="DD" style="margin-left: 10px;"> DD
						 <input type="checkbox" name="Coding" value="Coding" style="margin-left: 10px;"> Coding
						 <input type="checkbox" name="UT" value="UT" style="margin-left: 10px;"> UT
						 <input type="checkbox" name="IT" value="IT" style="margin-left: 10px;"> IT
						 <input type="checkbox" name="ST" value="ST" style="margin-left: 10px;"> ST
				</div><br>
				<div>Charge Cost <input value="" name="" style="margin-left: 10px"> (MM)</div><br>
				<div>Status 
					<select name="status" style="margin-left: 52px">
				    	<c:forEach var="projectStatus" items="${projectStatus}">   
				   			<option value="${projectStatus.status_id}" >${projectStatus.status_type}</option>
				     	</c:forEach>  
			    	</select>
				</div><br>
				<div><label for="Description">Description</label>
				 <textarea style="margin-left: 15px" cols="60" rows="3"></textarea></div><br>
				<div style="text-align: end;"><button value="save" style="background-color: green; color: white;">Create</button></div>
		</form>
	</div>

	<script>
	$(document).ready(function() {
	    $( ".datepicker" ).datepicker({
	          showOn: "button",
	          buttonImage: "${pageContext.request.contextPath}/resources/image/Date-32.png",
	          buttonImageOnly: true
	     });
	});
	</script>
</body>
</html>