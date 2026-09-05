<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.sunrise.dental.model.Appointment" %>
<%@ page import="com.sunrise.dental.model.Patient" %>

<%
    Appointment appointment =
            (Appointment) request.getAttribute("appointment");

    Patient patient =
            (Patient) request.getAttribute("patient");

    if (appointment == null) {
        response.sendRedirect(
                request.getContextPath()
                + "/search-appointment.jsp"
        );
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Appointment Details - Sunrise Dental Clinic</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background: #f4f7f9;
            margin: 0;
            padding: 40px;
        }

        .container {
            max-width: 900px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }

        h1 {
            text-align: center;
            color: #1f4e79;
            margin-bottom: 30px;
        }

        h2 {
            color: #1f4e79;
            border-bottom: 2px solid #1f4e79;
            padding-bottom: 10px;
            margin-top: 30px;
        }

        .details-grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 15px;
            margin-top: 20px;
        }

        .detail-box {
            background: #f8fafc;
            padding: 15px;
            border-radius: 8px;
            border: 1px solid #e1e5e8;
        }

        .label {
            font-weight: bold;
            color: #555;
            display: block;
            margin-bottom: 5px;
        }

        .value {
            color: #222;
            font-size: 16px;
        }

        .full-width {
            grid-column: 1 / -1;
        }

        .buttons {
            text-align: center;
            margin-top: 30px;
        }

        .btn {
            display: inline-block;
            padding: 12px 20px;
            margin: 5px;
            text-decoration: none;
            border-radius: 6px;
            color: white;
            background: #1f4e79;
        }

        .btn:hover {
            opacity: 0.9;
        }

    </style>

</head>

<body>

<div class="container">

    <h1>🦷 Sunrise Dental Clinic</h1>

    <h2>👤 Patient Details</h2>

    <div class="details-grid">

        <div class="detail-box">

            <span class="label">Patient ID</span>

            <span class="value">
                <%= patient != null
                    ? patient.getPatientId()
                    : "-" %>
            </span>

        </div>

        <div class="detail-box">

            <span class="label">Patient Name</span>

            <span class="value">
                <%= patient != null
                    ? patient.getFirstName() + " " + patient.getLastName()
                    : "-" %>
            </span>

        </div>

        <div class="detail-box">

            <span class="label">Phone</span>

            <span class="value">
                <%= patient != null
                    ? patient.getPhone()
                    : "-" %>
            </span>

        </div>

        <div class="detail-box">

            <span class="label">Email</span>

            <span class="value">
                <%= patient != null
                    ? patient.getEmail()
                    : "-" %>
            </span>

        </div>

        <div class="detail-box">

            <span class="label">Date of Birth</span>

            <span class="value">
                <%= patient != null
                    ? patient.getDateOfBirth()
                    : "-" %>
            </span>

        </div>

        <div class="detail-box">

            <span class="label">Gender</span>

            <span class="value">
                <%= patient != null
                    ? patient.getGender()
                    : "-" %>
            </span>

        </div>

        <div class="detail-box full-width">

            <span class="label">Address</span>

            <span class="value">
                <%= patient != null
                    ? patient.getAddress()
                    : "-" %>
            </span>

        </div>

    </div>


    <h2>📅 Appointment Details</h2>

    <div class="details-grid">

        <div class="detail-box">

            <span class="label">Appointment Number</span>

            <span class="value">
                <%= appointment.getAppointmentId() %>
            </span>

        </div>

        <div class="detail-box">

            <span class="label">Dentist Name</span>

            <span class="value">
                <%= appointment.getDentistName() != null
                    && !appointment.getDentistName().trim().isEmpty()
                    ? appointment.getDentistName()
                    : "-" %>
            </span>

        </div>

        <div class="detail-box">

            <span class="label">Appointment Date</span>

            <span class="value">
                <%= appointment.getAppointmentDate() %>
            </span>

        </div>

        <div class="detail-box">

            <span class="label">Appointment Time</span>

            <span class="value">
                <%= appointment.getAppointmentTime() %>
            </span>

        </div>

        <div class="detail-box full-width">

            <span class="label">Reason / Treatment</span>

            <span class="value">
                <%= appointment.getReason() != null
                    && !appointment.getReason().trim().isEmpty()
                    ? appointment.getReason()
                    : "-" %>
            </span>

        </div>

        <div class="detail-box">

            <span class="label">Status</span>

            <span class="value">
                <%= appointment.getStatus() != null
                    ? appointment.getStatus()
                    : "-" %>
            </span>

        </div>

    </div>


    <div class="buttons">

        <a class="btn"
           href="<%= request.getContextPath() %>/search-appointment.jsp">
            🔍 Search Another
        </a>

        <a class="btn"
           href="<%= request.getContextPath() %>/appointments">
            📅 Appointment List
        </a>

    </div>

</div>

</body>
</html>