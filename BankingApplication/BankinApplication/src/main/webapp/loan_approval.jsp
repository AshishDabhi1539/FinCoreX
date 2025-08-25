<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loan Approval</title>
</head>
<body>
<h2>Loan Approval</h2>
<% if (request.getParameter("approved") != null) { %>
  <p>Loan has been approved successfully.</p>
<% } else if (request.getParameter("rejected") != null) { %>
  <p>Loan has been rejected.</p>
<% } %>
</body>
</html>
