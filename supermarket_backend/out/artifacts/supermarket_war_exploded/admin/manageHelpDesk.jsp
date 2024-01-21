<%--
  Created by IntelliJ IDEA.
  User: Dulan
  Date: 1/1/2024
  Time: 11:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Help Desk</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/navbar.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/manageHelpDesk.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script>
        $(document).ready(function () {
            function loadProducts() {
                $.ajax({
                    url: "${pageContext.request.contextPath}/feedback",
                    type: "GET",
                    dataType: "json",
                    success: function (data) {
                        // Clear existing table rows
                        $("#helpDeskTableBody tbody").empty();

                        // Loop through the received products and append to the table
                        $.each(data, function (index, product) {
                            $("#helpDeskTableBody").append(
                                "<tr>" +
                                "<td>" + product.id + "</td>" +
                                "<td>" + product.customerId + "</td>" +
                                "<td>" + product.message + "</td>" +
                                "<td>" + product.rating + "</td>" +
                                "<td>" +
                                "<a href='${pageContext.request.contextPath}/products?action=view&id=" + product.id + "' class='btn btn-info btn-sm'>View</a>" +
                                "<a href='${pageContext.request.contextPath}/products?action=update&id=" + product.id + "' class='btn btn-warning btn-sm'>Update</a>" +
                                "<a href='${pageContext.request.contextPath}/products?action=delete&id=" + product.id + "' class='btn btn-danger btn-sm'>Delete</a>" +
                                "</td>" +
                                "</tr>"
                            );
                        });

                    },
                    error: function () {
                        console.error("Failed to load products.");
                    }
                });
            }

            // Load products on page load
            loadProducts();

            // Event listener for the "Load Products" button
            $("#helpDeskTableBody").click(function () {
                loadProducts();
            });
        });
    </script>
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container mt-4">
    <h2 class="mb-4">Manage Help Desk</h2>

    <form action="${pageContext.request.contextPath}/helpdesk" method="post">
        <div class="form-group">
            <label for="faqs">FAQs:</label>
            <input type="text" id="faqs" name="faqs" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="questions">Questions:</label>
            <input type="text" id="questions" name="questions" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="answers">Answers:</label>
            <input type="text" id="answers" name="answers" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="guidance">Guidance:</label>
            <textarea id="guidance" name="guidance" class="form-control" required></textarea>
        </div>

        <button type="submit" class="btn btn-success">Submit Help Desk Ticket</button>
    </form>

    <table class="table table-bordered mt-4">
        <thead>
        <tr>
            <th>ID</th>
            <th>FAQs</th>
            <th>Questions</th>
            <th>Answers</th>
            <th>Guidance</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody id="helpDeskTableBody">
        <!-- Table body will be populated dynamically using JavaScript or server-side logic -->
        </tbody>
    </table>
</div>

<script src="${pageContext.request.contextPath}/js/helpdesk.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>

