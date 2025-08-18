<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>User Registration</title>
  <style>
    body {
      font-family: 'Segoe UI', sans-serif;
      background: linear-gradient(to right, #6a11cb, #2575fc);
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
    }
    .container {
      background-color: #fff;
      padding: 35px 40px;
      border-radius: 12px;
      box-shadow: 0 8px 25px rgba(0,0,0,0.2);
      width: 400px;
    }
    h2 {
      text-align: center;
      margin-bottom: 20px;
      color: #333;
    }
    label {
      display: block;
      margin: 12px 0 5px;
      font-weight: bold;
    }
    input, select {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 6px;
      box-sizing: border-box;
    }
    .btn {
      width: 100%;
      padding: 12px;
      background: #2575fc;
      border: none;
      color: white;
      font-weight: bold;
      border-radius: 8px;
      margin-top: 20px;
      cursor: pointer;
    }
    .btn:hover {
      background: #1a5ed1;
    }
    .link {
      text-align: center;
      margin-top: 15px;
    }
    .link a {
      color: #2575fc;
      text-decoration: none;
    }
    .link a:hover {
      text-decoration: underline;
    }
    .error {
      color: red;
      text-align: center;
      margin-bottom: 10px;
    }
  </style>
</head>
<body>
  <div class="container">
    <h2>Register</h2>

    
    <c:if test="${not empty errorMessage}">
      <p class="error">${errorMessage}</p>
    </c:if>

    <form action="register" method="post">
      <label for="username">Username</label>
      <input type="text" id="username" name="username" required>

      <label for="password">Password</label>
      <input type="password" id="password" name="password" required>

      <label for="email">Email</label>
      <input type="email" id="email" name="email" required>

     
      

      <label for="theme">Theme</label>
      <select id="theme" name="theme" required>
        <option value="light">Light</option>
        <option value="dark">Dark</option>
      </select>

      <button type="submit" class="btn">Register</button>
    </form>

    <div class="link">
      <p>Already have an account? <a href="login.jsp">Login here</a></p>
    </div>
  </div>
</body>
</html>
