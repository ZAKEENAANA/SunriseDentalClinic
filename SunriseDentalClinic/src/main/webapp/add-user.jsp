<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Add User - Sunrise Dental Clinic</title>

    <style>

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f4f7fb;
        }

        .container {
            width: 500px;
            margin: 60px auto;
            background: white;
            padding: 35px;
            border-radius: 12px;
            box-shadow: 0 5px 20px rgba(0,0,0,0.10);
        }

        h2 {
            text-align: center;
            margin-bottom: 30px;
            color: #1f2937;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #374151;
        }

        input,
        select {
            width: 100%;
            padding: 12px;
            border: 1px solid #d1d5db;
            border-radius: 7px;
            font-size: 15px;
        }

        input:focus,
        select:focus {
            outline: none;
            border-color: #2563eb;
        }

        .btn {
            width: 100%;
            padding: 13px;
            border: none;
            border-radius: 7px;
            background: #2563eb;
            color: white;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
        }

        .btn:hover {
            background: #1d4ed8;
        }

        .back {
            display: block;
            text-align: center;
            margin-top: 20px;
            text-decoration: none;
            color: #2563eb;
        }

        .error {
            background: #fee2e2;
            color: #b91c1c;
            padding: 12px;
            border-radius: 7px;
            margin-bottom: 20px;
            text-align: center;
        }

    </style>

</head>

<body>

<div class="container">

    <h2>👤 Add New User</h2>


    <%-- Error Messages --%>

    <%
        String error =
                request.getParameter("error");

        if ("empty".equals(error)) {
    %>

        <div class="error">
            Please fill in all fields.
        </div>

    <%
        } else if ("true".equals(error)) {
    %>

        <div class="error">
            Failed to add user.
            Username may already exist.
        </div>

    <%
        }
    %>


    <form action="<%= request.getContextPath() %>/addUser"
          method="post">


        <!-- Username -->

        <div class="form-group">

            <label for="username">
                Username
            </label>

            <input
                type="text"
                id="username"
                name="username"
                placeholder="Enter username"
                maxlength="50"
                required>

        </div>


        <!-- Password -->

        <div class="form-group">

            <label for="password">
                Password
            </label>

            <input
                type="password"
                id="password"
                name="password"
                placeholder="Enter password"
                maxlength="255"
                required>

        </div>


        <!-- Role -->

        <div class="form-group">

            <label for="role">
                Role
            </label>

            <select
                id="role"
                name="role"
                required>

                <option value="STAFF">
                    STAFF
                </option>

                <option value="ADMIN">
                    ADMIN
                </option>

            </select>

        </div>


        <!-- Submit -->

        <button
            type="submit"
            class="btn">

            ➕ Add User

        </button>

    </form>


    <a
        href="<%= request.getContextPath() %>/dashboard"
        class="back">

        ← Back to Dashboard

    </a>

</div>

</body>
</html>