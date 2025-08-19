<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SecureBank - Manage Customers</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link href="../css/dashboard.css" rel="stylesheet">
</head>
<body>
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="dashboard.jsp">
                <i class="fas fa-university me-2"></i>SecureBank Admin
            </a>
            <div class="navbar-nav ms-auto">
                <span class="navbar-text me-3">
                    <i class="fas fa-user-shield me-2"></i>${user.fullName}
                </span>
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">
                    <i class="fas fa-sign-out-alt me-2"></i>Logout
                </a>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="card">
            <div class="card-header bg-info text-white">
                <h4><i class="fas fa-users me-2"></i>Customer Management</h4>
            </div>
            <div class="card-body">
                <!-- Search Bar -->
                <div class="row mb-3">
                    <div class="col-md-6">
                        <div class="input-group">
                            <span class="input-group-text"><i class="fas fa-search"></i></span>
                            <input type="text" class="form-control" id="searchCustomer" placeholder="Search customers...">
                        </div>
                    </div>
                    <div class="col-md-6 text-end">
                        <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addCustomerModal">
                            <i class="fas fa-plus me-2"></i>Add New Customer
                        </button>
                    </div>
                </div>

                <!-- Customers Table -->
                <div class="table-responsive">
                    <table class="table table-hover" id="customersTable">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Full Name</th>
                                <th>Username</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Accounts</th>
                                <th>Created</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="c" items="${customers}">
                            <tr>
                                <td>${c.userId}</td>
                                <td>
                                    <div>
                                        <strong>${c.fullName}</strong><br>
                                        <small class="text-muted">Customer</small>
                                    </div>
                                </td>
                                <td>
                                    <span class="badge bg-secondary">${c.username}</span>
                                </td>
                                <td>
                                    <a href="mailto:${c.email}" class="text-decoration-none">
                                        <i class="fas fa-envelope me-1"></i>${c.email}
                                    </a>
                                </td>
                                <td>
                                    <a href="tel:${c.phone}" class="text-decoration-none">
                                        <i class="fas fa-phone me-1"></i>${c.phone}
                                    </a>
                                </td>
                                <td>
                                    <span class="badge bg-primary">${c.accountCount} accounts</span>
                                </td>
                                <td>
                                    <small>${c.createdAt}</small>
                                </td>
                                <td>
                                    <div class="btn-group" role="group">
                                        <button type="button" class="btn btn-outline-primary btn-sm" title="View Details">
                                            <i class="fas fa-eye"></i>
                                        </button>
                                        <button type="button" class="btn btn-outline-warning btn-sm" title="Edit">
                                            <i class="fas fa-edit"></i>
                                        </button>
                                        <button type="button" class="btn btn-outline-danger btn-sm" title="Deactivate">
                                            <i class="fas fa-ban"></i>
                                        </button>
                                    </div>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- Pagination -->
                <nav aria-label="Customer pagination">
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

        <div class="text-center mt-3">
            <a href="admin/dashboard.jsp" class="btn btn-secondary">
                <i class="fas fa-arrow-left me-2"></i>Back to Dashboard
            </a>
        </div>
    </div>

    <!-- Add Customer Modal -->
    <div class="modal fade" id="addCustomerModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">
                        <i class="fas fa-user-plus me-2"></i>Add New Customer
                    </h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <form action="${pageContext.request.contextPath}/admin_add_customer" method="post">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="customerFullName" class="form-label">Full Name</label>
                            <input type="text" class="form-control" id="customerFullName" name="fullName" required>
                        </div>
                        <div class="mb-3">
                            <label for="customerEmail" class="form-label">Email</label>
                            <input type="email" class="form-control" id="customerEmail" name="email" required>
                        </div>
                        <div class="mb-3">
                            <label for="customerPhone" class="form-label">Phone</label>
                            <input type="tel" class="form-control" id="customerPhone" name="phone" required>
                        </div>
                        <div class="mb-3">
                            <label for="customerUsername" class="form-label">Username</label>
                            <input type="text" class="form-control" id="customerUsername" name="username" required>
                        </div>
                        <div class="mb-3">
                            <label for="customerPassword" class="form-label">Initial Password</label>
                            <input type="password" class="form-control" id="customerPassword" name="password" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-success">
                            <i class="fas fa-plus me-2"></i>Add Customer
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="../js/admin.js"></script>
</body>
</html>