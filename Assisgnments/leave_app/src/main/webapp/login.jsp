<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
  <style>
  /* Reset */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}

/* Body background */
body {
  background: linear-gradient(135deg, #3f87a6, #ebf8e1, #f69d3c);
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

/* Card container */
.card {
  background: #ffffff;
  padding: 30px 40px;
  border-radius: 12px;
  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.15);
  width: 350px;
  text-align: center;
  animation: fadeIn 0.6s ease;
}

.card h2 {
  margin-bottom: 20px;
  color: #333;
  font-size: 22px;
}

/* Labels */
label {
  display: block;
  margin: 12px 0 6px;
  text-align: left;
  font-size: 14px;
  color: #555;
}

/* Input fields */
input[type="text"],
input[type="password"] {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 8px;
  font-size: 14px;
  transition: border-color 0.3s;
}

input[type="text"]:focus,
input[type="password"]:focus {
  outline: none;
  border-color: #3f87a6;
  box-shadow: 0 0 5px rgba(63, 135, 166, 0.5);
}

/* Button */
button {
  margin-top: 20px;
  width: 100%;
  padding: 12px;
  background: #3f87a6;
  border: none;
  border-radius: 8px;
  color: #fff;
  font-size: 15px;
  cursor: pointer;
  transition: background 0.3s;
}

button:hover {
  background: #336b84;
}

/* Messages */
.msg {
  margin-top: 15px;
  color: #e74c3c;
  font-size: 13px;
}

.msg-ok {
  margin-top: 15px;
  color: #27ae60;
  font-size: 13px;
}

/* Animation */
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(-10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
  </style>
</head>
<body>
<div class="card">
  <h2>Leave Management – Login</h2>
  <form action="<%=request.getContextPath()%>/login" method="post">
    <label>Username</label>
    <input type="text" name="username" required>
    <label>Password</label>
    <input type="password" name="password" required>
    <button type="submit">Login</button>
  </form>
  <p class="msg"><%= request.getParameter("e")==null?"":request.getParameter("e") %></p>
  <p class="msg-ok"><%= request.getParameter("m")==null?"":request.getParameter("m") %></p>
  
  <p class="register-link">
    Don’t have an account?
    <a href="<%=request.getContextPath()%>/register.jsp">Register here</a>
  </p>
</div>
</body>
</html>
