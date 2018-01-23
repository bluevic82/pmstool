<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<head>
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap-reboot.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap-grid.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap-grid.css" />">
</head>



<div style="padding: 5px; margin-bottom: 20px"
	class = "class_menu">

	<div class="nav-bar">
		<!-- <div style="float: left;"> -->

		<ul class="nav-menu">
			
			<li><a href="${pageContext.request.contextPath}/welcome">Over
					View</a></li>
			<li><a href="${pageContext.request.contextPath}/addProject">Add
					Project</a></li>
			<li><a href="#">Project ></a>
				<ul>
					<c:forEach var="list_Project_For_menu" items="${list_Project_For_menu}">
						<li><a href="#">${list_Project_For_menu.project_name } ></a>
							<ul>
								<li><a href="#">Setting ></a>
									<ul>
										<li><a
											href="${pageContext.request.contextPath}/editproject/${list_Project_For_menu.project_id}">Update
												Project Information</a></li>
										<li><a
											href="${pageContext.request.contextPath}/${list_Project_For_menu.project_id}/${list_Project_For_menu.project_name}/resource">Resource</a></li>
										<li><%-- <a
											href="${pageContext.request.contextPath}/${ list_Project_For_menu.project_id}/createMileStone">
												Milestone</a> --%>
												<a
											href="${pageContext.request.contextPath}/createMileStone">
												Milestone</a></li>
									</ul></li>

								<li><a href="${pageContext.request.contextPath}/${list_Project_For_menu.project_id}/createTask">Create
										Task/Spec/Issue</a></li>
								<li><a href="${pageContext.request.contextPath}/${list_Project_For_menu.project_id}/editTask">Update Task/Spec/Issue</a></li>

								<li><a href="${pageContext.request.contextPath}/registerQA">Q&A
										register/Update</a></li>
								<li><a
									href="${pageContext.request.contextPath}/registerTimeSheet">Timesheet
										register/Update</a></li>
							</ul></li>
					</c:forEach>


				</ul></li>
			<li><a
				href="${pageContext.request.contextPath}/effortManagement">Effort
					Management </a></li>
			<li><a href="#">Users Management ></a>
				<ul>
					<li><a href="${pageContext.request.contextPath}/user">User Register</a></li>
					<li><a href="${pageContext.request.contextPath}/userInfo">User
							Infor</a></li>
					<li><a href="#">Permission Management</a></li>
				</ul></li>
			<li><a href="#"> Management ></a>
				<ul>
					<li><a href="#">Issue Management ></a>
						<ul>
							<li><a href="#">List Issue</a></li>
						</ul></li>
					<li><a href="#">Timesheet Management ></a>
						<ul>
							<li><a
								href="${pageContext.request.contextPath}/timeSheetList">List
									Timesheet</a></li>
						</ul></li>
					<li><a href="${pageContext.request.contextPath}/qaList">Q&A
							List</a></li>

				</ul></li>
			<c:if test="${pageContext.request.userPrincipal.name != null}">

				<li style="float: right;"><a
					href="${pageContext.request.contextPath}/logout">Logout</a></li>

			</c:if>
			<li style="float: right;"><a
				href="${pageContext.request.contextPath}/admin">Admin</a></li>

		</ul>
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
	background: linear-gradient(to bottom, #3366CC, #3366CC);
	/* Green gradient - light to dark. */
	font-size: 14px;
	font-family: Arial, sans-serif;
	text-align: left; /* May want to align center */
}

.nav-menu a {
	color: #fff; /* White font for link color. */
	border: 1px solid #F5F5F5;
	/* background-color: #000000; */
}

.nav-menu, .nav-menu ul {
	list-style: none;
	margin: 0;
	padding: 0;
}

.nav-menu li {
	position: relative;
}

.nav-menu a {
	padding: 5px 10px;
	margin: 5px 10px;
	display: block;
	text-decoration: none;
	border-radius: .2em; /* Nice little rounded corners. */
}

.nav-menu a:hover {
	opacity: 1.0;
	background: rgba(0, 0, 0, 0.8);
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
	
}

.nav-menu>li>a:hover {
	
}

/* Navigation Bar - Level 2 */
.nav-menu>li>ul {
	text-align: left;
	width: auto;
	/* Change auto value with 200px if you want a bigger menu */
	display: none;
	background: #3366CC; /* Dark green. */
	position: absolute;
	top: 100%;
	left: 0;
	padding-bottom: 5px;
	min-width: 150px;
	border-radius: 0 0 .5em .5em;
	z-index: 9999999;
}

.nav-menu>li:hover>ul {
	display: block;
}

.nav-menu ul li a {
	
}

.nav-menu ul li a:hover {
	
}

/* Navigation Bar - Level 3 */
.nav-menu>li>ul>li>ul {
	text-align: left;
	display: none;
	background: #3366CC; /* Light green. */
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
	
}

.nav-menu ul ul li a:hover {
	
}

/* Navigation Bar - Level 4 */
.nav-menu>li>ul>li>ul>li>ul {
	text-align: left;
	display: none;
	background: #3366CC; /* Light green. */
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
	
}

.nav-menu ul ul ul li a:hover {
	
}
</style>

