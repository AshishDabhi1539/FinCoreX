<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>500 - Internal Server Error</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
</head>
<body>
    <div class="container-fluid d-flex align-items-center justify-content-center" style="min-height: 100vh; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);">
        <div class="text-center text-white">
            <i class="fas fa-exclamation-circle fa-5x mb-4 text-danger"></i>
            <h1 class="display-4 mb-3">500 - Internal Server Error</h1>
            <p class="lead mb-4">Something went wrong on our end. Please try again later.</p>
            <a href="${pageContext.request.contextPath}/" class="btn btn-light btn-lg">
                <i class="fas fa-home me-2"></i>Go to Home
            </a>
        </div>
    </div>
</body>
</html>
