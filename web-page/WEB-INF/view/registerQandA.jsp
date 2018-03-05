<!DOCTYPE html>
<%@page import="org.springframework.web.servlet.view.UrlBasedViewResolver"%>
<%@page import="java.util.Base64"%>
<%@page import="com.fasterxml.jackson.databind.deser.Deserializers.Base"%>
<%@page import="org.springframework.util.Base64Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Q&amp;A Register</title>
<jsp:include page="_banner1.jsp"></jsp:include>
<jsp:include page="_menu.jsp" />
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" >
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  	<link rel="stylesheet" href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" />" >
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
  	
  	<script src="https://momentjs.com/downloads/moment.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker-standalone.css">
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker-standalone.min.css">
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker-standalone.min.css.map">
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css">
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
</head>
<body>
<h4>${project_Infor.project_name} > Q&A Register/Update</h4>
	<div class="container">	
		<form:form name="qa" id="id_form" action="/Login/actionRegisterQA?${_csrf.parameterName}=${_csrf.token}"  method="post" enctype="multipart/form-data"> 
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<div >
				Project Name <input disabled="disabled" value="${project_Infor.project_name}" name="project_id" size="30" style="margin-left: 22px;"/>
				<input type="hidden" value="${project_Infor.project_id}" name="project_id" >
			</div>
		<br>
			<div>
				Title <input id="title" maxlength="199" value="" name="q_a_title" style="margin-left: 82px;" size="50"/>
			</div>
		<br>
		<div>
			Reference point <input maxlength="249" style="margin-left: 10px;" size="50"/> 
 			<input type="file" name="i" id="referencepoint" style="margin-top: -25px; margin-left: 580px">
		</div>
			<br>
		<div class="row">
		<div class="col-sm-6">Question<br>
			<div style="margin-left: 90px">
				<label>VN</label>
				<textarea id="questionVi" maxlength="1999" name="q_a_question_vi" rows="4" cols="52"></textarea><br><br>
				<label>JP</label>
				<textarea maxlength="1999" name="q_a_question_jp" rows="4" cols="52"></textarea><br>
			</div>
		</div>
			
		<div class="col-sm-6" style="margin-top: 1px">Answer<br>
			<textarea maxlength="1999" name="q_a_answer_vi" rows="4" cols="50"></textarea><br><br>
			<textarea maxlength="1999" name="q_a_answer_jp" rows="4" cols="50" style="margin-top: 5px"></textarea><br>
		</div>
			
		</div><br>
			To
			<select name="member_project_id"
				style="margin-left: 90px">
				<c:forEach var="pic" items="${pic}">
					<option value="${pic.member_project_id}">
						${pic.member_project_name}</option>
				</c:forEach>
			</select>
			<br>
			<br>
			<div class="row">
				<div class="col-sm-1">Deadline</div>
					<div class="form-group col-sm-4" style="margin-left: 10px">
						<div class='input-group date' id='datetimepicker1'>
							<input id="dealine" name="q_a_dealine" size="20" type='text' class="form-control"/>
							<div class="input-group-addon">
								<div class="glyphicon glyphicon-calendar"></div>
							</div>
						</div>
					</div>
			<div class="col-sm-7"></div>
			</div>
			<div>
				Status <select name="status_id" style="margin-left: 65px">
					<c:forEach var="qaStatus" items="${qaStatus}">
						<option value="${qaStatus.status_id}">${qaStatus.status_type}</option>
					</c:forEach>
				</select>
				<button id="registerQA" style="background-color: green; color: white;
				 												margin-left: 1000px;">Register</button>
			</div>
			<br>
 			</form:form>
	</div>


	<script type="text/javascript">
       $(function () {
          $('#datetimepicker1').datetimepicker({
             format:"YYYY-MM-DD",
            });
         });
     </script> 
     
 	<script type="text/javascript">
		$("#registerQA").click(function(){
			var title = $("#title").val();
			var questionVi = $("#questionVi").val();
			var dealine = $("#dealine").val();
			
 			if (title.length == ""){
					alert("Title can not be empty");
				return false;
			}

 			if (questionVi.length == ""){
					alert("You have to Ask Questions VI");
				return false;
			}
			if (dealine.length == ""){
					alert("Dealine can not be empty");
				return false;
			} 
		});
		
	</script> 	
		<jsp:include page="_bottom1.jsp"></jsp:include>
</body>
</html>