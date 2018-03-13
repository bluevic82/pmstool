<%@page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Access Denied</title>
</head>
<body>
<jsp:include page="_banner.jsp"></jsp:include>
<jsp:include page="_menu.jsp"/>
    <h3 style="color:red;">Access denied</h3>
    <h3 style="color:red;">${message}</h3>
</body>
<jsp:include page="_bottom.jsp"></jsp:include>
</html>
