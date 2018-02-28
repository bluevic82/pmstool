<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<title> Q&amp;A Update</title>
<jsp:include page="_menu.jsp" />
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
	<div class="container">
	<form:form method="post" name="qa" action="/Login/actionUpdateQA">
	<form:hidden path="q_a_id" />
		<div >
			Project Name <input disabled="disabled" value="${project_infor.project_name}" name="project_id" size="30" style="margin-left: 22px;"/>
			<input type="hidden" value="${project_infor.project_id}" name="project_id" >
		</div>
		<br>
		<div>
			Title <form:input maxlength="199" id="title" path="q_a_title" name="q_a_title" style="margin-left: 82px;" size="50"/>
		</div>
		<br>
		<div>
			Reference point <form:input id="point" path="referencepoint" maxlength="249" name="referencepoint" style="margin-left: 10px;" size="50"/> 
			<button type="button" onchange="btnBrowser()" id="myFile" style="margin-left: 85px">Browse</button>
		</div>
		<br>
		<div class="row">
		<div class="col-sm-6">Question<br>
			<div style="margin-left: 90px">
				<label>VN</label>
				<form:textarea id="questionVi" path="q_a_question_vi" name="q_a_question_vi" rows="4" cols="52"/><br><br>
				<label>JP</label>
				<form:textarea id="questionJp" path="q_a_question_jp" name="q_a_question_jp" rows="4" cols="52"/><br>
			</div>
		</div>
			
		<div class="col-sm-6" style="margin-top: 1px">Answer<br>
			<form:textarea id="answerVi" path="q_a_answer_vi" name="q_a_answer_vi" rows="4" cols="50"></form:textarea><br><br>
			<form:textarea id="answerJp" path="q_a_answer_jp" name="q_a_answer_jp" rows="4" cols="50"></form:textarea><br>
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
							<form:input id="dealine" path="q_a_dealine" size="20" type='text' class="form-control" name="q_a_dealine" />
							<div class="input-group-addon">
								<div class="glyphicon glyphicon-calendar"></div>
							</div>
						</div>
					</div>
			<div class="col-sm-7"></div>
			</div>
			<div>
				Status <select name="status" style="margin-left: 65px">
					<c:forEach var="qaStatus" items="${qaStatus}">
						<option value="${qaStatus.status_id}">${qaStatus.status_type}</option>
					</c:forEach>
				</select>
				<form:button id="updateQA" name="actionUpdateQA" value="actionUpdateQA" style="background-color: green; color: white; margin-left: 1000px;">Save</form:button>
			</div><br>
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
		$("#updateQA").click(function(){
			var title = $("#title").val();
			var point = $("#point").val();
			var questionVi = $("#questionVi").val();
			var questionJp = $("#questionJp").val();
			var dealine = $("#dealine").val();
			var answerVi = $("#answerVi").val();
			var answerJp = $("#answerJp").val();
			
 			if (title.length == ""){
					alert("Title can not be empty");
				return false;
			}
			if(point.length == ""){
					alert("Reference point can not be empty");
				return false;
			}
 			if (questionVi.length == ""){
					alert("You have to Ask Questions VI");
				return false;
			}
 			if (questionVi.length > 2000){
				alert("Questions VI can not be more than 2000 characters");
			return false;
			}
 			if (questionJp.length > 2000){
					alert("Questions 日本語 can not be more than 2000 characters");
				return false;
			}
 			if (answerVi.length > 2000){
					alert("Answer VI can not be more than 2000 characters");
				return false;
			}
 			if (answerJp.length > 2000){
					alert("Answer 日本語 can not be more than 2000 characters");
				return false;
			}
			if (dealine.length == ""){
					alert("Dealine can not be empty");
				return false;
			} 
		});
	</script>
	
	<script >
		function btnBrowser() {
			var x = document.getElementById("myFile");
			var txt = "";
		}
		 
	</script>
</body>
</html>