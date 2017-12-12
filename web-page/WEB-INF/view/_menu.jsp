<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <head>
 		<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" >
  		<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-reboot.min.css" />" >
  		<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-grid.min.css" />" >
  		<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-grid.css" />" >
 </head>
<div style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">
 
  <a href="${pageContext.request.contextPath}/welcome">Home</a>
 
  | &nbsp;
  
   <a href="${pageContext.request.contextPath}/userInfo">User Info</a>
  | &nbsp;
   <a href="${pageContext.request.contextPath}/addProject">Add project</a>
  | &nbsp;
   <a href="${pageContext.request.contextPath}/updateProject">Update project</a>
   
  | &nbsp; 
	<a href="${pageContext.request.contextPath}/createTask">Create task/spec/issue</a>
  | &nbsp;
	<a href="${pageContext.request.contextPath}/updateTask">Update task/spec/issue</a>

  | &nbsp; 
   <a href="${pageContext.request.contextPath}/answerAndQ">Answer And Question List</a>
   
  | &nbsp;
  
  <a href="${pageContext.request.contextPath}/admin">Admin</a>
  
  <c:if test="${pageContext.request.userPrincipal.name != null}">
  
     | &nbsp;
     <a href="${pageContext.request.contextPath}/logout">Logout</a>
     
  </c:if>
  
</div>