<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.banking.model.Transaction" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Transactions</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="bg-light">
<div class="container mt-5">
    <h3 class="mb-3">Your Transactions</h3>

    <c:if test="${empty transactions}">
        <div class="alert alert-warning">No transactions found.</div>
    </c:if>

    <c:if test="${not empty transactions}">
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Type</th>
                <th>Amount</th>
                <th>From Account</th>
                <th>To Account</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="tx" items="${transactions}">
                <tr>
                    <td>${tx.transactionId}</td>
                    <td>${tx.type}</td>
                    <td>₹ ${tx.amount}</td>
                    <td>${tx.fromUserId}</td>
                    <td>${tx.toUserId}</td>
                    <td>${tx.timestamp}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <a href="${pageContext.request.contextPath}/customer/dashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
</div>
</body>
</html>
