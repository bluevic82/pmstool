<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<head>
<script src="https://momentjs.com/downloads/moment.min.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  	<link rel="stylesheet" href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" />" >
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker-standalone.min.css"/>
	 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
</head>


<div style="background-color: #7f7f7f;">
<div style="padding: 0px; margin-bottom: 1px; "
	class = "class_menu">
	<div class="nav-bar">
		<!-- <div style="float: left;"> -->

		<ul class="nav-menu" style=" font-size: 14px">
			
			<li><a href="${pageContext.request.contextPath}">Over View</a></li>
			<li><a href="${pageContext.request.contextPath}/addProject">Add Project</a></li>
			<li><a href="#">Projects</a>
				<ul >
					<c:forEach var="list_Project_For_menu" items="${list_Project_For_menu}">
					
						<li class="col-sm-6"><a href="#">${list_Project_For_menu.project_name }</a>
							<ul>
								<li><a href="#">Settings</a>
									<ul>
										<li><a href="${pageContext.request.contextPath}/editproject/${list_Project_For_menu.project_id}">Update
												Project Information</a></li>
										<li><a href="${pageContext.request.contextPath}/${list_Project_For_menu.project_id}/resource">
												Resource</a></li>
										<li> <a href="${pageContext.request.contextPath}/${list_Project_For_menu.project_id}/createMileStone">
												Milestone</a></li>
									</ul>
								</li>

								<li><a href="${pageContext.request.contextPath}/${list_Project_For_menu.project_id}/createTask">Create
										Task/Spec/Issue</a></li>
																
								<li><a href="${pageContext.request.contextPath}/${list_Project_For_menu.project_id}/createBug">Create
										Bug</a></li>

								<li><a href="${pageContext.request.contextPath}/${list_Project_For_menu.project_id}/registerQA">Q&amp;A
										register/Update</a></li>
										
								<li><a href="${pageContext.request.contextPath}/${list_Project_For_menu.project_id}/registerTimeSheet">
										Timesheet register/Update</a></li>
							</ul>
						</li>
					</c:forEach>
				</ul>
		
			</li>
			<li><a href="${pageContext.request.contextPath}/effortManagement">Effort
					Management </a></li>
					
			<li><a href="#">Users Management</a>
				<ul class="maxblock">
					<li><a href="${pageContext.request.contextPath}/user">User
							Register</a></li>
					<%-- <li><a href="${pageContext.request.contextPath}/userInfo">User
							Infor</a></li> --%>
					<sec:authentication property="principal.authorities"
						var="authorities" />
					<c:forEach items="${authorities}" var="authority" varStatus="vs">

						<c:if test="${authority.authority == '1'}">
							<li><a
								href="${pageContext.request.contextPath}/permissionManager">Permission
									Management</a></li>
						</c:if>
					</c:forEach>

				</ul></li>
			<li><a href="#"> Statistics</a>
				<ul class="maxblock">
					
							<li><a href="${pageContext.request.contextPath}/taskList">List Task/Spec/Issue</a></li>
						
					
							<li><a href="${pageContext.request.contextPath}/bugList">List Bug</a></li>
						
					
							<li><a
								href="${pageContext.request.contextPath}/timeSheetList">List
									Timesheet</a></li>
						
					<li><a href="${pageContext.request.contextPath}/qaList">Q&amp;A
							List</a></li>

				
				
				</ul>
				</li>
			<c:if test="${pageContext.request.userPrincipal.name != null}">

				<li style="float: right;">
				<%-- <a href="${pageContext.request.contextPath}/logout">Logout</a> --%>
				<%-- <a href="${pageContext.request.contextPath}/logout?${_csrf.parameterName}=${_csrf.token}">Logout</a> --%>
					<a href="${pageContext.request.contextPath}/logout">Logout</a>
				</li>

			</c:if>
			<li style="float: right;"><a
				href="${pageContext.request.contextPath}/admin">${UserInformation.user_fullName}</a></li>

		</ul>
	</div>
</div>
</div>


<style>
@CHARSET "ISO-8859-1";

body {
	margin: 0;
	padding: 0;
	/* background: #0101DF; */
}

