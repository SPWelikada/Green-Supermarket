<%--
  Created by IntelliJ IDEA.
  User: Dulan
  Date: 12/31/2023
  Time: 4:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Supermarket Home</title>
    <link rel="stylesheet" href="./home_styles.css">
</head>
<body>

<!-- Navbar -->
<div class="navbar">
    <a href="#" class="active">Home</a>
    <a href="#">Products</a>
    <a href="#">Special Offers</a>
    <div class="cart-icon">
        <a href="#" id="cart-icon-link">
            <img src="https://www.iconpacks.net/icons/2/free-shopping-cart-icon-3047-thumb.png" alt="Cart">
            <span id="cart-count">0</span>
        </a>
    </div>
</div>

<!-- Main Content -->
<div class="main-content">
    <h2>Welcome to Our Supermarket</h2>
    <p>Explore our products and find great deals!</p>

    <!-- Product Cards -->
    <div class="product-card">
        <img src="https://www.coolztricks.com/wp-content/uploads/2022/07/photo_2022-07-18-23.06.02.jpeg" alt="Product 1">
        <h3>Product 1</h3>
        <p>Description of Product 1.</p>
        <button class="add-to-cart-btn" onclick="addToCart(1)">Add to Cart</button>
    </div>

    <div class="product-card">
        <img src="https://american-image.com/wp-content/uploads/2022/07/free.jpg" alt="Product 2">
        <h3>Product 2</h3>
        <p>Description of Product 2.</p>
        <button class="add-to-cart-btn" onclick="addToCart(2)">Add to Cart</button>
    </div>

    <!-- Cart Sidebar -->
    <div class="cart-sidebar" id="cart-sidebar">
        <h3>Your Cart</h3>
        <ul id="cart-items">
            <!-- Cart items will be dynamically added here -->
        </ul>
    </div>
</div>

<!-- JavaScript for Cart -->
<script>
    function addToCart(productId) {
        // Add logic to add product to cart
        // For simplicity, let's update the cart count and items in the sidebar
        var cartCount = document.getElementById('cart-count');
        var cartSidebar = document.getElementById('cart-sidebar');
        var cartItems = document.getElementById('cart-items');

        // Update cart count
        cartCount.innerText = parseInt(cartCount.innerText) + 1;

        // Update cart sidebar (placeholder logic)
        var listItem = document.createElement('li');
        listItem.innerText = 'Product ' + productId;
        cartItems.appendChild(listItem);

        // Show cart sidebar
        cartSidebar.style.display = 'block';
    }
</script>

</body>
</html>

