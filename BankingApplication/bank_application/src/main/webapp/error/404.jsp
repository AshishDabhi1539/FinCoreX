<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>404 - Page Not Found</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
</head>
<body>
    <div class="container-fluid d-flex align-items-center justify-content-center" style="min-height: 100vh; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);">
        <div class="text-center text-white">
            <i class="fas fa-exclamation-triangle fa-5x mb-4 text-warning"></i>
            <h1 class="display-4 mb-3">404 - Page Not Found</h1>
            <p class="lead mb-4">The page you are looking for does not exist.</p>
            <a href="${pageContext.request.contextPath}/" class="btn btn-light btn-lg">
                <i class="fas fa-home me-2"></i>Go to Home
            </a>
        </div>
    </div>
</body>
</html>
