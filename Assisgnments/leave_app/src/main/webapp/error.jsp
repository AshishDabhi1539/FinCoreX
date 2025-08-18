<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8"/>
  <title>Error</title>
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background: linear-gradient(135deg, #1f1c2c, #928dab);
      margin: 0;
      min-height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 40px 20px;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
    }
    .container {
      background: rgba(255, 255, 255, 0.05);
      backdrop-filter: blur(12px);
      padding: 40px 50px;
      border-radius: 16px;
      box-shadow: 0 8px 32px rgba(0,0,0,0.4);
      max-width: 420px;
      width: 90%;
      text-align: center;
      border: 1px solid rgba(255,255,255,0.2);
    }
    h2 {
      color: #ff4d4d;
      margin-bottom: 20px;
      font-weight: 700;
      font-size: 28px;
      text-shadow: 0 2px 8px rgba(255, 77, 77, 0.4);
    }
    p {
      font-size: 16px;
      margin-bottom: 28px;
      color: #f0f0f0;
    }
    a {
      color: #fff;
      font-weight: 600;
      font-size: 15px;
      text-decoration: none;
      padding: 12px 28px;
      border-radius: 30px;
      background: linear-gradient(135deg, #00c6ff, #0072ff);
      border: none;
      transition: background 0.3s ease, transform 0.2s ease;
      display: inline-block;
    }
    a:hover {
      background: linear-gradient(135deg, #0072ff, #00c6ff);
      transform: translateY(-2px);
    }
    @media (max-width: 400px) {
      .container {
        padding: 30px 20px;
      }
    }
  </style>
</head>
<body>
  <div class="container">
    <h2>Error</h2>
    <p>An error occurred. Check server logs.</p>
    <a href="${pageContext.request.contextPath}/login">Back to Login</a>
  </div>
</body>
</html>
