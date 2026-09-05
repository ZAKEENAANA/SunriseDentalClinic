<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Add User - Sunrise Dental Clinic</title>

    <style>

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f4f8fb;
        }

        .header {
            background: #0b6fa4;
            color: white;
            padding: 20px;
            text-align: center;
        }

        .container {
            width: 450px;
            max-width: 90%;
            margin: 40px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.12);
        }

        h2 {
            text-align: center;
            color: #0b6fa4;
            margin-bottom: 25px;
        }

        .form-group {
            margin-bottom: 18px;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
        }

        input,
        select {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }

        .submit-btn {
            width: 100%;
            padding: 12px;
            background: #0b6fa4;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        .submit-btn:hover {
            background: #095b87;
        }

        .error {
            padding: 12px;
            margin-bottom: 20px;
            background: #f8d7da;
            color: #721c24;
            border-radius: 5px;
            text-align: center;
        }

        .success {
            padding: 12px;
            margin-bottom: 20px;
            background: #d4edda;
            color: #155724;
            border-radius: 5px;
            text-align: center;
        }

        .links {
            text-align: center;
            margin-top: 20px;
        }

        .links a {
            display: block;
            margin: 8px;
            color: #0b6fa4;
            text-decoration: none;
        }

        .links a:hover {
            text-decoration: underline;
        }

    </style>

</head>


<body>

    <div class="header">

        <h1>🦷 Sunrise Dental Clinic</h1>

        <p>User Management System</p>

    </div>


    <div class="container">

        <h2>Create New User</h2>


        <%
            String success = request.getParameter("success");
            String error = request.getParameter("error");

            if ("true".equals(success)) {
        %>

            <div class="success">
                User created successfully!
            </div>

        <%
            }

            if ("true".equals(error)) {
        %>

            <div class="error">
                Failed to create user.
                Username may already exist.
            </div>

        <%
            }
        %>


        <form action="addUser" method="post">


            <div class="form-group">

                <label for="username">
                    Username
                </label>

                <input type="text"
                       id="username"
                       name="username"
                       maxlength="50"
                       placeholder="Enter username"
                       required>

            </div>


            <div class="form-group">

                <label for="password">
                    Password
                </label>

                <input type="password"
                       id="password"
                       name="password"
                       maxlength="255"
                       placeholder="Enter password"
                       required>

            </div>


            <div class="form-group">

                <label for="role">
                    Role
                </label>

                <select id="role"
                        name="role">

                    <option value="staff">Staff</option>

                    <option value="admin">Admin</option>

                    <option value="dentist">Dentist</option>

                    <option value="receptionist">Receptionist</option>

                </select>

            </div>


            <button type="submit" class="submit-btn">
                Create User
            </button>

        </form>


        <div class="links">

            <a href="users">
                View All Users
            </a>

            <a href="dashboard.jsp">
                ← Back to Dashboard
            </a>

        </div>

    </div>

</body>

</html>