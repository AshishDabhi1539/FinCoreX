<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Receiver Page</title>
</head>
<body>
    <h1>Parameters Received:</h1>
    <p>Message: <%= request.getParameter("message") %></p>
    <p>Source: <%= request.getParameter("source") %></p>
</body>
</html>