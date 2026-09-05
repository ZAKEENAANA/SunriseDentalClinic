```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Add Appointment - Sunrise Dental Clinic</title>

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
            background: #0d6efd;
            color: white;
            padding: 20px;
            text-align: center;
        }

        .header h1 {
            margin: 0;
            font-size: 28px;
        }

        .container {
            width: 90%;
            max-width: 700px;
            margin: 40px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        }

        .container h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #333;
        }

        .form-group {
            margin-bottom: 18px;
        }

        label {
            display: block;
            margin-bottom: 7px;
            font-weight: bold;
            color: #333;
        }

        input,
        select,
        textarea {
            width: 100%;
            padding: 11px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 15px;
        }

        textarea {
            resize: vertical;
            min-height: 100px;
        }

        input:focus,
        select:focus,
        textarea:focus {
            outline: none;
            border-color: #0d6efd;
        }

        .btn-container {
            display: flex;
            gap: 10px;
            margin-top: 25px;
        }

        .btn {
            flex: 1;
            padding: 12px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
            text-decoration: none;
            text-align: center;
        }

        .btn-primary {
            background: #0d6efd;
            color: white;
        }

        .btn-primary:hover {
            background: #0b5ed7;
        }

        .btn-secondary {
            background: #6c757d;
            color: white;
        }

        .btn-secondary:hover {
            background: #5c636a;
        }

        .error {
            background: #f8d7da;
            color: #842029;
            padding: 12px;
            border-radius: 6px;
            margin-bottom: 20px;
            text-align: center;
        }

        .success {
            background: #d1e7dd;
            color: #0f5132;
            padding: 12px;
            border-radius: 6px;
            margin-bottom: 20px;
            text-align: center;
        }

    </style>

</head>

<body>

    <div class="header">

        <h1>Sunrise Dental Clinic</h1>

    </div>

    <div class="container">

        <h2>📅 Add New Appointment</h2>

        <%
            String error = request.getParameter("error");
            String success = request.getParameter("success");
        %>

        <% if ("true".equals(error)) { %>

            <div class="error">
                ❌ Failed to add appointment.
                Please check your details.
            </div>

        <% } %>

        <% if ("true".equals(success)) { %>

            <div class="success">
                ✅ Appointment added successfully.
            </div>

        <% } %>

        <form
            action="<%= request.getContextPath() %>/addAppointment"
            method="post">

            <!-- Patient ID -->

            <div class="form-group">

                <label for="patientId">
                    Patient ID
                </label>

                <input
                    type="number"
                    id="patientId"
                    name="patientId"
                    placeholder="Enter Patient ID"
                    required>

            </div>

            <!-- Dentist Name -->

            <div class="form-group">

                <label for="dentistName">
                    Dentist Name
                </label>

                <input
                    type="text"
                    id="dentistName"
                    name="dentistName"
                    placeholder="Enter dentist name"
                    required>

            </div>

            <!-- Appointment Date -->

            <div class="form-group">

                <label for="appointmentDate">
                    Appointment Date
                </label>

                <input
                    type="date"
                    id="appointmentDate"
                    name="appointmentDate"
                    required>

            </div>

            <!-- Appointment Time -->

            <div class="form-group">

                <label for="appointmentTime">
                    Appointment Time
                </label>

                <input
                    type="time"
                    id="appointmentTime"
                    name="appointmentTime"
                    required>

            </div>

            <!-- Reason -->

            <div class="form-group">

                <label for="reason">
                    Reason
                </label>

                <textarea
                    id="reason"
                    name="reason"
                    placeholder="Enter reason for appointment..."
                    required></textarea>

            </div>

            <!-- Status -->

            <div class="form-group">

                <label for="status">
                    Status
                </label>

                <select
                    id="status"
                    name="status">

                    <option value="Scheduled">
                        Scheduled
                    </option>

                    <option value="Completed">
                        Completed
                    </option>

                    <option value="Cancelled">
                        Cancelled
                    </option>

                    <option value="Rescheduled">
                        Rescheduled
                    </option>

                </select>

            </div>

            <!-- Buttons -->

            <div class="btn-container">

                <button
                    type="submit"
                    class="btn btn-primary">

                    ➕ Add Appointment

                </button>

                <a
                    href="<%= request.getContextPath() %>/appointments"
                    class="btn btn-secondary">

                    📋 View Appointments

                </a>

            </div>

        </form>

    </div>

</body>

</html>
```
