<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.banking.model.User"%>
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
    <title>🔄 Pending Approvals - Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        body { background: #f8f9fa; }
        .card { border: none; box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075); }
        .card-header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; }
        .btn-approve { background: #28a745; border: none; }
        .btn-reject { background: #dc3545; border: none; }
        .status-pending { color: #ffc107; font-weight: bold; }
        .user-info { background: #f8f9fa; padding: 15px; border-radius: 8px; margin: 10px 0; }
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
                <a class="nav-link text-white active" href="pending.jsp">⏳ Pending Approvals</a>
                <a class="nav-link text-white" href="customers.jsp">👥 All Customers</a>
                <a class="nav-link text-white" href="transactions.jsp">💳 Transactions</a>
                <a class="nav-link text-white" href="reports.jsp">📈 Reports</a>
                <a class="nav-link text-white" href="../logout">🚪 Logout</a>
            </nav>
        </div>

        <!-- Main Content -->
        <div class="col-md-10 p-4">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2><i class="fas fa-clock"></i> Pending Approvals</h2>
                <span class="badge bg-warning text-dark fs-6">${pendingUsers.size()} Pending</span>
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

            <c:choose>
                <c:when test="${empty pendingUsers}">
                    <div class="card">
                        <div class="card-body text-center py-5">
                            <i class="fas fa-check-circle text-success" style="font-size: 4rem;"></i>
                            <h4 class="mt-3">No Pending Approvals</h4>
                            <p class="text-muted">All user registrations have been processed.</p>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="row">
                        <c:forEach var="pendingUser" items="${pendingUsers}">
                            <div class="col-md-6 mb-4">
                                <div class="card">
                                    <div class="card-header">
                                        <h5 class="mb-0">
                                            <i class="fas fa-user-clock"></i> 
                                            ${pendingUser.fullName}
                                        </h5>
                                    </div>
                                    <div class="card-body">
                                        <div class="user-info">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <p><strong>Username:</strong> ${pendingUser.username}</p>
                                                    <p><strong>Email:</strong> ${pendingUser.email}</p>
                                                    <p><strong>Phone:</strong> ${pendingUser.phone}</p>
                                                    <p><strong>Account Type:</strong> ${pendingUser.accountType}</p>
                                                </div>
                                                <div class="col-md-6">
                                                    <p><strong>Initial Deposit:</strong> ₹${pendingUser.deposit}</p>
                                                    <p><strong>Address:</strong> ${pendingUser.address}</p>
                                                    <p><strong>Aadhaar:</strong> ${pendingUser.aadhaar}</p>
                                                    <p><strong>PAN:</strong> ${pendingUser.pan}</p>
                                                </div>
                                            </div>
                                            <div class="row mt-3">
                                                <div class="col-md-6">
                                                    <p><strong>Date of Birth:</strong> ${pendingUser.dob}</p>
                                                    <p><strong>Gender:</strong> ${pendingUser.gender}</p>
                                                </div>
                                                <div class="col-md-6">
                                                    <p><strong>Status:</strong> 
                                                        <span class="status-pending">${pendingUser.status}</span>
                                                    </p>
                                                    <p><strong>User ID:</strong> ${pendingUser.userId}</p>
                                                </div>
                                            </div>
                                        </div>
                                        
                                        <div class="d-flex justify-content-between mt-3">
                                            <button class="btn btn-approve text-white" 
                                                    onclick="approveUser(${pendingUser.userId})">
                                                <i class="fas fa-check"></i> Approve
                                            </button>
                                            <button class="btn btn-reject text-white" 
                                                    onclick="rejectUser(${pendingUser.userId})">
                                                <i class="fas fa-times"></i> Reject
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<!-- Reject Modal -->
<div class="modal fade" id="rejectModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Reject User Registration</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <form action="<%=request.getContextPath()%>/admin/reject" method="post">
                <div class="modal-body">
                    <input type="hidden" id="rejectUserId" name="userId">
                    <div class="mb-3">
                        <label for="reason" class="form-label">Reason for Rejection:</label>
                        <textarea class="form-control" id="reason" name="reason" rows="3" required></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-danger">Reject User</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<script>
function approveUser(userId) {
    if (confirm('Are you sure you want to approve this user?')) {
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = '<%=request.getContextPath()%>/admin/approve';
        
        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'userId';
        input.value = userId;
        
        form.appendChild(input);
        document.body.appendChild(form);
        form.submit();
    }
}

function rejectUser(userId) {
    document.getElementById('rejectUserId').value = userId;
    new bootstrap.Modal(document.getElementById('rejectModal')).show();
}
</script>

</body>
</html>
