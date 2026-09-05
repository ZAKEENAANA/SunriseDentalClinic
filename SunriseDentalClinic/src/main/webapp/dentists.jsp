<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.sunrise.dental.model.Dentist" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dentists - Sunrise Dental Clinic</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f9;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 90%;
            margin: 40px auto;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 25px;
        }

        h2 {
            color: #2c3e50;
            margin: 0;
        }

        .btn {
            padding: 10px 18px;
            border-radius: 5px;
            text-decoration: none;
            color: white;
            display: inline-block;
        }

        .btn-add {
            background-color: #27ae60;
        }

        .btn-add:hover {
            background-color: #219150;
        }

        .btn-back {
            background-color: #7f8c8d;
            margin-left: 8px;
        }

        .btn-back:hover {
            background-color: #636e72;
        }

        .success {
            background-color: #d4edda;
            color: #155724;
            padding: 12px;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        .error {
            background-color: #f8d7da;
            color: #721c24;
            padding: 12px;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 3px 10px rgba(0, 0, 0, 0.08);
        }

        th {
            background-color: #2c3e50;
            color: white;
            padding: 14px;
            text-align: left;
        }

        td {
            padding: 13px;
            border-bottom: 1px solid #ddd;
        }

        tr:hover {
            background-color: #f8f9fa;
        }

        .empty {
            text-align: center;
            padding: 30px;
            color: #777;
        }
    </style>
</head>

<body>

<div class="container">

    <div class="header">

        <h2>🦷 Dentist Management</h2>

        <div>
            <a href="add-dentist.jsp" class="btn btn-add">
                + Add Dentist
            </a>

            <a href="index.jsp" class="btn btn-back">
                Dashboard
            </a>
        </div>

    </div>


    <%
        String success = request.getParameter("success");
        String error = request.getParameter("error");

        if ("true".equals(success)) {
    %>

        <div class="success">
            Dentist added successfully!
        </div>

    <%
        }

        if ("true".equals(error)) {
    %>

        <div class="error">
            Unable to complete the operation. Please try again.
        </div>

    <%
        }

        List<Dentist> dentists =
            (List<Dentist>) request.getAttribute("dentists");
    %>


    <table>

        <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Specialization</th>
                <th>Phone</th>
                <th>Email</th>
            </tr>
        </thead>

        <tbody>

        <%
            if (dentists != null && !dentists.isEmpty()) {

                for (Dentist dentist : dentists) {
        %>

            <tr>
                <td><%= dentist.getDentistId() %></td>

                <td><%= dentist.getFirstName() %></td>

                <td><%= dentist.getLastName() %></td>

                <td><%= dentist.getSpecialization() %></td>

                <td><%= dentist.getPhone() %></td>

                <td><%= dentist.getEmail() %></td>
            </tr>

        <%
                }

            } else {
        %>

            <tr>
                <td colspan="6" class="empty">
                    No dentists found.
                </td>
            </tr>

        <%
            }
        %>

        </tbody>

    </table>

</div>

</body>
</html>