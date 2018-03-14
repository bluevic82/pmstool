<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Bug</title>
<jsp:include page="_banner2.jsp"></jsp:include>
<jsp:include page="_menu.jsp" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://momentjs.com/downloads/moment.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker-standalone.css">
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker-standalone.min.css">
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css">
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta name="_csrf_parameterName" content="${_csrf.parameterName}" />
</head>
<body>
<h6 style="margin-left: 20px">${project_Infor.project_name} > Update Bug</h6>
	<div class="container" style="margin-top: 20px;">
			<div class="row">
				<div class="col-sm-4">
					<div>
						Project Name <input disabled="disabled" id="project_name" value="${project_Infor.project_name}" name="${project_Infor.project_id}" size="30" style="margin-left: 7px;">
							<input type="hidden" id="project_id" value="${project_Infor.project_id}" name="project_id" >
							<input type="hidden" id="bug_id" value="${bugInfo.bug_id}" name="bug_id" >
					</div>
				</div>
			</div>
			<br>
			<div>
				Type <select name="type_id" id="type_id" style="margin-left: 63px">
					<c:forEach var="bugTypes" items="${bugTypes}">
							<option value="${bugTypes.type_id}"
							<c:if test="${bugTypes.type_id==bugInfo.type_id}">selected="selected"</c:if>
							>${bugTypes.type_name}</option>
					</c:forEach>
				</select>
				
			</div>
			<br>
			
			<div>
				Status
				 <select name="status_id" id="status_id" style="margin-left: 52px">
					<c:forEach var="bugStatus" items="${bugStatus}">
						<option value="${bugStatus.status_id}"
							<c:if test="${bugStatus.status_id==bugInfo.status_id}"> selected="selected"</c:if>
							>${bugStatus.status_name}</option>
					</c:forEach>
				</select>
				&emsp; Done<input value="${bugInfo.bug_done}" maxlength="3" id="done" style="margin-left: 10px;" name="bug_done"/>(%)
			</div>
			<br>
			
			<div>
			<div class="row">
				<div class="col-sm-5">
					<div class="row">
					  	<div class="col-sm-2">From</div><div class="form-group col-sm-8" style="margin-left: 15px;">
			                <div class='input-group date' id='datetimepicker1' >
			                    <input id="from" type='text' class="form-control" name="bug_from" value="${bugInfo.bug_from}"/>
			                    <div class="input-group-addon">
			                    	<div class="glyphicon glyphicon-calendar"></div>
			                    </div>
			                </div>
			            </div>
			         </div>
				</div>
				<div class="col-sm-5">
					<div class="row">
						 <div class="col-sm-1">To</div><div class="form-group col-sm-8">
			                <div class='input-group date' id='datetimepicker2' >
			                    <input id="to" type='text' class="form-control" name="bug_to" value="${bugInfo.bug_to}"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </div>
					</div>
				</div>
				<div class="col-sm-2"></div>
			</div>
			</div>
			<br>
			
			<div>
			  <div>
					Subject<input id="subject" value="${bugInfo.bug_subject}" name="bug_subject" size="58" style="margin-left: 49px">
			  </div>
			</div>
			<br>
			
			<div>
				Description
				<textarea id="description" name="bug_description" style="margin-left: 22px" cols="60" rows="3">${bugInfo.bug_description}</textarea>
			</div>
			<br>
			<div>
				Solution
				<textarea id="solution" name="bug_solution" style="margin-left: 42px" cols="60" rows="3">${bugInfo.bug_solution}</textarea>
			</div>
			<br>
			
			<div>
				 PIC
				 <select name="member_project_id" id="member_project_id" style="margin-left: 70px">
					<c:forEach var="pic" items="${pic}">
						<option value="${pic.member_project_id}"
							<c:if test="${pic.member_project_id==bugInfo.member_project_id}"> selected="selected"</c:if>
							>${pic.member_project_name}</option>
					</c:forEach>
				</select>	
				&emsp;Priority
				 	<select name="bug_priority" id="bug_priority">
						<option>Highest</option>
						<option>High</option>
						<option>Medium</option>
						<option>Low</option>
					</select>
			</div>
			<br>
			
			<div>
				 Category
				 <select name="category_id" id="category_id" style="margin-left: 36px">
					<c:forEach var="category" items="${category}">
						<option value="${category.category_id}">
							${category.category_name}</option>
					</c:forEach>
				</select>
			</div>
			<br>
			<div style="text-align: end;">
				<button id="btnSave" type="submit" style="background-color: green; color: white;">Save</button>
			</div>
	</div>
	
    <script type="text/javascript">
       $(function () {
          $('#datetimepicker1').datetimepicker({
             format:"YYYY-MM-DD",
            });
         });
     </script> 
      <script type="text/javascript">
       $(function () {
          $('#datetimepicker2').datetimepicker({
             format:"YYYY-MM-DD",
            });
         });
     </script> 
     
     <script type="text/javascript">
		$("#btnSave").click(function(){
			var done = $("#done").val();
			var from = $("#from").val();
			var to = $("#to").val();
			var subject = $("#subject").val();
			var description = $("#description").val();
			var solution = $("#solution").val();
			
			if (!$.isNumeric(done)){
					alert("You must enter the number for % Done");
				return false;
			}
			if(done > 100){
					alert("% Done can not enter the number greater than 100%");
				return false;
			}
			if(done < 0){
					alert("% Done can not enter the number less than < 0%");
				return false;
			}
 			if (from.length == ""){
					alert("From can not be empty");
				return false;
			}
 			if (to.length == ""){
					alert("To can not be empty");
				return false;
			}
 			if(from > to){
					alert("From can not be greater than To");
				return false;
 			}
			if (to.length == ""){
					alert("To can not be empty");
				return false;
			} 
			
			if (subject.length == ""){
					alert("Subject can not be empty");
				return false;
			}
			
			if (description.length > 1000){
					alert("Description can not be more than 1000 characters");
				return false;
			}
			
			if (solution.length == 1000){
					alert("Solution can not be more than 1000 characters");
				return false;
			}
			else ( ajaxUpdateBug());
		});
		function ajaxUpdateBug(){
			var id = $("#bug_id").val();
			var type = $("#type_id").val();
			var status = $("#status_id").val();
			var done = $("#done").val();
			var from = $("#from").val();
			var to = $("#to").val();
			var subject = $("#subject").val();
			var description = $("#description").val();
			var solution = $("#solution").val();
			var member_project_id = $("#member_project_id").val();
			var priority = $("#bug_priority").val();
			var category = $("#category_id").val();
			var project_id = $("#project_id").val();
			
			var obj = {
				bug_id : id,
				type_id : type,
				status_id : status,
				bug_done : done,
				bug_from : from,
				bug_to : to,
				bug_subject : subject,
				bug_description : description,
				bug_solution : solution,
				member_project_id : member_project_id,
				bug_priority : priority,
				category_id : category,
				project_id : project_id
			};
			console.log(obj);
			var token = $("meta[name='_csrf']").attr("content");

			var header = $("meta[name='_csrf_header']").attr("content"); 
			
		    	// DO POST
		    	$.ajax({
					type : "POST",
					url : "actionUpdateBug",
					data : JSON.stringify(obj),
					dataType : 'json',
					contentType : 'application/json;charset=UTF-8',
					beforeSend: function(xhr) {
			            // here it is
			            xhr.setRequestHeader(header, token);
			        },
					success : function(e) {
						alert("updated!");
						location.href="${pageContext.request.contextPath}/bugList";
					},
					error : function(e) {
						alert("update error!");
						
					}
				});	
		}
	</script>
<jsp:include page="_bottom2.jsp"></jsp:include>
</body>
</html>