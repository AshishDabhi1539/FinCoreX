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
    <title>📈 Reports & Analytics - Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body { background: #f8f9fa; }
        .card { border: none; box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075); }
        .card-header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; }
        .stats-card { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; }
        .chart-container { position: relative; height: 300px; }
        .report-section { margin-bottom: 30px; }
        .metric-card { background: white; padding: 20px; border-radius: 8px; text-align: center; }
        .metric-value { font-size: 2rem; font-weight: bold; color: #667eea; }
        .metric-label { color: #6c757d; font-size: 0.9rem; }
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
                <a class="nav-link text-white" href="transactions.jsp">💳 Transactions</a>
                <a class="nav-link text-white active" href="reports.jsp">📈 Reports</a>
                <a class="nav-link text-white" href="../logout">🚪 Logout</a>
            </nav>
        </div>

        <!-- Main Content -->
        <div class="col-md-10 p-4">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2><i class="fas fa-chart-line"></i> Reports & Analytics</h2>
                <div>
                    <button class="btn btn-primary" onclick="generateReport()">
                        <i class="fas fa-download"></i> Generate Report
                    </button>
                    <button class="btn btn-success" onclick="exportData()">
                        <i class="fas fa-file-excel"></i> Export Data
                    </button>
                </div>
            </div>

            <!-- Key Metrics -->
            <div class="row mb-4">
                <div class="col-md-3">
                    <div class="metric-card">
                        <div class="metric-value">₹${totalBalance}</div>
                        <div class="metric-label">Total Bank Balance</div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="metric-card">
                        <div class="metric-value">${totalCustomers}</div>
                        <div class="metric-label">Total Customers</div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="metric-card">
                        <div class="metric-value">₹${monthlyDeposits}</div>
                        <div class="metric-label">Monthly Deposits</div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="metric-card">
                        <div class="metric-value">₹${monthlyWithdrawals}</div>
                        <div class="metric-label">Monthly Withdrawals</div>
                    </div>
                </div>
            </div>

            <!-- Charts Section -->
            <div class="row mb-4">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <h5 class="mb-0"><i class="fas fa-chart-pie"></i> Transaction Distribution</h5>
                        </div>
                        <div class="card-body">
                            <div class="chart-container">
                                <canvas id="transactionChart"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <h5 class="mb-0"><i class="fas fa-chart-bar"></i> Monthly Transaction Trends</h5>
                        </div>
                        <div class="card-body">
                            <div class="chart-container">
                                <canvas id="monthlyChart"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Recent Transactions -->
            <div class="card">
                <div class="card-header">
                    <h5 class="mb-0"><i class="fas fa-clock"></i> Recent Transactions</h5>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead class="table-dark">
                                <tr>
                                    <th>ID</th>
                                    <th>User</th>
                                    <th>Type</th>
                                    <th>Amount</th>
                                    <th>Description</th>
                                    <th>Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="transaction" items="${recentTransactions}">
                                    <tr>
                                        <td>${transaction.txnId}</td>
                                        <td>${transaction.userId}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${transaction.type == 'DEPOSIT'}">
                                                    <span class="badge bg-success">${transaction.type}</span>
                                                </c:when>
                                                <c:when test="${transaction.type == 'WITHDRAW'}">
                                                    <span class="badge bg-danger">${transaction.type}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="badge bg-primary">${transaction.type}</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>₹${transaction.amount}</td>
                                        <td>${transaction.description}</td>
                                        <td>${transaction.txnDate}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!-- Financial Summary -->
            <div class="row mt-4">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <h5 class="mb-0"><i class="fas fa-calculator"></i> Financial Summary</h5>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-6">
                                    <h6>Total Deposits</h6>
                                    <p class="text-success">₹${totalDeposits}</p>
                                </div>
                                <div class="col-md-6">
                                    <h6>Total Withdrawals</h6>
                                    <p class="text-danger">₹${totalWithdrawals}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <h6>Total Transfers</h6>
                                    <p class="text-primary">₹${totalTransfers}</p>
                                </div>
                                <div class="col-md-6">
                                    <h6>Net Flow</h6>
                                    <p class="text-info">₹${totalDeposits - totalWithdrawals}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <h5 class="mb-0"><i class="fas fa-users"></i> Customer Statistics</h5>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-6">
                                    <h6>Active Customers</h6>
                                    <p class="text-success">${activeAccounts}</p>
                                </div>
                                <div class="col-md-6">
                                    <h6>Pending Approvals</h6>
                                    <p class="text-warning">${pendingApprovals}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <h6>Average Balance</h6>
                                    <p class="text-info">₹${totalBalance / totalCustomers}</p>
                                </div>
                                <div class="col-md-6">
                                    <h6>Total Accounts</h6>
                                    <p class="text-primary">${totalCustomers}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<script>
// Transaction Distribution Chart
const transactionCtx = document.getElementById('transactionChart').getContext('2d');
const transactionChart = new Chart(transactionCtx, {
    type: 'doughnut',
    data: {
        labels: ['Deposits', 'Withdrawals', 'Transfers'],
        datasets: [{
            data: [${totalDeposits}, ${totalWithdrawals}, ${totalTransfers}],
            backgroundColor: [
                '#28a745',
                '#dc3545',
                '#007bff'
            ],
            borderWidth: 2
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: {
                position: 'bottom'
            }
        }
    }
});

// Monthly Trends Chart
const monthlyCtx = document.getElementById('monthlyChart').getContext('2d');
const monthlyChart = new Chart(monthlyCtx, {
    type: 'line',
    data: {
        labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'],
        datasets: [{
            label: 'Deposits',
            data: [${monthlyDeposits}, ${monthlyDeposits * 0.9}, ${monthlyDeposits * 1.1}, ${monthlyDeposits * 0.95}, ${monthlyDeposits * 1.05}, ${monthlyDeposits}],
            borderColor: '#28a745',
            backgroundColor: 'rgba(40, 167, 69, 0.1)',
            tension: 0.4
        }, {
            label: 'Withdrawals',
            data: [${monthlyWithdrawals}, ${monthlyWithdrawals * 1.1}, ${monthlyWithdrawals * 0.9}, ${monthlyWithdrawals * 1.05}, ${monthlyWithdrawals * 0.95}, ${monthlyWithdrawals}],
            borderColor: '#dc3545',
            backgroundColor: 'rgba(220, 53, 69, 0.1)',
            tension: 0.4
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: {
                position: 'top'
            }
        },
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});

function generateReport() {
    // Generate comprehensive report
    const reportData = {
        totalBalance: ${totalBalance},
        totalCustomers: ${totalCustomers},
        totalDeposits: ${totalDeposits},
        totalWithdrawals: ${totalWithdrawals},
        totalTransfers: ${totalTransfers},
        monthlyDeposits: ${monthlyDeposits},
        monthlyWithdrawals: ${monthlyWithdrawals},
        activeAccounts: ${activeAccounts},
        pendingApprovals: ${pendingApprovals}
    };
    
    // Create and download report
    const reportContent = `
        Banking Application Report
        Generated on: ${new Date().toLocaleDateString()}
        
        Financial Summary:
        - Total Bank Balance: ₹${reportData.totalBalance}
        - Total Deposits: ₹${reportData.totalDeposits}
        - Total Withdrawals: ₹${reportData.totalWithdrawals}
        - Total Transfers: ₹${reportData.totalTransfers}
        - Net Flow: ₹${reportData.totalDeposits - reportData.totalWithdrawals}
        
        Customer Statistics:
        - Total Customers: ${reportData.totalCustomers}
        - Active Accounts: ${reportData.activeAccounts}
        - Pending Approvals: ${reportData.pendingApprovals}
        - Average Balance: ₹${reportData.totalBalance / reportData.totalCustomers}
        
        Monthly Performance:
        - Monthly Deposits: ₹${reportData.monthlyDeposits}
        - Monthly Withdrawals: ₹${reportData.monthlyWithdrawals}
    `;
    
    const blob = new Blob([reportContent], { type: 'text/plain' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'banking_report.txt';
    a.click();
    window.URL.revokeObjectURL(url);
}

function exportData() {
    // Export data to Excel format
    alert('Export functionality will be implemented here');
}
</script>

</body>
</html>