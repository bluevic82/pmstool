<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Project</title>
	<jsp:include page="_menu.jsp" />
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" >
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-reboot.min.css" />" >
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-grid.min.css" />" >
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-grid.css" />" >
  	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
</head>
<body>
	<div class="container">
		<form>
			<div class="row">
				<div class="col-sm-4">
					<div>Project Name <input value="" name="" size="30"></div>
				</div>
				<div class="col-sm-2">
					<div>Type <input value="" name="" size="10"></div>
				</div>
				<div class="col-sm-3">
					<div>From <input value="" class="datepicker" size="15"></div>	
				</div>
				<div class="col-sm-3">
						<div>To <input value="" class="datepicker" size="15"> </div>
					</div>
				</div><br>
				<div>Technical <input value="" name="" style="margin-left: 30px"></div><br>
				<div>Scope </div><br>
				<div>Charge Cost <input value="" name="" style="margin-left: 10px"> (MM)</div><br>
				<div>Status <input value="" name="" style="margin-left: 52px" ></div><br>
				<div>Description <textarea style="margin-left: 15px" cols="60"></textarea></div><br>
				<div><button value="save" style="background-color: green; color: white;">Save</button></div>
		</form>
	</div>

	<script>
	$(document).ready(function() {
	    $( ".datepicker" ).datepicker({
	          showOn: "button",
	          buttonImage: "${pageContext.request.contextPath}/resources/image/Date-32.png",
	          buttonImageOnly: true
	     });
	});
	</script>
</body>
</html>