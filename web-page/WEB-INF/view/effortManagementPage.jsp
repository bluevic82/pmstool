<%@page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>${title}</title>
<jsp:include page="_banner.jsp"></jsp:include>
<jsp:include page="_menu.jsp" />

<script src="https://momentjs.com/downloads/moment.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker-standalone.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
</head>
<body>
	<h6 style="margin-left: 20px"><a href="${pageContext.request.contextPath}/effortManagement">Effort Management</a></h6>
	<div class="container" style="overflow: scroll; height: 75%;">
		<c:forEach var="list" items="${list}">
			<div id="divMain_Effort" style="width: 1000px; height: 50px">

				<div
					style="width: 200px; height: 50px; background-color: Gainsboro; float: left; text-align: center"><b>
					<a href="effortCalculate/${list.project_id}">${list.project_name}</a></b>
				</div>
				<div
					style="width: 100px; height: 50px; background-color: green; float: left">
					<div
						style="width: 100px; height: 25px; background-color: FloralWhite; text-align: center;">Charge</div>
					<div
						style="width: 100px; height: 25px; background-color: FloralWhite; text-align: center;">Actual</div>
				</div>
				<div style="width: 700px; height: 50px; float: left;">
					<div
						style="text-align: center; width: ${list.width_project_charge_cost}; height: 25px; background-color: #6CA6CD; color: white; ">${list.project_charge_cost} (MM)</div>
					<div
						style="width:${list.width_project_actual_cost}; height: 25px; background-color: #EE1289; float: left; color: white ">${list.over_head }%</div>
				</div>


			</div>
			<br>
			

		</c:forEach>
		

	</div>
		<jsp:include page="_bottom1.jsp"></jsp:include>
</body>
</html>