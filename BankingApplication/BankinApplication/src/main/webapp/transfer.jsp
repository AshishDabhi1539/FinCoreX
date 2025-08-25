<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transfer</title>
</head>
<body>
<div class="content">
    <h2>🔄 Transfer Money</h2>
    <form action="<%=request.getContextPath()%>/customer/transfer" method="post">
        <label>Recipient Account Number:</label>
        <input type="number" name="toAccount" required />
        <br><br>
        <label>Amount:</label>
        <input type="number" step="0.01" name="amount" required />
        <button type="submit">Transfer</button>
    </form>
</div>

</body>
</html>