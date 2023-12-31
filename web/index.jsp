<%--
  Created by IntelliJ IDEA.
  User: Dulan
  Date: 12/31/2023
  Time: 3:39 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Sign In</title>
    <link rel="stylesheet" href="./styles.css">
</head>
<body>

<div class="container">
    <h2>User Sign In</h2>
    <form action="/signin" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit"><a href="/home.jsp">Sign In</a></button>
        <p>New here? <a href="/register.jsp">Create a new account</a></p>
    </form>

    <c:if test="${param.error eq 'true'}">
        <p style="color: red;">Sign-in failed. Please check your username and password.</p>
    </c:if>
</div>

</body>
</html>

