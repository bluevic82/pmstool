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

		
		<div  class="row" style="margin-top: 30px;">
			<div>Project information</div>
			
			
			 <div class="col-md-3">
			 	<table>
			 		<tr>
			 			<td WIDTH="100px">
			 			   Name 
			 			</td>
			 			<td>
			 				<input type="text" disabled value="${command.project_name}" />
			 			</td>
			 			</tr>
			 			<tr>
			 			<td>
			 			   From  
			 			</td>
			 			<td>
			 				<input type="text" disabled value="${command.project_from}" />
			 			</td>
			 			</tr>
			 			<tr>
			 			<td>
			 			   PM 
			 			</td>
			 			<td>
			 				<select style="width:100%">
						<c:forEach var="pm" items="${pm}">
						  <option value="${pm.member_project_name}">${pm.member_project_name}</option>
						</c:forEach>
						</select>
			 			</td>
			 		</tr>
			 	</table>
			 
			 </div>
			 <div class="col-md-3">
			 	<table>
			 		<tr>
			 			<td WIDTH="100px" >
			 			   Status 
			 			</td>
			 			<td>
			 				<input type="text" disabled value="${command.status}" />
			 			</td>
			 			</tr>
			 			<tr>
			 			<td>
			 			   To 
			 			</td>
			 			<td>
			 				<input type="text" disabled value="${command.project_to}" />
			 			</td>
			 			</tr>
			 			<tr>
			 			<td>
			 			   Technical 
			 			</td>
			 			<td>
			 				<input type="text" disabled value="${command.project_technical}" />
			 			</td>
			 		</tr>
			 	</table>
			 </div>
  		     <div class="col-md-6">
  		     	<table>
  		     			
  		     		<tr >
  		     			<td WIDTH="100px">
			 			   Description 
			 			</td>
			 			<td rowspan="3">
			 			<textarea style="height: 79px" name="description" cols="60" rows="3" disabled>${command.project_description}</textarea>
			 			</td>
  		     		</tr>
  		     	</table>
  		     </div>
  		     
		
		
			<%-- <div  class="col-sm-6" style="float: left;" >
				<table class="table_head">
					<tr >
						<td>Name</td>
						<td><input type="text" disabled
							value="${command.project_name}" /></td>
						<td>Status</td>
						<td><input type="text" disabled value="${command.status}" /></td>
					</tr>
					<tr>
						<td>From</td>
						<td><input type="text" disabled value="${command.project_from}" /></td>
						<td>To</td>
						<td><input type="text" disabled value="${command.project_to}" /></td>
					</tr>
					<tr>
						<td>PM</td>
						<td >
						
						<select>
						<c:forEach var="pm" items="${pm}">
						
						  <option value="${pm.member_project_name}">${pm.member_project_name}</option>
						 
						
						</c:forEach>
						</select>
						</td>
						<td >Technical</td>
						<td ><input type="text" disabled value="${command.project_technical}" /></td>
					</tr>
				</table>

			</div>
			<div class="col-sm-6" >
				<div>
					<div>Description</div>
					<textarea name="description" cols="60" rows="3" disabled>${command.project_description}</textarea>
				</div>
			</div>
		</div> --%>
		<div style="padding-top: 3cm;" class = "div_table">
		Spec / Task list
			<table class="table">
				<thead class="thead-light">
					<tr style="background-color: #f2e3d5;">
						<th scope="col">#</th>
						<th scope="col">Spec / Task</th>
						<th scope="col">start</th>
						<th scope="col">end</th>
						<th scope="col">flow chart</th>
						<th scope="col">Status</th>
					</tr>
				</thead>
				<tbody>
				
				
				<c:forEach var="taskid" items="${taskid}" varStatus="status">
						<tr>
						<th scope="row">${taskid.task_id}</th>
						<td>${taskid.task_subject}</td>
						<td>${taskid.task_from}</td>
						<td>${taskid.task_to}</td>
						<td>
						<div style="border: 1px solid black;width:402px;height:22px;">
						<c:forEach items="${per[status.index]}" var="entry">
						  	<c:if test = "${entry.value == 'green'}">
						         <div style="width:${taskid.task_done*4}px;height:20px;background-color: #82FA58">${taskid.task_done-entry.key}%
						 		 <div style="width:${entry.key*4}px;height:20px;background-color: ${entry.value};;float: right"><c:if test = "${entry.key != 0}">${entry.key}%</c:if></div>
						 		</div>
						     </c:if> 
						     <c:if test = "${entry.value == 'red'}">
						         <div style="width:${taskid.task_done*4}px;height:20px;background-color: #F7FE2E;float: left;">${taskid.task_done}%</div>
						 		 <div style="width:${entry.key*4}px;height:20px;background-color: ${entry.value};display:inline-block"><c:if test = "${entry.key != 0}">${entry.key}%</c:if></div>
						     </c:if> 
						 
						 	 <c:if test = "${entry.value == 'khong'}">
						         <div style="width:${taskid.task_done*4}px;height:20px;background-color: #82FA58;float: left;">${taskid.task_done}%</div>
						 		
						     </c:if> 
   						</c:forEach>	
						</div>
						</td>
						<td>
							${taskid.status}
							
						</td>
					</tr>
				</c:forEach>	
				</tbody>
			</table>
	
			
		</div>
		</div>

	</div>

	<!-- </form> -->





</body>
</html>