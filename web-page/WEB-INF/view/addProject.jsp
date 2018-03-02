<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta name="_csrf_parameterName" content="${_csrf.parameterName}" />
<title>Add Project</title>
	<jsp:include page="_banner.jsp"></jsp:include>
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
		<form id="project">
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
			<div class="row">
				<div class="col-xs-4">
					<div>Project Name <input id="project_name" name="project_name" ></div>
				</div>
				<div class="col-xs-2">
					<!-- <div>Type <input value="" name="" size="10"></div> -->
							<div>Type
           				      <select name="type_id" id="type_id">
							       <c:forEach var="projectTypes" items="${projectTypes}">   
							   			<option value="${projectTypes.type_id}" >${projectTypes.type_name}</option>
							      </c:forEach>  
						      </select></div>
				</div>
				<div class="col-xs-3">
			         <div>From<!-- <input id="project_from" class="date" name="project_from"/><img  alt="" src="resources/image/Date-32.png"> -->
			         <div class="form-group">
			                <div class='input-group date' id='project_from1' >
			                    <input id="project_from" type='text' class="form-control" name="project_from"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </div>
			            </div>
				</div>
				<div class="col-xs-3">
					<div>To<!-- <input id="project_to" class="date" name="project_to"/><img  alt="" src="resources/image/Date-32.png"> -->
					<div class="form-group">
			                <div class='input-group date' id='project_to1' >
			                    <input id="project_to" type='text' class="form-control" name="project_to"/>
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
           				<select id="project_technical" name="project_technical" style="margin-left: 30px">
						  <option value="PHP">PHP</option>
						  <option value="Java">Java</option>
						  <option value="IOS">IOS</option>
						  <option value="Android">Android</option>
						</select>
				</div><br>
				<div class="row">
					<div class="col-sm-1">Scope</div> 
					<div class="col-sm-11">
							<c:forEach var="projectScope" items="${projectScope}">   
								<input id="scope_id" type="checkbox" name="scope_id" value="${projectScope.scope_id}" style="margin-left: 10px;">${projectScope.scope_name}
							</c:forEach>
					</div>
				</div><br>
				<div>Charge Cost <input id="project_charge_cost"  name="project_charge_cost" style="margin-left: 10px"> (MM)</div><br>
				<div>Status 
					<select id="status_id" name="status_id" style="margin-left: 45px">
				    	<c:forEach var="projectStatus" items="${projectStatus}">   
				   			<option value="${projectStatus.status_id}" >${projectStatus.status_type}</option>
				     	</c:forEach>  
			    	</select>
				</div><br>
				<div>Description
				 <textarea id="project_description" name="project_description" style="margin-left: 15px" cols="60" rows="3"></textarea></div><br>
				<div style="text-align: end;">
					<button id="buttonAdd" type="submit"  style="background-color: green; color: white;">Create</button>
				</div>
		</form>
	</div>

 <script type="text/javascript">
       $(function () {
          $('#project_from1').datetimepicker({
             format:"YYYY-MM-DD",
            });
         });
</script> 
<script type="text/javascript">
       $(function () {
          $('#project_to1').datetimepicker({
             format:"YYYY-MM-DD",
            });
         });
</script> 
<script type="text/javascript">
$( document).ready(function() {
	var checkBoxValues = [];
	// SUBMIT FORM
    $("#buttonAdd").click(function(event) {
		//Prevent the form from submitting via the browser.
		event.preventDefault(); 
		if($("#project_name").val() == ""){
			alert("project not null");
		}else if($("#project_from").val() == ""){
			alert("From not null");
		}else if($("#project_to").val() == ""){
			alert("To not null");
		}else if(!$.isNumeric($("#project_charge_cost").val())){
			alert("charge must number");
		}else if($("#project_charge_cost").val() == ""){
			alert("charge not null");
		}else if($("#project_charge_cost").val() == "0"){
			alert("charge not 0");
		}else if($("#project_from").val() > $("#project_to").val()){
			alert("From do not bigger than To");
		}else if($("#project_name").val() != "" && $("#project_from").val() != "" && $("#project_charge_cost").val() != "" && $.isNumeric($("#project_charge_cost").val())){
			ajaxPostProject();
		}
			
			
	});
	
    function ajaxPostProject(){
    	
    	// PREPARE FORM DATA
	 	var project_name = $("#project_name").val();
		var project_type = $("#type_id").val();
		var project_from = $("#project_from").val();
		var project_to = $("#project_to").val();
		var project_tech = $("#project_technical").val();
		var project_charge = $("#project_charge_cost").val();
		var project_status = $("#status_id").val();
		var project_des = $("#project_description").val();
		$.each($("input[name='scope_id']:checked"), function(){
            checkBoxValues.push($(this).val());
        });
        console.log(checkBoxValues);
		
		
		var jsonObj = {"project_name" : project_name, "type_id" : project_type, "project_from" : project_from,
			"project_to" : project_to, "project_technical" : project_tech, "project_charge_cost" : project_charge,
			"status_id" : project_status, "project_description" : project_des, "scope_id" : checkBoxValues};
        
        var token = $("meta[name='_csrf']").attr(
		"content");

		var header = $("meta[name='_csrf_header']")
		.attr("content");
	    	// DO POST
	    	$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "actionAdd",
				data : JSON.stringify(jsonObj),
				dataType : 'json',
				beforeSend: function(xhr) {
		            // here it is
		            xhr.setRequestHeader(header, token);
		        },
				success : function(result) {
				alert("add success");
				location.href="${pageContext.request.contextPath}/welcome";
				},
				error : function(e) {
					alert("add false");
				}
			});	

    }
    
})
</script>

</body>
</html>