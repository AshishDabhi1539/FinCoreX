<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quiz Completed</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background: #f5f7fa;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    .card {
        background: #fff;
        padding: 25px;
        border-radius: 12px;
        box-shadow: 0 4px 10px rgba(0,0,0,0.15);
        width: 600px;
    }
    h2 {
        color: #0d47a1;
        text-align: center;
    }
    .btn {
        background: #0d47a1;
        color: #fff;
        padding: 8px 15px;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        display: block;
        margin: 20px auto 0;
    }
    .review-item {
        margin-top: 15px;
        padding: 12px;
        border: 1px solid #ddd;
        border-radius: 8px;
        background: #fafafa;
    }
    .wrong {
        color: red;
    }
    .correct {
        color: green;
    }
</style>
</head>
<body>
<div class="card">
    <h2>Quiz Completed</h2>
    <p>Hello, <b>${username}</b>!</p>
    <p>Your Score: <b>${score}</b></p>
    <p>Time: ${time}</p>

    <c:if test="${not empty review}">
        <h3>Review</h3>
        <c:forEach var="item" items="${review}">
            <c:out value="${item}" escapeXml="false"/>
        </c:forEach>
    </c:if>

    <form action="logout" method="post">
        <button type="submit" class="btn">Logout</button>
    </form>
</div>
</body>
</html>
