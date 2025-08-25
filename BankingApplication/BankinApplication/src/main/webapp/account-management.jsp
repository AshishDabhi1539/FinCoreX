<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.banking.model.User"%>
<%@ page import="java.util.List"%>
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
    <title>💳 Account Management - Admin</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
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
        .account-card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            transition: transform 0.3s;
        }
        .account-card:hover {
            transform: translateY(-2px);
        }
        .status-badge {
            font-size: 0.8rem;
            padding: 0.4rem 0.8rem;
        }
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
            <a href="<%=request.getContextPath()%>/admin/account-management" class="active"><i class="fas fa-credit-card"></i> Account Management</a>
            <a href="<%=request.getContextPath()%>/admin/loan-management"><i class="fas fa-money-bill-wave"></i> Loan Management</a>
            <a href="<%=request.getContextPath()%>/admin/transactions"><i class="fas fa-exchange-alt"></i> Transactions</a>
            <a href="<%=request.getContextPath()%>/admin/reports"><i class="fas fa-chart-bar"></i> Reports & Analytics</a>
        </div>

        <!-- Main Content -->
        <div class="col-md-10 p-4">

            <!-- Success/Error Messages -->
            <% if (session.getAttribute("message") != null) { %>
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <i class="fas fa-check-circle"></i> <%= session.getAttribute("message") %>
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
                <% session.removeAttribute("message"); %>
            <% } %>
            
            <% if (session.getAttribute("error") != null) { %>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <i class="fas fa-exclamation-triangle"></i> <%= session.getAttribute("error") %>
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
                <% session.removeAttribute("error"); %>
            <% } %>

            <!-- Page Header -->
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2><i class="fas fa-credit-card"></i> Account Management</h2>
                <div>
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#createAccountModal">
                        <i class="fas fa-plus"></i> Create New Account
                    </button>
                </div>
            </div>

            <!-- Filters and Search -->
            <div class="card mb-4">
                <div class="card-body">
                    <form method="get" action="<%=request.getContextPath()%>/admin/account-management">
                        <div class="row">
                            <div class="col-md-4">
                                <label for="statusFilter" class="form-label">Status Filter</label>
                                <select class="form-select" id="statusFilter" name="status">
                                    <option value="">All Statuses</option>
                                    <option value="ACTIVE">Active</option>
                                    <option value="FROZEN">Frozen</option>
                                    <option value="SUSPENDED">Suspended</option>
                                    <option value="CLOSED">Closed</option>
                                    <option value="PENDING">Pending</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <label for="accountTypeFilter" class="form-label">Account Type</label>
                                <select class="form-select" id="accountTypeFilter" name="accountType">
                                    <option value="">All Types</option>
                                    <option value="Savings">Savings</option>
                                    <option value="Current">Current</option>
                                    <option value="Fixed Deposit">Fixed Deposit</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <label for="searchTerm" class="form-label">Search</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" id="searchTerm" name="search" placeholder="Name, Email, or Phone">
                                    <button class="btn btn-outline-secondary" type="submit">
                                        <i class="fas fa-search"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Accounts List -->
            <div class="row">
                <% if (request.getAttribute("accounts") != null) { %>
                    <% List<User> accounts = (List<User>) request.getAttribute("accounts"); %>
                    <% for (User account : accounts) { %>
                        <div class="col-md-6 col-lg-4 mb-4">
                            <div class="card account-card">
                                <div class="card-body">
                                    <div class="d-flex justify-content-between align-items-start mb-3">
                                        <div>
                                            <h6 class="card-title mb-1">${account.fullName}</h6>
                                            <small class="text-muted">ID: ${account.userId}</small>
                                        </div>
                                        <span class="badge status-badge bg-${account.status == 'ACTIVE' ? 'success' : account.status == 'FROZEN' ? 'warning' : account.status == 'SUSPENDED' ? 'danger' : 'secondary'}">
                                            ${account.status}
                                        </span>
                                    </div>
                                    
                                    <div class="mb-3">
                                        <small class="text-muted d-block">
                                            <i class="fas fa-envelope"></i> ${account.email}
                                        </small>
                                        <small class="text-muted d-block">
                                            <i class="fas fa-phone"></i> ${account.phone}
                                        </small>
                                        <small class="text-muted d-block">
                                            <i class="fas fa-credit-card"></i> ${account.accountType}
                                        </small>
                                        <small class="text-muted d-block">
                                            <i class="fas fa-money-bill-wave"></i> ₹${account.deposit}
                                        </small>
                                    </div>
                                    
                                    <div class="btn-group w-100" role="group">
                                        <button type="button" class="btn btn-sm btn-outline-primary" 
                                                onclick="viewAccountDetails(${account.userId})">
                                            <i class="fas fa-eye"></i> View
                                        </button>
                                        
                                        <% if ("ACTIVE".equals(account.getStatus())) { %>
                                            <button type="button" class="btn btn-sm btn-outline-warning" 
                                                    onclick="freezeAccount(${account.userId})">
                                                <i class="fas fa-pause"></i> Freeze
                                            </button>
                                        <% } else if ("FROZEN".equals(account.getStatus())) { %>
                                            <button type="button" class="btn btn-sm btn-outline-success" 
                                                    onclick="unfreezeAccount(${account.userId})">
                                                <i class="fas fa-play"></i> Unfreeze
                                            </button>
                                        <% } %>
                                        
                                        <button type="button" class="btn btn-sm btn-outline-danger" 
                                                onclick="closeAccount(${account.userId})">
                                            <i class="fas fa-times"></i> Close
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <% } %>
                <% } else { %>
                    <div class="col-12">
                        <div class="alert alert-info">
                            <i class="fas fa-info-circle"></i> No accounts found.
                        </div>
                    </div>
                <% } %>
            </div>

        </div>
    </div>
