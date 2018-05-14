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
	
	<meta name="_csrf" content="${_csrf.token}" />
	<meta name="_csrf_header" content="${_csrf.headerName}" />
	<meta name="_csrf_parameterName" content="${_csrf.parameterName}" />
</head>
<body>
<h6 style="margin-left: 20px">${projectInfo.project_name} > Setting > Update Project Information</h6>
	<div class="container" style="margin-top: 10px;">
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
			<div class="row">
				<div class="col-xs-4">
					<div class="row">
						<div class="col-xs-4">
							Project Name
						</div>
						<div class="col-xs-4"> 
							<input type="text" id="project_name" value = "${projectInfo.project_name}" name="project_name"  size="30"/>
							<input type="hidden"value="${projectInfo.project_id}" name="project_id" id="p_id">
						</div>
						<div class="col-xs-4"> 
						</div>
					</div>
				</div>
				<div class="col-xs-4">
					<div class="row">
						<div class="col-xs-2">
							Type
						</div>
						<div class="col-xs-4"> 
						       <select name="type_id" id="id_type_id" style="width: 170px; height: 26px!important;">
							       <c:forEach var="projectTypes" items="${projectTypes}">   
							   			<option value="${projectTypes.type_id}" <c:if test="${projectTypes.type_id==projectInfo.type_id}"> selected="selected"</c:if> >${projectTypes.type_name}</option>
							      </c:forEach>  
						      </select>
						</div>
						<div class="col-xs-6"> 
						</div>
					
					</div>
				</div>
				<div class="col-xs-4"></div>
				</div><br>
				<div class="row">
					<div class="col-xs-4">
						<div class="row">
						<div class="col-xs-4"> From</div>
					             <div class="form-group col-xs-6">
					                <div class='input-group date' id='datetimepicker1' >
					                    <input id="project_from" type='text' class="form-control" name="project_from" value="${projectInfo.project_from }"/>
					                    <div class="input-group-addon">
					                    	<div class="glyphicon glyphicon-calendar"></div>
					                    </div>
					                </div>
					            </div>
					            <div class="col-xs-2"></div>
						</div>
					</div>
					<div class="col-xs-4">
					<div class="row">
						<div class="col-xs-2">To</div>
					            <div class="form-group col-xs-6">
					                <div class='input-group date' id='datetimepicker2' >
					                    <input id="project_to" type='text' class="form-control" name="project_to" value="${projectInfo.project_to }"/>
					                    <div class="input-group-addon">
					                    	<div class="glyphicon glyphicon-calendar"></div>
					                    </div>
					                </div>
					            </div>
					             <div class="col-xs-4"></div>
						</div>	
					</div>
					<div class="col-xs-4"></div>
				</div>
				<div class="row">
					<div class="col-xs-4">
						<div class="row">
							<div class="col-xs-4">
								Technical
							</div>
							<div class="col-xs-4">
								<select id="id_project_technical" name="project_technical" style="width: 170px; height: 26px!important;">
								  <option value="PHP" <c:if test="${projectInfo.project_technical=='PHP'}"> selected="selected"</c:if>>PHP</option>
								  <option value="Java" <c:if test="${projectInfo.project_technical=='Java'}"> selected="selected"</c:if>>Java</option>
								  <option value="IOS" <c:if test="${projectInfo.project_technical=='IOS'}"> selected="selected"</c:if>>IOS</option>
								  <option value="Android" <c:if test="${projectInfo.project_technical=='Android'}"> selected="selected"</c:if>>Android</option>
								</select>
							</div>
							<div class="col-xs-4"></div>
						</div>
					</div>
					<div class="col-xs-4">
					</div>
					<div class="col-xs-4"></div>
				</div><br>
				<div class="row">
					<div class="col-xs-1">
						Scope
					</div>
					<div class="col-xs-11">
						<div style="margin-left: 30px;">
									<input id="scope_all1" type="checkbox" name="scope_id1" value="1"  style="margin-left: 10px;">BD
									<input id="scope_all2" type="checkbox" name="scope_id1" value="2"  style="margin-left: 10px;">DD
									<input id="scope_all3" type="checkbox" name="scope_id1" value="3"  style="margin-left: 10px;">coding
									<input id="scope_all4" type="checkbox" name="scope_id1" value="4"  style="margin-left: 10px;">UT
									<input id="scope_all5" type="checkbox" name="scope_id1" value="5"  style="margin-left: 10px;">IT
									<input id="scope_all6" type="checkbox" name="scope_id1" value="6"  style="margin-left: 10px;">ST
								</div>
					</div>
				</div><br>
				<div class="row">
					<div class="col-xs-4">
						<div class="row">
							<div class="col-xs-4">
								Charge Cost
							</div>
							<div class="col-xs-8">
								<input id="project_charge_cost"  name="project_charge_cost" value="${projectInfo.project_charge_cost }"/> (MM)
							</div>
							
						</div>
					</div>
					<div class="col-xs-8">
					</div>
				</div><br>
				<div class="row">
					<div class="col-xs-4">
						<div class="row">
							<div class="col-xs-4">
								Status
							</div>
							<div class="col-xs-8">
						    	<select id="id_status_id" name="status_id"  style="width: 170px; height: 26px!important;">
							    	<c:forEach var="projectStatus" items="${projectStatus}">   
							   			<option value="${projectStatus.status_id}" <c:if test="${projectInfo.status_id==projectStatus.status_id}"> selected="selected"</c:if> >${projectStatus.status_type}</option>
							     	</c:forEach>  
						    	</select>
							</div>
							
						</div>
					</div>
					<div class="col-xs-8">
					</div>
				</div><br>
				<div class="row">
					<div class="col-xs-4">
						<div class="row">
							<div class="col-xs-4">
								Description
							</div>
							<div class="col-xs-8">
								
								<textarea id="id_project_description" name="project_description" cols="60" rows="3">${projectInfo.project_description }</textarea><br>
							</div>
						</div>
					</div>
					<div class="col-xs-8">
					</div>
				</div><br>
				<div style="text-align: end;">
					<button id="updateProject" style="background-color: green; color: white;">Save</button>
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
		
		var arr_s = [];
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



