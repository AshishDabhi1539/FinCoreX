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
%>
<div class="content">
    <h2>✏️ Update Profile</h2>
    <form action="UpdateProfileServlet" method="post">
        <label>Email:</label>
        <input type="email" name="email" value="<%= user.getEmail() %>" required /><br><br>
        <label>Phone:</label>
        <input type="text" name="phone" value="<%= user.getPhone() %>" required /><br><br>
        <label>Address:</label>
        <textarea name="address" required><%= user.getAddress() %></textarea><br><br>
        <button type="submit">Update</button>
    </form>
</div>
</body>
</html>