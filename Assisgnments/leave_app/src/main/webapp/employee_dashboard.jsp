<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Dashboard</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
    }
    h2 {
        color: #333;
    }
    form {
        margin-bottom: 20px;
    }
    select, button {
        padding: 5px 8px;
        margin-right: 10px;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 10px;
    }
    table, th, td {
        border: 1px solid #ddd;
    }
    th, td {
        padding: 8px;
        text-align: center;
    }
    th {
        background-color: #f4f4f4;
    }
</style>
</head>
<body>

<h2>Employee Dashboard</h2>

<!-- Filter Form -->
<form method="get" action="${pageContext.request.contextPath}/employee/dashboard">
    <label>Month:</label>
    <select name="month">
        <option value="">All</option>
        <option value="1" ${selectedMonth == '1' ? 'selected' : ''}>January</option>
        <option value="2" ${selectedMonth == '2' ? 'selected' : ''}>February</option>
        <option value="3" ${selectedMonth == '3' ? 'selected' : ''}>March</option>
        <option value="4" ${selectedMonth == '4' ? 'selected' : ''}>April</option>
        <option value="5" ${selectedMonth == '5' ? 'selected' : ''}>May</option>
        <option value="6" ${selectedMonth == '6' ? 'selected' : ''}>June</option>
        <option value="7" ${selectedMonth == '7' ? 'selected' : ''}>July</option>
        <option value="8" ${selectedMonth == '8' ? 'selected' : ''}>August</option>
        <option value="9" ${selectedMonth == '9' ? 'selected' : ''}>September</option>
        <option value="10" ${selectedMonth == '10' ? 'selected' : ''}>October</option>
        <option value="11" ${selectedMonth == '11' ? 'selected' : ''}>November</option>
        <option value="12" ${selectedMonth == '12' ? 'selected' : ''}>December</option>
    </select>

    <label>Status:</label>
    <select name="status">
        <option value="">All</option>
        <option value="Pending" ${selectedStatus == 'Pending' ? 'selected' : ''}>Pending</option>
        <option value="Approved" ${selectedStatus == 'Approved' ? 'selected' : ''}>Approved</option>
        <option value="Rejected" ${selectedStatus == 'Rejected' ? 'selected' : ''}>Rejected</option>
    </select>

    <button type="submit">Filter</button>
</form>

<!-- Leave Requests Table -->
<table>
    <thead>
        <tr>
            <th>Leave ID</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Status</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="req" items="${requests}">
        <tr>
            <td>${req.leaveId}</td>
            <td>${req.startDate}</td>
            <td>${req.endDate}</td>
            <td>${req.status}</td>
        </tr>
    </c:forEach>
    <c:if test="${empty requests}">
        <tr>
            <td colspan="4">No records found</td>
        </tr>
    </c:if>
    </tbody>
</table>

</body>
</html>
