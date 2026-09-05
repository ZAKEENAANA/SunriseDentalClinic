<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    // Check whether the user is logged in
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String success = request.getParameter("success");
    String error = request.getParameter("error");
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Book Appointment - Sunrise Dental Clinic</title>

    <style>

        * {
            box-sizing: border-box;
        }

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

        .header h1 {
            margin: 0 0 8px 0;
        }

        .header p {
            margin: 0;
        }

        .container {
            width: 600px;
            max-width: 95%;
            margin: 40px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.12);
        }

        h2 {
            text-align: center;
            color: #0b6fa4;
            margin-top: 0;
            margin-bottom: 25px;
        }

        .form-group {
            margin-bottom: 18px;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
            color: #333;
        }

        .required {
            color: red;
        }

        input,
        textarea {
            width: 100%;
            padding: 11px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
            font-family: Arial, sans-serif;
        }

        input:focus,
        textarea:focus {
            outline: none;
            border-color: #0b6fa4;
            box-shadow: 0 0 4px rgba(11, 111, 164, 0.25);
        }

        textarea {
            height: 90px;
            resize: vertical;
        }

        .submit-btn {
            width: 100%;
            padding: 13px;
            background: #0b6fa4;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
        }

        .submit-btn:hover {
            background: #095b87;
        }

        .success {
            padding: 12px;
            margin-bottom: 20px;
            background: #d4edda;
            color: #155724;
            border-radius: 5px;
            text-align: center;
            font-weight: bold;
        }

        .error {
            padding: 12px;
            margin-bottom: 20px;
            background: #f8d7da;
            color: #721c24;
            border-radius: 5px;
            text-align: center;
            font-weight: bold;
        }

        .back {
            display: block;
            text-align: center;
            margin-top: 18px;
            color: #0b6fa4;
            text-decoration: none;
            font-weight: bold;
        }

        .back:hover {
            text-decoration: underline;
        }

        .help-text {
            display: block;
            margin-top: 5px;
            font-size: 12px;
            color: #777;
        }

        @media (max-width: 700px) {

            .container {
                padding: 20px;
            }

            .header h1 {
                font-size: 24px;
            }

        }

    </style>

</head>


<body>

    <!-- Header -->

    <div class="header">

        <h1>🦷 Sunrise Dental Clinic</h1>

        <p>Appointment Management System</p>

    </div>


    <!-- Main Container -->

    <div class="container">

        <h2>Book New Appointment</h2>


        <!-- Success Message -->

        <% if ("true".equals(success)) { %>

            <div class="success">
                Appointment created successfully!
            </div>

        <% } %>


        <!-- Error Message -->

        <% if ("true".equals(error)) { %>

            <div class="error">
                Failed to create appointment.
                Please check the entered information and try again.
            </div>

        <% } %>


        <!-- Appointment Form -->

        <form action="addAppointment"
              method="post">


            <!-- Patient ID -->

            <div class="form-group">

                <label for="patientId">
                    Patient ID <span class="required">*</span>
                </label>

                <input type="number"
                       id="patientId"
                       name="patientId"
                       min="1"
                       placeholder="Enter patient ID"
                       required>

                <span class="help-text">
                    Enter the ID of an existing patient.
                </span>

            </div>


            <!-- Appointment Date -->

            <div class="form-group">

                <label for="appointmentDate">
                    Appointment Date <span class="required">*</span>
                </label>

                <input type="date"
                       id="appointmentDate"
                       name="appointmentDate"
                       required>

            </div>


            <!-- Appointment Time -->

            <div class="form-group">

                <label for="appointmentTime">
                    Appointment Time <span class="required">*</span>
                </label>

                <input type="time"
                       id="appointmentTime"
                       name="appointmentTime"
                       required>

            </div>


            <!-- Reason -->

            <div class="form-group">

                <label for="reason">
                    Reason for Appointment
                </label>

                <textarea id="reason"
                          name="reason"
                          maxlength="255"
                          placeholder="Example: Dental check-up, tooth pain, cleaning..."></textarea>

            </div>


            <!-- Submit -->

            <button type="submit"
                    class="submit-btn">

                📅 Book Appointment

            </button>

        </form>


        <!-- Navigation -->

        <a href="appointments"
           class="back">

            📋 View All Appointments

        </a>


        <a href="dashboard"
           class="back">

            ← Back to Dashboard

        </a>

    </div>


    <!-- Prevent selecting a past date -->

    <script>

        document.addEventListener("DOMContentLoaded", function () {

            const dateInput =
                document.getElementById("appointmentDate");

            const today =
                new Date().toISOString().split("T")[0];

            dateInput.min = today;

        });

    </script>

</body>

</html>