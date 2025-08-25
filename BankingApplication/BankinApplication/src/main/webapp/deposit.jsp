<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Deposit</title>
</head>
<body>
<div class="content">
    <h2>💰 Deposit</h2>
    <form action="deposit" method="post">
        <label>Amount:</label>
        <input type="number" step="0.01" name="amount" required />
        <button type="submit">Deposit</button>
    </form>
</div>

</body>
</html>