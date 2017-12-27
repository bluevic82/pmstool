<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>permission manager</title>
<jsp:include page="_menu.jsp" />
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" >
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-reboot.min.css" />" >
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-grid.min.css" />" >
  	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-grid.css" />" >
  
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

	<script src="/resources/js/bootstrap.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-6">
				<table style="width:100%">
					  <tr>
					    <th></th>
					    <th>Manager</th>
					    <th>Developer</th>
					    <th>Tester</th>
					    <th>Reporter</th>
					    <th>Customer</th>
					  </tr>
					  <tr>
					    <td>OverView</td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td></td>
					  </tr>
					 <tr>
					    <td>Project detail</td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td></td>
					  </tr>
					  <tr>
					    <td>Add project</td>
					    <td><input type="checkbox"></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					  </tr>
					 <tr>
					    <td>Setting - Update project info</td>
					    <td><input type="checkbox"></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					  </tr>
					  <tr>
					    <td>Setting - resource</td>
					    <td><input type="checkbox"></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					  </tr>
					 <tr>
					    <td>Create issue</td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td></td>
					  </tr>
					  <tr>
					    <td>Update issue</td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td></td>
					  </tr>
					 <tr>
					    <td>Setting - Register milestone</td>
					    <td><input type="checkbox"></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					  </tr>
					  <tr>
					    <td>Effort management</td>
					    <td><input type="checkbox"></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					  </tr>
					 <tr>
					    <td>Effort canculate</td>
					    <td><input type="checkbox"></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					  </tr>
					  <tr>
					    <td>Q&A register-update</td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					  </tr>
					 <tr>
					    <td>Timesheet register-update</td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td></td>
					  </tr>
					  <tr>
					    <td>User register</td>
					    <td><input type="checkbox"></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					  </tr>
					 <tr>
					    <td>Issue list</td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td></td>
					  </tr>
					  <tr>
					    <td>Timesheet list</td>
					    <td><input type="checkbox"></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					  </tr>
					 <tr>
					    <td>Q&A list</td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					    <td><input type="checkbox"></td>
					  </tr>
				</table>
			</div>
			<div class="col-sm-6"></div>
			</div>
		
		<div style="text-align: right; margin-top: 50px;"><button type="submit" style="background-color: green; color: white; width: 150px;">Save</button> </div>
	</div>
</body>
</html>