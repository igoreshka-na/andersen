<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>

<head>
    <title>User Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="<%=request.getContextPath()%>/list" class="navbar-brand"> Company Management App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Companies</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${company != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${company == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${company != null}">
                                Edit User
                            </c:if>
                            <c:if test="${company == null}">
                                Add New User
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${company != null}">
                        <input type="hidden" name="id" value="<c:out value='${company.id}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label>Name</label> <input type="text" value="<c:out value='${company.name}' />"
                                                   class="form-control" name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>City</label> <input type="text" value="<c:out value='${company.city}' />"
                                                   class="form-control" name="city">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Creator</label> <input type="text" value="<c:out value='${company.creator}' />"
                                                      class="form-control" name="creator">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>

</html>