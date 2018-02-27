<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Project</title>
	<jsp:include page="_menu.jsp" />
  	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  	<link rel="stylesheet" href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" />" >
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
  	
  	<script src="https://momentjs.com/downloads/moment.min.js"></script>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker-standalone.min.css"/>
	 
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
</head>
<body>
	<div class="container" style="margin-top: 10px;">
		<form:form>
			<form:hidden path="project_id"/>
			<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
			<div class="row">
		
				<div class="col-sm-4">
					<div>Project Name <form:input value="" name="project_name" path="project_name" size="30"/></div>
				</div>
				<div class="col-sm-2">
					<!-- <div>Type <input value="" name="" size="10"></div> -->
							Type
           				      <form:select name="type_id" path="type_id">
							       <c:forEach var="projectTypes" items="${projectTypes}">   
							   			<option value="${projectTypes.type_id}" >${projectTypes.type_name}</option>
							      </c:forEach>  
						      </form:select>
				</div>
				<!-- <div class="col-sm-3">
					<div>From <input name="project_from" class="datepicker"   size="15"></div>	
				</div>
				<div class="col-sm-3">
						<div>To <input name="project_to" class="datepicker" size="15"> </div>
					</div> -->
					
				<div class="col-sm-3">
			         From<form:input class="date" name="project_from" path="project_from" id="datetimepicker1"/><img  alt="" src="../resources/image/Date-32.png">
				</div>
				<div class="col-sm-3">
					To<form:input class="date" id="datetimepicker2" name="project_to" path="project_to"/><img  alt="" src="../resources/image/Date-32.png">
				</div>
					
				</div><br>
				<div>Technical
					<!-- <input value="" name="" style="margin-left: 30px"> -->
           				<form:select name="project_technical" path="project_technical" style="margin-left: 30px">
						  <option value="PHP">PHP</option>
						  <option value="Java">Java</option>
						  <option value="IOS">IOS</option>
						  <option value="Android">Android</option>
						</form:select>
				</div><br>
				<div class="row">
					<div class="col-sm-1">Scope</div> 
					<div class="col-sm-11" >
					<%-- <c:forEach var="scope" items="${projectScope}"> --%>
					
						<c:forEach var="projectScope" items="${projectScope}"> 
						
							<%-- <input type="checkbox" name="over_view"  <c:if test="${scope.scope_id} == ${projectScope.scope_id}">checked="checked" </c:if>  />${projectScope.scope_name} --%>
							<input id="scope_all" type="checkbox" name="scope_id" value="${projectScope.scope_id}"  style="margin-left: 10px;">${projectScope.scope_name}
						</c:forEach>
					<%-- 	</c:forEach> --%>
					</div>
				</div><br>
				<div>Charge Cost <form:input  name="project_charge_cost" path="project_charge_cost" style="margin-left: 10px"/> (MM)</div><br>
				<div>Status 
					<form:select name="status_id" path="status_id" style="margin-left: 45px">
				    	<c:forEach var="projectStatus" items="${projectStatus}">   
				   			<option value="${projectStatus.status_id}" >${projectStatus.status_type}</option>
				     	</c:forEach>  
			    	</form:select>
				</div><br>
				<div>Description
				 <form:textarea name="project_description" path="project_description" style="margin-left: 15px" cols="60" rows="3"></form:textarea></div><br>
				<div style="text-align: end;">
					<button value="actionCreateProject" type="submit" name="actionCreateProject" style="background-color: green; color: white;">Save</button>
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
	$(document).ready(function() {
		
		//get value scope had add
		var array_Scope = new Array(); 
		var array_s_p = new Array();
		<c:forEach var="scope" items="${scope}">   
			array_Scope.push(${scope.scope_id});
			array_s_p.push('${scope.scope_project_id}');
			
		</c:forEach>
		console.log(array_Scope);
		
		//get all value scope
		var arr_defaul_scope = 	new Array();
		<c:forEach var="projectScope" items="${projectScope}">   
			arr_defaul_scope.push(${projectScope.scope_id});
			
		</c:forEach>
		console.log(arr_defaul_scope);
		console.log($("#scope_all").val());
	})
</script>
<script type="text/javascript">
		
</script>
</body>
</html>