<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SecureBank - My Accounts</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link href="../css/dashboard.css" rel="stylesheet">
</head>
<body>
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-success">
        <div class="container-fluid">
            <a class="navbar-brand" href="dashboard.jsp">
                <i class="fas fa-university me-2"></i>SecureBank
            </a>
            <div class="navbar-nav ms-auto">
                <span class="navbar-text me-3">
                    <i class="fas fa-user me-2"></i>${user.fullName}
                </span>
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">
                    <i class="fas fa-sign-out-alt me-2"></i>Logout
                </a>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h4><i class="fas fa-credit-card me-2"></i>My Bank Accounts</h4>
                    </div>
                    <div class="card-body">
                        <!-- Account Cards -->
                        <div class="row">
                            <c:forEach var="account" items="${accounts}">
                            <div class="col-md-6 mb-4">
                                <div class="card account-card border-primary">
                                    <div class="card-header bg-gradient-primary text-white">
                                        <div class="d-flex justify-content-between">
                                            <span>
                                                <i class="fas fa-piggy-bank me-2"></i>
                                                ${account.accountType} Account
                                            </span>
                                            <span class="badge bg-light text-dark">${account.status}</span>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="account-balance">
                                            <h2 class="text-primary mb-3">₹${account.balance}</h2>
                                        </div>
                                        <p class="mb-2">
                                            <strong>Account Number:</strong> 
                                            <span class="text-muted">${account.accountNumber}</span>
                                        </p>
                                        <p class="mb-3">
                                            <strong>Opened:</strong> 
                                            <span class="text-muted">${account.createdAt}</span>
                                        </p>
                                        
                                        <div class="d-grid gap-2">
                                            <a href="${pageContext.request.contextPath}/account_statement?accountId=${account.accountId}" 
                                               class="btn btn-outline-primary btn-sm">
                                                <i class="fas fa-file-alt me-2"></i>View Statement
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </c:forEach>
                            
                            <!-- Default account if no accounts found -->
                            <c:if test="${empty accounts}">
                            <div class="col-md-6 mb-4">
                                <div class="card account-card border-primary">
                                    <div class="card-header bg-primary text-white">
                                        <div class="d-flex justify-content-between">
                                            <span>
                                                <i class="fas fa-piggy-bank me-2"></i>
                                                Savings Account
                                            </span>
                                            <span class="badge bg-light text-dark">Active</span>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="account-balance">
                                            <h2 class="text-primary mb-3">₹50,000.00</h2>
                                        </div>
                                        <p class="mb-2">
                                            <strong>Account Number:</strong> 
                                            <span class="text-muted">ACC1001001</span>
                                        </p>
                                        <p class="mb-3">
                                            <strong>Opened:</strong> 
                                            <span class="text-muted">Aug 15, 2025</span>
                                        </p>
                                        
                                        <div class="d-grid gap-2">
                                            <a href="${pageContext.request.contextPath}/account_statement" 
                                               class="btn btn-outline-primary btn-sm">
                                                <i class="fas fa-file-alt me-2"></i>View Statement
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </c:if>
                        </div>

                        <!-- Quick Actions -->
                        <div class="row mt-4">
                            <div class="col-12">
                                <div class="card bg-light">
                                    <div class="card-body">
                                        <h6 class="card-title">
                                            <i class="fas fa-bolt me-2"></i>Quick Actions
                                        </h6>
                                        <div class="row">
                                            <div class="col-md-3 mb-2">
                                                <a href="${pageContext.request.contextPath}/transfer_money" class="btn btn-success w-100">
                                                    <i class="fas fa-paper-plane me-2"></i>Transfer
                                                </a>
                                            </div>
                                            <div class="col-md-3 mb-2">
                                                <a href="${pageContext.request.contextPath}/apply_loan" class="btn btn-info w-100">
                                                    <i class="fas fa-plus me-2"></i>Apply Loan
                                                </a>
                                            </div>
                                            <div class="col-md-3 mb-2">
                                                <button class="btn btn-warning w-100" data-bs-toggle="modal" data-bs-target="#depositModal">
                                                    <i class="fas fa-plus-circle me-2"></i>Deposit
                                                </button>
                                            </div>
                                            <div class="col-md-3 mb-2">
                                                <button class="btn btn-danger w-100" data-bs-toggle="modal" data-bs-target="#withdrawModal">
                                                    <i class="fas fa-minus-circle me-2"></i>Withdraw
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="text-center mt-3">
                    <a href="dashboard.jsp" class="btn btn-secondary">
                        <i class="fas fa-arrow-left me-2"></i>Back to Dashboard
                    </a>
                </div>
            </div>
        </div>
    </div>

    <!-- Deposit Modal -->
    <div class="modal fade" id="depositModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">
                        <i class="fas fa-plus-circle me-2"></i>Deposit Money
                    </h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <form action="${pageContext.request.contextPath}/deposit" method="post">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="depositAccount" class="form-label">Select Account</label>
                            <select class="form-select" id="depositAccount" name="accountId" required>
                                <option value="1">Savings - ACC1001001</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="depositAmount" class="form-label">Amount (₹)</label>
                            <input type="number" class="form-control" id="depositAmount" name="amount" min="1" step="0.01" required>
                        </div>
                        <div class="mb-3">
                            <label for="depositDescription" class="form-label">Description</label>
                            <input type="text" class="form-control" id="depositDescription" name="description" placeholder="Optional">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-success">
                            <i class="fas fa-plus-circle me-2"></i>Deposit
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Withdraw Modal -->
    <div class="modal fade" id="withdrawModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">
                        <i class="fas fa-minus-circle me-2"></i>Withdraw Money
                    </h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <form action="${pageContext.request.contextPath}/withdraw" method="post">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="withdrawAccount" class="form-label">Select Account</label>
                            <select class="form-select" id="withdrawAccount" name="accountId" required>
                                <option value="1">Savings - ACC1001001 (₹50,000.00)</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="withdrawAmount" class="form-label">Amount (₹)</label>
                            <input type="number" class="form-control" id="withdrawAmount" name="amount" min="1" max="50000" step="0.01" required>
                            <div class="form-text">Available balance: ₹50,000.00</div>
                        </div>
                        <div class="mb-3">
                            <label for="withdrawDescription" class="form-label">Description</label>
                            <input type="text" class="form-control" id="withdrawDescription" name="description" placeholder="Optional">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-danger">
                            <i class="fas fa-minus-circle me-2"></i>Withdraw
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>