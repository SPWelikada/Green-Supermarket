<%--
  Created by IntelliJ IDEA.
  User: Dulan
  Date: 1/1/2024
  Time: 11:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%-- Import necessary classes --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Products</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/navbar.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/manageProducts.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            function loadProducts() {
                $.ajax({
                    url: "${pageContext.request.contextPath}/products?action=viewAll",
                    type: "GET",
                    dataType: "json",
                    success: function (data) {
                        $("#productTable tbody").empty();
                        $.each(data, function (index, product) {
                            var imageElement = $("<img>").attr("src", product.imagePath).attr("alt", "").css({
                                "width": "50px",
                                "height": "50px"
                            });
                            $("#productTable tbody").append(
                                "<tr>" +
                                "<td>" + imageElement.prop('outerHTML') + "</td>" +
                                "<td>" + product.id + "</td>" +
                                "<td>" + product.name + "</td>" +
                                "<td>" + product.listPrice + "</td>" +
                                "<td>" + product.unitPrice + "</td>" +
                                "<td>" + product.qty + "</td>" +
                                "</tr>"
                            );
                        });
                    },
                    error: function () {
                        console.error("Failed to load products.");
                    }
                });
            }

            function loadProductData(id, name, listPrice, unitPrice, qty) {
                $("#productId").val(id);
                $("#name").val(name);
                $("#listPrice").val(listPrice);
                $("#unitPrice").val(unitPrice);
                $("#qty").val(qty);
            }

            function updateProduct() {
                var updatedData = {
                    id: $("#productId").val(),
                    name: $("#name").val(),
                    listPrice: $("#listPrice").val(),
                    unitPrice: $("#unitPrice").val(),
                    qty: $("#qty").val()
                };

                $.ajax({
                    url: "${pageContext.request.contextPath}/products",
                    type: "PUT", // Change the method to PUT
                    contentType: "application/json",
                    data: JSON.stringify(updatedData),
                    success: function () {
                        loadProducts();
                    },
                    error: function () {
                        console.error("Failed to update product.");
                    }
                });
            }


            function deleteProduct() {
                var productId = $("#productId").val();

                $.ajax({
                    url: "${pageContext.request.contextPath}/products?action=delete&id=" + productId,
                    type: "POST",
                    success: function () {
                        loadProducts();
                    },
                    error: function () {
                        console.error("Failed to delete product.");
                    }
                });
            }

            loadProducts();

            $("#loadProductsBtn").click(function () {
                loadProducts();
            });

            $("#productTable tbody").on("click", "tr", function () {
                var id = $(this).find("td:eq(1)").text();
                var name = $(this).find("td:eq(2)").text();
                var listPrice = $(this).find("td:eq(3)").text();
                var unitPrice = $(this).find("td:eq(4)").text();
                var qty = $(this).find("td:eq(5)").text();

                loadProductData(id, name, listPrice, unitPrice, qty);
            });

            $("#updateProductBtn").click(function () {
                updateProduct();
            });

            $("#deleteProductBtn").click(function () {
                deleteProduct();
            });
        });
    </script>
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container mt-4">
    <h2 class="mb-4">Manage Products</h2>
    <button id="loadProductsBtn" class="btn btn-primary mb-3">Load Products</button>

    <form action="${pageContext.request.contextPath}/products" method="post" enctype="multipart/form-data">
        <input type="hidden" id="action" name="action" value="add">

        <div class="form-group">
            <label for="productId">Product Id:</label>
            <input type="text" id="productId" name="id" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="name">Product Name:</label>
            <input type="text" id="name" name="name" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="listPrice">List Price:</label>
            <input type="number" id="listPrice" name="listPrice" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="unitPrice">Unit Price:</label>
            <input type="number" id="unitPrice" name="unitPrice" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="qty">Quantity:</label>
            <input type="number" id="qty" name="qty" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="imageFile">Product Image:</label>
            <input type="file" id="imageFile" name="imageFile" class="form-control-file" accept="image/*" required>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-success">Add Product</button>
            <button type="button" id="updateProductBtn" class="btn btn-warning">Update Product</button>
            <button type="button" id="deleteProductBtn" class="btn btn-danger">Delete Product</button>
        </div>
    </form>

    <table id="productTable" class="table table-bordered mt-4">
        <thead>
        <tr>
            <th></th>
            <th>ID</th>
            <th>Name</th>
            <th>List Price</th>
            <th>Unit Price</th>
            <th>Quantity</th>
        </tr>
        </thead>
        <tbody>
        <!-- Table body will be populated dynamically using JavaScript -->
        </tbody>
    </table>
</div>

</body>
</html>
