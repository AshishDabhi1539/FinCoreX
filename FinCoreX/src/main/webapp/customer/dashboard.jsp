<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SecureBank - Customer Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link href="../css/dashboard.css" rel="stylesheet">
</head>
<body>
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-success">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
                <i class="fas fa-university me-2"></i>SecureBank
            </a>
            <div class="navbar-nav ms-auto">
                <span class="navbar-text me-3">
                    <i class="fas fa-user me-2"></i>Welcome, ${user.fullName}
                </span>
                <a class="nav-link" href="${pageContext.request.contextPath}/logout" 
                   onclick="return confirm('Are you sure you want to logout?');">
                    <i class="fas fa-sign-out-alt me-2"></i>Logout
                </a>
            </div>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container-fluid mt-4">
        <div class="row">
            <!-- Sidebar -->
            <div class="col-md-3">
                <div class="card">
                    <div class="card-header bg-success text-white">
                        <h5><i class="fas fa-wallet me-2"></i>Banking Services</h5>
                    </div>
                    <div class="list-group list-group-flush">
                        <a href="${pageContext.request.contextPath}/view_accounts" class="list-group-item list-group-item-action">
                            <i class="fas fa-credit-card me-2"></i>My Accounts
                        </a>
                        <a href="${pageContext.request.contextPath}/apply_loan" class="list-group-item list-group-item-action">
                            <i class="fas fa-file-invoice me-2"></i>Apply for Loan
                        </a>
                        <a href="${pageContext.request.contextPath}/transfer_money" class="list-group-item list-group-item-action">
                            <i class="fas fa-exchange-alt me-2"></i>Transfer Money
                        </a>
                        <a href="${pageContext.request.contextPath}/transaction_history" class="list-group-item list-group-item-action">
                            <i class="fas fa-history me-2"></i>Transaction History
                        </a>
                        <a href="${pageContext.request.contextPath}/my_loans" class="list-group-item list-group-item-action">
                            <i class="fas fa-handshake me-2"></i>My Loans
                        </a>
                    </div>
                </div>
            </div>

            <!-- Dashboard Content -->
            <div class="col-md-9">
                <!-- Account Summary Cards -->
                <div class="row">
                    <div class="col-md-6 mb-4">
                        <div class="card border-primary">
                            <div class="card-header bg-primary text-white">
                                <h5><i class="fas fa-piggy-bank me-2"></i>Savings Account</h5>
                            </div>
                            <div class="card-body">
                                <h3 class="text-primary">₹50,000.00</h3>
                                <p class="text-muted mb-0">Account: ACC1001001</p>
                                <small class="text-success">
                                    <i class="fas fa-arrow-up me-1"></i>Last transaction: +₹5,000
                                </small>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-md-6 mb-4">
                        <div class="card border-info">
                            <div class="card-header bg-info text-white">
                                <h5><i class="fas fa-credit-card me-2"></i>Quick Actions</h5>
                            </div>
                            <div class="card-body">
                                <div class="d-grid gap-2">
                                    <a href="${pageContext.request.contextPath}/transfer_money" class="btn btn-outline-primary btn-sm">
                                        <i class="fas fa-paper-plane me-2"></i>Send Money
                                    </a>
                                    <a href="${pageContext.request.contextPath}/apply_loan" class="btn btn-outline-success btn-sm">
                                        <i class="fas fa-plus me-2"></i>Apply Loan
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Recent Transactions -->
                <div class="card">
                    <div class="card-header">
                        <h5><i class="fas fa-list me-2"></i>Recent Transactions</h5>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Date</th>
                                        <th>Description</th>
                                        <th>Type</th>
                                        <th>Amount</th>
                                        <th>Balance</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Aug 19, 2025</td>
                                        <td>Salary Credit</td>
                                        <td><span class="badge bg-success">Credit</span></td>
                                        <td class="text-success">+₹45,000</td>
                                        <td>₹50,000</td>
                                    </tr>
                                    <tr>
                                        <td>Aug 18, 2025</td>
                                        <td>ATM Withdrawal</td>
                                        <td><span class="badge bg-danger">Debit</span></td>
                                        <td class="text-danger">-₹2,000</td>
                                        <td>₹5,000</td>
                                    </tr>
                                    <tr>
                                        <td>Aug 17, 2025</td>
                                        <td>Account Opening</td>
                                        <td><span class="badge bg-info">Credit</span></td>
                                        <td class="text-success">+₹7,000</td>
                                        <td>₹7,000</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="text-center">
                            <a href="${pageContext.request.contextPath}/transaction_history" class="btn btn-outline-primary">
                                View All Transactions
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
