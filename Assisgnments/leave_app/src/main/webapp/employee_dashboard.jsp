<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
  <title>Employee Dashboard</title>
  <style>
  /* Global */
body {
  font-family: Arial, sans-serif;
  background: #f5f7fa;
  margin: 0;
  padding: 0;
  color: #333;
}

h2, h3 {
  margin: 0 0 10px 0;
}

/* Topbar */
.topbar {
  background: #4a90e2;
  color: #fff;
  padding: 15px 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.topbar h2 {
  font-size: 20px;
}

.topbar .btn {
  background: #fff;
  color: #4a90e2;
  padding: 6px 14px;
  border-radius: 4px;
  text-decoration: none;
  font-weight: bold;
  transition: 0.2s;
}

.topbar .btn:hover {
  background: #e6f0ff;
}

/* Card */
.card {
  background: #fff;
  padding: 20px;
  margin: 20px auto;
  border-radius: 8px;
  box-shadow: 0 2px 6px rgba(0,0,0,0.1);
  max-width: 800px;
}

.card h3 {
  color: #4a90e2;
  margin-bottom: 10px;
}

/* Form */
form {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

label {
  font-weight: bold;
  margin-bottom: 5px;
}

input[type="text"], input[type="date"], textarea {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  width: 100%;
}

textarea {
  resize: vertical;
}

button {
  background: #4a90e2;
  color: #fff;
  border: none;
  padding: 10px;
  border-radius: 4px;
  cursor: pointer;
  transition: 0.2s;
}

button:hover {
  background: #357ABD;
}

.inline {
  display: inline-flex;
  gap: 5px;
}

/* Messages */
.msg {
  color: #d9534f;
  font-weight: bold;
}

.msg-ok {
  color: #28a745;
  font-weight: bold;
}

.muted {
  color: #777;
  font-style: italic;
}

/* Grid for 3 sections */
.grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
}

/* Tables */
table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 10px;
}

table th, table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

table th {
  background: #f0f4f8;
  font-weight: bold;
}

table tr:nth-child(even) {
  background: #fafafa;
}
  
  </style>
</head>
<body>
<div class="topbar">
  <h2>Employee Dashboard</h2>
  <a class="btn" href="<%=request.getContextPath()%>/logout">Logout</a>
</div>

<div class="card">
  <h3>Apply Leave</h3>
  <form action="<%=request.getContextPath()%>/employee/applyLeave" method="post">
    <label>Leave Date</label>
    <input type="date" name="leaveDate" required min="<%= LocalDate.now() %>">
    <label>Reason</label>
    <textarea name="reason" required></textarea>
    <button type="submit">Apply</button>
  </form>
  <p class="msg"><c:out value='${param.e}'/></p>
  <p class="msg-ok"><c:out value='${param.m}'/></p>
</div>

<c:set var="pending" value="${fn:split('',',')}" />
<c:set var="approved" value="${fn:split('',',')}" />
<c:set var="rejected" value="${fn:split('',',')}" />


<c:set var="pendingList" value="${empty myLeaves ? null :
  myLeaves.stream().filter(l->l.status=='PENDING').toList() }" />

<!-- Simple JSTL-friendly grouping -->
<c:set var="hasAny" value="false"/>
<c:forEach var="l" items="${myLeaves}">
  <c:set var="hasAny" value="true"/>
</c:forEach>

<c:if test="${hasAny}">
  <div class="grid">
    <!-- Pending -->
    <div class="card">
      <h3>Pending</h3>
      <c:set var="hasPending" value="false"/>
      <table>
        <thead><tr><th>#</th><th>Date</th><th>Reason</th><th>Action</th></tr></thead>
        <tbody>
        <c:forEach var="l" items="${myLeaves}">
          <c:if test="${l.status eq 'PENDING'}">
            <c:set var="hasPending" value="true"/>
            <tr>
              <td>${l.id}</td>
              <td>${l.leaveDate}</td>
              <td>${l.reason}</td>
              <td>
                <form action="<%=request.getContextPath()%>/employee/updateLeave" method="post" class="inline">
                  <input type="hidden" name="leaveId" value="${l.id}">
                  <input type="date" name="leaveDate" required min="<%= LocalDate.now() %>">
                  <input type="text" name="reason" value="${l.reason}" required>
                  <button type="submit">Update</button>
                </form>
              </td>
            </tr>
          </c:if>
        </c:forEach>
        </tbody>
      </table>
      <c:if test="${not hasPending}">
        <p class="muted">No pending requests.</p>
      </c:if>
    </div>

    <!-- Approved -->
    <div class="card">
      <h3>Approved</h3>
      <c:set var="hasApproved" value="false"/>
      <table>
        <thead><tr><th>#</th><th>Date</th><th>Reason</th></tr></thead>
        <tbody>
        <c:forEach var="l" items="${myLeaves}">
          <c:if test="${l.status eq 'APPROVED'}">
            <c:set var="hasApproved" value="true"/>
            <tr>
              <td>${l.id}</td>
              <td>${l.leaveDate}</td>
              <td>${l.reason}</td>
            </tr>
          </c:if>
        </c:forEach>
        </tbody>
      </table>
      <c:if test="${not hasApproved}">
        <p class="muted">No approved requests.</p>
      </c:if>
    </div>

    <!-- Rejected -->
    <div class="card">
      <h3>Rejected</h3>
      <c:set var="hasRejected" value="false"/>
      <table>
        <thead><tr><th>#</th><th>Date</th><th>Reason</th><th>Rejected Because</th></tr></thead>
        <tbody>
        <c:forEach var="l" items="${myLeaves}">
          <c:if test="${l.status eq 'REJECTED'}">
            <c:set var="hasRejected" value="true"/>
            <tr>
              <td>${l.id}</td>
              <td>${l.leaveDate}</td>
              <td>${l.reason}</td>
              <td>${l.rejectionReason}</td>
            </tr>
          </c:if>
        </c:forEach>
        </tbody>
      </table>
      <c:if test="${not hasRejected}">
        <p class="muted">No rejected requests.</p>
      </c:if>
    </div>
  </div>
</c:if>
<c:if test="${not hasAny}">
  <div class="card"><p class="muted">No leave records yet.</p></div>
</c:if>

</body>
</html>
