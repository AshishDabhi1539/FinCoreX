<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Sender Page</title>
</head>
<body>
    <h1>Sending Parameters...</h1>
    <jsp:forward page="receiver.jsp">
        <jsp:param name="message" value="Hello from sender!" />
        <jsp:param name="source" value="sender.jsp" />
    </jsp:forward>
</body>
</html>