<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.banking.model.User" %>
<%
    User user = (User) session.getAttribute("loggedInUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="content">
    <h2>👤 Account Details</h2>
    <table border="1" cellpadding="8">
        <tr><th>Full Name</th><td><%= user.getFullName() %></td></tr>
        <tr><th>Username</th><td><%= user.getUsername() %></td></tr>
        <tr><th>Email</th><td><%= user.getEmail() %></td></tr>
        <tr><th>Phone</th><td><%= user.getPhone() %></td></tr>
        <tr><th>Aadhaar</th><td><%= user.getAadhaar() %></td></tr>
        <tr><th>PAN</th><td><%= user.getPan() %></td></tr>
        <tr><th>Account Type</th><td><%= user.getAccountType() %></td></tr>
        <tr><th>Balance</th><td>₹ <%= user.getDeposit() %></td></tr>
    </table>
</div>

</body>
</html>