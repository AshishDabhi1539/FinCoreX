<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>👥 All Customers - Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        body { background: #f8f9fa; }
        .card { border: none; box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075); }
        .card-header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; }
        .status-active { color: #28a745; font-weight: bold; }
        .status-pending { color: #ffc107; font-weight: bold; }
        .status-rejected { color: #dc3545; font-weight: bold; }
        .status-frozen { color: #6c757d; font-weight: bold; }
        .table-responsive { border-radius: 8px; }
        .search-box { background: white; padding: 20px; border-radius: 8px; margin-bottom: 20px; }
    </style>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <div class="col-md-2 bg-dark text-white min-vh-100 p-3">
            <h4 class="text-center mb-4">🏦 Admin Panel</h4>
            <nav class="nav flex-column">
                <a class="nav-link text-white" href="dashboard.jsp">📊 Dashboard</a>
                <a class="nav-link text-white" href="pending.jsp">⏳ Pending Approvals</a>
                <a class="nav-link text-white active" href="customers.jsp">👥 All Customers</a>
                <a class="nav-link text-white" href="transactions.jsp">💳 Transactions</a>
                <a class="nav-link text-white" href="reports.jsp">📈 Reports</a>
                <a class="nav-link text-white" href="../logout">🚪 Logout</a>
            </nav>
        </div>

        <!-- Main Content -->
        <div class="col-md-10 p-4">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2><i class="fas fa-users"></i> All Customers</h2>
                <span class="badge bg-primary fs-6">${customers.size()} Total Customers</span>
            </div>

            <!-- Messages -->
            <c:if test="${not empty message}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <i class="fas fa-check-circle"></i> ${message}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>
            
            <c:if test="${not empty error}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <i class="fas fa-exclamation-circle"></i> ${error}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>

            <!-- Search and Filter -->
            <div class="search-box">
                <div class="row">
                    <div class="col-md-4">
                        <input type="text" id="searchInput" class="form-control" placeholder="Search by name, email, or username...">
                    </div>
                    <div class="col-md-3">
                        <select id="statusFilter" class="form-select">
                            <option value="">All Status</option>
                            <option value="ACTIVE">Active</option>
                            <option value="PENDING">Pending</option>
                            <option value="REJECTED">Rejected</option>
                            <option value="FROZEN">Frozen</option>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <select id="accountTypeFilter" class="form-select">
                            <option value="">All Account Types</option>
                            <option value="Savings">Savings</option>
                            <option value="Current">Current</option>
                            <option value="Fixed Deposit">Fixed Deposit</option>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <button class="btn btn-primary w-100" onclick="exportCustomers()">
                            <i class="fas fa-download"></i> Export
                        </button>
                    </div>
                </div>
            </div>

            <!-- Customers Table -->
            <div class="card">
                <div class="card-header">
                    <h5 class="mb-0"><i class="fas fa-table"></i> Customer Details</h5>
                </div>
                <div class="card-body p-0">
                    <div class="table-responsive">
                        <table class="table table-hover mb-0" id="customersTable">
                            <thead class="table-dark">
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Username</th>
                                    <th>Email</th>
                                    <th>Phone</th>
                                    <th>Account Type</th>
                                    <th>Balance</th>
                                    <th>Status</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="customer" items="${customers}">
                                    <tr>
                                        <td>${customer.userId}</td>
                                        <td>
                                            <strong>${customer.fullName}</strong>
                                            <br><small class="text-muted">${customer.gender} | ${customer.dob}</small>
                                        </td>
                                        <td>${customer.username}</td>
                                        <td>${customer.email}</td>
                                        <td>${customer.phone}</td>
                                        <td>
                                            <span class="badge bg-info">${customer.accountType}</span>
                                        </td>
                                        <td>
                                            <strong>₹${customer.deposit}</strong>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${customer.status == 'ACTIVE'}">
                                                    <span class="status-active">${customer.status}</span>
                                                </c:when>
                                                <c:when test="${customer.status == 'PENDING'}">
                                                    <span class="status-pending">${customer.status}</span>
                                                </c:when>
                                                <c:when test="${customer.status == 'REJECTED'}">
                                                    <span class="status-rejected">${customer.status}</span>
                                                </c:when>
                                                <c:when test="${customer.status == 'FROZEN'}">
                                                    <span class="status-frozen">${customer.status}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="text-muted">${customer.status}</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <div class="btn-group" role="group">
                                                <button class="btn btn-sm btn-outline-primary" 
                                                        onclick="viewCustomer(${customer.userId})" 
                                                        title="View Details">
                                                    <i class="fas fa-eye"></i>
                                                </button>
                                                <button class="btn btn-sm btn-outline-success" 
                                                        onclick="editCustomer(${customer.userId})" 
                                                        title="Edit">
                                                    <i class="fas fa-edit"></i>
                                                </button>
                                                <c:if test="${customer.status == 'ACTIVE'}">
                                                    <button class="btn btn-sm btn-outline-warning" 
                                                            onclick="freezeAccount(${customer.userId})" 
                                                            title="Freeze Account">
                                                        <i class="fas fa-lock"></i>
                                                    </button>
                                                </c:if>
                                                <c:if test="${customer.status == 'FROZEN'}">
                                                    <button class="btn btn-sm btn-outline-info" 
                                                            onclick="unfreezeAccount(${customer.userId})" 
                                                            title="Unfreeze Account">
                                                        <i class="fas fa-unlock"></i>
                                                    </button>
                                                </c:if>
                                                <button class="btn btn-sm btn-outline-danger" 
                                                        onclick="deleteCustomer(${customer.userId})" 
                                                        title="Delete">
                                                    <i class="fas fa-trash"></i>
                                                </button>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Customer Details Modal -->
<div class="modal fade" id="customerModal" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Customer Details</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body" id="customerModalBody">
                <!-- Customer details will be loaded here -->
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<script>
// Search functionality
document.getElementById('searchInput').addEventListener('keyup', function() {
    filterTable();
});

document.getElementById('statusFilter').addEventListener('change', function() {
    filterTable();
});

document.getElementById('accountTypeFilter').addEventListener('change', function() {
    filterTable();
});

function filterTable() {
    const searchTerm = document.getElementById('searchInput').value.toLowerCase();
    const statusFilter = document.getElementById('statusFilter').value;
    const accountTypeFilter = document.getElementById('accountTypeFilter').value;
    
    const table = document.getElementById('customersTable');
    const rows = table.getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    
    for (let row of rows) {
        const name = row.cells[1].textContent.toLowerCase();
        const username = row.cells[2].textContent.toLowerCase();
        const email = row.cells[3].textContent.toLowerCase();
        const accountType = row.cells[5].textContent;
        const status = row.cells[7].textContent;
        
        const matchesSearch = name.includes(searchTerm) || 
                            username.includes(searchTerm) || 
                            email.includes(searchTerm);
        const matchesStatus = !statusFilter || status.includes(statusFilter);
        const matchesAccountType = !accountTypeFilter || accountType.includes(accountTypeFilter);
        
        row.style.display = (matchesSearch && matchesStatus && matchesAccountType) ? '' : 'none';
    }
}

function viewCustomer(userId) {
    // Load customer details via AJAX
    fetch(`<%=request.getContextPath()%>/admin/customer/${userId}`)
        .then(response => response.text())
        .then(html => {
            document.getElementById('customerModalBody').innerHTML = html;
            new bootstrap.Modal(document.getElementById('customerModal')).show();
        });
}

function editCustomer(userId) {
    // Redirect to edit page or show edit modal
    window.location.href = `<%=request.getContextPath()%>/admin/edit-customer?id=${userId}`;
}

function freezeAccount(userId) {
    if (confirm('Are you sure you want to freeze this account?')) {
        updateCustomerStatus(userId, 'FROZEN');
    }
}

function unfreezeAccount(userId) {
    if (confirm('Are you sure you want to unfreeze this account?')) {
        updateCustomerStatus(userId, 'ACTIVE');
    }
}

function deleteCustomer(userId) {
    if (confirm('Are you sure you want to delete this customer? This action cannot be undone.')) {
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = '<%=request.getContextPath()%>/admin/delete';
        
        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'userId';
        input.value = userId;
        
        form.appendChild(input);
        document.body.appendChild(form);
        form.submit();
    }
}

function updateCustomerStatus(userId, status) {
    const form = document.createElement('form');
    form.method = 'POST';
    form.action = '<%=request.getContextPath()%>/admin/update';
    
    const userIdInput = document.createElement('input');
    userIdInput.type = 'hidden';
    userIdInput.name = 'userId';
    userIdInput.value = userId;
    
    const statusInput = document.createElement('input');
    statusInput.type = 'hidden';
    statusInput.name = 'status';
    statusInput.value = status;
    
    form.appendChild(userIdInput);
    form.appendChild(statusInput);
    document.body.appendChild(form);
    form.submit();
}

function exportCustomers() {
    // Implement export functionality
    alert('Export functionality will be implemented here');
}
</script>

</body>
</html>