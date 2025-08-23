<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ page import="com.banking.model.NotificationPreference" %>
<%
    NotificationPreference pref = (NotificationPreference) request.getAttribute("preferences");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="content">
    <h2>🔔 Manage Notification Preferences</h2>
    <form action="notifications" method="post">
        <label>Email:</label>
        <input type="checkbox" name="email" <%= pref.isEmailEnabled() ? "checked" : "" %> /><br>
        <label>SMS:</label>
        <input type="checkbox" name="sms" <%= pref.isSmsEnabled() ? "checked" : "" %> /><br>
        <label>WhatsApp:</label>
        <input type="checkbox" name="whatsapp" <%= pref.isWhatsappEnabled() ? "checked" : "" %> /><br><br>
        <button type="submit">Update Preferences</button>
    </form>
</div>
</body>
</html>