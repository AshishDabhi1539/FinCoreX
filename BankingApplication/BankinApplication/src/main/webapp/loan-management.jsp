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
    <title>💰 Loan Management - Admin</title>
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
        .loan-card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            transition: transform 0.3s;
        }
        .loan-card:hover {
            transform: translateY(-2px);
        }
        .status-badge {
            font-size: 0.8rem;
            padding: 0.4rem 0.8rem;
        }
        .loan-amount {
            font-size: 1.2rem;
            font-weight: bold;
            color: #28a745;
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
            <a href="<%=request.getContextPath()%>/admin/account-management"><i class="fas fa-credit-card"></i> Account Management</a>
            <a href="<%=request.getContextPath()%>/admin/loan-management" class="active"><i class="fas fa-money-bill-wave"></i> Loan Management</a>
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
                <h2><i class="fas fa-money-bill-wave"></i> Loan Management</h2>
                <div>
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#createLoanModal">
                        <i class="fas fa-plus"></i> Create New Loan
                    </button>
                </div>
            </div>

            <!-- Loan Statistics -->
            <div class="row mb-4">
                <div class="col-md-3">
                    <div class="card text-center">
                        <div class="card-body">
                            <i class="fas fa-clock fa-2x text-warning mb-2"></i>
                            <h4 class="text-warning">15</h4>
                            <p class="text-muted mb-0">Pending Applications</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card text-center">
                        <div class="card-body">
                            <i class="fas fa-check-circle fa-2x text-success mb-2"></i>
                            <h4 class="text-success">45</h4>
                            <p class="text-muted mb-0">Approved Loans</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card text-center">
                        <div class="card-body">
                            <i class="fas fa-times-circle fa-2x text-danger mb-2"></i>
                            <h4 class="text-danger">8</h4>
                            <p class="text-muted mb-0">Rejected Loans</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card text-center">
                        <div class="card-body">
                            <i class="fas fa-rupee-sign fa-2x text-info mb-2"></i>
                            <h4 class="text-info">₹2.5M</h4>
                            <p class="text-muted mb-0">Total Loan Amount</p>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Filters -->
            <div class="card mb-4">
                <div class="card-body">
                    <form method="get" action="<%=request.getContextPath()%>/admin/loan-management">
                        <div class="row">
                            <div class="col-md-3">
                                <label for="statusFilter" class="form-label">Loan Status</label>
                                <select class="form-select" id="statusFilter" name="status">
                                    <option value="">All Statuses</option>
                                    <option value="PENDING">Pending</option>
                                    <option value="APPROVED">Approved</option>
                                    <option value="REJECTED">Rejected</option>
                                    <option value="ACTIVE">Active</option>
                                    <option value="CLOSED">Closed</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label for="loanTypeFilter" class="form-label">Loan Type</label>
                                <select class="form-select" id="loanTypeFilter" name="loanType">
                                    <option value="">All Types</option>
                                    <option value="PERSONAL">Personal Loan</option>
                                    <option value="HOME">Home Loan</option>
                                    <option value="BUSINESS">Business Loan</option>
                                    <option value="EDUCATION">Education Loan</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label for="amountFilter" class="form-label">Amount Range</label>
                                <select class="form-select" id="amountFilter" name="amountRange">
                                    <option value="">All Amounts</option>
                                    <option value="0-50000">₹0 - ₹50,000</option>
                                    <option value="50000-200000">₹50,000 - ₹2,00,000</option>
                                    <option value="200000-500000">₹2,00,000 - ₹5,00,000</option>
                                    <option value="500000+">₹5,00,000+</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label for="searchTerm" class="form-label">Search</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" id="searchTerm" name="search" placeholder="Customer name or ID">
                                    <button class="btn btn-outline-secondary" type="submit">
                                        <i class="fas fa-search"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Loan Applications List -->
            <div class="row">
                <% if (request.getAttribute("loanApplicants") != null) { %>
                    <% List<User> loanApplicants = (List<User>) request.getAttribute("loanApplicants"); %>
                    <% for (User applicant : loanApplicants) { %>
                        <div class="col-md-6 col-lg-4 mb-4">
                            <div class="card loan-card">
                                <div class="card-body">
                                    <div class="d-flex justify-content-between align-items-start mb-3">
                                        <div>
                                            <h6 class="card-title mb-1">${applicant.fullName}</h6>
                                            <small class="text-muted">ID: ${applicant.userId}</small>
                                        </div>
                                        <span class="badge status-badge bg-warning">PENDING</span>
                                    </div>
                                    
                                    <div class="mb-3">
                                        <small class="text-muted d-block">
                                            <i class="fas fa-envelope"></i> ${applicant.email}
                                        </small>
                                        <small class="text-muted d-block">
                                            <i class="fas fa-phone"></i> ${applicant.phone}
                                        </small>
                                        <small class="text-muted d-block">
                                            <i class="fas fa-credit-card"></i> ${applicant.accountType}
                                        </small>
                                        <div class="loan-amount mt-2">
                                            <i class="fas fa-rupee-sign"></i> 50,000
                                        </div>
                                        <small class="text-muted">Personal Loan</small>
                                    </div>
                                    
                                    <div class="btn-group w-100" role="group">
                                        <button type="button" class="btn btn-sm btn-outline-primary" 
                                                onclick="viewLoanDetails(${applicant.userId})">
                                            <i class="fas fa-eye"></i> View
                                        </button>
                                        <button type="button" class="btn btn-sm btn-outline-success" 
                                                onclick="approveLoan(${applicant.userId})">
                                            <i class="fas fa-check"></i> Approve
                                        </button>
                                        <button type="button" class="btn btn-sm btn-outline-danger" 
                                                onclick="rejectLoan(${applicant.userId})">
                                            <i class="fas fa-times"></i> Reject
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <% } %>
                <% } else { %>
                    <div class="col-12">
                        <div class="alert alert-info">
                            <i class="fas fa-info-circle"></i> No loan applications found.
                        </div>
                    </div>
                <% } %>
            </div>

        </div>
    </div>
</div>

<!-- Create Loan Modal -->
<div class="modal fade" id="createLoanModal" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><i class="fas fa-plus"></i> Create New Loan</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <form action="<%=request.getContextPath()%>/admin/process-loan" method="post">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="userId" class="form-label">Customer ID *</label>
                            <input type="number" class="form-control" id="userId" name="userId" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="loanType" class="form-label">Loan Type *</label>
                            <select class="form-select" id="loanType" name="loanType" required>
                                <option value="">Select Loan Type</option>
                                <option value="PERSONAL">Personal Loan</option>
                                <option value="HOME">Home Loan</option>
                                <option value="BUSINESS">Business Loan</option>
                                <option value="EDUCATION">Education Loan</option>
                            </select>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="amount" class="form-label">Loan Amount *</label>
                            <input type="number" class="form-control" id="amount" name="amount" min="10000" step="1000" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="tenure" class="form-label">Tenure (Months) *</label>
                            <select class="form-select" id="tenure" name="tenure" required>
                                <option value="">Select Tenure</option>
                                <option value="12">12 Months</option>
                                <option value="24">24 Months</option>
                                <option value="36">36 Months</option>
                                <option value="48">48 Months</option>
                                <option value="60">60 Months</option>
                            </select>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="interestRate" class="form-label">Interest Rate (%) *</label>
                            <input type="number" class="form-control" id="interestRate" name="interestRate" min="5" max="20" step="0.1" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="action" class="form-label">Action *</label>
                            <select class="form-select" id="action" name="action" required>
                                <option value="approve">Approve</option>
                                <option value="reject">Reject</option>
                            </select>
                        </div>
                        <div class="col-12 mb-3">
                            <label for="reason" class="form-label">Reason/Notes</label>
                            <textarea class="form-control" id="reason" name="reason" rows="3"></textarea>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary">Process Loan</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Loan Details Modal -->
<div class="modal fade" id="loanDetailsModal" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><i class="fas fa-file-alt"></i> Loan Application Details</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body" id="loanDetailsContent">
                <!-- Content will be loaded dynamically -->
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>

<script>
function viewLoanDetails(userId) {
    // Load loan details via AJAX
    fetch('<%=request.getContextPath()%>/admin/loan-details?userId=' + userId)
        .then(response => response.text())
        .then(data => {
            document.getElementById('loanDetailsContent').innerHTML = data;
            new bootstrap.Modal(document.getElementById('loanDetailsModal')).show();
        });
}

function approveLoan(userId) {
    if (confirm('Are you sure you want to approve this loan application?')) {
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = '<%=request.getContextPath()%>/admin/process-loan';
        
        const fields = [
            { name: 'userId', value: userId },
            { name: 'action', value: 'approve' },
            { name: 'amount', value: '50000' }, // Default amount
            { name: 'reason', value: 'Loan approved by admin' }
        ];
        
        fields.forEach(field => {
            const input = document.createElement('input');
            input.type = 'hidden';
            input.name = field.name;
            input.value = field.value;
            form.appendChild(input);
        });
        
        document.body.appendChild(form);
        form.submit();
    }
}

function rejectLoan(userId) {
    const reason = prompt('Please provide a reason for rejection:');
    if (reason) {
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = '<%=request.getContextPath()%>/admin/process-loan';
        
        const fields = [
            { name: 'userId', value: userId },
            { name: 'action', value: 'reject' },
            { name: 'amount', value: '0' },
            { name: 'reason', value: reason }
        ];
        
        fields.forEach(field => {
            const input = document.createElement('input');
            input.type = 'hidden';
            input.name = field.name;
            input.value = field.value;
            form.appendChild(input);
        });
        
        document.body.appendChild(form);
        form.submit();
    }
}

// Apply filters on page load
document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    const statusFilter = urlParams.get('status');
    const loanTypeFilter = urlParams.get('loanType');
    const amountRangeFilter = urlParams.get('amountRange');
    const searchTerm = urlParams.get('search');
    
    if (statusFilter) document.getElementById('statusFilter').value = statusFilter;
    if (loanTypeFilter) document.getElementById('loanTypeFilter').value = loanTypeFilter;
    if (amountRangeFilter) document.getElementById('amountFilter').value = amountRangeFilter;
    if (searchTerm) document.getElementById('searchTerm').value = searchTerm;
});
</script>

</body>
</html>
