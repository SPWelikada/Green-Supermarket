<%--
  Created by IntelliJ IDEA.
  User: Dulan
  Date: 1/2/2024
  Time: 10:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/navbar.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/profile.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container mt-4">
    <h2 class="mb-4">User Profile</h2>

    <form action="${pageContext.request.contextPath}/updateProfile" method="post">
        <div class="form-group">
            <label for="contact">Contact:</label>
            <input type="text" id="contact" name="contact" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="userName">User Name:</label>
            <input type="text" id="userName" name="userName" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-success">Update Profile</button>
    </form>
</div>

<script src="${pageContext.request.contextPath}/admin/js/profile.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
