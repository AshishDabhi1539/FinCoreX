<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Withdraw</title>
</head>
<body>
<div class="content">
    <h2>🏧 Withdraw</h2>
    <form action="withdraw" method="post">
        <label>Amount:</label>
        <input type="number" step="0.01" name="amount" required />
        <button type="submit">Withdraw</button>
    </form>
</div>

</body>
</html>