<%--
  Created by IntelliJ IDEA.
  User: Dulan
  Date: 1/1/2024
  Time: 11:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/navbar.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/manageFeedbacks.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <%--    <script>--%>
    <%--        $(document).ready(function () {--%>
    <%--            function loadProducts() {--%>
    <%--                $.ajax({--%>
    <%--                    url: "${pageContext.request.contextPath}/feedback",--%>
    <%--                    type: "GET",--%>
    <%--                    dataType: "json",--%>
    <%--                    success: function (data) {--%>
    <%--                        // Clear existing table rows--%>
    <%--                        $("#feedbackTableBody tbody").empty();--%>

    <%--                        // Loop through the received products and append to the table--%>
    <%--                        $.each(data, function (index, product) {--%>
    <%--                            $("#feedbackTableBody").append(--%>
    <%--                                "<tr>" +--%>
    <%--                                "<td>" + product.id + "</td>" +--%>
    <%--                                "<td>" + product.customerId + "</td>" +--%>
    <%--                                "<td>" + product.message + "</td>" +--%>
    <%--                                "<td>" + product.rating + "</td>" +--%>
    <%--                                "<td>" +--%>
    <%--                                "<a href='${pageContext.request.contextPath}/products?action=view&id=" + product.id + "' class='btn btn-info btn-sm'>View</a>" +--%>
    <%--                                "<a href='${pageContext.request.contextPath}/products?action=update&id=" + product.id + "' class='btn btn-warning btn-sm'>Update</a>" +--%>
    <%--                                "<a href='${pageContext.request.contextPath}/products?action=delete&id=" + product.id + "' class='btn btn-danger btn-sm'>Delete</a>" +--%>
    <%--                                "</td>" +--%>
    <%--                                "</tr>"--%>
    <%--                            );--%>
    <%--                        });--%>

    <%--                    },--%>
    <%--                    error: function () {--%>
    <%--                        console.error("Failed to load products.");--%>
    <%--                    }--%>
    <%--                });--%>
    <%--            }--%>

    <%--            // Load products on page load--%>
    <%--            loadProducts();--%>

    <%--            // Event listener for the "Load Products" button--%>
    <%--            $("#loadProductsBtn").click(function () {--%>
    <%--                loadProducts();--%>
    <%--            });--%>
    <%--        });--%>
    <%--    </script>--%>

    <script>
        $(document).ready(function () {
            function loadProducts() {
                $.ajax({
                    url: "${pageContext.request.contextPath}/feedback",
                    type: "GET",
                    dataType: "json",
                    success: function (data) {
                        $("#feedbackTableBody tbody").empty();
                        $.each(data, function (index, product) {
                            $("#feedbackTableBody").append(
                                "<tr onclick='loadFeedbackData(" + product.id + ", \"" + product.customerId + "\", \"" + product.message + "\", " + product.rating + ")'>" +
                                "<td>" + product.id + "</td>" +
                                "<td>" + product.customerId + "</td>" +
                                "<td>" + product.message + "</td>" +
                                "<td>" + product.rating + "</td>" +
                                "</tr>"
                            );
                        });
                    },
                    error: function () {
                        console.error("Failed to load products.");
                    }
                });
            }

            function loadFeedbackData(id, customerId, message, rating) {
                $("#customerId").val(customerId);
                $("#message").val(message);
                $("#rating").val(rating);
                $("#productId").val(id);
            }

            function updateFeedback() {
                var productId = $("#productId").val();
                var updatedData = {
                    customerId: $("#customerId").val(),
                    message: $("#message").val(),
                    rating: $("#rating").val()
                };

                $.ajax({
                    url: "${pageContext.request.contextPath}/feedback?action=update&id=" + productId,
                    type: "POST",
                    contentType: "application/json",
                    data: JSON.stringify(updatedData),
                    success: function () {
                        loadProducts();
                    },
                    error: function () {
                        console.error("Failed to update feedback.");
                    }
                });
            }

            function deleteFeedback() {
                var productId = $("#productId").val();

                $.ajax({
                    url: "${pageContext.request.contextPath}/feedback?action=delete&id=" + productId,
                    type: "POST",
                    success: function () {
                        loadProducts();
                    },
                    error: function () {
                        console.error("Failed to delete feedback.");
                    }
                });
            }

            loadProducts();

            $("#loadFeedbacksBtn").click(function () {
                loadProducts();
            });
        });
    </script>
</head>
<body>
<%@ include file="navbar.jsp" %>
<%--<div class="sidenav bg-dark text-white">--%>
<%--    <a href="${pageContext.request.contextPath}/admin" class="text-white">Dashboard</a>--%>
<%--    <a href="${pageContext.request.contextPath}/admin?section=products" class="text-white">Manage Products</a>--%>
<%--    <a href="${pageContext.request.contextPath}/admin?section=helpdesk" class="text-white">Manage Help Desk</a>--%>
<%--    <a href="${pageContext.request.contextPath}/admin?section=feedbacks" class="text-white">Manage Feedbacks</a>--%>
<%--    <a href="${pageContext.request.contextPath}/admin?section=profile" class="text-white">Manage Profile</a>--%>
<%--</div>--%>

<div class="container mt-4">
    <h2 class="mb-4">Manage Feedbacks</h2>
    <button id="loadFeedbacksBtn" class="btn btn-primary mb-3">Load Feedbacks</button>

    <form id="addFeedbackForm" action="${pageContext.request.contextPath}/feedback?action=add" method="post">
        <div class="form-group">
            <label for="customerId">Customer ID:</label>
            <input type="text" id="customerId" name="customerId" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="message">Message:</label>
            <textarea id="message" name="message" class="form-control" required></textarea>
        </div>
        <div class="form-group">
            <label for="rating">Rating:</label>
            <input type="text" id="rating" name="rating" class="form-control" required>
        </div>
        <div class="form-group">
            <input type="hidden" id="productId" name="productId">
            <button type="submit" class="btn btn-success">Add Feedback</button>
            <button type="button" class="btn btn-primary" onclick="updateFeedback()">Update Feedback</button>
            <button type="button" class="btn btn-danger" onclick="deleteFeedback()">Delete Feedback</button>
        </div>
    </form>

    <table id="feedbackTable" class="table table-bordered mt-4">
        <thead>
        <tr>
            <th>ID</th>
            <th>Customer ID</th>
            <th>Message</th>
            <th>Rating</th>
        </tr>
        </thead>
        <tbody id="feedbackTableBody">
        <!-- Data will be loaded dynamically using JavaScript -->
        </tbody>
    </table>
</div>

<!-- Include the full version of jQuery before manageFeedbacks.js -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="${pageContext.request.contextPath}/js/manageFeedbacks.js"></script>
<!-- Include Bootstrap JavaScript after jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
