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
    <link rel="stylesheet" href="admin/css/styles.css">
</head>
<body>

<div class="container">
    <h2>User Sign In</h2>
    <form action="<%= request.getContextPath()%>/signin" method="post" onsubmit="return validateForm()">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Sign In</button>
        <p>New here? <a href="/register.jsp">Create a new account</a></p>
    </form>

    <script>
        function validateForm() {
            var urlParams = new URLSearchParams(window.location.search);
            var errorParam = urlParams.get('error');

            if (errorParam === 'true') {
                var errorMessage = document.createElement('p');
                errorMessage.style.color = 'red';
                errorMessage.textContent = 'Sign-in failed. Please check your username and password.';
                document.querySelector('.container').appendChild(errorMessage);
                return false;
            }

            // Remove the else block
            return true;
        }

    </script>
</div>

</body>
</html>
