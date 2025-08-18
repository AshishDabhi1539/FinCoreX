<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
  <c:when test="${empty list}">
    <p class="muted">No records found.</p>
  </c:when>
  <c:otherwise>
    <table>
      <thead>
      <tr>
        <th>#</th><th>User ID</th><th>Date</th><th>Status</th><th>Reason</th><th>Rejection Reason</th><th>Action</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="l" items="${list}">
        <tr>
          <td>${l.id}</td>
          <td>${l.userId}</td>
          <td>${l.leaveDate}</td>
          <td>${l.status}</td>
          <td>${l.reason}</td>
          <td>${l.rejectionReason}</td>
          <td>
            <c:if test="${l.status eq 'PENDING'}">
              <form action="<%=request.getContextPath()%>/admin/leaveAction" method="post" class="inline">
                <input type="hidden" name="leaveId" value="${l.id}">
                <button name="action" value="APPROVE" type="submit">Approve</button>
              </form>

              <form action="<%=request.getContextPath()%>/admin/leaveAction" method="post" class="inline">
                <input type="hidden" name="leaveId" value="${l.id}">
                <select name="rejectionReason" required>
                  <option value="">Select reason</option>
                  <option>Not enough resources</option>
                  <option>Project deadline</option>
                  <option>Already too many on leave</option>
                  <option>Other</option>
                </select>
                <input type="text" name="customReason" placeholder="If Other, type here">
                <button name="action" value="REJECT" type="submit">Reject</button>
              </form>
            </c:if>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </c:otherwise>
</c:choose>
