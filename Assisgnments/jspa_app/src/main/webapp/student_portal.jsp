<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Portal</title>
</head>
<body>




<%! 
        String studentName = "Mahek Morzaria";
        String course = "Computer Engineering - Artificial Intelligence";
        double marks = 92;
        String grade;
%>

<!-- Scriptlet for Grade Calculation -->
    <%
        if (marks >= 90) {
            grade = "A";
        } else if (marks >= 75 && marks < 90) {
            grade = "B";
        } else if (marks >= 50 && marks < 75) {
            grade = "C";
        } else {
            grade = "F";
        }
    %>
    
    <h2>Student Information</h2>
    <p>Today's Date = <%=new Date() %></p>
    <p>Student Name: <%= studentName %></p>
    <p>Course: <%= course %></p>
    <p>Marks: <%= marks %></p>
    <p>Grade: <%= grade %></p>

</body>
</html>