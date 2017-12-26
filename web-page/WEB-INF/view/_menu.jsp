<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <head>
 		<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" >
  		<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-reboot.min.css" />" >
  		<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-grid.min.css" />" >
  		<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-grid.css" />" >
 </head>
<div style="border: 1px solid #ccc;padding:5px;margin-bottom:20px; background-color: #837CFF;  ">
 
  <a href="${pageContext.request.contextPath}/welcome" style="color: #ffffff;">Home</a>
 
  | &nbsp;
  
   <a href="${pageContext.request.contextPath}/userInfo" style="color: #ffffff;">User Info</a>
  | &nbsp;
     <a href="${pageContext.request.contextPath}/detailProject" style="color: #ffffff;">Project Information</a>
  | &nbsp;
   <a href="${pageContext.request.contextPath}/addProject" style="color: #ffffff;">Add project</a>
  | &nbsp; 
	<a href="${pageContext.request.contextPath}/createTask" style="color: #ffffff;">Create task/spec/issue</a>
  | &nbsp;
  	<a href="${pageContext.request.contextPath}/taskList" style="color: #ffffff;">List task/spec/issue</a>
  | &nbsp;  
    <a href="${pageContext.request.contextPath}/createMileStone" style="color: #ffffff;">Register Milestone</a>
  | &nbsp; 
   <a href="${pageContext.request.contextPath}/registerQA" style="color: #ffffff;">Register QA</a>
  | &nbsp;
   <a href="${pageContext.request.contextPath}/qaList" style="color: #ffffff;">QA List</a>
  | &nbsp;   
  <a href="${pageContext.request.contextPath}/admin" style="color: #ffffff;">Admin</a>
  
  <c:if test="${pageContext.request.userPrincipal.name != null}">
  
     | &nbsp;
     <a href="${pageContext.request.contextPath}/logout" style="color: #ffffff;">Logout</a>
     
  </c:if>
  
</div>