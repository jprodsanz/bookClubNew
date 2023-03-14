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
    <title>Edit Member </title>
    <%--    Bootstrap--%>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
<div class="container">
    <h1 class="display-1 text-center" >Edit Member  </h1>
    <form:form action="/member/${member.id}/edit" method="POST" modelAttribute="member">
        <%--    line below is crucial --%>
    <input type="hidden" name="_method" value="PUT"/>
        <%--    if you don't have this member id will be removed from member entry --%>
<%--    <div class="mb-3">--%>
<%--        <form:errors path="book" class="error"/>--%>
<%--        <form:input type="hidden" path="book" value="${book.id}" class="form"/>--%>
<%--    </div>--%>
    <div class="mb-3">
        <form:label for="firstName" class="form-label" path="firstName">First Name:</form:label>
        <form:input style="width:250px;" type="text" class="form-control" id="firstName" aria-describedby="firstName" path="firstName"/>
        <form:errors path="firstName" class="text-danger"/>
    </div>
    <div class="mb-3">
        <form:label for="lastName" class="form-label" path="lastName">Last Name:</form:label>
        <form:input style="width:250px;" type="text" class="form-control" id="lastName" aria-describedby="lastName" path="lastName"/>
        <form:errors path="lastName" class="text-danger"/>
    </div>

    <button type="submit" class="btn btn-outline-primary">Update Member </button>
    </form:form>
    <div>
        <small class="text-muted ">
            return to dashboard?
            <a class="ml-2"href="/member/dashboard">Dashboard</a>
        </small>
    </div>
</body>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</html>
