<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SecureBank - Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link href="../css/dashboard.css" rel="stylesheet">
</head>
<body>
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
                <i class="fas fa-university me-2"></i>SecureBank Admin
            </a>
            <div class="navbar-nav ms-auto">
                <span class="navbar-text me-3">
                    <i class="fas fa-user-shield me-2"></i>Welcome, ${user.fullName}
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
                    <div class="card-header bg-primary text-white">
                        <h5><i class="fas fa-tachometer-alt me-2"></i>Admin Panel</h5>
                    </div>
                    <div class="list-group list-group-flush">
                        <a href="${pageContext.request.contextPath}/manage_customers" class="list-group-item list-group-item-action">
                            <i class="fas fa-users me-2"></i>Manage Customers
                        </a>
                        <a href="${pageContext.request.contextPath}/loan_approval" class="list-group-item list-group-item-action">
                            <i class="fas fa-check-circle me-2"></i>Loan Approval
                        </a>
                        <a href="${pageContext.request.contextPath}/reports" class="list-group-item list-group-item-action">
                            <i class="fas fa-chart-bar me-2"></i>View Reports
                        </a>
                        <a href="${pageContext.request.contextPath}/manage_accounts" class="list-group-item list-group-item-action">
                            <i class="fas fa-credit-card me-2"></i>Manage Accounts
                        </a>
                    </div>
                </div>
            </div>

            <!-- Dashboard Content -->
            <div class="col-md-9">
                <div class="row"></div>

                <div class="card">
                    <div class="card-header">
                        <h5><i class="fas fa-history me-2"></i>Recent Activities</h5>
                    </div>
                    <div class="card-body text-muted">No data to display.</div>
                </div>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>