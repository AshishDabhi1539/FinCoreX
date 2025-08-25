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
    <title>💳 All Transactions - Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        body { background: #f8f9fa; }
        .card { border: none; box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075); }
        .card-header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; }
        .transaction-deposit { color: #28a745; font-weight: bold; }
        .transaction-withdraw { color: #dc3545; font-weight: bold; }
        .transaction-transfer { color: #007bff; font-weight: bold; }
        .table-responsive { border-radius: 8px; }
        .filter-box { background: white; padding: 20px; border-radius: 8px; margin-bottom: 20px; }
        .stats-card { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; }
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
                <a class="nav-link text-white" href="customers.jsp">👥 All Customers</a>
                <a class="nav-link text-white active" href="transactions.jsp">💳 Transactions</a>
                <a class="nav-link text-white" href="reports.jsp">📈 Reports</a>
                <a class="nav-link text-white" href="../logout">🚪 Logout</a>
            </nav>
        </div>

        <!-- Main Content -->
        <div class="col-md-10 p-4">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2><i class="fas fa-exchange-alt"></i> All Transactions</h2>
                <span class="badge bg-primary fs-6">${transactions.size()} Total Transactions</span>
            </div>

            <!-- Transaction Statistics -->
            <div class="row mb-4">
                <div class="col-md-3">
                    <div class="card stats-card">
                        <div class="card-body text-center">
                            <h4><i class="fas fa-arrow-up"></i> Total Deposits</h4>
                            <h3>₹${totalDeposits}</h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card stats-card">
                        <div class="card-body text-center">
                            <h4><i class="fas fa-arrow-down"></i> Total Withdrawals</h4>
                            <h3>₹${totalWithdrawals}</h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card stats-card">
                        <div class="card-body text-center">
                            <h4><i class="fas fa-exchange-alt"></i> Total Transfers</h4>
                            <h3>₹${totalTransfers}</h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card stats-card">
                        <div class="card-body text-center">
                            <h4><i class="fas fa-chart-line"></i> Net Flow</h4>
                            <h3>₹${totalDeposits - totalWithdrawals}</h3>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Filter and Search -->
            <div class="filter-box">
                <div class="row">
                    <div class="col-md-3">
                        <input type="text" id="searchInput" class="form-control" placeholder="Search by user ID or description...">
                    </div>
                    <div class="col-md-2">
                        <select id="typeFilter" class="form-select">
                            <option value="">All Types</option>
                            <option value="DEPOSIT">Deposits</option>
                            <option value="WITHDRAW">Withdrawals</option>
                            <option value="TRANSFER_DEBIT">Transfer Out</option>
                            <option value="TRANSFER_CREDIT">Transfer In</option>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <input type="date" id="startDate" class="form-control">
                    </div>
                    <div class="col-md-2">
                        <input type="date" id="endDate" class="form-control">
                    </div>
                    <div class="col-md-2">
                        <input type="number" id="amountFilter" class="form-control" placeholder="Min Amount">
                    </div>
                    <div class="col-md-1">
                        <button class="btn btn-primary w-100" onclick="exportTransactions()">
                            <i class="fas fa-download"></i>
                        </button>
                    </div>
                </div>
            </div>

            <!-- Transactions Table -->
            <div class="card">
                <div class="card-header">
                    <h5 class="mb-0"><i class="fas fa-table"></i> Transaction Details</h5>
                </div>
                <div class="card-body p-0">
                    <div class="table-responsive">
                        <table class="table table-hover mb-0" id="transactionsTable">
                            <thead class="table-dark">
                                <tr>
                                    <th>ID</th>
                                    <th>User ID</th>
                                    <th>Type</th>
                                    <th>Amount</th>
                                    <th>Description</th>
                                    <th>Date</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="transaction" items="${transactions}">
                                    <tr>
                                        <td>${transaction.txnId}</td>
                                        <td>
                                            <strong>${transaction.userId}</strong>
                                            <button class="btn btn-sm btn-link" onclick="viewUser(${transaction.userId})">
                                                <i class="fas fa-user"></i>
                                            </button>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${transaction.type == 'DEPOSIT'}">
                                                    <span class="transaction-deposit">
                                                        <i class="fas fa-arrow-up"></i> ${transaction.type}
                                                    </span>
                                                </c:when>
                                                <c:when test="${transaction.type == 'WITHDRAW'}">
                                                    <span class="transaction-withdraw">
                                                        <i class="fas fa-arrow-down"></i> ${transaction.type}
                                                    </span>
                                                </c:when>
                                                <c:when test="${transaction.type == 'TRANSFER_DEBIT'}">
                                                    <span class="transaction-transfer">
                                                        <i class="fas fa-arrow-right"></i> Transfer Out
                                                    </span>
                                                </c:when>
                                                <c:when test="${transaction.type == 'TRANSFER_CREDIT'}">
                                                    <span class="transaction-transfer">
                                                        <i class="fas fa-arrow-left"></i> Transfer In
                                                    </span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="text-muted">${transaction.type}</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <strong>₹${transaction.amount}</strong>
                                        </td>
                                        <td>${transaction.description}</td>
                                        <td>
                                            <small>${transaction.txnDate}</small>
                                        </td>
                                        <td>
                                            <div class="btn-group" role="group">
                                                <button class="btn btn-sm btn-outline-primary" 
                                                        onclick="viewTransaction(${transaction.txnId})" 
                                                        title="View Details">
                                                    <i class="fas fa-eye"></i>
                                                </button>
                                                <button class="btn btn-sm btn-outline-info" 
                                                        onclick="viewUserTransactions(${transaction.userId})" 
                                                        title="User Transactions">
                                                    <i class="fas fa-list"></i>
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

            <!-- Pagination -->
            <nav aria-label="Transaction pagination" class="mt-4">
                <ul class="pagination justify-content-center">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1">Previous</a>
                    </li>
                    <li class="page-item active"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item">
                        <a class="page-link" href="#">Next</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>

<!-- Transaction Details Modal -->
<div class="modal fade" id="transactionModal" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Transaction Details</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body" id="transactionModalBody">
                <!-- Transaction details will be loaded here -->
            </div>
        </div>
    </div>
</div>

<!-- User Transactions Modal -->
<div class="modal fade" id="userTransactionsModal" tabindex="-1">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">User Transaction History</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body" id="userTransactionsModalBody">
                <!-- User transactions will be loaded here -->
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<script>
// Filter functionality
document.getElementById('searchInput').addEventListener('keyup', function() {
    filterTransactions();
});

document.getElementById('typeFilter').addEventListener('change', function() {
    filterTransactions();
});

document.getElementById('startDate').addEventListener('change', function() {
    filterTransactions();
});

document.getElementById('endDate').addEventListener('change', function() {
    filterTransactions();
});

document.getElementById('amountFilter').addEventListener('keyup', function() {
    filterTransactions();
});

function filterTransactions() {
    const searchTerm = document.getElementById('searchInput').value.toLowerCase();
    const typeFilter = document.getElementById('typeFilter').value;
    const startDate = document.getElementById('startDate').value;
    const endDate = document.getElementById('endDate').value;
    const amountFilter = document.getElementById('amountFilter').value;
    
    const table = document.getElementById('transactionsTable');
    const rows = table.getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    
    for (let row of rows) {
        const userId = row.cells[1].textContent.toLowerCase();
        const description = row.cells[4].textContent.toLowerCase();
        const type = row.cells[2].textContent;
        const amount = parseFloat(row.cells[3].textContent.replace('₹', ''));
        const date = row.cells[5].textContent;
        
        const matchesSearch = userId.includes(searchTerm) || description.includes(searchTerm);
        const matchesType = !typeFilter || type.includes(typeFilter);
        const matchesAmount = !amountFilter || amount >= parseFloat(amountFilter);
        const matchesDate = (!startDate || date >= startDate) && (!endDate || date <= endDate);
        
        row.style.display = (matchesSearch && matchesType && matchesAmount && matchesDate) ? '' : 'none';
    }
}

function viewTransaction(txnId) {
    // Load transaction details via AJAX
    fetch(`<%=request.getContextPath()%>/admin/transaction/${txnId}`)
        .then(response => response.text())
        .then(html => {
            document.getElementById('transactionModalBody').innerHTML = html;
            new bootstrap.Modal(document.getElementById('transactionModal')).show();
        });
}

function viewUser(userId) {
    // Redirect to user details page
    window.location.href = `<%=request.getContextPath()%>/admin/customers.jsp?userId=${userId}`;
}

function viewUserTransactions(userId) {
    // Load user transactions via AJAX
    fetch(`<%=request.getContextPath()%>/admin/user-transactions/${userId}`)
        .then(response => response.text())
        .then(html => {
            document.getElementById('userTransactionsModalBody').innerHTML = html;
            new bootstrap.Modal(document.getElementById('userTransactionsModal')).show();
        });
}

function exportTransactions() {
    const startDate = document.getElementById('startDate').value;
    const endDate = document.getElementById('endDate').value;
    const type = document.getElementById('typeFilter').value;
    
    // Create export URL with filters
    let exportUrl = `<%=request.getContextPath()%>/admin/export-transactions?`;
    if (startDate) exportUrl += `startDate=${startDate}&`;
    if (endDate) exportUrl += `endDate=${endDate}&`;
    if (type) exportUrl += `type=${type}`;
    
    window.open(exportUrl, '_blank');
}
</script>

</body>
</html>
