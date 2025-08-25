<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.banking.model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>🏦 Customer Banking Services</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        body { 
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
        }
        .main-container {
            background: white;
            border-radius: 20px;
            margin: 20px;
            box-shadow: 0 20px 40px rgba(0,0,0,0.1);
        }
        .service-card {
            background: white;
            border: none;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            margin-bottom: 20px;
        }
        .service-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 20px 40px rgba(0,0,0,0.15);
        }
        .service-icon {
            font-size: 3rem;
            margin-bottom: 15px;
            color: #667eea;
        }
        .navbar-brand {
            font-weight: bold;
            font-size: 1.5rem;
        }
        .status-badge {
            padding: 8px 16px;
            border-radius: 20px;
            font-size: 14px;
            font-weight: bold;
        }
        .status-active { background: #dcfce7; color: #16a34a; }
        .status-pending { background: #fef3c7; color: #d97706; }
        .status-rejected { background: #fee2e2; color: #dc2626; }
        .status-suspended { background: #fef2f2; color: #dc2626; }
        .status-blocked { background: #fef2f2; color: #dc2626; }
        .status-inactive { background: #f3f4f6; color: #6b7280; }
        
        .message {
            padding: 15px;
            margin: 20px 0;
            border-radius: 10px;
            font-size: 14px;
            line-height: 1.5;
            border-left: 4px solid;
        }
        .error-message {
            background: rgba(239, 68, 68, 0.1);
            border-color: #dc2626;
            color: #dc2626;
        }
        .success-message {
            background: rgba(34, 197, 94, 0.1);
            border-color: #16a34a;
            color: #16a34a;
        }
        .info-message {
            background: rgba(59, 130, 246, 0.1);
            border-color: #2563eb;
            color: #2563eb;
        }
        .warning-message {
            background: rgba(245, 158, 11, 0.1);
            border-color: #d97706;
            color: #d97706;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark" style="background: linear-gradient(135deg, #1e3a8a 0%, #3b82f6 100%);">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <i class="fas fa-university me-2"></i>MyBank
        </a>
        <div class="d-flex align-items-center">
            <span class="navbar-text me-3">
                <i class="fas fa-user-circle me-2"></i>Welcome, <%= user.getFullName() %>
            </span>
            <a href="<%=request.getContextPath()%>/logout" class="btn btn-light">
                <i class="fas fa-sign-out-alt me-2"></i>Logout
            </a>
        </div>
    </div>
</nav>

<div class="main-container">
    <div class="container-fluid p-4">
        
        <!-- Display Messages -->
        <c:if test="${not empty sessionScope.error}">
            <div class="message error-message">
                <i class="fas fa-exclamation-circle me-2"></i>${sessionScope.error}
            </div>
            <% session.removeAttribute("error"); %>
        </c:if>
        
        <c:if test="${not empty sessionScope.message}">
            <div class="message success-message">
                <i class="fas fa-check-circle me-2"></i>${sessionScope.message}
            </div>
            <% session.removeAttribute("message"); %>
        </c:if>
        
        <c:if test="${not empty sessionScope.info}">
            <div class="message info-message">
                <i class="fas fa-info-circle me-2"></i>${sessionScope.info}
            </div>
            <% session.removeAttribute("info"); %>
        </c:if>
        
        <c:if test="${not empty sessionScope.warning}">
            <div class="message warning-message">
                <i class="fas fa-exclamation-triangle me-2"></i>${sessionScope.warning}
            </div>
            <% session.removeAttribute("warning"); %>
        </c:if>

        <!-- Account Status Banner -->
        <div class="row mb-4">
            <div class="col-12">
                <div class="card service-card">
                    <div class="card-body text-center">
                        <h4><i class="fas fa-user-circle me-2"></i>Account Information</h4>
                        <div class="row">
                            <div class="col-md-3">
                                <strong>Account:</strong> <%= user.getUserId() %>
                            </div>
                            <div class="col-md-3">
                                <strong>Type:</strong> <%= user.getAccountType() %>
                            </div>
                            <div class="col-md-3">
                                <strong>Balance:</strong> ₹<%= String.format("%.2f", user.getDeposit()) %>
                            </div>
                            <div class="col-md-3">
                                <strong>Status:</strong> 
                                <span class="status-badge status-<%= user.getStatus().toLowerCase() %>">
                                    <%= user.getStatus() %>
                                </span>
                            </div>
                        </div>
                        
                        <% if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) { %>
                            <div class="alert alert-warning mt-3" role="alert">
                                <i class="fas fa-exclamation-triangle me-2"></i>
                                <strong>Account Status Notice:</strong>
                                <% if ("PENDING".equalsIgnoreCase(user.getStatus())) { %>
                                    Your account is pending admin approval. You will be able to perform transactions once approved.
                                <% } else if ("REJECTED".equalsIgnoreCase(user.getStatus())) { %>
                                    Your account registration was rejected. Please contact customer support for assistance.
                                <% } else if ("SUSPENDED".equalsIgnoreCase(user.getStatus())) { %>
                                    Your account has been suspended due to suspicious activity. Please contact customer support immediately.
                                <% } else if ("BLOCKED".equalsIgnoreCase(user.getStatus())) { %>
                                    Your account has been blocked. Please contact customer support to resolve this issue.
                                <% } else if ("INACTIVE".equalsIgnoreCase(user.getStatus())) { %>
                                    Your account is inactive due to prolonged inactivity. Please contact customer support to reactivate.
                                <% } %>
                            </div>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>

        <!-- Banking Services Grid -->
        <div class="row">
            <!-- Account Services -->
            <div class="col-md-6 col-lg-4">
                <div class="card service-card h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-university service-icon"></i>
                        <h5 class="card-title">Account Services</h5>
                        <p class="card-text">Manage your account details and view information</p>
                        <a href="accountDetails.jsp" class="btn btn-primary">
                            <i class="fas fa-eye me-2"></i>View Details
                        </a>
                    </div>
                </div>
            </div>

            <!-- Deposit -->
            <div class="col-md-6 col-lg-4">
                <div class="card service-card h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-plus-circle service-icon"></i>
                        <h5 class="card-title">Deposit Money</h5>
                        <p class="card-text">Add funds to your account securely</p>
                        <a href="deposit.jsp" class="btn btn-success">
                            <i class="fas fa-plus me-2"></i>Deposit
                        </a>
                    </div>
                </div>
            </div>

            <!-- Withdraw -->
            <div class="col-md-6 col-lg-4">
                <div class="card service-card h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-minus-circle service-icon"></i>
                        <h5 class="card-title">Withdraw Money</h5>
                        <p class="card-text">Withdraw funds from your account</p>
                        <a href="withdraw.jsp" class="btn btn-warning">
                            <i class="fas fa-minus me-2"></i>Withdraw
                        </a>
                    </div>
                </div>
            </div>

            <!-- Transfer -->
            <div class="col-md-6 col-lg-4">
                <div class="card service-card h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-exchange-alt service-icon"></i>
                        <h5 class="card-title">Transfer Money</h5>
                        <p class="card-text">Send money to other accounts</p>
                        <a href="transfer.jsp" class="btn btn-info">
                            <i class="fas fa-exchange-alt me-2"></i>Transfer
                        </a>
                    </div>
                </div>
            </div>

            <!-- Mini Statement -->
            <div class="col-md-6 col-lg-4">
                <div class="card service-card h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-receipt service-icon"></i>
                        <h5 class="card-title">Mini Statement</h5>
                        <p class="card-text">View recent transaction history</p>
                        <a href="<%=request.getContextPath()%>/customer/miniStatement" class="btn btn-secondary">
                            <i class="fas fa-list me-2"></i>View Statement
                        </a>
                    </div>
                </div>
            </div>

            <!-- Full History -->
            <div class="col-md-6 col-lg-4">
                <div class="card service-card h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-history service-icon"></i>
                        <h5 class="card-title">Transaction History</h5>
                        <p class="card-text">Complete transaction records</p>
                        <a href="<%=request.getContextPath()%>/customer/fullHistory" class="btn btn-dark">
                            <i class="fas fa-clock me-2"></i>Full History
                        </a>
                    </div>
                </div>
            </div>

            <!-- Profile Update -->
            <div class="col-md-6 col-lg-4">
                <div class="card service-card h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-user-edit service-icon"></i>
                        <h5 class="card-title">Update Profile</h5>
                        <p class="card-text">Modify your personal information</p>
                        <a href="updateProfile.jsp" class="btn btn-outline-primary">
                            <i class="fas fa-edit me-2"></i>Edit Profile
                        </a>
                    </div>
                </div>
            </div>

            <!-- Notification Preferences -->
            <div class="col-md-6 col-lg-4">
                <div class="card service-card h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-bell service-icon"></i>
                        <h5 class="card-title">Notifications</h5>
                        <p class="card-text">Manage your notification preferences</p>
                        <a href="notificationPreferences.jsp" class="btn btn-outline-success">
                            <i class="fas fa-cog me-2"></i>Preferences
                        </a>
                    </div>
                </div>
            </div>

            <!-- Back to Dashboard -->
            <div class="col-md-6 col-lg-4">
                <div class="card service-card h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-tachometer-alt service-icon"></i>
                        <h5 class="card-title">Dashboard</h5>
                        <p class="card-text">Return to main dashboard view</p>
                        <a href="customerdashboard.jsp" class="btn btn-outline-secondary">
                            <i class="fas fa-home me-2"></i>Dashboard
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

