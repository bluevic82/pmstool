<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Milestone</title>
<jsp:include page="_menu.jsp" />
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" >
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-reboot.min.css" />" >
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-grid.min.css" />" >
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-grid.css" />" >
  	<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" >
  
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
	<div class="container">
		<form action="actionCreateMileStone" name="milestone">
			<div>
				<label>Project Name</label>
				<input name="project_id">
			</div>

			<!-- <div class="row">
				<div class="col-sm-2" style="margin-top: 20px">
					<label>Milestone</label>
				</div>
				<div class="col-sm-3" style="margin-top: 10px">
					<div class="form-group">
						<div class='input-group date' id='datetimepicker'>
							<input type='text' class="form-control" name="milestone_date" />
							<div class="input-group-addon">
								<div class="glyphicon glyphicon-calendar"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-7">
					<input type="text" name="milestone_description" style="margin-top: 15px; width: 700px; height: 35px"></input>
				</div> 
			</div>
			
			<div class="row" style="margin-left: 200px">
				<div class="col-sm-3" style="margin-top: 10px">
						<div class="form-group">
							<div class='input-group date' id='datetimepicker'>
								<input type='text' class="form-control" name="milestone_date" />
								<div class="input-group-addon">
									<div class="glyphicon glyphicon-calendar"></div>
								</div>
							</div>
						</div>
				</div>
				<div class="col-sm-7">
					<input type="text" name="milestone_description" style="margin-top: 10px; width: 700px; height: 35px"></input>
				</div> 
			</div>
			
			<div class="row" style="margin-left: 200px">
				<div class="col-sm-3" style="margin-top: 10px">
						<div class="form-group">
							<div class='input-group date' id='datetimepicker'>
								<input type='text' class="form-control" name="milestone_date" />
								<div class="input-group-addon">
									<div class="glyphicon glyphicon-calendar"></div>
								</div>
							</div>
						</div>
				</div>
				<div class="col-sm-7">
					<input type="text" name="milestone_description" style="margin-top: 10px; width: 700px; height: 35px"></input>
				</div> 
			</div> -->
						
			<div class="row">
				<div class="col-sm-1">Milestone</div>
				<div class="container1 col-sm-11">
    				
    					<!-- <input type="text" name="mytext[]"> -->
    					<div class="form-group">
    						<button class="add_form_field"><span style="font-size:16px; font-weight:bold;">+</span></button>
							<div class="input-group date" id="datetimepicker" >
								
								<input type="text" class="form-control" name="milestone_date" />
								<div class="input-group-addon">
									<div class="glyphicon glyphicon-calendar"></div>
								</div>
								<textarea rows="1" cols="90"></textarea>
							</div>
						</div>
    				
   				 </div>
   			
   			</div>
			<div style="text-align: end;">
				<button name="actionCreateMileStone" value=actionCreateMileStone style="background-color: green; color: white;">Create</button>
			</div>
		</form>
	</div>
<script type="text/javascript">
       $(function () {
          $('#datetimepicker').datetimepicker({
             format:"YYYY-MM-DD",
            });
         });
</script> 

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script> -->
<script>
$(document).ready(function() {
    var max_fields      = 50;
    var wrapper         = $(".container1");
    var add_button      = $(".add_form_field"); 
    
    var x = 1; 
    $(add_button).click(function(e){ 
        e.preventDefault();
        if(x < max_fields){ 
            x++; 
            $(wrapper).append('<div><div class="form-group" style="width: 150px;"><div class="input-group date" id="datetimepicker"><input type="text" class="form-control" name="milestone_date" width="10px;" /><div class="input-group-addon"><div class="glyphicon glyphicon-calendar"></div></div></div></div><textarea rows="1" cols="90" name=""></textarea><a href="#" class="delete">Delete</a></div>');  //add input box
        }
		else
		{
		alert('You Reached the limits')
		}
    });
    
    $(wrapper).on("click",".delete", function(e){ 
        e.preventDefault(); $(this).parent('div').remove(); x--;
    })
});
</script>   
</body>
</html>