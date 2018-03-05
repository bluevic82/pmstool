<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Task/Spec/Issue</title>
<jsp:include page="_banner1.jsp"></jsp:include>
<jsp:include page="_menu.jsp" />
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://momentjs.com/downloads/moment.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
	 <link rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
</head>
<body>
<h4>${project_Infor.project_name} > Create Task/Spec/Issue</h4>
	<div class="container" style="margin-top: 50px;">
	
	<form:form id="id_form" action="/Login/actionCreateTask" method="post">
		<input type="hidden"  name="${_csrf.parameterName}"  value="${_csrf.token}"/>
			<div class="row">
				<div class="col-sm-4">
					<div>
						Project Name <input disabled="disabled" value="${project_Infor.project_name}" name="project_name" size="30" style="margin-left: 7px;">
						<input type="hidden" value="${project_Infor.project_id}" name="project_id" >
					</div>	
				</div>
			</div>
			<br>
			<div >
				Type <select name="type_id" style="margin-left: 63px">
					<c:forEach var="taskTypes" items="${taskTypes}">
						<option value="${taskTypes.type_id}">
							${taskTypes.type_name}</option>
					</c:forEach>
				</select>
				
			</div>
											
			<br>
			
			<div>
				Status
				 <select name="status_id" style="margin-left: 52px">
					<c:forEach var="taskStatus" items="${taskStatus}">
						<option value="${taskStatus.status_id}">
							${taskStatus.status_name}</option>
					</c:forEach>
				</select>
				&emsp; Done<input id="done" style="margin-left: 10px;" maxlength="3" value="" name="task_done">(%)
			</div>
			<br>
			
			<div>
			<div class="row">
				<div class="col-sm-5">
					<div class="row">
					  	<div class="col-sm-2">From</div><div class="form-group col-sm-8" style="margin-left: 15px;">
			                <div class='input-group date' id='datetimepicker1' >
			                    <input id="from" type='text' class="form-control" name="task_from"/>
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
			                    <input id="to" type='text' class="form-control" name="task_to"/>
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
			  	   Subject<input id="subject" value="" name="task_subject" size="58" maxlength="199" style="margin-left: 49px">
			  </div>
			</div>
			<br>
			
			<div>
				Description
				<textarea name="task_description" maxlength="999" placeholder="optional & can not be more than 1000 characters" 
							style="margin-left: 20px" cols="60" rows="3"></textarea>
			</div>
			<br>
			
			<div>
				 PIC
				 <select name="member_project_id" style="margin-left: 65px">
					<c:forEach var="pic" items="${pic}">
						<option value="${pic.member_project_id}">
							${pic.member_project_name}</option>
					</c:forEach>
				</select>	
				&emsp;Priority
				 	<select name="task_priority">
						<option>Highest</option>
						<option>High</option>
						<option>Medium</option>
						<option>Low</option>
					</select>
			</div>
			<br>
			
			<div>
				 Category
				 <select name="category_id" style="margin-left: 30px">
					<c:forEach var="category" items="${category}">
						<option value="${category.category_id}">
							${category.category_name}</option>
					</c:forEach>
				</select>
			</div>
			<br>
			<div style="text-align: end;">
				<button id="createTask" type="submit" style="background-color: green; color: white;">Create</button>
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
		$("#createTask").click(function(){
			var done = $("#done").val();
			var from = $("#from").val();
			var to = $("#to").val();
			var subject = $("#subject").val();
			
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
		});
	</script>
    		<jsp:include page="_bottom1.jsp"></jsp:include>
</body>
</html>