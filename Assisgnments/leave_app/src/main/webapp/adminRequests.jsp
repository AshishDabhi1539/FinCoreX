<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Side</title>
<style>
body {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  background: #f5f8ff;
  margin: 0;
  padding: 20px;
  color: #333;
}

table {
  border-collapse: collapse;
  width: 100%;
  max-width: 1000px;
  margin: 0 auto;
  background: #fff;
  box-shadow: 0 4px 20px rgba(0,0,0,0.05);
  border-radius: 8px;
  overflow: hidden;
}

th, td {
  text-align: left;
  padding: 14px 16px;
}

th {
  background-color: #0056b3;
  color: white;
  font-weight: 600;
}

tr:nth-child(even) {
  background-color: #f2f6fc;
}

tr:hover {
  background-color: #e9f2ff;
}

form {
  display: flex;
  gap: 8px;
  flex-wrap: wrap;
  align-items: center;
}

select, input[type="text"], input[name="comment"] {
  padding: 6px 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 14px;
}

select:focus, input:focus {
  border-color: #0056b3;
  outline: none;
  box-shadow: 0 0 5px rgba(0,86,179,0.3);
}

button {
  background: #0056b3;
  color: white;
  border: none;
  padding: 6px 12px;
  border-radius: 4px;
  font-size: 14px;
  cursor: pointer;
  transition: background 0.3s ease;
}

button:hover {
  background: #004090;
}

@media (max-width: 768px) {
  table, thead, tbody, th, td, tr {
    display: block;
    width: 100%;
  }
  tr {
    margin-bottom: 15px;
    background: #fff;
    box-shadow: 0 2px 8px rgba(0,0,0,0.05);
    border-radius: 6px;
    overflow: hidden;
  }
  th {
    display: none;
  }
  td {
    padding: 10px;
    border-bottom: 1px solid #eee;
  }
  td:before {
    content: attr(data-label);
    font-weight: bold;
    display: block;
    margin-bottom: 4px;
    color: #555;
  }
}
</style>
</head>
<body>

<h2 style="text-align:center; color:#0056b3;">Pending Leave Requests</h2>

<table>
  <thead>
    <tr>
      <th>ID</th>
      <th>User</th>
      <th>Type</th>
      <th>From</th>
      <th>To</th>
      <th>Days</th>
      <th>Action</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="r" items="${pending}">
      <tr>
        <td data-label="ID">${r.requestId}</td>
        <td data-label="User">${r.userId}</td>
        <td data-label="Type">${r.leaveType}</td>
        <td data-label="From">${r.startDate}</td>
        <td data-label="To">${r.endDate}</td>
        <td data-label="Days">${r.days}</td>
        <td data-label="Action">
          <form action="${pageContext.request.contextPath}/admin/requests" method="post">
            <input type="hidden" name="requestId" value="${r.requestId}" />
            <select name="decision">
              <option value="APPROVED">Approve</option>
              <option value="REJECTED">Reject</option>
            </select>
            <input type="text" name="comment" placeholder="Comment" />
            <button type="submit">Submit</button>
          </form>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>

</body>
</html>
