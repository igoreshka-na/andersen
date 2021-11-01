<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>

<head>
    <title>Company Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="<%=request.getContextPath()%>" class="navbar-brand"> Company
                Management App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>" class="nav-link">Companies</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Company</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
                New Company</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>City</th>
                <th>Creator</th>
            </tr>
            </thead>
            <tbody>

            <jsp:useBean id="listCompany" scope="request" type="java.util.List"/>
            <c:forEach var="company" items="${listCompany}">
                <c:if test="${company == null}">
                    <tr>
                        <th>${company}</th>
                    </tr>
                </c:if>

                <tr>
                    <td>
                        <c:out value="${company.id}" />
                    </td>
                    <td>
                        <c:out value="${company.name}" />
                    </td>
                    <td>
                        <c:out value="${company.city}" />
                    </td>
                    <td>
                        <c:out value="${company.creator}" />
                    </td>
                    <td>
                        <a href="<%=request.getContextPath()%>/edit?id=<c:out value='${company.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="<%=request.getContextPath()%>/delete?id=<c:out value='${company.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>

</html>