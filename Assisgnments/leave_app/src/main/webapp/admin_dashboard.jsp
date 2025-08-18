<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tss.model.LeaveRequest" %>

<%
    List<LeaveRequest> leaves = (List<LeaveRequest>) request.getAttribute("leaves");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - Leave Requests</title>
    <style>
    body {
    font-family: Arial, sans-serif;
    background: #f4f7fa;
    margin: 0;
    padding: 0;
}

.container {
    width: 90%;
    margin: 20px auto;
    background: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px #ccc;
}

.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

h2 {
    color: #333;
    margin: 0;
}

.logout-btn {
    background: #d9534f;
    border: none;
    padding: 8px 15px;
    color: #fff;
    font-size: 14px;
    cursor: pointer;
    border-radius: 5px;
}
.logout-btn:hover {
    background: #c9302c;
}

.filter-form {
    margin: 20px 0;
    display: flex;
    gap: 10px;
    align-items: center;
}

.filter-form input,
.filter-form select,
.filter-form button {
    padding: 6px;
    font-size: 14px;
}

.filter-form button {
    background: #0275d8;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}
.filter-form button:hover {
    background: #025aa5;
}

.leave-table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

.leave-table th,
.leave-table td {
    border: 1px solid #ddd;
    padding: 10px;
    text-align: center;
}

.leave-table th {
    background: #0275d8;
    color: #fff;
}

.approve-btn {
    background: #5cb85c;
    color: white;
    border: none;
    padding: 5px 10px;
    cursor: pointer;
    border-radius: 4px;
}
.approve-btn:hover {
    background: #449d44;
}

.reject-btn {
    background: #d9534f;
    color: white;
    border: none;
    padding: 5px 10px;
    cursor: pointer;
    border-radius: 4px;
}
.reject-btn:hover {
    background: #c9302c;
}

.no-action {
    color: #777;
}

.no-records {
    text-align: center;
    color: #999;
    font-style: italic;
}

td.pending {
    color: orange;
    font-weight: bold;
}
td.approved {
    color: green;
    font-weight: bold;
}
td.rejected {
    color: red;
    font-weight: bold;
}
    
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h2>Admin Dashboard - Leave Requests</h2>
        <form action="<%=request.getContextPath()%>/logout" method="get">
            <button type="submit" class="logout-btn">Logout</button>
        </form>
    </div>

    <!-- Filter Form -->
    <form method="get" action="<%=request.getContextPath()%>/admin/dashboard" class="filter-form">
        <label>From:</label>
        <input type="date" name="fromDate"/>
        <label>To:</label>
        <input type="date" name="toDate"/>
        <label>Status:</label>
        <select name="status">
            <option value="">All</option>
            <option value="PENDING">Pending</option>
            <option value="APPROVED">Approved</option>
            <option value="REJECTED">Rejected</option>
        </select>
        <button type="submit">Filter</button>
    </form>

    <!-- Leave Requests Table -->
    <table class="leave-table">
        <tr>
            <th>ID</th>
            <th>User</th>
            <th>Date</th>
            <th>Status</th>
            <th>Reason</th>
            <th>Admin Action</th>
        </tr>
        <%
            if (leaves != null && !leaves.isEmpty()) {
                for (LeaveRequest l : leaves) {
        %>
        <tr>
            <td><%= l.getId() %></td>
            <td><%= l.getUserId() %></td>
            <td><%= l.getLeaveDate() %></td>
            <td class="<%= l.getStatus().toLowerCase() %>"><%= l.getStatus() %></td>
            <td><%= l.getReason() %></td>
            <td>
                <% if ("PENDING".equals(l.getStatus())) { %>
                    <form action="<%= request.getContextPath() %>/admin/leaveAction" method="post" class="action-form">
                        <input type="hidden" name="leaveId" value="<%= l.getId() %>"/>
                        <button type="submit" name="action" value="APPROVE" class="approve-btn">Approve</button>
                        <button type="submit" name="action" value="REJECT" class="reject-btn">Reject</button>
                    </form>
                <% } else { %>
                    <span class="no-action">-</span>
                <% } %>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="6" class="no-records">No leave requests found</td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>
