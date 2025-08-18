<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Employees</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            padding: 20px;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        table {
            width: 90%;
            margin: auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0px 2px 10px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 10px 12px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #2d87f0;
            color: white;
            font-size: 14px;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        a {
            display: block;
            width: fit-content;
            margin: 20px auto;
            padding: 8px 16px;
            background-color: #2d87f0;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        a:hover {
            background-color: #1a5fb4;
        }
    </style>
</head>
<body>
    <h2>Employee List</h2>
    <table>
        <tr>
            <th>Emp No</th>
            <th>Name</th>
            <th>Job</th>
            <th>Manager</th>
            <th>Hire Date</th>
            <th>Salary</th>
            <th>Commission</th>
            <th>Dept No</th>
        </tr>
        <c:forEach var="emp" items="${employees}">
            <tr>
                <td>${emp.empNo}</td>
                <td>${emp.eName}</td>
                <td>${emp.job}</td>
                <td>${emp.mgr}</td>
                <td>${emp.hireDate}</td>
                <td>${emp.sal}</td>
                <td>${emp.comm}</td>
                <td>${emp.deptNo}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="index.jsp">Back</a>
</body>
</html>
