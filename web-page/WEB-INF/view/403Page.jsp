<%@page session="false"%>
<html>
<head>
<title>Access Denied</title>
</head>
<body>
<jsp:include page="_banner1.jsp"></jsp:include>
<jsp:include page="_menu.jsp"/>
    <h3 style="color:red;">Access denied</h3>
    <h3 style="color:red;">${message}</h3>
</body>
</html>
