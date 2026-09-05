<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

```
<meta charset="UTF-8">

<title>Sunrise Dental Clinic - Login</title>

<style>

    body {
        margin: 0;
        font-family: Arial, sans-serif;
        background: #f4f8fb;
    }

    .header {
        background: #0b6fa4;
        color: white;
        padding: 25px;
        text-align: center;
    }

    .header h1 {
        margin: 0;
    }

    .container {
        width: 350px;
        max-width: 90%;
        margin: 70px auto;
        background: white;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.15);
    }

    h2 {
        text-align: center;
        color: #0b6fa4;
    }

    label {
        display: block;
        margin-top: 15px;
        margin-bottom: 5px;
        font-weight: bold;
    }

    input[type="text"],
    input[type="password"] {
        width: 100%;
        padding: 10px;
        box-sizing: border-box;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    input[type="submit"] {
        width: 100%;
        margin-top: 25px;
        padding: 12px;
        background: #0b6fa4;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
    }

    input[type="submit"]:hover {
        background: #095b87;
    }

    .error {
        padding: 12px;
        margin-top: 15px;
        background: #f8d7da;
        color: #721c24;
        border-radius: 5px;
        text-align: center;
    }

    .success {
        padding: 12px;
        margin-top: 15px;
        background: #d1e7dd;
        color: #0f5132;
        border-radius: 5px;
        text-align: center;
    }

</style>
```

</head>

<body>

```
<div class="header">

    <h1>🦷 Sunrise Dental Clinic</h1>

    <p>Dental Appointment & Patient Management System</p>

</div>


<div class="container">

    <h2>Staff Login</h2>


    <form action="<%= request.getContextPath() %>/login" method="post">

        <label for="username">
            Username
        </label>

        <input type="text"
               id="username"
               name="username"
               placeholder="Enter username"
               required>


        <label for="password">
            Password
        </label>

        <input type="password"
               id="password"
               name="password"
               placeholder="Enter password"
               required>


        <input type="submit" value="Login">

    </form>


    <%

        String error = request.getParameter("error");

        if ("true".equals(error)) {

    %>

        <div class="error">
            Invalid username or password.
            Please try again.
        </div>

    <%

        }

    %>


    <%

        String logout = request.getParameter("logout");

        if ("true".equals(logout)) {

    %>

        <div class="success">
            You have been logged out successfully.
        </div>

    <%

        }

    %>


</div>
```

</body>

</html>
