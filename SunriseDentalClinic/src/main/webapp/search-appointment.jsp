<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Search Appointment - Sunrise Dental Clinic</title>

    <style>

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background: #f4f7fb;
        }

        .header {
            background: #0b6fa4;
            color: white;
            padding: 20px;
            text-align: center;
        }

        .header h1 {
            margin: 0 0 5px 0;
        }

        .header p {
            margin: 0;
        }

        .container {
            width: 90%;
            max-width: 600px;
            margin: 50px auto;
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
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #333;
        }

        input {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 16px;
        }

        input:focus {
            outline: none;
            border-color: #0b6fa4;
        }

        .search-btn {
            width: 100%;
            padding: 12px;
            background: #0b6fa4;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
        }

        .search-btn:hover {
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

        .info {
            margin-top: 20px;
            padding: 15px;
            background: #e8f4f8;
            color: #333;
            border-radius: 6px;
            text-align: center;
        }

        .back {
            display: block;
            margin-top: 20px;
            text-align: center;
            color: #0b6fa4;
            text-decoration: none;
        }

        .back:hover {
            text-decoration: underline;
        }

    </style>

</head>

<body>

    <div class="header">

        <h1>🦷 Sunrise Dental Clinic</h1>

        <p>Appointment Management System</p>

    </div>

    <div class="container">

        <h2>🔍 Search Appointment</h2>

        <%
            String error = request.getParameter("error");
        %>

        <% if ("notfound".equals(error)) { %>

            <div class="error">
                ❌ Appointment not found.
                Please check the Appointment Number.
            </div>

        <% } else if ("invalid".equals(error)) { %>

            <div class="error">
                ❌ Invalid Appointment Number.
                Please enter a valid number.
            </div>

        <% } else if ("true".equals(error)) { %>

            <div class="error">
                ❌ An error occurred while searching.
                Please try again.
            </div>

        <% } %>

        <form
            action="<%= request.getContextPath() %>/searchAppointment"
            method="get">

            <div class="form-group">

                <label for="appointmentId">
                    Appointment Number
                </label>

                <input
                    type="number"
                    id="appointmentId"
                    name="appointmentId"
                    placeholder="Enter Appointment Number"
                    min="1"
                    required>

            </div>

            <button
                type="submit"
                class="search-btn">

                🔍 Search Appointment

            </button>

        </form>

        <div class="info">

            Enter the Appointment Number to view
            complete appointment details.

        </div>

        <a
            href="<%= request.getContextPath() %>/appointments"
            class="back">

            ← Back to Appointment List

        </a>

    </div>

</body>

</html>