<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8"/>
  <title>Register</title>
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background: linear-gradient(120deg, #74ABE2, #5563DE);
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
      background: rgba(255, 255, 255, 0.15);
      backdrop-filter: blur(12px);
      padding: 40px 50px;
      border-radius: 16px;
      border: 1px solid rgba(255, 255, 255, 0.25);
      max-width: 380px;
      width: 90%;
      box-sizing: border-box;
      box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
      animation: fadeIn 0.7s ease;
    }

    h2 {
      margin-bottom: 28px;
      font-weight: 700;
      color: #fff;
      text-align: center;
    }

    input {
      width: 100%;
      padding: 14px 16px;
      margin-bottom: 20px;
      font-size: 16px;
      border-radius: 12px;
      border: none;
      background: rgba(255, 255, 255, 0.2);
      color: #fff;
      box-sizing: border-box;
      transition: all 0.3s ease;
    }

    input::placeholder {
      color: rgba(255, 255, 255, 0.7);
    }

    input:focus {
      background: rgba(255, 255, 255, 0.3);
      outline: none;
      box-shadow: 0 0 10px rgba(255,255,255,0.6);
    }

    button {
      width: 100%;
      padding: 14px 0;
      background: linear-gradient(135deg, #4facfe, #00f2fe);
      color: #fff;
      font-weight: bold;
      font-size: 17px;
      border: none;
      border-radius: 12px;
      cursor: pointer;
      transition: transform 0.3s ease, box-shadow 0.3s ease;
    }

    button:hover {
      transform: translateY(-3px);
      box-shadow: 0 8px 15px rgba(0,0,0,0.2);
    }

    .error {
      color: #ffbaba;
      background-color: rgba(255,0,0,0.15);
      border: 1px solid rgba(255,0,0,0.4);
      padding: 8px 12px;
      border-radius: 8px;
      margin-bottom: 20px;
      text-align: center;
    }

    a {
      display: block;
      margin-top: 24px;
      font-size: 14px;
      color: #e0e0e0;
      text-decoration: none;
      text-align: center;
      transition: color 0.3s ease;
    }

    a:hover {
      color: #fff;
      text-decoration: underline;
    }

    @keyframes fadeIn {
      from { opacity: 0; transform: translateY(-10px); }
      to { opacity: 1; transform: translateY(0); }
    }

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
    <h2>Employee Register</h2>
    <c:if test="${not empty error}">
      <div class="error">${error}</div>
    </c:if>
    <form action="${pageContext.request.contextPath}/register" method="post">
      <input type="text" name="name" placeholder="Full name" required autofocus />
      <input type="text" name="username" placeholder="Username" required/>
      <input type="password" name="password" placeholder="Password" required/>
      <button type="submit">Register</button>
    </form>
    <a href="${pageContext.request.contextPath}/login">Already have account? Login</a>
  </div>
</body>
</html>
