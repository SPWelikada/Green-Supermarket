<%--
  Created by IntelliJ IDEA.
  User: Dulan
  Date: 1/1/2024
  Time: 11:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/sidebar.css">
</head>
<body>
<div class="wrapper">
    <nav id="sidebar">
        <div class="sidebar-header">
            <h3>Admin Dashboard</h3>
        </div>

        <ul class="list-unstyled components">
            <p>Welcome, Admin!</p>
            <li>
                <a href="${pageContext.request.contextPath}/admin">Dashboard</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin?section=products">Manage Products</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin?section=helpdesk">Manage Help Desk</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin?section=feedbacks">Manage Feedbacks</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin?section=profile">Manage Profile</a>
            </li>
        </ul>

        <ul class="list-unstyled CTAs">
            <li>
                <a href="${pageContext.request.contextPath}/index1.jsp" class="btn btn-danger">Logout</a>
            </li>
        </ul>
    </nav>

    <div id="content">
        <!-- Your page content goes here -->
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
