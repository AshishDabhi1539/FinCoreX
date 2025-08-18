<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Employee App</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: white;
            padding: 30px 40px;
            box-shadow: 0px 2px 10px rgba(0,0,0,0.1);
            border-radius: 8px;
            text-align: center;
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
        }

        button {
            padding: 10px 20px;
            background-color: #2d87f0;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background-color: #1a5fb4;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to Employee App</h1>
        <form action="${pageContext.request.contextPath}/employees" method="get">
            <button type="submit">Show Employees</button>
        </form>
    </div>
</body>
</html>
