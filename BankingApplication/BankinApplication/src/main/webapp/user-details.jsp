<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.banking.model.User"%>
<%@ page import="com.banking.model.Transaction"%>
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
    <title>👤 User Details - Admin</title>
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
        .detail-card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        .status-badge {
            font-size: 0.9rem;
            padding: 0.5rem 1rem;
        }
        .transaction-item {
            border-left: 3px solid #667eea;
            padding: 10px;
            margin-bottom: 10px;
            background: white;
            border-radius: 5px;
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
            <a href="<%=request.getContextPath()%>/admin/loan-management"><i class="fas fa-money-bill-wave"></i> Loan Management</a>
            <a href="<%=request.getContextPath()%>/admin/transactions"><i class="fas fa-exchange-alt"></i> Transactions</a>
            <a href="<%=request.getContextPath()%>/admin/reports"><i class="fas fa-chart-bar"></i> Reports & Analytics</a>
        </div>

        <!-- Main Content -->
        <div class="col-md-10 p-4">

            <!-- Back Button -->
            <div class="mb-4">
                <a href="<%=request.getContextPath()%>/admin/customers" class="btn btn-secondary">
                    <i class="fas fa-arrow-left"></i> Back to Customers
                </a>
            </div>

            <% if (request.getAttribute("user") != null) { %>
                <% User customerUser = (User) request.getAttribute("user"); %>
                
                <!-- User Information -->
                <div class="row mb-4">
                    <div class="col-md-8">
                        <div class="card detail-card">
                            <div class="card-header">
                                <h5 class="mb-0"><i class="fas fa-user"></i> Customer Information</h5>
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-6">
                                        <h6 class="text-primary">Personal Details</h6>
                                        <table class="table table-borderless">
                                            <tr>
                                                <td><strong>Full Name:</strong></td>
                                                <td>${customerUser.fullName}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Username:</strong></td>
                                                <td>${customerUser.username}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Email:</strong></td>
                                                <td>${customerUser.email}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Phone:</strong></td>
                                                <td>${customerUser.phone}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Date of Birth:</strong></td>
                                                <td>${customerUser.dob}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Gender:</strong></td>
                                                <td>${customerUser.gender}</td>
                                            </tr>
                                        </table>
                                    </div>
                                    <div class="col-md-6">
                                        <h6 class="text-primary">Account Details</h6>
                                        <table class="table table-borderless">
                                            <tr>
                                                <td><strong>User ID:</strong></td>
                                                <td>${customerUser.userId}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Account Type:</strong></td>
                                                <td>${customerUser.accountType}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Balance:</strong></td>
                                                <td><span class="text-success fw-bold">₹${customerUser.deposit}</span></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Status:</strong></td>
                                                <td>
                                                    <span class="badge status-badge bg-${customerUser.status == 'ACTIVE' ? 'success' : customerUser.status == 'PENDING' ? 'warning' : customerUser.status == 'FROZEN' ? 'danger' : 'secondary'}">
                                                        ${customerUser.status}
                                                    </span>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><strong>Role:</strong></td>
                                                <td>${customerUser.role}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Last Login:</strong></td>
                                                <td>${customerUser.lastLogin != null ? customerUser.lastLogin : 'Never'}</td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                
                                <div class="row mt-3">
                                    <div class="col-12">
                                        <h6 class="text-primary">Address</h6>
                                        <p class="mb-0">${customerUser.address}</p>
                                    </div>
                                </div>
                                
                                <div class="row mt-3">
                                    <div class="col-md-6">
                                        <h6 class="text-primary">KYC Information</h6>
                                        <table class="table table-borderless">
                                            <tr>
                                                <td><strong>Aadhaar:</strong></td>
                                                <td>${customerUser.aadhaar}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>PAN:</strong></td>
                                                <td>${customerUser.pan}</td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-md-4">
                        <div class="card detail-card mb-3">
                            <div class="card-header">
                                <h6 class="mb-0"><i class="fas fa-cog"></i> Quick Actions</h6>
                            </div>
                            <div class="card-body">
                                <div class="d-grid gap-2">
                                    <% if ("ACTIVE".equals(customerUser.getStatus())) { %>
                                        <button class="btn btn-warning btn-sm" onclick="freezeAccount(${customerUser.userId})">
                                            <i class="fas fa-pause"></i> Freeze Account
                                        </button>
                                    <% } else if ("FROZEN".equals(customerUser.getStatus())) { %>
                                        <button class="btn btn-success btn-sm" onclick="unfreezeAccount(${customerUser.userId})">
                                            <i class="fas fa-play"></i> Unfreeze Account
                                        </button>
                                    <% } %>
                                    
                                    <button class="btn btn-danger btn-sm" onclick="closeAccount(${customerUser.userId})">
                                        <i class="fas fa-times"></i> Close Account
                                    </button>
                                    
                                    <button class="btn btn-info btn-sm" onclick="sendNotification(${customerUser.userId})">
                                        <i class="fas fa-bell"></i> Send Notification
                                    </button>
                                    
                                    <button class="btn btn-primary btn-sm" onclick="editUser(${customerUser.userId})">
                                        <i class="fas fa-edit"></i> Edit User
                                    </button>
                                </div>
                            </div>
                        </div>
                        
                        <div class="card detail-card">
                            <div class="card-header">
                                <h6 class="mb-0"><i class="fas fa-chart-pie"></i> Account Summary</h6>
                            </div>
                            <div class="card-body">
                                <div class="text-center">
                                    <h4 class="text-success">₹${customerUser.deposit}</h4>
                                    <p class="text-muted">Current Balance</p>
                                </div>
                                <hr>
                                <div class="row text-center">
                                    <div class="col-6">
                                        <h6 class="text-primary">0</h6>
                                        <small class="text-muted">Total Deposits</small>
                                    </div>
                                    <div class="col-6">
                                        <h6 class="text-danger">0</h6>
                                        <small class="text-muted">Total Withdrawals</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Recent Transactions -->
                <div class="card detail-card">
                    <div class="card-header">
                        <h5 class="mb-0"><i class="fas fa-history"></i> Recent Transactions</h5>
                    </div>
                    <div class="card-body">
                        <% if (request.getAttribute("userTransactions") != null) { %>
                            <% List<Transaction> transactions = (List<Transaction>) request.getAttribute("userTransactions"); %>
                            <% if (!transactions.isEmpty()) { %>
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>Transaction ID</th>
                                                <th>Type</th>
                                                <th>Amount</th>
                                                <th>Description</th>
                                                <th>Date</th>
                                                <th>Status</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% for (Transaction txn : transactions) { %>
                                                <tr>
                                                    <td>${txn.txnId}</td>
                                                    <td>
                                                        <span class="badge bg-${txn.type == 'DEPOSIT' ? 'success' : txn.type == 'WITHDRAW' ? 'danger' : 'primary'}">
                                                            ${txn.type}
                                                        </span>
                                                    </td>
                                                    <td class="fw-bold">₹${txn.amount}</td>
                                                    <td>${txn.description}</td>
                                                    <td>${txn.txnDate}</td>
                                                    <td>
                                                        <span class="badge bg-success">Completed</span>
                                                    </td>
                                                </tr>
                                            <% } %>
                                        </tbody>
                                    </table>
                                </div>
                            <% } else { %>
                                <div class="text-center py-4">
                                    <i class="fas fa-inbox fa-3x text-muted mb-3"></i>
                                    <p class="text-muted">No transactions found for this user.</p>
                                </div>
                            <% } %>
                        <% } else { %>
                            <div class="text-center py-4">
                                <i class="fas fa-spinner fa-spin fa-3x text-muted mb-3"></i>
                                <p class="text-muted">Loading transactions...</p>
                            </div>
                        <% } %>
                    </div>
                </div>

            <% } else { %>
                <div class="alert alert-danger">
                    <i class="fas fa-exclamation-triangle"></i> User not found or access denied.
                </div>
            <% } %>

        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>

<script>
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

function sendNotification(userId) {
    const message = prompt('Enter notification message:');
    if (message) {
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = '<%=request.getContextPath()%>/admin/send-notification';
        
        const fields = [
            { name: 'userIds', value: userId },
            { name: 'message', value: message },
            { name: 'type', value: 'EMAIL' }
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

function editUser(userId) {
    // Redirect to edit user page or open modal
    alert('Edit user functionality will be implemented here');
}
</script>

</body>
</html>
