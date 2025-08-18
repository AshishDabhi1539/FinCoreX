<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8"/>
  <title>Login</title>
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background: #f5f7fa;
      color: #333;
      margin: 0;
      padding-top: 80px;
      min-height: 100vh;
      display: flex;
      justify-content: center;
      align-items: flex-start;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
    }
    .card {
      background: #ffffff;
      padding: 40px 50px;
      border-radius: 14px;
      box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
      max-width: 380px;
      width: 90%;
      box-sizing: border-box;
      border: 1px solid #ddd;
      transition: transform 0.3s ease, box-shadow 0.3s ease;
    }
    .card:hover {
      transform: translateY(-6px);
      box-shadow: 0 10px 30px rgba(0,0,0,0.15);
      border-color: #007BFF;
    }
    h2 {
      margin-bottom: 28px;
      font-weight: 700;
      color: #007BFF;
      user-select: none;
    }
    input {
      width: 100%;
      padding: 14px 16px;
      margin-bottom: 22px;
      font-size: 16px;
      border-radius: 10px;
      border: 1.5px solid #ccc;
      background-color: #fafafa;
      color: #333;
      box-sizing: border-box;
      transition: border-color 0.3s ease, background-color 0.3s ease;
    }
    input::placeholder {
      color: #999;
    }
    input:focus {
      border-color: #007BFF;
      background-color: #fff;
      outline: none;
      box-shadow: 0 0 8px #007BFF88;
    }
    button {
      width: 100%;
      padding: 14px 0;
      background: linear-gradient(135deg, #007BFF, #0056b3);
      color: #fff;
      font-weight: 700;
      font-size: 17px;
      border: none;
      border-radius: 10px;
      cursor: pointer;
      transition: background 0.4s ease, box-shadow 0.3s ease;
      user-select: none;
    }
    button:hover {
      background: linear-gradient(135deg, #0056b3, #003f7f);
      box-shadow: 0 0 15px #0056b3;
    }
    .error {
      color: #dc3545;
      font-weight: 700;
      margin-bottom: 24px;
      user-select: none;
    }
    a {
      display: block;
      margin-top: 24px;
      font-size: 14px;
      color: #007BFF;
      text-decoration: none;
      transition: color 0.3s ease;
      user-select: none;
    }
    a:hover {
      color: #0056b3;
      text-decoration: underline;
    }

    /* Responsive */
    @media (max-width: 400px) {
      .card {
        padding: 30px 25px;
      }
      input, button {
        font-size: 15px;
        padding: 12px 14px;
      }
    }
  </style>
</head>
<body>
  <div class="card">
    <h2>Employee Login</h2>
    <c:if test="${not empty error}">
      <div class="error">${error}</div>
    </c:if>
    <form action="${pageContext.request.contextPath}/login" method="post">
      <input type="text" name="username" placeholder="Username" required autofocus />
      <input type="password" name="password" placeholder="Password" required/>
      <button type="submit">Login</button>
    </form>
    <a href="${pageContext.request.contextPath}/register">Register new user</a>
  </div>
</body>
</html>