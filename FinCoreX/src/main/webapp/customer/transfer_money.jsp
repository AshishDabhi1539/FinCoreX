<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SecureBank - Transfer Money</title>
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
                <a class="nav-link" href="${pageContext.request.contextPath}/logout"
                   onclick="return confirm('Are you sure you want to logout?');">
                    <i class="fas fa-sign-out-alt me-2"></i>Logout
                </a>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <div class="card">
                    <div class="card-header bg-success text-white">
                        <h4><i class="fas fa-exchange-alt me-2"></i>Transfer Money</h4>
                    </div>
                    <div class="card-body">
                        <!-- Error/Success Messages -->
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger alert-dismissible fade show animated fadeIn" role="alert">
                                <i class="fas fa-exclamation-circle me-2"></i>${error}
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            </div>
                        </c:if>
                        <c:if test="${param.success}">
                            <div class="alert alert-success alert-dismissible fade show animated fadeIn" role="alert">
                                <i class="fas fa-check-circle me-2"></i>Transfer completed successfully!
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            </div>
                        </c:if>

                        <!-- Transfer Form -->
                        <form action="${pageContext.request.contextPath}/transfer_money" method="post" id="transferForm">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="sourceAccount" class="form-label">
                                            <i class="fas fa-credit-card me-2"></i>From Account
                                        </label>
                                        <select class="form-select animated fadeInUp" id="sourceAccount" name="sourceAccountId" required>
                                            <option value="">Select Account</option>
                                            <c:forEach var="account" items="${accounts}">
                                                <option value="${account.accountId}">
                                                    ${account.accountType} - ${account.accountNumber} (₹${account.balance})
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="recipient" class="form-label">
                                            <i class="fas fa-user-friends me-2"></i>Recipient
                                        </label>
                                        <select class="form-select animated fadeInUp" id="recipient" name="recipientId">
                                            <option value="">Select Beneficiary (Optional)</option>
                                            <c:forEach var="beneficiary" items="${beneficiaries}">
                                                <option value="${beneficiary.beneficiaryId}">
                                                    ${beneficiary.beneficiaryName} - ${beneficiary.accountNumber}
                                                </option>
                                            </c:forEach>
                                            <option value="other">Other Account</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="mb-3" id="otherAccountDetails" style="display: none;">
                                <label for="recipientAccountNumber" class="form-label">
                                    <i class="fas fa-credit-card me-2"></i>Recipient Account Number
                                </label>
                                <input type="text" class="form-control animated fadeInUp" id="recipientAccountNumber" name="recipientAccountNumber">
                                <label for="recipientIfscCode" class="form-label mt-2">
                                    <i class="fas fa-university me-2"></i>IFSC Code
                                </label>
                                <input type="text" class="form-control animated fadeInUp" id="recipientIfscCode" name="recipientIfscCode">
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="amount" class="form-label">
                                            <i class="fas fa-money-bill me-2"></i>Amount (₹)
                                        </label>
                                        <input type="number" class="form-control animated fadeInUp" id="amount" name="amount" min="1" step="0.01" required>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="description" class="form-label">
                                            <i class="fas fa-comment me-2"></i>Description
                                        </label>
                                        <input type="text" class="form-control animated fadeInUp" id="description" name="description" placeholder="Optional">
                                    </div>
                                </div>
                            </div>
                            <div class="text-center">
                                <button type="submit" class="btn btn-success btn-lg me-3 animated pulse">
                                    <i class="fas fa-paper-plane me-2"></i>Transfer Now
                                </button>
                                <a href="customer/dashboard.jsp" class="btn btn-secondary animated fadeIn">
                                    <i class="fas fa-times me-2"></i>Cancel
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="../js/admin.js"></script>
    <script>
        // Show/hide recipient account details based on selection
        document.getElementById('recipient').addEventListener('change', function() {
            const otherAccountDetails = document.getElementById('otherAccountDetails');
            if (this.value === 'other') {
                otherAccountDetails.style.display = 'block';
                document.getElementById('recipientAccountNumber').required = true;
                document.getElementById('recipientIfscCode').required = true;
            } else {
                otherAccountDetails.style.display = 'none';
                document.getElementById('recipientAccountNumber').required = false;
                document.getElementById('recipientIfscCode').required = false;
            }
        });

        // Form validation
        document.getElementById('transferForm').addEventListener('submit', function(e) {
            const amount = parseFloat(document.getElementById('amount').value);
            if (amount <= 0) {
                e.preventDefault();
                alert('Amount must be greater than zero.');
            }
        });
    </script>
</body>
</html>