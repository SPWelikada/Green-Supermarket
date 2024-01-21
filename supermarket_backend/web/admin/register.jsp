<%--
  Created by IntelliJ IDEA.
  User: Dulan
  Date: 12/31/2023
  Time: 12:12 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

<div class="container">
    <h2>User Registration</h2>
    <form action="<%= request.getContextPath()%>/customer" method="post">
        <input type="hidden" name="action" value="register">

        <label for="contact">Contact:</label>
        <input type="text" id="contact" name="contact" required>

        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Register</button>
    </form>

    <p>Already have an account? <a href="/index1.jsp">Back to Sign In</a></p>
</div>

</body>
</html>
