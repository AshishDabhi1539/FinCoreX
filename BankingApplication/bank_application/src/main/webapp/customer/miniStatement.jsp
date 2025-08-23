<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,com.banking.model.Transaction" %>
<%
    List<Transaction> txns = (List<Transaction>) request.getAttribute("miniStatement");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="content">
    <h2>📄 Mini Statement (Last 5)</h2>
    <table border="1" cellpadding="8">
        <tr><th>ID</th><th>Type</th><th>Amount</th><th>Date</th></tr>
        <%
            for(Transaction t : txns){
        %>
        <tr>
            <td><%= t.getTxnId() %></td>
            <td><%= t.getType() %></td>
            <td>₹ <%= t.getAmount() %></td>
            <td><%= t.getTimestamp() %></td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>