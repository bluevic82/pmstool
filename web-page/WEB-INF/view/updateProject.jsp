<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Project</title>
<jsp:include page="_banner1.jsp"></jsp:include>
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
<h6 style="margin-left: 20px">${projectInfo.project_name} > Setting > Update Project Information</h6>
	<div class="container" style="margin-top: 20px;">
		<form:form id="formProject" action="/Login/actionUpdateP" method="post" modelAttribute="projectInfo">
			<form:hidden path="project_id"/>
			<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
			<div class="row">
		
				<div class="col-sm-4">
					<div>Project Name <form:input id="project_name" value="" name="project_name" path="project_name" size="30"/></div>
					<input type="hidden"
						value="${projectInfo.project_id}" name="project_id" id="p_id">
				</div>
				<div class="col-sm-3">
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
			         From<%-- <form:input class="date" name="project_from" path="project_from" id="datetimepicker1"/><img  alt="" src="../resources/image/Date-32.png"> --%>
			         <div class="form-group">
			                <div class='input-group date' id='datetimepicker1' >
			                    <form:input id="project_from" type='text' class="form-control" name="project_from" path="project_from"/>
			                    <div class="input-group-addon">
			                    	<div class="glyphicon glyphicon-calendar"></div>
			                    </div>
			                </div>
			            </div>
				</div>
				<div class="col-sm-3">
					To<%-- <form:input class="date" id="datetimepicker2" name="project_to" path="project_to"/><img  alt="" src="../resources/image/Date-32.png"> --%>
					<div class="form-group">
			                <div class='input-group date' id='datetimepicker2' >
			                    <form:input id="project_to" type='text' class="form-control" name="project_to" path="project_to"/>
			                    <div class="input-group-addon">
			                    	<div class="glyphicon glyphicon-calendar"></div>
			                    </div>
			                </div>
			            </div>
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
					
						<%-- <c:forEach var="projectScope" items="${projectScope}"> 
							<input id="scope_all" type="checkbox" name="scope_id" value="${projectScope.scope_id}"  style="margin-left: 10px;">${projectScope.scope_name}
						</c:forEach> --%>
						<input id="scope_all1" type="checkbox" name="scope_id" value="1"  style="margin-left: 10px;">BD
						<input id="scope_all2" type="checkbox" name="scope_id" value="2"  style="margin-left: 10px;">DD
						<input id="scope_all3" type="checkbox" name="scope_id" value="3"  style="margin-left: 10px;">coding
						<input id="scope_all4" type="checkbox" name="scope_id" value="4"  style="margin-left: 10px;">UT
						<input id="scope_all5" type="checkbox" name="scope_id" value="5"  style="margin-left: 10px;">IT
						<input id="scope_all6" type="checkbox" name="scope_id" value="6"  style="margin-left: 10px;">ST
					</div>
				</div><br>
				<div>Charge Cost <form:input id="project_charge_cost"  name="project_charge_cost" path="project_charge_cost" style="margin-left: 10px"/> (MM)</div><br>
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
					<button id="updateProject" value="actionUpdateP" type="submit" name="actionUpdateP" style="background-color: green; color: white;">Save</button>
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
$("#updateProject").click(function() {
 
	if($("#project_name").val() == ""){
		alert("project not null");
		return false;
	}
	if($("#project_from").val() == ""){
		alert("From not null");
		return false;
	}
	if($("#project_to").val() == ""){
		alert("To not null");
		return false;
	}
	if($("#project_from").val() > $("#project_to").val()){
		alert("From do not bigger than To");
		return false;
	}
	if(!$.isNumeric($("#project_charge_cost").val())){
		alert("charge must number");
		return false;
	}
	if($("#project_charge_cost").val() == ""){
		alert("charge not null");
		return false;
	}
	if($("#project_charge_cost").val() == "0"){
		alert("charge not 0");
		return false;
	}
});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		
		//get value scope had add
		var array_Scope = new Array(); 
		<c:forEach var="scope" items="${scope}">   
			array_Scope.push(${scope.scope_id});
			if($("#scope_all1").val() == ${scope.scope_id}){
				
				$("#scope_all1").prop( "checked", true );
			}
			if($("#scope_all2").val() == ${scope.scope_id}){
				$("#scope_all2").prop( "checked", true );
			} 
			if($("#scope_all3").val() == ${scope.scope_id}){
				$("#scope_all3").prop( "checked", true );
			} 
			if($("#scope_all4").val() == ${scope.scope_id}){
				$("#scope_all4").prop( "checked", true );
			} 
			if($("#scope_all5").val() == ${scope.scope_id}){				
				$("#scope_all5").prop( "checked", true );
			}
			if($("#scope_all6").val() == ${scope.scope_id}){
				$("#scope_all6").prop( "checked", true );
			} 	
		</c:forEach>
		console.log(array_Scope);
		var arr_s = new Array();
		var s1 = $("#scope_all1:checked").val();
		var s2 = $("#scope_all2:checked").val();
		var s3 = $("#scope_all3:checked").val();
		var s4 = $("#scope_all4:checked").val();
		var s5 = $("#scope_all5:checked").val();
		var s6 = $("#scope_all6:checked").val();
		
		if(s1 != null){
		arr_s.push(s1);
		}
		if(s2 != null){
		arr_s.push(s2);
		}
		if(s3 != null){
			arr_s.push(s3);
		}
		if(s4 != null){
			arr_s.push(s4);
		}
		if(s5 != null){
			arr_s.push(s5);
		}
		if(s6 != null){
			arr_s.push(s6);
		}
		console.log("arr da tich:" + arr_s);
		
})
</script>
	<jsp:include page="_bottom1.jsp"></jsp:include>
</body>
</html>