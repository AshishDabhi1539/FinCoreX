<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Apply Leave</title>
<style>
    body {
        margin: 0;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background: linear-gradient(135deg, #1a0033, #33004d, #660066);
        color: #f0e6ff;
        min-height: 100vh;
        display: flex;
        flex-direction: column;
        align-items: center;
        padding: 20px;
    }
    h1 {
        color: #ffccff;
        text-align: center;
        margin-bottom: 20px;
        text-shadow: 0 0 6px rgba(255, 204, 255, 0.6);
    }
    form {
        background: rgba(255, 255, 255, 0.07);
        padding: 25px;
        border-radius: 15px;
        width: 350px;
        box-shadow: 0px 4px 20px rgba(0,0,0,0.4);
        backdrop-filter: blur(8px);
    }
    label {
        display: block;
        margin: 10px 0 5px;
        font-weight: 600;
        color: #f0dfff;
    }
    input, select, textarea {
        width: 100%;
        padding: 10px;
        border: 1px solid rgba(255,255,255,0.2);
        border-radius: 8px;
        margin-bottom: 15px;
        font-size: 14px;
        background: rgba(255, 255, 255, 0.15);
        color: white;
        transition: border-color 0.3s ease, box-shadow 0.3s ease;
    }
    input:focus, select:focus, textarea:focus {
        outline: none;
        border-color: #ff66cc;
        box-shadow: 0 0 6px rgba(255, 102, 204, 0.6);
    }
    option {
        background: #33004d;
        color: #fff;
    }
    textarea {
        resize: none;
    }
    button {
        background: linear-gradient(90deg, #a600a6, #ff33cc);
        color: white;
        padding: 12px;
        border: none;
        border-radius: 25px;
        cursor: pointer;
        font-weight: bold;
        width: 100%;
        font-size: 15px;
        transition: background 0.3s ease, transform 0.2s ease;
    }
    button:hover {
        background: linear-gradient(90deg, #ff33cc, #a600a6);
        transform: translateY(-2px);
        box-shadow: 0 4px 12px rgba(255, 51, 204, 0.4);
    }
    .error {
        color: #ff6666;
        margin-top: 12px;
        text-align: center;
        font-weight: bold;
        text-shadow: 0 0 6px rgba(255,102,102,0.5);
    }
    @media (max-width: 420px) {
        form {
            width: 100%;
            padding: 20px;
        }
    }
</style>
</head>
<body>

<h1>Apply for Leave</h1>

<form action="${pageContext.request.contextPath}/leave" method="post">
    <label>Type</label>
    <select name="leaveType" required>
        <option value="" disabled selected>Select leave type</option>
        <option value="ANNUAL">Annual Leave</option>
        <option value="SICK">Sick Leave</option>
    </select>

    <label>Start Date</label>
    <input type="date" name="startDate" required />

    <label>End Date</label>
    <input type="date" name="endDate" required />

    <label>Reason</label>
    <textarea name="reason" rows="4" placeholder="Enter your reason..."></textarea>

    <button type="submit">Apply</button>
</form>

<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>

</body>
</html>
