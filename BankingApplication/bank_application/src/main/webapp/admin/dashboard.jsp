<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.banking.model.User"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
        response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>🛡️ Admin Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Chart.js -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body { background: #f0f2f5; }
        .sidebar {
            min-height: 100vh;
            background-color: #111827;
            color: white;
            padding-top: 20px;
        }
        .sidebar a {
            color: white;
            text-decoration: none;
            display: block;
            padding: 12px 20px;
            border-radius: 8px;
            margin-bottom: 5px;
            transition: 0.3s;
        }
        .sidebar a:hover { background-color: #2563eb; }
        .card-header { background-color: #2563eb; color: white; }
        .badge-status { font-size: 0.9rem; }
        .emoji { font-size: 1.3rem; margin-right: 6px; }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">🏦 Admin Dashboard</a>
        <div class="d-flex align-items-center">
            <span class="navbar-text me-3">👋 Welcome, <%= user.getFullName() %></span>
            <a href="<%=request.getContextPath()%>/logout" class="btn btn-light">Logout</a>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">

        <!-- Sidebar Menu -->
        <div class="col-md-2 sidebar">
            <h5 class="text-center mb-3">📋 Menu</h5>
            <a href="#summary">🏠 Home / Summary</a>
            <a href="#viewCustomers">👥 View All Customers</a>
            <a href="#approveCustomers">✅ Approve / ❌ Reject</a>
            <a href="#createUser">➕ Create New User</a>
            <a href="#openAccount">💳 Open Account</a>
            <a href="#loanManagement">💰 Loan Management</a>
            <a href="#reports">📊 Reports</a>
            <a href="#closeAccount">🔒 Close / Deactivate Account</a>
        </div>

        <!-- Main Content -->
        <div class="col-md-10 p-4">

            <!-- Home / Summary -->
            <div class="row mb-4" id="summary">
                <div class="col-md-3">
                    <div class="card text-center shadow-sm">
                        <div class="card-header">👥 Total Customers</div>
                        <div class="card-body">
                            <h3>120</h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card text-center shadow-sm">
                        <div class="card-header">✅ Pending Approvals</div>
                        <div class="card-body">
                            <h3>15</h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card text-center shadow-sm">
                        <div class="card-header">💳 Active Accounts</div>
                        <div class="card-body">
                            <h3>100</h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card text-center shadow-sm">
                        <div class="card-header">🔒 Closed / Deactivated</div>
                        <div class="card-body">
                            <h3>5</h3>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Example Chart -->
            <div class="card shadow-sm mb-4">
                <div class="card-header">📊 Customer Status Overview</div>
                <div class="card-body">
                    <canvas id="statusChart" height="150"></canvas>
                </div>
            </div>

            <!-- The rest of the sections (View Customers, Approve, Create User, etc.) 
                 can follow the same cards/forms structure as before -->
            <!-- ... copy previous sections here ... -->

        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>

<!-- Chart.js -->
<script>
    const ctx = document.getElementById('statusChart').getContext('2d');
    const statusChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: ['Active', 'Pending', 'Suspended', 'Closed'],
            datasets: [{
                label: 'Accounts',
                data: [100, 15, 0, 5], // Replace with dynamic data
                backgroundColor: [
                    'rgba(37, 99, 235, 0.7)',
                    'rgba(234, 179, 8, 0.7)',
                    'rgba(220, 38, 38, 0.7)',
                    'rgba(107, 114, 128, 0.7)'
                ],
                borderColor: [
                    'rgba(37, 99, 235, 1)',
                    'rgba(234, 179, 8, 1)',
                    'rgba(220, 38, 38, 1)',
                    'rgba(107, 114, 128, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: { responsive: true }
    });
</script>

</body>
</html>
