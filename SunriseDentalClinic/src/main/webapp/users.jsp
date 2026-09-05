<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.sunrise.dental.model.User" %>

<%
    List<User> users = (List<User>) request.getAttribute("users");
%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>User Management - Sunrise Dental Clinic</title>

    <style>

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f4f7fb;
            color: #333;
        }

        .header {
            background: #198754;
            color: white;
            padding: 20px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .header h1 {
            margin: 0;
            font-size: 24px;
        }

        .container {
            width: 90%;
            max-width: 1100px;
            margin: 40px auto;
        }

        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 25px;
        }

        .top-bar h2 {
            margin: 0;
            color: #198754;
        }

        .btn {
            text-decoration: none;
            padding: 10px 18px;
            border-radius: 6px;
            color: white;
            font-weight: bold;
            display: inline-block;
        }

        .btn-add {
            background: #0d6efd;
        }

        .btn-dashboard {
            background: #6c757d;
            margin-left: 8px;
        }

        .btn-delete {
            background: #dc3545;
            padding: 7px 12px;
            font-size: 13px;
        }

        .success {
            background: #d1e7dd;
            color: #0f5132;
            padding: 12px 15px;
            border-radius: 6px;
            margin-bottom: 20px;
        }

        .error {
            background: #f8d7da;
            color: #842029;
            padding: 12px 15px;
            border-radius: 6px;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
            box-shadow: 0 3px 12px rgba(0,0,0,0.08);
            border-radius: 8px;
            overflow: hidden;
        }

        th {
            background: #198754;
            color: white;
            padding: 14px;
            text-align: left;
        }

        td {
            padding: 13px 14px;
            border-bottom: 1px solid #eee;
        }

        tr:hover {
            background: #f8f9fa;
        }

        .role-admin {
            color: #dc3545;
            font-weight: bold;
        }

        .role-staff {
            color: #198754;
            font-weight: bold;
        }

        .empty {
            text-align: center;
            padding: 30px;
            background: white;
            border-radius: 8px;
            color: #777;
        }

    </style>

</head>

<body>

    <div class="header">

        <h1>🦷 Sunrise Dental Clinic</h1>

        <span>User Management</span>

    </div>


    <div class="container">

        <div class="top-bar">

            <h2>User List</h2>

            <div>

                <a href="<%= request.getContextPath() %>/addUser"
                   class="btn btn-add">
                    + Add User
                </a>

                <a href="<%= request.getContextPath() %>/dashboard"
                   class="btn btn-dashboard">
                    Dashboard
                </a>

            </div>

        </div>


        <% if ("true".equals(request.getParameter("success"))) { %>

            <div class="success">
                User added successfully!
            </div>

        <% } %>


        <% if ("true".equals(request.getParameter("deleted"))) { %>

            <div class="success">
                User deleted successfully!
            </div>

        <% } %>


        <% if ("true".equals(request.getParameter("error"))) { %>

            <div class="error">
                Unable to complete the requested operation.
            </div>

        <% } %>


        <% if (users != null && !users.isEmpty()) { %>

            <table>

                <thead>

                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Role</th>
                        <th>Action</th>
                    </tr>

                </thead>

                <tbody>

                    <% for (User user : users) { %>

                        <tr>

                            <td>
                                <%= user.getId() %>
                            </td>

                            <td>
                                <%= user.getUsername() %>
                            </td>

                            <td>

                                <% if ("ADMIN".equalsIgnoreCase(user.getRole())) { %>

                                    <span class="role-admin">
                                        ADMIN
                                    </span>

                                <% } else { %>

                                    <span class="role-staff">
                                        STAFF
                                    </span>

                                <% } %>

                            </td>

                            <td>

                                <a class="btn btn-delete"
                                   href="<%= request.getContextPath() %>/deleteUser?id=<%= user.getId() %>"
                                   onclick="return confirm('Are you sure you want to delete this user?');">
                                    Delete
                                </a>

                            </td>

                        </tr>

                    <% } %>

                </tbody>

            </table>

        <% } else { %>

            <div class="empty">
                <h3>No users found</h3>
                <p>Please add a user to the system.</p>
            </div>

        <% } %>

    </div>

</body>
</html>