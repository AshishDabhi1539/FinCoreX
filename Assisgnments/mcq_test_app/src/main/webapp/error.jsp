<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Error</title>
  <style>
    body {
      font-family: 'Segoe UI', sans-serif;
      background: linear-gradient(to right, #ffdde1, #ee9ca7);
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
    }
    .container {
      background-color: #fff;
      padding: 30px 40px;
      border-radius: 12px;
      box-shadow: 0 8px 25px rgba(0, 0, 0, 0.2);
      width: 450px;
      text-align: center;
    }
    h1 { color: #d8000c; margin-bottom: 15px; }
    p { margin-bottom: 10px; color: #333; }
    .code { font-weight: bold; font-size: 1.1rem; color: #b00000; }
    a { display: inline-block; margin-top: 15px; color: #007acc; text-decoration: none; }
    a:hover { text-decoration: underline; }
  </style>
</head>
<body>
  <div class="container">
    <h1>Something went wrong</h1>

    <p class="code">Error Code: ${pageContext.errorData.statusCode}</p>
    <p>Message: ${pageContext.errorData.throwable != null ? pageContext.errorData.throwable.message : "Unknown error"}</p>
    <p>Request URI: ${pageContext.errorData.requestURI}</p>

    <a href="login.jsp">Go Back to Login</a>
  </div>
</body>
</html>
