<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>500 - Server Error | MyBank</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #dc3545 0%, #c82333 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .error-container {
            text-align: center;
            color: white;
            max-width: 600px;
            padding: 2rem;
        }
        .error-code {
            font-size: 8rem;
            font-weight: bold;
            margin-bottom: 1rem;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.3);
        }
        .error-message {
            font-size: 1.5rem;
            margin-bottom: 2rem;
            opacity: 0.9;
        }
        .error-description {
            font-size: 1.1rem;
            margin-bottom: 2rem;
            opacity: 0.8;
        }
        .btn-home {
            background: rgba(255,255,255,0.2);
            border: 2px solid white;
            color: white;
            padding: 12px 30px;
            font-size: 1.1rem;
            transition: all 0.3s ease;
        }
        .btn-home:hover {
            background: white;
            color: #dc3545;
            transform: translateY(-2px);
        }
        .btn-refresh {
            background: rgba(255,255,255,0.1);
            border: 2px solid white;
            color: white;
            padding: 12px 30px;
            font-size: 1.1rem;
            transition: all 0.3s ease;
            margin-left: 10px;
        }
        .btn-refresh:hover {
            background: white;
            color: #dc3545;
            transform: translateY(-2px);
        }
        .server-icon {
            font-size: 4rem;
            margin-bottom: 2rem;
            opacity: 0.8;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <div class="server-icon">
            <i class="fas fa-server"></i>
        </div>
        <div class="error-code">500</div>
        <div class="error-message">Internal Server Error</div>
        <div class="error-description">
            We're experiencing technical difficulties. Our team has been notified and is working to resolve the issue.
        </div>
        <div class="mt-4">
            <a href="${pageContext.request.contextPath}/" class="btn btn-home">
                <i class="fas fa-home"></i> Back to Home
            </a>
            <button onclick="location.reload()" class="btn btn-refresh">
                <i class="fas fa-redo"></i> Try Again
            </button>
        </div>
        <div class="mt-3">
            <small style="opacity: 0.7;">
                If the problem persists, please contact our technical support team.
            </small>
        </div>
    </div>
</body>
</html>
