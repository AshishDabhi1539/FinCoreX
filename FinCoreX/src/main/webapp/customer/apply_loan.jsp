<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SecureBank - Apply for Loan</title>
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
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <div class="card">
                    <div class="card-header bg-info text-white">
                        <h4><i class="fas fa-file-invoice me-2"></i>Loan Application</h4>
                    </div>
                    <div class="card-body">
                        <!-- Error/Success Messages -->
                        <c:if test="${not empty error}">
                        <div class="alert alert-danger" role="alert">
                            <i class="fas fa-exclamation-circle me-2"></i>
                            ${error}
                        </div>
                        </c:if>
                        
                        <c:if test="${param.success}">
                        <div class="alert alert-success" role="alert">
                            <i class="fas fa-check-circle me-2"></i>
                            Loan application submitted successfully! We'll review and get back to you.
                        </div>
                        </c:if>

                        

                        <!-- Loan Application Form -->
                        <form action="${pageContext.request.contextPath}/apply_loan" method="post" id="loanForm">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="loanType" class="form-label">
                                            <i class="fas fa-list me-2"></i>Loan Type
                                        </label>
                                        <select class="form-select" id="loanType" name="loanType" required>
                                            <option value="">Select Loan Type</option>
                                            <option value="Personal Loan">Personal Loan</option>
                                            <option value="Home Loan">Home Loan</option>
                                            <option value="Car Loan">Car Loan</option>
                                            <option value="Education Loan">Education Loan</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="amountRequested" class="form-label">
                                            <i class="fas fa-money-bill me-2"></i>Loan Amount (₹)
                                        </label>
                                        <input type="number" class="form-control" id="amountRequested" name="amountRequested" 
                                               min="10000" max="5000000" step="1000" required>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="termMonths" class="form-label">
                                            <i class="fas fa-calendar me-2"></i>Loan Term (Months)
                                        </label>
                                        <select class="form-select" id="termMonths" name="termMonths" required>
                                            <option value="">Select Term</option>
                                            <option value="12">12 months</option>
                                            <option value="24">24 months</option>
                                            <option value="36">36 months</option>
                                            <option value="48">48 months</option>
                                            <option value="60">60 months</option>
                                            <option value="120">120 months (10 years)</option>
                                            <option value="240">240 months (20 years)</option>
                                            <option value="360">360 months (30 years)</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="accountId" class="form-label">
                                            <i class="fas fa-credit-card me-2"></i>Repayment Account
                                        </label>
                                        <select class="form-select" id="accountId" name="accountId">
                                            <option value="">Select Account (Optional)</option>
                                            <c:forEach var="a" items="${accounts}">
                                                <option value="${a.accountId}">${a.accountType} - ${a.accountNumber}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="purpose" class="form-label">
                                    <i class="fas fa-comment me-2"></i>Purpose of Loan
                                </label>
                                <textarea class="form-control" id="purpose" name="purpose" rows="3" 
                                         placeholder="Briefly describe the purpose of this loan"></textarea>
                            </div>

                            <!-- EMI Calculator -->
                            <div class="card bg-light mb-3">
                                <div class="card-body">
                                    <h6><i class="fas fa-calculator me-2"></i>EMI Calculator</h6>
                                    <div class="row">
                                        <div class="col-md-3">
                                            <small class="text-muted">Principal Amount</small>
                                            <div id="emi-principal">₹0</div>
                                        </div>
                                        <div class="col-md-3">
                                            <small class="text-muted">Interest Rate</small>
                                            <div id="emi-rate">0%</div>
                                        </div>
                                        <div class="col-md-3">
                                            <small class="text-muted">Term</small>
                                            <div id="emi-term">0 months</div>
                                        </div>
                                        <div class="col-md-3">
                                            <small class="text-muted">Monthly EMI</small>
                                            <div id="emi-amount" class="fw-bold text-primary">₹0</div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="text-center">
                                <button type="submit" class="btn btn-success btn-lg me-3">
                                    <i class="fas fa-paper-plane me-2"></i>Submit Application
                                </button>
                                <a href="dashboard.jsp" class="btn btn-secondary">
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
    <script>
        // Interest rates for different loan types
        const interestRates = {
            'Personal Loan': 10.5,
            'Home Loan': 7.2,
            'Car Loan': 9.0,
            'Education Loan': 8.5
        };

        // EMI Calculator
        function calculateEMI() {
            const principal = parseFloat(document.getElementById('amountRequested').value) || 0;
            const loanType = document.getElementById('loanType').value;
            const term = parseInt(document.getElementById('termMonths').value) || 0;
            
            const rate = interestRates[loanType] || 0;
            const monthlyRate = rate / (12 * 100);
            
            document.getElementById('emi-principal').textContent = '₹' + principal.toLocaleString('en-IN');
            document.getElementById('emi-rate').textContent = rate + '%';
            document.getElementById('emi-term').textContent = term + ' months';
            
            if (principal > 0 && rate > 0 && term > 0) {
                const emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, term)) / 
                           (Math.pow(1 + monthlyRate, term) - 1);
                document.getElementById('emi-amount').textContent = '₹' + Math.round(emi).toLocaleString('en-IN');
            } else {
                document.getElementById('emi-amount').textContent = '₹0';
            }
        }

        // Event listeners
        document.getElementById('loanType').addEventListener('change', calculateEMI);
        document.getElementById('amountRequested').addEventListener('input', calculateEMI);
        document.getElementById('termMonths').addEventListener('change', calculateEMI);
    </script>
</body>
</html>