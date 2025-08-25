<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Bank Login</title>
    <style>
        body {
            margin: 0;
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #0f172a, #1e3a8a, #3b82f6);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            color: white;
        }

        /* Wrapper for animated glowing border */
        .login {
            position: relative;
            width: 380px;
            border-radius: 20px;
        }

        .login::before {
            content: "";
            position: absolute;
            inset: -3px;
            border-radius: 20px;
            background: linear-gradient(45deg, #ff00cc, #3333ff, #00ffcc, #ff9900, #ff00cc);
            background-size: 400% 400%;
            animation: rotating 8s linear infinite;
            z-index: -1;
            filter: blur(5px);
        }

        .login::after {
            content: "";
            position: absolute;
            inset: 0;
            border-radius: 18px;
            background: rgba(255, 255, 255, 0.05);
            backdrop-filter: blur(20px);
            z-index: -1;
        }

        @keyframes rotating {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        .login-container {
            position: relative;
            padding: 40px;
            text-align: center;
            z-index: 2;
        }

        .login-container h2 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #facc15;
        }

        .login-container input, .login-container select {
            width: 100%;
            padding: 12px;
            margin: 10px 0;
            border-radius: 8px;
            border: none;
            outline: none;
            font-size: 14px;
            background: rgba(255, 255, 255, 0.85);
        }

        .login-container input:focus, .login-container select:focus {
            box-shadow: 0 0 8px #3b82f6;
        }

        .login-container button {
            width: 100%;
            padding: 12px;
            margin-top: 15px;
            background: #2563eb;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: 0.3s;
        }

        .login-container button:hover {
            background: #1d4ed8;
            transform: scale(1.05);
        }

        .links {
            margin-top: 15px;
            font-size: 14px;
        }

        .links a {
            color: #facc15;
            text-decoration: none;
            transition: 0.3s;
        }

        .links a:hover {
            text-decoration: underline;
        }

        /* Message Styles */
        .message {
            padding: 12px;
            margin: 15px 0;
            border-radius: 8px;
            font-size: 14px;
            line-height: 1.4;
            text-align: left;
        }

        .error-message {
            background: rgba(239, 68, 68, 0.2);
            border: 1px solid rgba(239, 68, 68, 0.5);
            color: #fecaca;
        }

        .success-message {
            background: rgba(34, 197, 94, 0.2);
            border: 1px solid rgba(34, 197, 94, 0.5);
            color: #bbf7d0;
        }

        .info-message {
            background: rgba(59, 130, 246, 0.2);
            border: 1px solid rgba(59, 130, 246, 0.5);
            color: #bfdbfe;
        }

        .warning-message {
            background: rgba(245, 158, 11, 0.2);
            border: 1px solid rgba(245, 158, 11, 0.5);
            color: #fed7aa;
        }
    </style>
</head>
<body>
    <div class="${pageContext.request.contextPath}/login">
        <div class="login-container">
            <h2>🔒 Secure Login</h2>
            
            <!-- Display Messages -->
            <c:if test="${not empty error}">
                <div class="message error-message">
                    ${error}
                </div>
            </c:if>
            
            <c:if test="${not empty success}">
                <div class="message success-message">
                    ${success}
                </div>
            </c:if>
            
            <c:if test="${not empty logoutMessage}">
                <div class="message info-message">
                    ${logoutMessage}
                </div>
            </c:if>
            
            <c:if test="${not empty message}">
                <div class="message info-message">
                    ${message}
                </div>
            </c:if>
            
            <form action="/login" method="post">
                <input type="text" name="username" placeholder="Enter Username" required>
                <input type="password" name="password" placeholder="Enter Password" required>
                <button type="submit">Login</button>
            </form>
            <div class="links">
                <a href="register.jsp">New User? Register</a></p>
            </div>
        </div>
    </div>
</body>
</html>
