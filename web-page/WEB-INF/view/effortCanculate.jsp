<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Effort Canculate</title>
<jsp:include page="_banner1.jsp"></jsp:include>
<jsp:include page="_menu.jsp" />
   <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" >
  	<script src="https://momentjs.com/downloads/moment.min.js"></script>
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker-standalone.css">
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker-standalone.min.css">
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css">
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
</head>
<body>
<h6 style="margin-left: 20px">${effort.project_name} > Effort Calculate</h6>
	<div class="container" style="margin-top: 20px">
		<div class="row">
			<div class="col-sm-6">Project Name<input style="margin-left: 50px" size="40" disabled value ="${effort.project_name}"></div>
			<div class="col-sm-6">Status<input style="margin-left: 50px" disabled value = "${effort.status_type }"></div>
		</div><br>
		<div>
			Charge Cost <input style="margin-left: 53px" disabled value = "${effort.project_charge_cost }">&emsp;(MM)
		</div><br>
		<div>
			Actual Cost <input style="margin-left: 60px" disabled value = "${effort.project_actual_cost}">&emsp;(MM)
		</div><br>
		<div>
			Overhead <input style="margin-left: 70px" disabled value = "${effort.over_head }">&emsp;(%)
		</div><br>
		<div>
			Change Request <input style="margin-left: 25px" value = "${effort.change_Request }" disabled>&emsp;(%)
		</div><br>
	
	</div>

</body>
<jsp:include page="_bottom1.jsp"></jsp:include>
</html>