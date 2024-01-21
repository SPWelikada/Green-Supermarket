document.addEventListener('DOMContentLoaded', function () {
    const loadProductsBtn = document.getElementById('loadProductsBtn');
    const productTableBody = document.querySelector('#productTable tbody');

    loadProductsBtn.addEventListener('click', function () {
        fetch('${pageContext.request.contextPath}/products?action=loadTable')
            .then(response => response.json())
            .then(data => {
                productTableBody.innerHTML = '';

                data.forEach(product => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.listPrice}</td>
                        <td>${product.unitPrice}</td>
                        <td>${product.qty}</td>
                        <td>Action buttons here</td>
                    `;
                    productTableBody.appendChild(row);
                });
            })
            .catch(error => console.error('Error loading products:', error));
    });
});
$(document).ready(function () {
    // Function to load products using AJAX
    function loadProducts() {
        $.ajax({
            url: "${pageContext.request.contextPath}/products?action=viewAll",
            type: "GET",
            dataType: "json",
            success: function (data) {
                // Clear existing table rows
                $("#productTable tbody").empty();

                // Loop through the received products and append to the table
                $.each(data, function (index, product) {
                    $("#productTable tbody").append(
                        "<tr>" +
                        "<td>" + product.id + "</td>" +
                        "<td>" + product.name + "</td>" +
                        "<td>" + product.listPrice + "</td>" +
                        "<td>" + product.unitPrice + "</td>" +
                        "<td>" + product.qty + "</td>" +
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
    $("#loadProductsBtn").click(function () {
        loadProducts();
    });
});