/* Navigation Bar */
.nav-bar {
	background: linear-gradient(to bottom, #7f7f7f, #7f7f7f);
	/* Green gradient - light to dark. */
	font-size: 14px;
	font-family: Arial, sans-serif;
	text-align: left; /* May want to align center */
	height: 30px;
}

.nav-menu a {
	color: #e1e1e1; /* White font for link color. */
	/* border: 1px solid #F5F5F5; */
	/* background-color: #000000; */
	
}

.nav-menu, .nav-menu ul {
	list-style: none;
	margin: 0;
	padding: 0;
	padding-left: 10px;
	
}

.nav-menu li {
	position: relative;
	/* padding-left: 10px;
	padding-right: 10px; */
	padding: 0px 10px 0px 10px; /*top right botton left*/
}

.nav-menu a {
    padding: 5px 5px; 
	margin: 5px 5px;
	display: block;
	text-decoration: none;
	border-radius: .2em; /* Nice little rounded corners. */
}

.nav-menu a:hover {
	opacity: 1.0;
	background: rgba(0, 0, 0, 0);
	color: #9B1111;
	text-decoration: none;
	
	/* Solid black rollover for all items. */
}

/* Selected Menu Item */
.selected {
	opacity: 1.0;
	background: rgba(255, 255, 255, 0.5);
	/* Transparent white selected item. */
	color: #000 !important; /* Black font. */
	border-radius: .2em;
}

/* Navigation Bar - Level 1 Drop Down Menu */
.nav-menu>li {
	display: inline-block;
	vertical-align: top;
	margin-left: -4px;
}

.nav-menu>li:first-child {
	margin-left: 0;
}

.nav-menu>li>a {
	padding: 0px 0px 0px 0px;
}

.nav-menu>li>a:hover {
	
}

/* Navigation Bar - Level 2 */
.nav-menu>li>ul {
	text-align: left;
	width: auto;
	/* Change auto value with 200px if you want a bigger menu */
	display: none;
	background: #7f7f7f; /* Dark green. */
	position: absolute;
	top: 100%;
	left: 0;
	padding-bottom: 5px;
	width: 500px;
	border-radius: 0 0 .5em .5em;
	z-index: 9999999;
	
	
}
.maxblock{
	max-width: 150px;
	
}
.nav-menu li ul li {
	padding-left: 0px;
}

.nav-menu>li:hover>ul {
	display: inline-block;
	/* text-align: center; */
}

.nav-menu ul li a {
	/*  background-color: #4F4F4F; */
	/* border: solid; 
	border-width: 0.5px; */
	border-color: #777777;
	color: #e1e1e1;
	
}

.nav-menu ul li a:hover {
	background-color: #e1e1e1;
}

/* Navigation Bar - Level 3 */
.nav-menu>li>ul>li>ul {
	text-align: left;
	display: none;
	background: #666666; /* Light green. */
	position: absolute;
	left: 100%;
	top: 0;
	padding-bottom: 5px;
	min-width: 150px;
	border-radius: 0 0 .5em .5em;
	z-index: 9999999;
}

.nav-menu>li>ul>li:hover>ul {
	display: block;
	
}

.nav-menu ul ul li {
	
}

.nav-menu ul ul li a {
	/* background-color: #363636; */
	border-color: #1C1C1C;
	/* text-align: center; */
	color: white;
}

.nav-menu ul ul li a:hover {
	
}

/* Navigation Bar - Level 4 */
.nav-menu>li>ul>li>ul>li>ul {
	text-align: left;
	display: none;
	background: #555555; /* Light green. */
	position: absolute;
	left: 100%;
	top: 0;
	padding-bottom: 5px;
	min-width: 250px;
	border-radius: 0 0 .5em .5em;
	z-index: 9999999;
}

.nav-menu>li>ul>li>ul>li:hover>ul {
	display: block;
}

.nav-menu ul ul ul li {
	
}

.nav-menu ul ul ul li a {
	/* background-color: #000000; */
	/* text-align: center; */
}

.nav-menu ul ul ul li a:hover {
	
}
</style>

