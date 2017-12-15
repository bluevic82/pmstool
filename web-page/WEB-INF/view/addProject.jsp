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
  
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  	<link rel="stylesheet" href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" />" >
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
  	
  	<script src="https://momentjs.com/downloads/moment.min.js"></script>
	<script src="/resources/js/bootstrap.js"></script>
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
	<div class="container" style="margin-top: 10px;">
		<form name="project" action="actionCreateProject">
			<div class="row">
				<div class="col-sm-4">
					<div>Project Name <input value="" name="project_name" size="30"></div>
				</div>
				<div class="col-sm-2">
					<!-- <div>Type <input value="" name="" size="10"></div> -->
							Type
           				      <select name="type_id" >
							       <c:forEach var="projectTypes" items="${projectTypes}">   
							   			<option value="${projectTypes.type_id}" >${projectTypes.type_name}</option>
							      </c:forEach>  
						      </select>
				</div>
				<!-- <div class="col-sm-3">
					<div>From <input name="project_from" class="datepicker"   size="15"></div>	
				</div>
				<div class="col-sm-3">
						<div>To <input name="project_to" class="datepicker" size="15"> </div>
					</div> -->
					
				<div class="col-sm-3">
					<div class="row">
					  	<div class="col-sm-2">From</div><div class="form-group col-sm-8" style="margin-left: 15px;">
			                <div class='input-group date' id='datetimepicker1' >
			                    <input type='text' class="form-control" name="project_from"/>
			                    <div class="input-group-addon">
			                    	<div class="glyphicon glyphicon-calendar"></div>
			                    </div>
			                </div>
			            </div>
			         </div>
				</div>
				<div class="col-sm-3">
					<div class="row">
						 <div class="col-sm-1">To</div><div class="form-group col-sm-8">
			                <div class='input-group date' id='datetimepicker2' >
			                    <input type='text' class="form-control" name="project_to"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </div>
					</div>
				</div>
					
				</div><br>
				<div>Technical
					<!-- <input value="" name="" style="margin-left: 30px"> -->
           				<select name="project_technical" style="margin-left: 30px">
						  <option value="PHP">PHP</option>
						  <option value="Java">Java</option>
						  <option value="IOS">IOS</option>
						  <option value="Android">Android</option>
						</select>
				</div><br>
				<div class="row">
					<div class="col-sm-1">Scope</div> 
					<div class="col-sm-11" >
						<c:forEach var="projectScope" items="${projectScope}">   
							<input type="checkbox" name="" value="${projectScope.scope_id}" style="margin-left: 10px;">${projectScope.scope_name}
						</c:forEach>  
					</div>
				</div><br>
				<div>Charge Cost <input  name="project_charge_cost" style="margin-left: 10px"> (MM)</div><br>
				<div>Status 
					<select name="status_id" style="margin-left: 45px">
				    	<c:forEach var="projectStatus" items="${projectStatus}">   
				   			<option value="${projectStatus.status_id}" >${projectStatus.status_type}</option>
				     	</c:forEach>  
			    	</select>
				</div><br>
				<div>Description
				 <textarea name="project_description" style="margin-left: 15px" cols="60" rows="3"></textarea></div><br>
				<div style="text-align: end;">
					<button value="actionCreateProject" name="actionCreateProject" style="background-color: green; color: white;">Create</button>
				</div>
		</form>
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

</body>
</html>