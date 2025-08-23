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
    <title>👤 Create New User - Admin</title>
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
        .form-card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
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
            <a href="<%=request.getContextPath()%>/admin/create-user" class="active"><i class="fas fa-user-plus"></i> Create User</a>
            <a href="<%=request.getContextPath()%>/admin/account-management"><i class="fas fa-credit-card"></i> Account Management</a>
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

            <!-- Create User Form -->
            <div class="card form-card">
                <div class="card-header">
                    <h4 class="mb-0"><i class="fas fa-user-plus"></i> Create New User</h4>
                </div>
                <div class="card-body">
                    <form action="<%=request.getContextPath()%>/admin/create-user" method="post" id="createUserForm">
                        
                        <!-- Personal Information -->
                        <div class="row mb-4">
                            <div class="col-12">
                                <h5 class="text-primary"><i class="fas fa-user"></i> Personal Information</h5>
                                <hr>
                            </div>
                            
                            <div class="col-md-6 mb-3">
                                <label for="fullName" class="form-label">Full Name *</label>
                                <input type="text" class="form-control" id="fullName" name="fullName" required>
                            </div>
                            
                            <div class="col-md-6 mb-3">
                                <label for="username" class="form-label">Username *</label>
                                <input type="text" class="form-control" id="username" name="username" required>
                            </div>
                            
                            <div class="col-md-6 mb-3">
                                <label for="email" class="form-label">Email Address *</label>
                                <input type="email" class="form-control" id="email" name="email" required>
                            </div>
                            
                            <div class="col-md-6 mb-3">
                                <label for="phone" class="form-label">Phone Number *</label>
                                <input type="tel" class="form-control" id="phone" name="phone" required>
                            </div>
                            
                            <div class="col-md-6 mb-3">
                                <label for="dob" class="form-label">Date of Birth *</label>
                                <input type="date" class="form-control" id="dob" name="dob" required>
                            </div>
                            
                            <div class="col-md-6 mb-3">
                                <label for="gender" class="form-label">Gender *</label>
                                <select class="form-select" id="gender" name="gender" required>
                                    <option value="">Select Gender</option>
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                    <option value="Other">Other</option>
                                </select>
                            </div>
                            
                            <div class="col-12 mb-3">
                                <label for="address" class="form-label">Address *</label>
                                <textarea class="form-control" id="address" name="address" rows="3" required></textarea>
                            </div>
                        </div>

                        <!-- KYC Information -->
                        <div class="row mb-4">
                            <div class="col-12">
                                <h5 class="text-primary"><i class="fas fa-id-card"></i> KYC Information</h5>
                                <hr>
                            </div>
                            
                            <div class="col-md-6 mb-3">
                                <label for="aadhaar" class="form-label">Aadhaar Number *</label>
                                <input type="text" class="form-control" id="aadhaar" name="aadhaar" maxlength="12" required>
                            </div>
                            
                            <div class="col-md-6 mb-3">
                                <label for="pan" class="form-label">PAN Number *</label>
                                <input type="text" class="form-control" id="pan" name="pan" maxlength="10" required>
                            </div>
                        </div>

                        <!-- Account Information -->
                        <div class="row mb-4">
                            <div class="col-12">
                                <h5 class="text-primary"><i class="fas fa-credit-card"></i> Account Information</h5>
                                <hr>
                            </div>
                            
                            <div class="col-md-6 mb-3">
                                <label for="accountType" class="form-label">Account Type *</label>
                                <select class="form-select" id="accountType" name="accountType" required>
                                    <option value="">Select Account Type</option>
                                    <option value="Savings">Savings Account</option>
                                    <option value="Current">Current Account</option>
                                    <option value="Fixed Deposit">Fixed Deposit</option>
                                </select>
                            </div>
                            
                            <div class="col-md-6 mb-3">
                                <label for="initialDeposit" class="form-label">Initial Deposit *</label>
                                <input type="number" class="form-control" id="initialDeposit" name="initialDeposit" min="1000" step="0.01" required>
                                <small class="text-muted">Minimum deposit: ₹1,000</small>
                            </div>
                        </div>

                        <!-- Security Information -->
                        <div class="row mb-4">
                            <div class="col-12">
                                <h5 class="text-primary"><i class="fas fa-lock"></i> Security Information</h5>
                                <hr>
                            </div>
                            
                            <div class="col-md-6 mb-3">
                                <label for="password" class="form-label">Temporary Password *</label>
                                <input type="password" class="form-control" id="password" name="password" required>
                                <small class="text-muted">User will be asked to change this on first login</small>
                            </div>
                            
                            <div class="col-md-6 mb-3">
                                <label for="confirmPassword" class="form-label">Confirm Password *</label>
                                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                            </div>
                        </div>

                        <!-- Additional Options -->
                        <div class="row mb-4">
                            <div class="col-12">
                                <h5 class="text-primary"><i class="fas fa-cog"></i> Additional Options</h5>
                                <hr>
                            </div>
                            
                            <div class="col-md-6 mb-3">
                                <label for="role" class="form-label">User Role</label>
                                <select class="form-select" id="role" name="role">
                                    <option value="CUSTOMER" selected>Customer</option>
                                    <option value="ADMIN">Admin</option>
                                </select>
                            </div>
                            
                            <div class="col-md-6 mb-3">
                                <label for="status" class="form-label">Account Status</label>
                                <select class="form-select" id="status" name="status">
                                    <option value="ACTIVE" selected>Active</option>
                                    <option value="PENDING">Pending</option>
                                    <option value="SUSPENDED">Suspended</option>
                                </select>
                            </div>
                        </div>

                        <!-- Form Actions -->
                        <div class="row">
                            <div class="col-12">
                                <div class="d-flex justify-content-between">
                                    <a href="<%=request.getContextPath()%>/admin/dashboard" class="btn btn-secondary">
                                        <i class="fas fa-arrow-left"></i> Back to Dashboard
                                    </a>
                                    <div>
                                        <button type="reset" class="btn btn-warning me-2">
                                            <i class="fas fa-undo"></i> Reset Form
                                        </button>
                                        <button type="submit" class="btn btn-primary">
                                            <i class="fas fa-save"></i> Create User
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </form>
                </div>
            </div>

        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>

<script>
// Form validation
document.getElementById('createUserForm').addEventListener('submit', function(e) {
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const initialDeposit = parseFloat(document.getElementById('initialDeposit').value);
    
    // Check password match
    if (password !== confirmPassword) {
        e.preventDefault();
        alert('Passwords do not match!');
        return;
    }
    
    // Check minimum deposit
    if (initialDeposit < 1000) {
        e.preventDefault();
        alert('Initial deposit must be at least ₹1,000!');
        return;
    }
    
    // Check password strength
    if (password.length < 8) {
        e.preventDefault();
        alert('Password must be at least 8 characters long!');
        return;
    }
});

// Aadhaar validation
document.getElementById('aadhaar').addEventListener('input', function(e) {
    this.value = this.value.replace(/[^0-9]/g, '');
    if (this.value.length > 12) {
        this.value = this.value.slice(0, 12);
    }
});

// PAN validation
document.getElementById('pan').addEventListener('input', function(e) {
    this.value = this.value.toUpperCase().replace(/[^A-Z0-9]/g, '');
    if (this.value.length > 10) {
        this.value = this.value.slice(0, 10);
    }
});

// Phone validation
document.getElementById('phone').addEventListener('input', function(e) {
    this.value = this.value.replace(/[^0-9]/g, '');
    if (this.value.length > 10) {
        this.value = this.value.slice(0, 10);
    }
});
</script>

</body>
</html>
