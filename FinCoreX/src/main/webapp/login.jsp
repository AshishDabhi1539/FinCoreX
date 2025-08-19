<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SecureBank - Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link href="css/custom.css" rel="stylesheet">
</head>
<body class="login-bg">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-4">
                <div class="card login-card mt-5">
                    <div class="card-body">
                        <div class="text-center mb-4">
                            <i class="fas fa-university fa-3x text-primary mb-3"></i>
                            <h2 class="card-title">SecureBank</h2>
                            <p class="text-muted">Login to your account</p>
                        </div>

                        <!-- Error Message -->
                        <% if (request.getAttribute("error") != null) { %>
                        <div class="alert alert-danger" role="alert">
                            <i class="fas fa-exclamation-circle me-2"></i>
                            <%= request.getAttribute("error") %>
                        </div>
                        <% } %>

                        <!-- Success Message -->
                        <% if ("true".equals(request.getParameter("registered"))) { %>
                        <div class="alert alert-success" role="alert">
                            <i class="fas fa-check-circle me-2"></i>
                            Registration successful! Please login.
                        </div>
                        <% } %>

                        <form action="login" method="post">
                            <div class="mb-3">
                                <label for="username" class="form-label">
                                    <i class="fas fa-user me-2"></i>Username
                                </label>
                                <input type="text" class="form-control" id="username" name="username" required autofocus>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">
                                    <i class="fas fa-lock me-2"></i>Password
                                </label>
                                <input type="password" class="form-control" id="password" name="password" required>
                            </div>
                            <button type="submit" class="btn btn-primary w-100 mb-3">
                                <i class="fas fa-sign-in-alt me-2"></i>Login
                            </button>
                        </form>

                        <div class="text-center">
                            <p class="mb-0">Don't have an account? 
                                <a href="register.jsp" class="text-decoration-none">Register here</a>
                            </p>
                        </div>

                        <!-- Demo Credentials -->
                        <div class="demo-credentials mt-4">
                            <h6><i class="fas fa-info-circle me-2"></i>Demo Credentials:</h6>
                            <small class="text-muted">
                                <strong>Admin:</strong> admin / admin123<br>
                                <strong>Customer:</strong> john_doe / john123
                            </small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>