<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import =  "java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome jsp</title>
</head>
<body>
<b> Welcome!! to JSPS</b>

<% 
int number = 10;
//aya java nu code lakhvanu gmte 

int number2 = 20;

Date date = new Date();

%>

<%= number+number2 %>
<%=date %>
<%! double pi = 3.14; %>

<%=pi %>
</body>
</html>