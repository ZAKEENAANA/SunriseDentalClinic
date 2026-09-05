<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.sunrise.dental.model.Appointment" %>

<%
    if (session.getAttribute("user") == null) {

        response.sendRedirect(
            request.getContextPath() + "/login.jsp"
        );

        return;
    }

    List<Appointment> appointments =
        (List<Appointment>) request.getAttribute("appointments");
%>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Appointments - Sunrise Dental Clinic</title>

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
            margin: 0 0 5px 0;
        }

        .header p {
            margin: 0;
        }

        .container {
            width: 95%;
            margin: 30px auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.12);
        }

        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            gap: 10px;
            flex-wrap: wrap;
        }

        h2 {
            color: #0b6fa4;
            margin: 0;
        }

        .top-buttons {
            display: flex;
            gap: 10px;
            flex-wrap: wrap;
        }

        .button {
            background: #0b6fa4;
            color: white;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
            display: inline-block;
        }

        .button:hover {
            background: #095b87;
        }

        .dashboard-button {
            background: #0b6fa4;
        }

        .dashboard-button:hover {
            background: #095b87;
        }

        .search-button {
            background: #198754;
        }

        .search-button:hover {
            background: #146c43;
        }

        .success {
            padding: 12px;
            margin-bottom: 20px;
            background: #d4edda;
            color: #155724;
            border-radius: 5px;
            text-align: center;
        }

        .error {
            padding: 12px;
            margin-bottom: 20px;
            background: #f8d7da;
            color: #721c24;
            border-radius: 5px;
            text-align: center;
        }

        .table-container {
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th {
            background: #0b6fa4;
            color: white;
            padding: 12px;
            text-align: left;
        }

        td {
            padding: 11px;
            border-bottom: 1px solid #ddd;
            vertical-align: top;
        }

        tr:hover {
            background: #f1f7fa;
        }

        .no-data {
            text-align: center;
            padding: 30px;
            color: #777;
        }

        .status-form {
            display: flex;
            flex-direction: column;
            align-items: stretch;
            gap: 8px;
            min-width: 190px;
        }

        .status-select {
            padding: 7px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background: white;
            width: 100%;
        }

        .reschedule-fields {
            display: none;
            flex-direction: column;
            gap: 6px;
            padding: 8px;
            background: #f4f8fb;
            border-radius: 5px;
            border: 1px solid #d9e5ec;
        }

        .reschedule-title {
            color: #0b6fa4;
            font-size: 12px;
            font-weight: bold;
        }

        .reschedule-fields label {
            font-size: 12px;
            font-weight: bold;
            color: #444;
        }

        .reschedule-fields input {
            padding: 7px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 100%;
        }

        .update-btn {
            padding: 7px 12px;
            background: #0b6fa4;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .update-btn:hover {
            background: #095b87;
        }

        .delete-btn {
            padding: 7px 12px;
            background: #dc3545;
            color: white;
            border-radius: 5px;
            text-decoration: none;
            cursor: pointer;
        }

        .delete-btn:hover {
            background: #b02a37;
        }

        .action-buttons {
            display: flex;
            align-items: center;
            gap: 8px;
            flex-wrap: wrap;
        }

        @media (max-width: 800px) {

            .top-bar {
                flex-direction: column;
                gap: 15px;
                align-items: stretch;
            }

            .top-buttons {
                flex-direction: column;
            }

            .button {
                text-align: center;
            }

            th,
            td {
                padding: 8px;
                font-size: 13px;
            }

            .status-form {
                min-width: 160px;
            }

            .action-buttons {
                flex-direction: column;
                align-items: stretch;
            }
        }

    </style>

</head>

<body>

    <div class="header">

        <h1>
            🦷 Sunrise Dental Clinic
        </h1>

        <p>
            Appointment Management System
        </p>

    </div>


    <div class="container">


        <div class="top-bar">

            <h2>
                Appointment List
            </h2>


            <div class="top-buttons">


                <!-- Dashboard -->
                <a
                    href="<%= request.getContextPath() %>/dashboard"
                    class="button dashboard-button">

                    🏠 Dashboard

                </a>


                <!-- Search Appointment -->
                <a
                    href="<%= request.getContextPath() %>/search-appointment.jsp"
                    class="button search-button">

                    🔍 Search Appointment

                </a>


                <!-- Book New Appointment -->
                <a
                    href="<%= request.getContextPath() %>/add-appointment.jsp"
                    class="button">

                    + Book New Appointment

                </a>

            </div>

        </div>


        <%

            String success =
                request.getParameter("success");

            String statusUpdated =
                request.getParameter("statusUpdated");

            String deleted =
                request.getParameter("deleted");

            String error =
                request.getParameter("error");

        %>


        <% if ("true".equals(success)) { %>

            <div class="success">

                ✅ Appointment created successfully!

            </div>

        <% } %>


        <% if ("true".equals(statusUpdated)) { %>

            <div class="success">

                ✅ Appointment updated successfully!

            </div>

        <% } %>


        <% if ("true".equals(deleted)) { %>

            <div class="success">

                ✅ Appointment deleted successfully!

            </div>

        <% } %>


        <% if ("true".equals(error)) { %>

            <div class="error">

                ❌ Failed to process appointment.
                Please try again.

            </div>

        <% } %>


        <% if ("invalid".equals(error)) { %>

            <div class="error">

                ❌ Invalid appointment number.

            </div>

        <% } %>


        <%

            if (appointments != null
                    && !appointments.isEmpty()) {

        %>


        <div class="table-container">

            <table>

                <thead>

                    <tr>

                        <th>Appointment ID</th>

                        <th>Patient ID</th>

                        <th>Dentist Name</th>

                        <th>Date</th>

                        <th>Time</th>

                        <th>Reason</th>

                        <th>Status</th>

                        <th>Actions</th>

                    </tr>

                </thead>


                <tbody>


                <%

                    for (Appointment appointment :
                            appointments) {

                %>


                    <tr>


                        <td>
                            <%= appointment.getAppointmentId() %>
                        </td>


                        <td>
                            <%= appointment.getPatientId() %>
                        </td>


                        <td>

                            <%= appointment.getDentistName() != null
                                && !appointment.getDentistName()
                                        .trim().isEmpty()
                                ? appointment.getDentistName()
                                : "-" %>

                        </td>


                        <td>
                            <%= appointment.getAppointmentDate() %>
                        </td>


                        <td>
                            <%= appointment.getAppointmentTime() %>
                        </td>


                        <td>

                            <%= appointment.getReason() != null
                                && !appointment.getReason()
                                        .trim().isEmpty()
                                ? appointment.getReason()
                                : "-" %>

                        </td>


                        <td>


                            <form
                                action="<%= request.getContextPath() %>/updateAppointmentStatus"
                                method="post"
                                class="status-form"
                                onsubmit="return validateReschedule(this);">


                                <input
                                    type="hidden"
                                    name="appointmentId"
                                    value="<%= appointment.getAppointmentId() %>">


                                <select
                                    name="status"
                                    class="status-select"
                                    onchange="toggleRescheduleFields(this);">


                                    <option
                                        value="Scheduled"
                                        <%= "Scheduled".equals(
                                                appointment.getStatus())
                                                ? "selected"
                                                : "" %>>

                                        Scheduled

                                    </option>


                                    <option
                                        value="Completed"
                                        <%= "Completed".equals(
                                                appointment.getStatus())
                                                ? "selected"
                                                : "" %>>

                                        Completed

                                    </option>


                                    <option
                                        value="Cancelled"
                                        <%= "Cancelled".equals(
                                                appointment.getStatus())
                                                ? "selected"
                                                : "" %>>

                                        Cancelled

                                    </option>


                                    <option
                                        value="Rescheduled"
                                        <%= "Rescheduled".equals(
                                                appointment.getStatus())
                                                ? "selected"
                                                : "" %>>

                                        Rescheduled

                                    </option>


                                </select>


                                <div class="reschedule-fields">


                                    <div class="reschedule-title">

                                        🔄 New Appointment Date & Time

                                    </div>


                                    <label>
                                        New Date
                                    </label>


                                    <input
                                        type="date"
                                        name="appointmentDate">


                                    <label>
                                        New Time
                                    </label>


                                    <input
                                        type="time"
                                        name="appointmentTime">


                                </div>


                                <button
                                    type="submit"
                                    class="update-btn">

                                    Update

                                </button>


                            </form>


                        </td>


                        <td>


                            <div class="action-buttons">


                                <a
                                    href="<%= request.getContextPath() %>/deleteAppointment?appointmentId=<%= appointment.getAppointmentId() %>"
                                    class="delete-btn"
                                    onclick="return confirm('Are you sure you want to delete this appointment?');">

                                    Delete

                                </a>


                            </div>


                        </td>


                    </tr>


                <%

                    }

                %>


                </tbody>

            </table>

        </div>


        <%

            } else {

        %>


            <div class="no-data">

                📭 No appointments found.

            </div>


        <%

            }

        %>


    </div>


    <script>

        function toggleRescheduleFields(selectElement) {

            var form =
                selectElement.closest("form");

            var fields =
                form.querySelector(".reschedule-fields");

            var dateInput =
                form.querySelector(
                    "input[name='appointmentDate']"
                );

            var timeInput =
                form.querySelector(
                    "input[name='appointmentTime']"
                );


            if (selectElement.value === "Rescheduled") {

                fields.style.display = "flex";

                dateInput.required = true;

                timeInput.required = true;

            } else {

                fields.style.display = "none";

                dateInput.required = false;

                timeInput.required = false;

                dateInput.value = "";

                timeInput.value = "";
            }
        }


        function validateReschedule(form) {

            var status =
                form.querySelector(
                    "select[name='status']"
                ).value;


            if (status === "Rescheduled") {

                var date =
                    form.querySelector(
                        "input[name='appointmentDate']"
                    ).value;

                var time =
                    form.querySelector(
                        "input[name='appointmentTime']"
                    ).value;


                if (!date || !time) {

                    alert(
                        "Please select a new appointment date and time."
                    );

                    return false;
                }
            }


            return true;
        }


        document.addEventListener(
            "DOMContentLoaded",
            function() {

                var statusSelects =
                    document.querySelectorAll(
                        ".status-select"
                    );


                statusSelects.forEach(
                    function(selectElement) {

                        toggleRescheduleFields(
                            selectElement
                        );

                    }
                );

            }
        );

    </script>


</body>

</html>