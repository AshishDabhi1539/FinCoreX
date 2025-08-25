<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Repay Loan</title>
</head>
<body>
<h2>Loan Repayment</h2>
<% if (request.getParameter("success") != null) { %>
  <p>Loan repayment processed successfully.</p>
<% } %>
</body>
</html>
