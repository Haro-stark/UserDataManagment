<%--
  Created by IntelliJ IDEA.
  User: Haroon Rasheed
  Date: 9/23/2021
  Time: 11:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a href="https://www.javaguides.net" class="navbar-brand"> User
                Management App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Users</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">Operations</h3>
        <hr>

        <br>
        <table class="table table-bordered html-editor-align-center">
            <thead>
            <tr>
                <th>Available Operations</th>
            </tr>
            </thead>
            <tbody>

                <tr>
                    <td>
                        <a href="userInfo">Show Users</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="user-Insert.jsp">Add a new User</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="userInfo">Update User</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="userInfo">Delete a User</a>
                    </td>
                </tr>

            </tbody>

        </table>
    </div>
</div>
</body>
</html>