</div>

<!-- Create Account Modal -->
<div class="modal fade" id="createAccountModal" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><i class="fas fa-plus"></i> Create New Account</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <form action="<%=request.getContextPath()%>/admin/create-account" method="post">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="userId" class="form-label">User ID *</label>
                            <input type="number" class="form-control" id="userId" name="userId" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="accountType" class="form-label">Account Type *</label>
                            <select class="form-select" id="accountType" name="accountType" required>
                                <option value="">Select Type</option>
                                <option value="Savings">Savings Account</option>
                                <option value="Current">Current Account</option>
                                <option value="Fixed Deposit">Fixed Deposit</option>
                            </select>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="initialDeposit" class="form-label">Initial Deposit *</label>
                            <input type="number" class="form-control" id="initialDeposit" name="initialDeposit" min="1000" step="0.01" required>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary">Create Account</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Account Details Modal -->
<div class="modal fade" id="accountDetailsModal" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><i class="fas fa-user"></i> Account Details</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body" id="accountDetailsContent">
                <!-- Content will be loaded dynamically -->
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>

<script>
function viewAccountDetails(userId) {
    // Load account details via AJAX
    fetch('<%=request.getContextPath()%>/admin/user-details?userId=' + userId)
        .then(response => response.text())
        .then(data => {
            document.getElementById('accountDetailsContent').innerHTML = data;
            new bootstrap.Modal(document.getElementById('accountDetailsModal')).show();
        });
}

function freezeAccount(userId) {
    if (confirm('Are you sure you want to freeze this account?')) {
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = '<%=request.getContextPath()%>/admin/freeze-account';
        
        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'userId';
        input.value = userId;
        
        form.appendChild(input);
        document.body.appendChild(form);
        form.submit();
    }
}

function unfreezeAccount(userId) {
    if (confirm('Are you sure you want to unfreeze this account?')) {
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = '<%=request.getContextPath()%>/admin/unfreeze-account';
        
        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'userId';
        input.value = userId;
        
        form.appendChild(input);
        document.body.appendChild(form);
        form.submit();
    }
}

function closeAccount(userId) {
    if (confirm('Are you sure you want to close this account? This action cannot be undone.')) {
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = '<%=request.getContextPath()%>/admin/close-account';
        
        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'userId';
        input.value = userId;
        
        form.appendChild(input);
        document.body.appendChild(form);
        form.submit();
    }
}

// Apply filters on page load
document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    const statusFilter = urlParams.get('status');
    const accountTypeFilter = urlParams.get('accountType');
    const searchTerm = urlParams.get('search');
    
    if (statusFilter) document.getElementById('statusFilter').value = statusFilter;
    if (accountTypeFilter) document.getElementById('accountTypeFilter').value = accountTypeFilter;
    if (searchTerm) document.getElementById('searchTerm').value = searchTerm;
});
</script>

</body>
</html>
