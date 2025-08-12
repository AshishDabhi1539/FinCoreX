<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="com.tss.model.UserBean" scope="session" />
<!DOCTYPE html>
<html>
<head>
<title>User Information</title>
</head>
<body>
    <h1>User Details:</h1>
    <jsp:setProperty name="user" property="name" value="Mahek Morzaria" />
    <jsp:setProperty name="user" property="age" value="20" />

    <p>Name: <jsp:getProperty name="user" property="name" /></p>
    <p>Age: <jsp:getProperty name="user" property="age" /></p>
</body>
</html>