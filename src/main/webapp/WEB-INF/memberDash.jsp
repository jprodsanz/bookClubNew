<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<html>
<head>
  <title>Member Dashboard</title>
  <%--    Bootstrap--%>
  <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
<div class="container">
  <h1 class="text-center"> Member Dashboard</h1>
  <p>
    <a href="/book/new" class="btn btn-outline-primary">Add Book </a>
  </p>
  <p>
    <a href="/member/new" class="btn btn-outline-primary">Add User</a>
  </p>
  <table class="table table-striped table-bordered text-center">
    <thead class="table-dark">
    <tr>
      <th scope="col">ID</th>
      <th scope="col">First Name</th>
      <th scope="col">Last Name</th>
      <th scope="col">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="member" items="${allMembers}">
      <tr>
        <td><c:out value="${member.id}"/></td>
        <td><c:out value="${member.firstName}"/></td>
        <td><c:out value="${member.lastName}"/></td
      </tr>
      <td>
        <a href="/member/${member.id}/delete" class="btn btn-danger">Delete</a>
        <a href="/member/${member.id}" class="btn btn-primary">View</a>
        <a href="/member/${member.id}/edit" class="btn btn-primary">Edit</a>
      </td>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</html>