<script type="text/javascript">


/* function function_submit(){ */
	
	
	


 $("#updateProject").click(function() { 
	 if($("#project_name").val() == ""){
			alert("project not null");
		}else if($("#project_from").val() == ""){
			alert("From not null");
		}else if($("#project_to").val() == ""){
			alert("To not null");
		}else if(!$.isNumeric($("#project_charge_cost").val())){
			alert("charge must number and use value .");
		}else if($("#project_charge_cost").val() == ""){
			alert("charge not null");
		}else if($("#project_charge_cost").val() == "0"){
			alert("charge not 0");
		}else if($("#project_from").val() > $("#project_to").val()){
			alert("From do not bigger than To");
		}else if($("#id_project_description").val().length > 1000){
			alert("Description < 1000 character");
		}
		
		else if($("#project_name").val() != "" && $("#project_from").val() != "" && $("#project_charge_cost").val() != "" && $.isNumeric($("#project_charge_cost").val())){
			axjaxUpdate()
		}
	
 });
 function axjaxUpdate(){
	//submit by ajax
		
		
		
			var arr_sp = [];
			$.each($("input[name='scope_id1']:checked"), function(){
				arr_sp.push($(this).val());
	        });
	        console.log(arr_sp);
	
		// PREPARE FORM DATA
			var project_id = $("#p_id").val();
		 	var project_name = $("#project_name").val();
			var project_type = $("#id_type_id").val();//ok
			var project_from = $("#project_from").val();
			var project_to = $("#project_to").val();
			var project_tech = $("#id_project_technical").val();
			var project_charge = $("#project_charge_cost").val();
			var project_status = $("#id_status_id").val();
			var project_des = $("#id_project_description").html();
			

			var object_Scope_And_Project = {
					project_id : project_id,
					project_name : project_name,
					type_id : project_type,
					project_from : project_from,
					project_to : project_to,
					project_technical : project_tech,
					project_charge_cost : project_charge,
					status_id : project_status,
					project_description : project_des,
					scope_id : arr_sp
				} ;
			console.log(object_Scope_And_Project);
			
		/* alert(object_Scope_And_Project.Scope_id.length); */
		  var token = $("meta[name='_csrf']").attr(
			"content");

			var header = $("meta[name='_csrf_header']")
			.attr("content");
		    	// DO POST
		    	$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "actionUpdateP",
					data : JSON.stringify(object_Scope_And_Project),
					dataType : 'json',
					beforeSend: function(xhr) {
			            // here it is
			            xhr.setRequestHeader(header, token);
			        },
					success : function(result) {
					alert("update success");
					location.href="${pageContext.request.contextPath}"; 
					},
					error : function(e) {
						alert("update false");
					}
				});	  
 };
</script>


	<jsp:include page="_bottom1.jsp"></jsp:include>
</body>
</html>