<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.sunrise.dental.model.Appointment" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Appointment List - Sunrise Dental Clinic</title>

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
            width: 95%;
            margin: 30px auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.12);
        }

        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        h2 {
            color: #0b6fa4;
        }

        .button {
            background: #0b6fa4;
            color: white;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
        }

        .button:hover {
            background: #095b87;
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
        }

        tr:hover {
            background: #f1f7fa;
        }

        .status {
            background: #d4edda;
            color: #155724;
            padding: 5px 10px;
            border-radius: 15px;
            display: inline-block;
        }

        .no-data {
            text-align: center;
            padding: 30px;
            color: #777;
        }

    </style>

</head>

<body>

    <div class="header">

        <h1>🦷 Sunrise Dental Clinic</h1>

        <p>Appointment Management System</p>

    </div>


    <div class="container">

        <div class="top-bar">

            <h2>Appointment List</h2>

            <a href="appointment.jsp" class="button">
                + New Appointment
            </a>

        </div>


        <%
            List<Appointment> appointments =
                (List<Appointment>) request.getAttribute("appointments");

            if (appointments != null && !appointments.isEmpty()) {
        %>


        <table>

            <thead>

                <tr>
                    <th>Appointment ID</th>
                    <th>Patient ID</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Reason</th>
                    <th>Status</th>
                </tr>

            </thead>


            <tbody>

            <%
                for (Appointment appointment : appointments) {
            %>

                <tr>

                    <td>
                        <%= appointment.getAppointmentId() %>
                    </td>

                    <td>
                        <%= appointment.getPatientId() %>
                    </td>

                    <td>
                        <%= appointment.getAppointmentDate() %>
                    </td>

                    <td>
                        <%= appointment.getAppointmentTime() %>
                    </td>

                    <td>
                        <%= appointment.getReason() %>
                    </td>

                    <td>

                        <span class="status">
                            <%= appointment.getStatus() %>
                        </span>

                    </td>

                </tr>

            <%
                }
            %>

            </tbody>

        </table>


        <%
            } else {
        %>

            <div class="no-data">
                No appointments found.
            </div>

        <%
            }
        %>


    </div>

</body>

</html>