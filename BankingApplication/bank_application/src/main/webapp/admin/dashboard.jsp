<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.banking.model.User"%>
<%@ page import="com.banking.model.Transaction"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    User user = (User) session.getAttribute("user");
    // Temporarily removed authentication check to test redirect loop
    // if (user == null) {
    //     response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
    //     return;
    // }
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
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        body { background: #f8f9fa; }
        .sidebar {
            min-height: 100vh;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
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
        .sidebar a:hover { 
            background-color: rgba(255,255,255,0.2); 
            transform: translateX(5px);
        }
        .card-header { 
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); 
            color: white; 
            border: none;
        }
        .badge-status { font-size: 0.9rem; }
        .emoji { font-size: 1.3rem; margin-right: 6px; }
        .stat-card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            transition: transform 0.3s;
        }
        .stat-card:hover {
            transform: translateY(-5px);
        }
        .recent-activity {
            max-height: 400px;
            overflow-y: auto;
        }
        .activity-item {
            padding: 10px;
            border-left: 3px solid #667eea;
            margin-bottom: 10px;
            background: white;
            border-radius: 5px;
        }

        /* Message Styles */
        .message {
            padding: 15px;
            margin: 20px 0;
            border-radius: 8px;
            font-size: 14px;
            line-height: 1.5;
            text-align: left;
            border-left: 4px solid;
        }

        .error-message {
            background: rgba(239, 68, 68, 0.1);
            border-color: #dc2626;
            color: #dc2626;
        }

        .success-message {
            background: rgba(34, 197, 94, 0.1);
            border-color: #16a34a;
            color: #16a34a;
        }

        .info-message {
            background: rgba(59, 130, 246, 0.1);
            border-color: #2563eb;
            color: #2563eb;
        }

        .warning-message {
            background: rgba(245, 158, 11, 0.1);
            border-color: #d97706;
            color: #d97706;
        }

        .system-status {
            padding: 10px;
            border-radius: 8px;
            margin: 10px 0;
            font-size: 12px;
        }

        .status-healthy { background: rgba(34, 197, 94, 0.1); color: #16a34a; }
        .status-warning { background: rgba(245, 158, 11, 0.1); color: #d97706; }
        .status-critical { background: rgba(239, 68, 68, 0.1); color: #dc2626; }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark" style="background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"><i class="fas fa-shield-alt"></i> Admin Dashboard</a>
        <div class="d-flex align-items-center">
            <span class="navbar-text me-3"><i class="fas fa-user"></i> Welcome, <%= user.getFullName() %></span>
            <a href="<%=request.getContextPath()%>/logout" class="btn btn-light"><i class="fas fa-sign-out-alt"></i> Logout</a>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">

        <!-- Sidebar Menu -->
        <div class="col-md-2 sidebar">
            <h5 class="text-center mb-4"><i class="fas fa-tachometer-alt"></i> Admin Panel</h5>
            <a href="<%=request.getContextPath()%>/admin/dashboard"><i class="fas fa-home"></i> Dashboard</a>
            <a href="<%=request.getContextPath()%>/admin/customers"><i class="fas fa-users"></i> All Customers</a>
            <a href="<%=request.getContextPath()%>/admin/pending"><i class="fas fa-clock"></i> Pending Approvals</a>
            <a href="<%=request.getContextPath()%>/admin/create-user"><i class="fas fa-user-plus"></i> Create User</a>
            <a href="<%=request.getContextPath()%>/admin/account-management"><i class="fas fa-credit-card"></i> Account Management</a>
            <a href="<%=request.getContextPath()%>/admin/loan-management"><i class="fas fa-money-bill-wave"></i> Loan Management</a>
            <a href="<%=request.getContextPath()%>/admin/transactions"><i class="fas fa-exchange-alt"></i> Transactions</a>
            <a href="<%=request.getContextPath()%>/admin/reports"><i class="fas fa-chart-bar"></i> Reports & Analytics</a>
            <hr class="my-3">
            <a href="<%=request.getContextPath()%>/admin/search"><i class="fas fa-search"></i> Search Users</a>
            <a href="#" onclick="exportData()"><i class="fas fa-download"></i> Export Data</a>
        </div>

        <!-- Main Content -->
        <div class="col-md-10 p-4">

            <!-- Display Messages -->
            <c:if test="${not empty sessionScope.error}">
                <div class="message error-message">
                    <i class="fas fa-exclamation-triangle"></i> ${sessionScope.error}
                </div>
                <% session.removeAttribute("error"); %>
            </c:if>
            
            <c:if test="${not empty sessionScope.success}">
                <div class="message success-message">
                    <i class="fas fa-check-circle"></i> ${sessionScope.success}
                </div>
                <% session.removeAttribute("success"); %>
            </c:if>
            
            <c:if test="${not empty sessionScope.info}">
                <div class="message info-message">
                    <i class="fas fa-info-circle"></i> ${sessionScope.info}
                </div>
                <% session.removeAttribute("info"); %>
            </c:if>
            
            <c:if test="${not empty sessionScope.warning}">
                <div class="message warning-message">
                    <i class="fas fa-exclamation-circle"></i> ${sessionScope.warning}
                </div>
                <% session.removeAttribute("warning"); %>
            </c:if>

            <!-- System Status Alerts -->
            <c:if test="${not empty sessionScope.systemAlert}">
                <div class="system-status status-warning">
                    <i class="fas fa-server"></i> <strong>System Alert:</strong> ${sessionScope.systemAlert}
                </div>
                <% session.removeAttribute("systemAlert"); %>
            </c:if>

            <!-- Dashboard Header -->
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2><i class="fas fa-tachometer-alt"></i> Dashboard Overview</h2>
                <div class="text-muted">
                    <i class="fas fa-calendar"></i> <%= new java.text.SimpleDateFormat("EEEE, MMMM dd, yyyy").format(new java.util.Date()) %>
                </div>
            </div>

            <!-- Statistics Cards -->
            <div class="row mb-4">
                <div class="col-md-3">
                    <div class="card stat-card">
                        <div class="card-body text-center">
                            <i class="fas fa-users fa-2x text-primary mb-2"></i>
                            <h4>${totalCustomers}</h4>
                            <p class="text-muted">Total Customers</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card stat-card">
                        <div class="card-body text-center">
                            <i class="fas fa-clock fa-2x text-warning mb-2"></i>
                            <h4>${pendingApprovals}</h4>
                            <p class="text-muted">Pending Approvals</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card stat-card">
                        <div class="card-body text-center">
                            <i class="fas fa-credit-card fa-2x text-success mb-2"></i>
                            <h4>${activeAccounts}</h4>
                            <p class="text-muted">Active Accounts</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card stat-card">
                        <div class="card-body text-center">
                            <i class="fas fa-rupee-sign fa-2x text-info mb-2"></i>
                            <h4>₹${String.format("%.2f", totalBalance)}</h4>
                            <p class="text-muted">Total Balance</p>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Transaction Summary -->
            <div class="row mb-4">
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-header">
                            <i class="fas fa-arrow-up text-success"></i> Total Deposits
                        </div>
                        <div class="card-body">
                            <h5 class="text-success">₹${String.format("%.2f", totalDeposits)}</h5>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-header">
                            <i class="fas fa-arrow-down text-danger"></i> Total Withdrawals
                        </div>
                        <div class="card-body">
                            <h5 class="text-danger">₹${String.format("%.2f", totalWithdrawals)}</h5>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-header">
                            <i class="fas fa-exchange-alt text-primary"></i> Total Transfers
                        </div>
                        <div class="card-body">
                            <h5 class="text-primary">₹${String.format("%.2f", totalTransfers)}</h5>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Recent Activity -->
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <i class="fas fa-history"></i> Recent Transactions
                        </div>
                        <div class="card-body recent-activity">
                            <c:if test="${not empty recentTransactions}">
                                <c:forEach var="txn" items="${recentTransactions}">
                                    <div class="activity-item">
                                        <div class="d-flex justify-content-between">
                                            <span><strong>${txn.type}</strong></span>
                                            <span class="text-muted">₹${String.format("%.2f", txn.amount)}</span>
                                        </div>
                                        <small class="text-muted">${txn.description}</small>
                                    </div>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty recentTransactions}">
                                <p class="text-muted text-center">No recent transactions</p>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <i class="fas fa-user-plus"></i> Recent Users
                        </div>
                        <div class="card-body recent-activity">
                            <c:if test="${not empty recentUsers}">
                                <c:forEach var="user" items="${recentUsers}">
                                    <div class="activity-item">
                                        <div class="d-flex justify-content-between">
                                            <span><strong>${user.fullName}</strong></span>
                                            <span class="badge badge-status bg-${user.status == 'ACTIVE' ? 'success' : user.status == 'PENDING' ? 'warning' : 'danger'}">${user.status}</span>
                                        </div>
                                        <small class="text-muted">${user.email}</small>
                                    </div>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty recentUsers}">
                                <p class="text-muted text-center">No recent users</p>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Quick Actions -->
            <div class="row mt-4">
                <div class="col-12">
                    <div class="card">
                        <div class="card-header">
                            <h5 class="mb-0"><i class="fas fa-bolt"></i> Quick Actions</h5>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-3 mb-2">
                                    <a href="<%=request.getContextPath()%>/admin/pending" class="btn btn-warning btn-sm w-100">
                                        <i class="fas fa-clock"></i> Review Pending
                                    </a>
                                </div>
                                <div class="col-md-3 mb-2">
                                    <a href="<%=request.getContextPath()%>/admin/create-user" class="btn btn-primary btn-sm w-100">
                                        <i class="fas fa-user-plus"></i> Create User
                                    </a>
                                </div>
                                <div class="col-md-3 mb-2">
                                    <a href="<%=request.getContextPath()%>/admin/reports" class="btn btn-info btn-sm w-100">
                                        <i class="fas fa-chart-bar"></i> View Reports
                                    </a>
                                </div>
                                <div class="col-md-3 mb-2">
                                    <a href="<%=request.getContextPath()%>/admin/transactions" class="btn btn-success btn-sm w-100">
                                        <i class="fas fa-exchange-alt"></i> Monitor Transactions
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>

<script>
function exportData() {
    // Implementation for data export
    alert('Export functionality will be implemented here');
}

// Auto-refresh dashboard every 30 seconds
setInterval(function() {
    location.reload();
}, 30000);
</script>

</body>
</html>
