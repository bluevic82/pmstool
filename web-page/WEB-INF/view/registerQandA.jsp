<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<title> QandA Register/Update</title>
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
	<div class="container">
	<form:form method="POST" name="qa" action="actionSave">
		<div >
			Project Name <input name="project_id" size="30" style="margin-left: 7px;"/>
		</div>
		<br>
		<div>
			Title <input name="q_a_title" style="margin-left: 82px;" size="50"/>
		</div>
		<br>
		<div>
			Reference point <input name="referencepoint" style="margin-left: 10px;" size="50"/> 
		<button>Browse</button>
		</div>
		<br>
		<div class="row">
		<div class="col-sm-6">Question<br>
			<div >
				<label>VN</label>
				<textarea name="q_a_question_vi" rows="4" cols="30"></textarea><br><br>
				<label>JP</label>
				<textarea name="q_a_question_jp" rows="4" cols="30"></textarea><br>
			</div>
		</div>
			
		<div class="col-sm-6">Answer<br>
			<textarea name="q_a_answer_vi" rows="4" cols="30"></textarea><br><br>
			<textarea name="q_a_anser_jp" rows="4" cols="30"></textarea><br>
		</div>
			
		</div><br>
			To
			<select name="member_project_id"
				style="margin-left: 65px">
				<c:forEach var="pic" items="${pic}">
					<option value="${pic.member_project_id}">
						${pic.member_project_name}</option>
				</c:forEach>
			</select>
			<br>
			<br>
			<div class="row">
				<div class="col-sm-1">Deadline</div>
					<div class="form-group col-sm-4">
						<div class='input-group date' id='datetimepicker1'>
							<input size="20" type='text' class="form-control" name="q_a_dealine" />
							<div class="input-group-addon">
								<div class="glyphicon glyphicon-calendar"></div>
							</div>
						</div>
					</div>
			<div class="col-sm-7"></div>
			</div>
			<div>
				Status <select name="status">
					<c:forEach var="qaStatus" items="${qaStatus}">
						<option value="${qaStatus.status_id}">${qaStatus.status_type}</option>
					</c:forEach>
				</select>
				<button name="actionSave" value="actionSave" style="background-color: green; color: white; margin-left: 1000px;">Save</button>
			</div><br>
			</form:form>
	</div>


<script type="text/javascript">
       $(function () {
          $('#datetimepicker1').datetimepicker({
             format:"YYYY-MM-DD",
            });
         });
     </script> 
</body>
</html>