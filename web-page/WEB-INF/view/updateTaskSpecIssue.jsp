<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Task/Spec/Issue</title>
<jsp:include page="_banner1.jsp"></jsp:include>
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
	

</head>
<body>
	<div class="container" style="margin-top: 10px;">
		<form:form id="id_form" action="/Login/actionUpdateTask" method="post">
			<form:hidden path="task_id"/>
			<div class="row">
				<div class="col-sm-4">
					<div>
						Project Name <input disabled="disabled" id="project_name" value="${project_Infor.project_name}" name="${project_Infor.project_id}" size="30" style="margin-left: 7px;">
							<input type="hidden" id="project_id" value="${project_Infor.project_id}" name="project_id" >
					</div>
				</div>
			</div>
			<br>
			<div>
				Type <form:select name="type_id" path="type_id" style="margin-left: 63px">
					<c:forEach var="taskTypes" items="${taskTypes}">
						<option value="${taskTypes.type_id}">
							${taskTypes.type_name}</option>
					</c:forEach>
				</form:select>
				
			</div>
			<br>
			
			<div>
				Status
				 <form:select name="status_id" path="status_id" style="margin-left: 52px">
					<c:forEach var="taskStatus" items="${taskStatus}">
						<option value="${taskStatus.status_id}">
							${taskStatus.status_name}</option>
					</c:forEach>
				</form:select>
				&emsp; Done<form:input maxlength="3" id="done" style="margin-left: 10px;" value="" name="task_done" path="task_done"/>(%)
			</div>
			<br>
			
			<div>
			<div class="row">
				<div class="col-sm-5">
					<div class="row">
					  	<div class="col-sm-2">From</div><div class="form-group col-sm-8" style="margin-left: 15px;">
			                <div class='input-group date' id='datetimepicker1' >
			                    <form:input id="from" type='text' class="form-control" name="task_from" path="task_from"/>
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
			                    <form:input id="to" type='text' class="form-control" name="task_to" path="task_to"/>
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
			  	   Subject<form:input maxlength="199" id="subject" value="" name="task_subject" path="task_subject" size="58" style="margin-left: 49px"/>
			  </div>
			</div>
			<br>
			
			<div>
				Description
				<form:textarea id="description" name="task_description" path="task_description" style="margin-left: 20px" cols="60" rows="3"></form:textarea>
			</div>
			<br>
			<div>
				Solution
				<form:textarea id="solution" name="task_solution" path="task_solution" style="margin-left: 36px" cols="60" rows="3"></form:textarea>
			</div>
			<br>
			
			<div>
				 PIC
				 <form:select name="member_project_id" path="member_project_id" style="margin-left: 65px">
					<c:forEach var="pic" items="${pic}">
						<option value="${pic.member_project_id}" >
							${pic.member_project_name}</option>
					</c:forEach>
				</form:select>	
				&emsp;Priority
				 	<form:select name="task_priority" path="task_priority">
						<option>Highest</option>
						<option>High</option>
						<option>Medium</option>
						<option>Low</option>
					</form:select>
			</div>
			<br>
			
			<div>
				 Category
				 <form:select name="category_id" path="category_id" style="margin-left: 30px">
					<c:forEach var="category" items="${category}">
						<option value="${category.category_id}">
							${category.category_name}</option>
					</c:forEach>
				</form:select>
			</div>
			<br>
			<div style="text-align: end;">
				<button id="btnSave" type="submit" style="background-color: green; color: white;">Save</button>
			</div>
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
			
 			if (done.length == ""){
					alert("% Done can not be empty");
				return false;
			}
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
		});
	</script>
</body>
</html>