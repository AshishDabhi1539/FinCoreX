<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loan Status</title>
</head>
<body>
<h2>Loan Application</h2>
<% if (request.getParameter("applied") != null) { %>
  <p>Your loan application has been submitted successfully.</p>
<% } %>
</body>
</html>
