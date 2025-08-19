<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.banking.model.User"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"CUSTOMER".equalsIgnoreCase(user.getRole())) {
        response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>💰 Customer Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Chart.js -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body { background: #f0f2f5; }
        .sidebar {
            min-height: 100vh;
            background-color: #1e3a8a;
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
        .sidebar a:hover { background-color: #2563eb; }
        .card-header { background-color: #2563eb; color: white; }
        .btn-action { margin: 5px 5px 5px 0; }
        .emoji { font-size: 1.3rem; margin-right: 6px; }
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

        <!-- Sidebar -->
        <div class="col-md-2 sidebar">
            <h5 class="text-center mb-3">📋 Menu</h5>
            <a href="#account">💳 Account Details</a>
            <a href="#depositModal" data-bs-toggle="modal">💰 Deposit</a>
            <a href="#withdrawModal" data-bs-toggle="modal">💸 Withdraw</a>
            <a href="#transferModal" data-bs-toggle="modal">🔄 Transfer</a>
            <a href="#miniStatement">📝 Mini Statement</a>
            <a href="#fullStatement">📄 Full History</a>
            <a href="#updateProfile">✏️ Update Profile</a>
            <a href="#notifications">🔔 Manage Notifications</a>
        </div>

        <!-- Main Content -->
        <div class="col-md-10 p-4">

            <!-- Account Overview Card -->
            <div class="card shadow-sm mb-4" id="account">
                <div class="card-header">💳 Account Overview</div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-6">
                            <p><strong>Account Number:</strong> <%= user.getUserId() %></p>
                            <p><strong>Account Type:</strong> <%= user.getAccountType() %></p>
                            <p><strong>Email:</strong> <%= user.getEmail() %></p>
                            <p><strong>Phone:</strong> <%= user.getPhone() %></p>
                        </div>
                        <div class="col-md-6">
                            <p><strong>Balance:</strong> ₹<%= String.format("%.2f", user.getDeposit()) %></p>
                            <p><strong>Address:</strong> <%= user.getAddress() %></p>
                            <p><strong>Status:</strong> <span class="badge bg-success"><%= user.getStatus() %></span></p>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Quick Actions Card -->
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

            <!-- Transaction Graph -->
            <div class="card shadow-sm mb-4">
                <div class="card-header">📊 Transaction Summary</div>
                <div class="card-body">
                    <canvas id="transactionChart" height="150"></canvas>
                </div>
            </div>

        </div>
    </div>
</div>

<!-- Deposit Modal -->
<div class="modal fade" id="depositModal" tabindex="-1" aria-labelledby="depositModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="<%=request.getContextPath()%>/customer/deposit" method="post">
        <div class="modal-header">
          <h5 class="modal-title" id="depositModalLabel">💰 Deposit Money</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>
        <div class="modal-body">
          <input type="number" name="amount" class="form-control" placeholder="Amount ₹" min="1" required>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-success">Deposit</button>
        </div>
      </form>
    </div>
  </div>
</div>

<!-- Withdraw Modal -->
<div class="modal fade" id="withdrawModal" tabindex="-1" aria-labelledby="withdrawModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="<%=request.getContextPath()%>/customer/withdraw" method="post">
        <div class="modal-header">
          <h5 class="modal-title" id="withdrawModalLabel">💸 Withdraw Money</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>
        <div class="modal-body">
          <input type="number" name="amount" class="form-control" placeholder="Amount ₹" min="1" required>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-danger">Withdraw</button>
        </div>
      </form>
    </div>
  </div>
</div>

<!-- Transfer Modal -->
<div class="modal fade" id="transferModal" tabindex="-1" aria-labelledby="transferModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="<%=request.getContextPath()%>/customer/transfer" method="post">
        <div class="modal-header">
          <h5 class="modal-title" id="transferModalLabel">🔄 Transfer Money</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>
        <div class="modal-body">
          <input type="text" name="toAccount" class="form-control mb-2" placeholder="Recipient Account #" required>
          <input type="number" name="amount" class="form-control" placeholder="Amount ₹" min="1" required>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-primary">Transfer</button>
        </div>
      </form>
    </div>
  </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>

<script>
    // Sample chart data
    const ctx = document.getElementById('transactionChart').getContext('2d');
    const transactionChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'],
            datasets: [{
                label: 'Balance Over Months (₹)',
                data: [<%=user.getDeposit()%>, 12000, 15000, 14000, 16000, 18000],
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
