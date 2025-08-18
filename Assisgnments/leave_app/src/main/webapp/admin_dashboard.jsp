<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>Admin Dashboard</title>
<style>
  body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background: linear-gradient(135deg, #1f1f2e, #2e2e44);
    color: #fff;
    margin: 0;
    padding: 20px;
    min-height: 100vh;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }

  h2 {
    margin-bottom: 20px;
    color: #00f2fe;
    text-shadow: 0 0 8px #4facfe;
    user-select: none;
  }

  table {
    width: 100%;
    border-collapse: collapse;
    background: rgba(255, 255, 255, 0.05);
    border-radius: 12px;
    overflow: hidden;
    box-shadow: 0 8px 25px rgba(0,0,0,0.3);
    margin-top: 15px;
    backdrop-filter: blur(8px);
  }

  th, td {
    padding: 14px 12px;
    text-align: center;
    font-size: 14px;
    color: #e0e0e0;
    user-select: text;
  }

  th {
    background: rgba(0, 242, 254, 0.8);
    color: #0f0f0f;
    font-weight: 700;
    text-transform: uppercase;
    letter-spacing: 0.05em;
  }

  tr:hover td {
    background: rgba(0, 242, 254, 0.15);
    color: #00f2fe;
    transition: background 0.25s ease;
  }

  tr:last-child td {
    border-bottom: none;
  }

  .approve, .reject {
    padding: 8px 12px;
    border: none;
    border-radius: 8px;
    font-weight: 600;
    cursor: pointer;
    transition: all 0.3s ease;
  }

  .approve {
    background: linear-gradient(135deg, #28a745, #1c7c31);
    color: #fff;
  }
  .approve:hover {
    background: linear-gradient(135deg, #1c7c31, #138a24);
    box-shadow: 0 0 12px #28a745aa;
  }

  .reject {
    background: linear-gradient(135deg, #dc3545, #a71d2a);
    color: #fff;
    margin-left: 8px;
  }
  .reject:hover {
    background: linear-gradient(135deg, #a71d2a, #7a151f);
    box-shadow: 0 0 12px #dc3545aa;
  }

  /* Fixed Logout button */
  .logout-fixed {
    position: fixed;
    top: 16px;
    right: 16px;
    background: linear-gradient(135deg, #dc3545, #a71d2a);
    color: #fff;
    padding: 10px 18px;
    border-radius: 12px;
    font-weight: 700;
    text-decoration: none;
    box-shadow: 0 6px 15px rgba(220,53,69,0.5);
    transition: all 0.3s ease;
    font-size: 14px;
    z-index: 1000;
    user-select: none;
  }

  .logout-fixed:hover {
    background: linear-gradient(135deg, #a71d2a, #7a151f);
    box-shadow: 0 0 18px #dc3545aa;
  }

  @media (max-width: 600px) {
    table, th, td {
      font-size: 12px;
      padding: 10px 8px;
    }
    .approve, .reject {
      padding: 6px 8px;
      font-size: 12px;
    }
    .logout-fixed {
      padding: 8px 14px;
      font-size: 12px;
    }
  }
</style>
</head>
<body>

  <a class="logout-fixed" href="${pageContext.request.contextPath}/logout">Logout</a>

  <h2>Admin - Pending Requests</h2>

  <table>
    <tr>
      <th>ID</th>
      <th>Emp ID</th>
      <th>Start</th>
      <th>End</th>
      <th>Reason</th>
      <th>Applied On</th>
      <th>Action</th>
    </tr>
    <c:forEach var="r" items="${leaveRequests}">
      <tr>
        <td>${r.leaveId}</td>
        <td>${r.empId}</td>
        <td>${r.startDate}</td>
        <td>${r.endDate}</td>
        <td>${r.reason}</td>
        <td>${r.appliedOn}</td>
        <td>
          <form action="${pageContext.request.contextPath}/admin/action" method="post" style="display:inline">
            <input type="hidden" name="leaveId" value="${r.leaveId}"/>
            <button class="approve" name="action" value="APPROVE">Approve</button>
          </form>
          <form action="${pageContext.request.contextPath}/admin/action" method="post" style="display:inline">
            <input type="hidden" name="leaveId" value="${r.leaveId}"/>
            <button class="reject" name="action" value="REJECT">Reject</button>
          </form>
        </td>
      </tr>
    </c:forEach>
  </table>

</body>
</html>
