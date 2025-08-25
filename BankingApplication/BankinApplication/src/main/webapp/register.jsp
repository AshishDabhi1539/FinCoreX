<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Bank Registration</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(to right, #1e3c72, #2a5298);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background: #fff;
            border-radius: 12px;
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.2);
            padding: 30px 40px;
            width: 800px;
            max-height: 90vh;
            overflow-y: auto;
        }
        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #2a5298;
        }
        form {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px 30px;
        }
        .form-group {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-bottom: 6px;
            font-weight: 600;
        }
        input, select, textarea {
            padding: 10px;
            border-radius: 6px;
            border: 1px solid #ccc;
            font-size: 15px;
        }
        input:focus, select:focus, textarea:focus {
            border-color: #2a5298;
            outline: none;
            box-shadow: 0 0 4px rgba(42, 82, 152, 0.5);
        }
        .btn {
            grid-column: span 2;
            background: #2a66ff;
            color: #fff;
            padding: 14px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
            transition: 0.3s ease;
        }
        .btn:hover {
            background: #1746b6;
        }
        .link {
            grid-column: span 2;
            text-align: center;
            margin-top: 15px;
        }
        .link a {
            color: #2a66ff;
            text-decoration: none;
            font-size: 14px;
        }
        .link a:hover {
            text-decoration: underline;
        }
        .required { color: red; }

        /* Message Styles */
        .message {
            padding: 15px;
            margin: 20px 0;
            border-radius: 8px;
            font-size: 14px;
            line-height: 1.5;
            text-align: left;
        }

        .error-message {
            background: rgba(239, 68, 68, 0.1);
            border: 1px solid rgba(239, 68, 68, 0.3);
            color: #dc2626;
        }

        .success-message {
            background: rgba(34, 197, 94, 0.1);
            border: 1px solid rgba(34, 197, 94, 0.3);
            color: #16a34a;
        }

        .info-message {
            background: rgba(59, 130, 246, 0.1);
            border: 1px solid rgba(59, 130, 246, 0.3);
            color: #2563eb;
        }

        .warning-message {
            background: rgba(245, 158, 11, 0.1);
            border: 1px solid rgba(245, 158, 11, 0.3);
            color: #d97706;
        }

        .error-list {
            margin: 0;
            padding-left: 20px;
        }

        .error-list li {
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>🏦 Bank Account Registration</h2>
        
        <!-- Display Messages -->
        <c:if test="${not empty error}">
            <div class="message error-message">
                ${error}
            </div>
        </c:if>
        
        <c:if test="${not empty success}">
            <div class="message success-message">
                ${success}
            </div>
        </c:if>
        
        <c:if test="${not empty message}">
            <div class="message info-message">
                ${message}
            </div>
        </c:if>
        
        <c:if test="${not empty errors}">
            <div class="message error-message">
                <strong>❌ Registration failed. Please correct the following errors:</strong>
                <ul class="error-list">
                    <c:forEach var="err" items="${errors}">
                        <li>${err}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/register" method="post" class="form-grid">

            <!-- Full Name -->
            <div class="form-group">
                <label for="fullName">Full Name <span class="required">*</span></label>
                <input type="text" id="fullName" name="fullName"
                       placeholder="Enter your full name"
                       required minlength="2" maxlength="50">
            </div>

            <!-- Username -->
            <div class="form-group">
                <label for="username">Username <span class="required">*</span></label>
                <input type="text" id="username" name="username"
                       placeholder="Choose a unique username"
                       required minlength="3" maxlength="20">
            </div>

            <!-- Email -->
            <div class="form-group">
                <label for="email">Email <span class="required">*</span></label>
                <input type="email" id="email" name="email"
                       placeholder="Enter your email address"
                       required>
            </div>

            <!-- Phone -->
            <div class="form-group">
                <label for="phone">Phone Number <span class="required">*</span></label>
                <input type="tel" id="phone" name="phone"
                       placeholder="10-digit mobile number"
                       pattern="[0-9]{10}" required>
            </div>

            <!-- Date of Birth -->
            <div class="form-group">
                <label for="dob">Date of Birth <span class="required">*</span></label>
                <input type="date" id="dob" name="dob"
                       required>
            </div>

            <!-- Gender -->
            <div class="form-group">
                <label for="gender">Gender <span class="required">*</span></label>
                <select id="gender" name="gender" required>
                    <option value="">-- Select Gender --</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select>
            </div>

            <!-- Address -->
            <div class="form-group" style="grid-column: span 2;">
                <label for="address">Address <span class="required">*</span></label>
                <textarea id="address" name="address" rows="3"
                          placeholder="Enter full address"
                          required minlength="10" maxlength="200"></textarea>
            </div>

            <!-- Aadhaar Number -->
            <div class="form-group">
                <label for="aadhaar">Aadhaar Number <span class="required">*</span></label>
                <input type="text" id="aadhaar" name="aadhaar"
                       placeholder="12-digit Aadhaar number"
                       pattern="[0-9]{12}" required>
            </div>

            <!-- PAN Number -->
            <div class="form-group">
                <label for="pan">PAN Number <span class="required">*</span></label>
                <input type="text" id="pan" name="pan"
                       placeholder="ABCDE1234F"
                       pattern="[A-Z]{5}[0-9]{4}[A-Z]{1}" required>
            </div>

            <!-- Initial Deposit -->
            <div class="form-group">
                <label for="deposit">Initial Deposit (₹) <span class="required">*</span></label>
                <input type="number" id="deposit" name="deposit"
                       placeholder="Minimum ₹1000"
                       min="1000" required>
            </div>

            <!-- Account Type -->
            <div class="form-group">
                <label for="accountType">Account Type <span class="required">*</span></label>
                <select id="accountType" name="accountType" required>
                    <option value="">-- Select Account Type --</option>
                    <option value="Savings">Savings</option>
                    <option value="Current">Current</option>
                    <option value="Fixed Deposit">Fixed Deposit</option>
                </select>
            </div>

            <!-- Password -->
            <div class="form-group">
                <label for="password">Password <span class="required">*</span></label>
                <input type="password" id="password" name="password"
                       placeholder="8-20 chars, uppercase, lowercase, digit & special (@#$%^&+=!)"
                       required minlength="8" maxlength="20">
            </div>

            <!-- Confirm Password -->
            <div class="form-group">
                <label for="confirmPassword">Confirm Password <span class="required">*</span></label>
                <input type="password" id="confirmPassword" name="confirmPassword"
                       placeholder="Re-enter password"
                       required minlength="8" maxlength="20">
            </div>

            <!-- Submit -->
            <button type="submit" class="btn">Register</button>

            <!-- Link to Login -->
            <div class="link">
                <p>Already have an account? <a href="login.jsp">Login here</a></p>
            </div>
        </form>
    </div>

    <script>
        document.querySelector("form").addEventListener("submit", function(e) {
            const password = document.getElementById("password").value;
            const confirm = document.getElementById("confirmPassword").value;

            // Regex for strong password
            const strongPassword = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@#$%^&+=!]).{8,20}$/;

            if (!strongPassword.test(password)) {
                e.preventDefault();
                alert("Password must be 8-20 characters and include:\n" +
                      "- At least 1 uppercase letter\n" +
                      "- At least 1 lowercase letter\n" +
                      "- At least 1 digit\n" +
                      "- At least 1 special character (@#$%^&+=!)");
                return;
            }

            if (password !== confirm) {
                e.preventDefault();
                alert("Passwords do not match!");
            }
        });
    </script>
</body>
</html>
