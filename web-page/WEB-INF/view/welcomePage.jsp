<%@page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>${title}</title>
<jsp:include page="_banner.jsp"></jsp:include>
<jsp:include page="_menu.jsp" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
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
	<h6 style="margin-left: 20px"><a href="${pageContext.request.contextPath}/">${message}</a></h6>
	<div class="container-fluid" style="margin: auto;">
	
		<div class="row" style="padding: 10px 180px 1px;">
		<form method="post" action="/pms/">
			<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
			<div class="col-sm-3">Name <input type="text" name="name" value="${sname}" /></div>
			<div class="col-sm-2">PM <input type="text" size="10" name="pm" value="${spm}"/></div>
			<div class="col-sm-3">From <input type="text" name="from" value="${sfrom}"/></div>
			<div class="col-sm-3">To <input type="text" name="to" value="${sto}"/></div>
			<div class="col-sm-1"><button type="submit" style="background-color: green;">Search</button></div>
		</form>
		</div>
		</div>
		<div class="container" style="margin: auto;">
		
		
		<div class="row" style="padding: 10px 0px 0px;">
			<div class=col-md-1 style="font-weight:bold;"><div style="float: right;">#</div></div>
			<div class=col-md-10></div>
			<div class=col-md-1 style="font-weight:bold;text-align: center;">PM</div>
		</div>
		
		
		
			<c:forEach var="list" items="${listP}" varStatus="status">
			
			<div class="row" style="padding: 0px 0px 0px;">
			<div class=col-md-1 style="font-weight:bold"></div>
			<div class=col-md-10><a href="detalproject/${list.project_id}">${list.project_name}</a></div>
			<div class=col-md-1 style="font-weight:bold"></div>
		</div>
			<div class="row" style="padding: 0px 0px 3px;">
				<div class=col-md-1 style="padding-top: 10px" ><div style="float: right;">${status.index +1}</div></div>
				<div class=col-md-10>
					<div style="border: 2px solid black;width:100%;height: 44px;">
					
							<c:forEach items="${thuaTHieu[status.index]}" var="entry">
									<c:if test = "${entry.value == 'green'}">
								 		<div style="width:${(tongPer[status.index]-entry.key)}%;height:40px;background-color:  #82FA58;float: left;text-align: center;font-weight:bold;padding-top:10px;">${tongPer[status.index]-entry.key}%</div>
								 		 <div style="width:${entry.key}%;height:40px;background-color: ${entry.value};display:inline-block;text-align: center;font-weight:bold;padding-top:10px;"><c:if test = "${entry.key != 0}">${entry.key}%</c:if></div>
								     </c:if> 
								     <c:if test = "${entry.value == 'red'}">
								         <div style="width:${tongPer[status.index]}%;height:40px;font-weight:bold;background-color: #F7FE2E;text-align: center;float: left;padding-top:10px;">${tongPer[status.index]}%</div>
								 		 <div style="width:${entry.key}%;height:40px;background-color: ${entry.value};font-weight:bold;display:inline-block;text-align: center;padding-top:10px;"><c:if test = "${entry.key != 0}">${entry.key}%</c:if></div>
								     </c:if> 
								 
								 	 <c:if test = "${entry.value == 'khong'}">
								         <div style="width:${tongPer[status.index]}%;height:40px;background-color: #82FA58;text-align: center;font-weight:bold;float: left;padding-top:10px;"><c:if test = "${tongPer[status.index] != 0}">${tongPer[status.index]}%</c:if></div>
								 		
								     </c:if> 
							
										</c:forEach>
							
										
									</div>
				
								</div>
						<div class=col-md-1 style="float: left ;height: 42px;">
								<div style="width:150px;padding-top: 10px">${list.pm}</div>		
							
						</div>
				</div>
						
			</c:forEach>
			</div>
			<!-- <div class=col-lg-1 style="font-weight:bold">#</div>
			<div class=col-lg-10>
					<div style="border: 1px solid black;width:902px;">
								<div style="width:300px;height:40px;background-color:  #82FA58;float: left;text-align: center;">4</div>
						 		 <div style="width:150px;height:40px;background-color: red;display:inline-block">a</div>
					</div>
				
			</div>
				<div class=col-lg-1 style="font-weight:bold">PM</div>
			</div>
 -->
		<%-- 	<c:forEach var="list" items="${listP}" varStatus="status">
			<div class="row" style="padding: 20px 10px 10px;">
				<div class=col-md-1 style="font-weight:bold;text-align: center; ">${status.index}</div>
				<div class=col-md-10;>
					<div style="border: 1px solid black;width:100%;height:42px;">
						<c:forEach items="${thuaTHieu[status.index]}" var="entry">
							<c:if test = "${entry.value == 'green'}">
						         <div style="width:${(tongPer[status.index]-entry.key)*4}px;height:20px;background-color: #82FA58">${(tongPer[status.index]-entry.key)}%
						 		 <div style="width:${entry.key*4}px;height:20px;background-color: ${entry.value};float: right"><c:if test = "${entry.key != 0}">${entry.key}%</c:if></div>
						 		</div>
						 		<div style="width:${(tongPer[status.index]-entry.key)}%;height:40px;background-color:  #82FA58;float: left;text-align: center;">${tongPer[status.index]-entry.key}%</div>
						 		 <div style="width:${entry.key}%;height:40px;background-color: ${entry.value};display:inline-block"><c:if test = "${entry.key != 0}">${entry.key}%</c:if></div>
						     </c:if> 
						     <c:if test = "${entry.value == 'red'}">
						         <div style="width:${tongPer[status.index]}%;height:40px;background-color: #F7FE2E;float: left;">${tongPer[status.index]}%</div>
						 		 <div style="width:${entry.key}%;height:40px;background-color: ${entry.value};display:inline-block"><c:if test = "${entry.key != 0}">${entry.key}%</c:if></div>
						     </c:if> 
						 
						 	 <c:if test = "${entry.value == 'khong'}">
						         <div style="width:${tongPer[status.index]}%;height:40px;background-color: #82FA58;float: left;"><c:if test = "${tongPer[status.index] != 0}">${tongPer[status.index]}%</c:if></div>
						 		
						     </c:if> 
							
							
							
						</c:forEach>
					</div>
				</div>
				<div class=col-md-1 style="font-weight:bold;text-align: center;">
						<select style="width:100%">
						<c:forEach var="pm" items="${pm[status.index]}">
						  <option value="${pm.value}">${pm.value}</option>
						</c:forEach>
						</select></div>
						
			</div>			
			</c:forEach> --%>
		<%-- <table style="margin-top: 50px;">
			
				<c:forEach var="list" items="${listP}" varStatus="status">
				
				<tr>   
					<td width="3%" ><div style="text-align: center;">${status.index}</div></td>
					<td scope="row" style="padding-top: 10px;"><a href="detalproject/${list.project_id}">${list.project_name}</a>
					<div>
					<div style="border: 1px solid black;width:1002px;height:42px;">
						<c:forEach items="${thuaTHieu[status.index]}" var="entry">
							<c:if test = "${entry.value == 'green'}">
						         <div style="width:${(tongPer[status.index]-entry.key)*4}px;height:20px;background-color: #82FA58">${(tongPer[status.index]-entry.key)}%
						 		 <div style="width:${entry.key*4}px;height:20px;background-color: ${entry.value};float: right"><c:if test = "${entry.key != 0}">${entry.key}%</c:if></div>
						 		</div>
						 		<div style="width:${(tongPer[status.index]-entry.key)*10}px;height:40px;background-color:  #82FA58;float: left;text-align: center;">${tongPer[status.index]-entry.key}%</div>
						 		 <div style="width:${entry.key*10}px;height:40px;background-color: ${entry.value};display:inline-block"><c:if test = "${entry.key != 0}">${entry.key}%</c:if></div>
						     </c:if> 
						     <c:if test = "${entry.value == 'red'}">
						         <div style="width:${tongPer[status.index]*10}px;height:40px;background-color: #F7FE2E;float: left;">${tongPer[status.index]}%</div>
						 		 <div style="width:${entry.key*10}px;height:40px;background-color: ${entry.value};display:inline-block"><c:if test = "${entry.key != 0}">${entry.key}%</c:if></div>
						     </c:if> 
						 
						 	 <c:if test = "${entry.value == 'khong'}">
						         <div style="width:${tongPer[status.index]*10}px;height:40px;background-color: #82FA58;float: left;"><c:if test = "${tongPer[status.index] != 0}">${tongPer[status.index]}%</c:if></div>
						 		
						     </c:if> 
							
							
							
						</c:forEach>
					</div>
					</div>
					
					</td>
					
					<td>
					
					<select style="width:100%">
						<c:forEach var="pm" items="${pm[status.index]}">
						  <option value="${pm.value}">${pm.value}</option>
						</c:forEach>
						</select>
						
					</td>
					
					</tr>
				 </c:forEach>
			 
		</table> --%>
	
	<jsp:include page="_bottom.jsp"></jsp:include>
</body>
</html>