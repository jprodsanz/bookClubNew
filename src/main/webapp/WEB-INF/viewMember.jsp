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
    <title>View Member</title>
    <%--    Bootstrap--%>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
<div class="container">
    <div class="card text-center">
        <div class="card-header">
            Featured
        </div>
        <div class="card-body">
            <h5 class="card-title">Members </h5>
            <p class="card-text">
                <%--            <c:out value="${member.firstName + '' + member.lastName}"/>--%>
                <c:out value="${String.format(' %s %s', member.firstName, member.lastName) }" />
            </p>
<%--            <p>--%>
<%--                <a href="/member/dashboard" class="btn btn-primary">Dashboard</a>--%>
<%--            </p>--%>
            <div class="row">
                <form action="/member/${id}/checkout" method="POST" classs="col-sm">
                    <label for="books">Check out a book: </label>
                    <select name="book" id="books">
                        <c:forEach var="book" items="${allBooks}">
                            <c:choose>
                                <c:when test="${book.member == null}">
                                    <option value="${book.id}">
                                        <c:out value="${book.title}"/>
                                    </option>
                                </c:when>

                                <c:when test="${book.member.id == myId}">
                                    <option value="${book.id}">
                                        <c:out value="${book.title}"/> Book is checked out
                                    </option>
                                </c:when>

                                <c:otherwise>
                                    <option
                                            value="${book.id}">
                                    </option>
                                </c:otherwise>

                            </c:choose>
<%--                            <c:if test="${book.member == null}">--%>
<%--                                <option value="${book.id}">--%>
<%--                                    <c:out value="${book.title}"/>--%>
<%--                                </option>--%>
<%--                            </c:if>--%>
                        </c:forEach>
                    </select>
                    <p>
                        <button class="btn btn-primary">Check Out </button>
                    </p>
                </form>
                <div class="container">
                    <h2> Book Currently Checked Out </h2>
                    <ul class = "col-sm">
                            <c:forEach var="book" items="${member.booksAdded}">
                                <li>
                                    <c:out value="${book.title}"/>
                                    <p>
                                        <a href="/book/${book.id}/return" class="btn btn-danger">Return</a>
                                    </p>
                                </li>
                            </c:forEach>
                    </ul>
                </div>

            </div>
        </div>

        <div class="card-footer text-muted">
            <div>
                <small class="text-muted ">
                    return to dashboard?
                    <a class="ml-2"href="/member/dashboard">Dashboard</a>
                </small>
            </div>
        </div>
    </div>
</div>
</body>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</html>
