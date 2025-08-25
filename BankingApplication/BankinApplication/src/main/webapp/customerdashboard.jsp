<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.banking.model.User, com.banking.model.Account, com.banking.dao.AccountDao, com.banking.dao.TransactionDao, com.banking.model.Transaction, java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
        return;
    }
    AccountDao accountDao = new AccountDao();
    Account account = accountDao.getAccountsByUserId(user.getUserId()).stream().findFirst().orElse(null);
    if (account == null) {
        session.setAttribute("error", "No active account found. Please contact support.");
        response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
        return;
    }
    TransactionDao transactionDao = new TransactionDao();
    List<Transaction> transactions = transactionDao.getRecentTransactions(user.getUserId(), 6);
    request.setAttribute("transactions", transactions);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>💰 Customer Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        /* Existing styles unchanged */
        body { background: #f0f2f5; }
        .sidebar { min-height: 100vh; background-color: #1e3a8a; color: white; padding-top: 20px; }
        .sidebar a { color: white; text-decoration: none; display: block; padding: 12px 20px; border-radius: 8px; margin-bottom: 5px; transition: 0.3s; }
        .sidebar a:hover { background-color: #2563eb; }
        .card-header { background-color: #2563eb; color: white; }
        .btn-action { margin: 5px 5px 5px 0; }
        .emoji { font-size: 1.3rem; margin-right: 6px; }
        .message { padding: 15px; margin: 20px 0; border-radius: 8px; font-size: 14px; line-height: 1.5; text-align: left; border-left: 4px solid; }
        .error-message { background: rgba(239, 68, 68, 0.1); border-color: #dc2626; color: #dc2626; }
        .success-message { background: rgba(34, 197, 94, 0.1); border-color: #16a34a; color: #16a34a; }
        .info-message { background: rgba(59, 130, 246, 0.1); border-color: #2563eb; color: #2563eb; }
        .warning-message { background: rgba(245, 158, 11, 0.1); border-color: #d97706; color: #d97706; }
        .status-badge { padding: 4px 8px; border-radius: 4px; font-size: 12px; font-weight: bold; }
        .status-active { background: #dcfce7; color: #16a34a; }
        .status-pending { background: #fef3c7; color: #d97706; }
        .status-rejected { background: #fee2e2; color: #dc2626; }
        .status-suspended { background: #fef2f2; color: #dc2626; }
        .status-blocked { background: #fef2f2; color: #dc2626; }
        .status-inactive { background: #f3f4f6; color: #6b7280; }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">🏦 MyBank Dashboard</a>
            <div class="d-flex align-items-center">
                <span class="navbar-text me-3">👋 Welcome, <%= user.getFullName() %></span>
                <a href="<%=request.getContextPath()%>/logout" class="btn btn-light">Logout</a>
            </div>
        </div>
    </nav>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-2 sidebar">
                <h5 class="text-center mb-3">📋 Menu</h5>
                <ul>
                    <li><a href="accountDetails.jsp">Account Details</a></li>
                    <li><a href="#depositModal" data-bs-toggle="modal">Deposit</a></li>
                    <li><a href="#withdrawModal" data-bs-toggle="modal">Withdraw</a></li>
                    <li><a href="#transferModal" data-bs-toggle="modal">Transfer</a></li>
                    <li><a href="MiniStatementServlet">Mini Statement</a></li>
                    <li><a href="TransactionHistoryServlet">Full History</a></li>
                    <li><a href="updateProfile.jsp">Update Profile</a></li>
                    <li><a href="NotificationServlet">Notification Preferences</a></li>
                    <li><a href="logout.jsp">Logout</a></li>
                </ul>
            </div>
            <div class="col-md-10 p-4">
                <c:if test="${not empty sessionScope.error}">
                    <div class="message error-message">${sessionScope.error}</div>
                    <% session.removeAttribute("error"); %>
                </c:if>
                <c:if test="${not empty sessionScope.message}">
                    <div class="message success-message">${sessionScope.message}</div>
                    <% session.removeAttribute("message"); %>
                </c:if>
                <c:if test="${not empty sessionScope.info}">
                    <div class="message info-message">${sessionScope.info}</div>
                    <% session.removeAttribute("info"); %>
                </c:if>
                <c:if test="${not empty sessionScope.warning}">
                    <div class="message warning-message">${sessionScope.warning}</div>
                    <% session.removeAttribute("warning"); %>
                </c:if>
                <div class="card shadow-sm mb-4" id="account">
                    <div class="card-header">💳 Account Overview</div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-6">
                                <p><strong>Account Number:</strong> <%= account.getAccountNumber() %></p>
                                <p><strong>Account Type:</strong> <%= account.getAccountType() %></p>
                                <p><strong>Email:</strong> <%= user.getEmail() %></p>
                                <p><strong>Phone:</strong> <%= user.getPhone() %></p>
                            </div>
                            <div class="col-md-6">
                                <p><strong>Balance:</strong> ₹<%= String.format("%.2f", account.getBalance()) %></p>
                                <p><strong>Address:</strong> <%= user.getAddress() %></p>
                                <p><strong>Status:</strong> 
                                    <span class="status-badge status-<%= account.getStatus().toLowerCase() %>">
                                        <%= account.getStatus() %>
                                    </span>
                                </p>
                            </div>
                        </div>
                        <% if (!"ACTIVE".equalsIgnoreCase(account.getStatus())) { %>
                            <div class="alert alert-warning mt-3" role="alert">
                                <strong>⚠️ Account Status Notice:</strong>
                                <% if ("PENDING".equalsIgnoreCase(account.getStatus())) { %>
                                    Your account is pending admin approval.
                                <% } else if ("REJECTED".equalsIgnoreCase(account.getStatus())) { %>
                                    Your account registration was rejected.
                                <% } else if ("SUSPENDED".equalsIgnoreCase(account.getStatus())) { %>
                                    Your account has been suspended.
                                <% } else if ("BLOCKED".equalsIgnoreCase(account.getStatus())) { %>
                                    Your account has been blocked.
                                <% } else if ("INACTIVE".equalsIgnoreCase(account.getStatus())) { %>
                                    Your account is inactive.
                                <% } %>
                            </div>
                        <% } %>
                    </div>
                </div>
                <div class="card shadow-sm mb-4">
                    <div class="card-header">⚡ Quick Actions</div>
                    <div class="card-body">
                        <a href="#depositModal" data-bs-toggle="modal" class="btn btn-success btn-action">💰 Deposit</a>
                        <a href="#withdrawModal" data-bs-toggle="modal" class="btn btn-danger btn-action">💸 Withdraw</a>
                        <a href="#transferModal" data-bs-toggle="modal" class="btn btn-primary btn-action">🔄 Transfer</a>
                        <a href="#miniStatement" class="btn btn-secondary btn-action">📝 Mini Statement</a>
                        <a href="#fullStatement" class="btn btn-secondary btn-action">📄 Full History</a>
                        <a href="#updateProfile" class="btn btn-warning btn-action">✏️ Update Profile</a>
                        <a href="#notifications" class="btn btn-info btn-action">🔔 Notifications</a>
                    </div>
                </div>
                <div class="card shadow-sm mb-4">
                    <div class="card-header">📊 Transaction Summary</div>
                    <div class="card-body">
                        <canvas id="transactionChart" height="150"></canvas>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="depositModal" tabindex="-1" aria-labelledby="depositModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="<%=request.getContextPath()%>/customer/deposit" method="post">
                    <div class="modal-header">
                        <h5 class="modal-title" id="depositModalLabel">💰 Deposit Money</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="depositAmount" class="form-label">Amount (₹)</label>
                            <input type="number" id="depositAmount" name="amount" class="form-control" placeholder="Enter amount" min="1" max="100000" step="0.01" required>
                            <div class="form-text">Maximum deposit limit: ₹100,000</div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-success">Deposit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Withdraw and Transfer modals unchanged -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        const ctx = document.getElementById('transactionChart').getContext('2d');
        const transactionChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: [<c:forEach items="${transactions}" var="txn">'${txn.txnDate.toLocalDate().getMonthValue()}-${txn.txnDate.toLocalDate().getYear()}',</c:forEach>],
                datasets: [{
                    label: 'Balance Over Time (₹)',
                    data: [<c:forEach items="${transactions}" var="txn">${txn.amount},</c:forEach>],
                    backgroundColor: 'rgba(37, 99, 235, 0.2)',
                    borderColor: 'rgba(37, 99, 235, 1)',
                    borderWidth: 2,
                    fill: true,
                    tension: 0.3
                }]
            },
            options: {
                responsive: true,
                plugins: { legend: { display: true } }
            }
        });
    </script>
</body>
</html>