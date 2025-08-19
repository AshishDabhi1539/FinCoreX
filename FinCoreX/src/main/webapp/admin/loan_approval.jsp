<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SecureBank - Loan Approval</title>
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
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-header bg-warning text-dark">
                        <h4><i class="fas fa-check-circle me-2"></i>Loan Approval Management</h4>
                    </div>
                    <div class="card-body">
                        <!-- Success Message -->
                        <c:if test="${param.success}">
                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                            <i class="fas fa-check-circle me-2"></i>
                            Action completed successfully!
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                        </div>
                        </c:if>

                        <!-- Filter Tabs -->
                        <ul class="nav nav-tabs mb-3" id="loanTabs" role="tablist">
                            <li class="nav-item" role="presentation">
                                <button class="nav-link active" id="pending-tab" data-bs-toggle="tab" data-bs-target="#pending" type="button">
                                    <i class="fas fa-clock me-2"></i>Pending (15)
                                </button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="approved-tab" data-bs-toggle="tab" data-bs-target="#approved" type="button">
                                    <i class="fas fa-check me-2"></i>Approved (45)
                                </button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="rejected-tab" data-bs-toggle="tab" data-bs-target="#rejected" type="button">
                                    <i class="fas fa-times me-2"></i>Rejected (12)
                                </button>
                            </li>
                        </ul>

                        <!-- Tab Content -->
                        <div class="tab-content" id="loanTabContent">
                            <div class="tab-pane fade show active" id="pending" role="tabpanel">
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead class="table-dark">
                                            <tr>
                                                <th>Loan ID</th>
                                                <th>Customer</th>
                                                <th>Type</th>
                                                <th>Amount</th>
                                                <th>Approved Date</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="l" items="${loans}">
                                                <c:if test="${l.approvalStatus == 'Approved'}">
                                                <tr>
                                                    <td>${l.loanId}</td>
                                                    <td>
                                                        <div>
                                                            <strong>${l.customerName}</strong><br>
                                                            <small class="text-muted">ID: ${l.userId}</small>
                                                        </div>
                                                    </td>
                                                    <td><span class="badge bg-info">${l.loanType}</span></td>
                                                    <td><strong>₹${l.amountApproved}</strong></td>
                                                    <td>${l.approvalDate}</td>
                                                    <td>
                                                        <form method="post" style="display:inline;">
                                                            <input type="hidden" name="loanId" value="${l.loanId}">
                                                            <button name="action" value="disburse" class="btn btn-primary btn-sm">
                                                                <i class="fas fa-money-bill-wave me-1"></i>Disburse
                                                            </button>
                                                        </form>
                                                    </td>
                                                </tr>
                                                </c:if>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            
                            <div class="tab-pane fade" id="rejected" role="tabpanel">
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead class="table-dark">
                                            <tr>
                                                <th>Loan ID</th>
                                                <th>Customer</th>
                                                <th>Type</th>
                                                <th>Amount</th>
                                                <th>Rejected Date</th>
                                                <th>Reason</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="l" items="${loans}">
                                                <c:if test="${l.approvalStatus == 'Rejected'}">
                                                <tr>
                                                    <td>${l.loanId}</td>
                                                    <td>
                                                        <div>
                                                            <strong>${l.customerName}</strong><br>
                                                            <small class="text-muted">ID: ${l.userId}</small>
                                                        </div>
                                                    </td>
                                                    <td><span class="badge bg-secondary">${l.loanType}</span></td>
                                                    <td>₹${l.amountRequested}</td>
                                                    <td>${l.rejectionDate}</td>
                                                    <td><small class="text-muted">Insufficient credit history</small></td>
                                                </tr>
                                                </c:if>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="text-center mt-3">
                    <a href="admin/dashboard.jsp" class="btn btn-secondary">
                        <i class="fas fa-arrow-left me-2"></i>Back to Dashboard
                    </a>
                </div>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>