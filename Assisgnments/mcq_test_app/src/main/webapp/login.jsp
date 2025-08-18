<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <style>
    body {
      font-family: 'Segoe UI', sans-serif;
      background: linear-gradient(to right, #74ebd5, #acb6e5);
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
    }
    .container {
      background-color: #ffffff;
      padding: 35px 30px;
      border-radius: 12px;
      box-shadow: 0 8px 25px rgba(0, 0, 0, 0.2);
      width: 360px;
    }
    h2 { text-align: center; color: #333; margin-bottom: 25px; }
    input { width: 100%; padding: 12px 15px; margin-bottom: 15px; border: 1px solid #ccc; border-radius: 8px; }
    button { width: 100%; padding: 12px; background-color: #00aaff; color: white; border: none; border-radius: 8px; cursor: pointer; }
    button:hover { background-color: #007acc; }
    a { display: block; margin-top: 15px; text-align: center; color: #00aaff; }
    .error { color: #d8000c; background-color: #ffbaba; padding: 10px; border-radius: 5px; margin-bottom: 15px; font-size: 0.9rem; }
  </style>
</head>
<body>
  <div class="container">
    <h2>Login</h2>

    <!-- Show error message if login fails -->
    <c:if test="${not empty error}">
      <p class="error">${error}</p>
    </c:if>

    <form action="login" method="post">
      <input type="text" name="username" placeholder="Username" required />
      <input type="password" name="password" placeholder="Password" required />
      <button type="submit">Login</button>
    </form>
    <a href="register.jsp">Don't have an account? Register</a>
  </div>
</body>
</html>
