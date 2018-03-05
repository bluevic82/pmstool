<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QA List</title>
<jsp:include page="_banner.jsp"></jsp:include>
	<jsp:include page="_menu.jsp" />
  	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
</head>
<body>
<h4 style="padding-bottom: 2%">Q&A List</h4>
	<div class="container" style="margin-top: 20px;">
	<form method="post" action="/Login/qaList/">
			<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		<div class="row">
			<div class="col-sm-9">
				<div>
					Project Name
						<select name="projectName">
						<option value="0"></option>
					    	<c:forEach var="projectName" items="${projectName}">   
					   			<option value="${projectName.project_id}"<c:if test="${projectName.project_id==pn}">selected="selected"</c:if> >${projectName.project_name}</option>
					     	</c:forEach>  
			    		</select>
					<label style="margin-left: 50px">Status</label>
						<select name="status">
						<option value="0"></option>
					    	<c:forEach var="qaStatus" items="${qaStatus}">   
					   			<option value="${qaStatus.status_id}" <c:if test="${qaStatus.status_id==st}">selected="selected"</c:if>>${qaStatus.status_type}</option>
					     	</c:forEach>  
			    		</select>
					<label style="margin-left: 50px">PIC</label>
						<select name="member_project_id">
						<option value="0"></option>
					    	<c:forEach var="pic" items="${pic}">   
					   			<option value="${pic.member_project_id}"<c:if test="${pic.member_project_id==mp}">selected="selected"</c:if> >${pic.member_project_name}</option>
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
					 <c:forEach var="list" items="${list}">
					  	<tr>   
						  	<th scope="row"><a href="/Login/qaList/${list.q_a_id}/editQA/${list.project_id}">${list.q_a_id}</a></th>
						  	<th scope="row"><a href="/Login/qaList/${list.q_a_id}/editQA/${list.project_id}">${list.q_a_title}</a></th>
						  	<th>${list.mb}</th>
						  	<th>${list.q_a_dealine}</th>
						  	<th>${list.status}</th>
					  	</tr>
				  	</c:forEach>
				  </tbody>
				</table>				
			</div>
			<div class="col-sm-2">
				<div style="text-align: end;">
					<button type="submit"  style="background-color: green; color: white; margin-left: -30px">Search</button>
				</div>
			</div>
		</div>
		</form>
	</div>
</body>
</html>