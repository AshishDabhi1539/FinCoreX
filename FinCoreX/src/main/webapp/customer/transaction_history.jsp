<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SecureBank - Transactions</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link href="../css/dashboard.css" rel="stylesheet">
    </head>
<body>
    <div class="container mt-4">
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h5><i class="fas fa-history me-2"></i>Transaction History</h5>
            </div>
            <div class="card-body">
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Date</th>
                                <th>Type</th>
                                <th>From</th>
                                <th>To</th>
                                <th>Amount</th>
                                <th>Description</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="t" items="${transactions}">
                                <tr>
                                    <td>${t.transactionDate}</td>
                                    <td>${t.transactionType}</td>
                                    <td>${t.fromAccountId}</td>
                                    <td>${t.toAccountId}</td>
                                    <td>₹${t.amount}</td>
                                    <td>${t.description}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="text-center mt-3">
                    <a href="dashboard.jsp" class="btn btn-secondary">
                        <i class="fas fa-arrow-left me-2"></i>Back
                    </a>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

