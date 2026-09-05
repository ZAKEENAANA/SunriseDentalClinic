<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("user") == null) {

        response.sendRedirect(
            request.getContextPath() + "/login.jsp"
        );

        return;
    }

    String username =
        (String) session.getAttribute("username");

    String role =
        (String) session.getAttribute("role");

    boolean isAdmin =
        "ADMIN".equalsIgnoreCase(role);

    Integer patientCount =
        (Integer) request.getAttribute("patientCount");

    Integer appointmentCount =
        (Integer) request.getAttribute("appointmentCount");

    Integer treatmentCount =
        (Integer) request.getAttribute("treatmentCount");

    Integer pendingBillCount =
        (Integer) request.getAttribute("pendingBillCount");

    if (patientCount == null) {
        patientCount = 0;
    }

    if (appointmentCount == null) {
        appointmentCount = 0;
    }

    if (treatmentCount == null) {
        treatmentCount = 0;
    }

    if (pendingBillCount == null) {
        pendingBillCount = 0;
    }

    String accessDenied =
        request.getParameter("accessDenied");
%>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>
        Dashboard - Sunrise Dental Clinic
    </title>

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
            height: 70px;
            background: #0b6fa4;
            color: white;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 30px;
        }

        .header h2 {
            margin: 0;
        }

        .header-right {
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .user-info {
            font-size: 14px;
        }

        .logout {
            color: white;
            text-decoration: none;
            background: #d9534f;
            padding: 10px 18px;
            border-radius: 5px;
        }

        .logout:hover {
            background: #c9302c;
        }

        .layout {
            display: flex;
            min-height: calc(100vh - 70px);
        }

        .sidebar {
            width: 230px;
            background: white;
            box-shadow: 2px 0 8px rgba(0, 0, 0, 0.08);
            padding-top: 20px;
        }

        .sidebar-title {
            padding: 0 25px 15px;
            color: #777;
            font-size: 13px;
            font-weight: bold;
            text-transform: uppercase;
        }

        .sidebar a {
            display: block;
            padding: 15px 25px;
            color: #333;
            text-decoration: none;
        }

        .sidebar a:hover {
            background: #eaf5fb;
            color: #0b6fa4;
        }

        .sidebar .active {
            background: #eaf5fb;
            color: #0b6fa4;
            font-weight: bold;
        }

        .content {
            flex: 1;
            padding: 35px;
        }

        .welcome {
            margin-bottom: 30px;
        }

        .welcome h1 {
            color: #0b6fa4;
            margin-bottom: 10px;
        }

        .welcome p {
            color: #555;
        }

        .access-denied {
            margin-bottom: 25px;
            padding: 15px 20px;
            background: #f8d7da;
            color: #842029;
            border: 1px solid #f5c2c7;
            border-radius: 8px;
            font-weight: bold;
            text-align: center;
        }

        .cards {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 20px;
        }

        .card {
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 3px 10px rgba(0, 0, 0, 0.10);
        }

        .card h3 {
            margin-top: 0;
            color: #555;
        }

        .number {
            font-size: 32px;
            font-weight: bold;
            color: #0b6fa4;
        }

        .card-link {
            display: inline-block;
            margin-top: 10px;
            color: #0b6fa4;
            text-decoration: none;
            font-size: 14px;
        }

        .card-link:hover {
            text-decoration: underline;
        }

        .quick-actions {
            margin-top: 30px;
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 3px 10px rgba(0, 0, 0, 0.10);
        }

        .quick-actions h2 {
            color: #0b6fa4;
            margin-top: 0;
        }

        .actions {
            display: flex;
            gap: 15px;
            flex-wrap: wrap;
        }

        .action-btn {
            display: inline-block;
            padding: 12px 18px;
            background: #0b6fa4;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .action-btn:hover {
            background: #095b87;
        }

        .help-btn {
            background: #198754;
        }

        .help-btn:hover {
            background: #146c43;
        }

        .role-badge {
            padding: 4px 8px;
            border-radius: 4px;
            font-weight: bold;
        }

        .admin-role {
            background: #f8d7da;
            color: #842029;
        }

        .staff-role {
            background: #d1e7dd;
            color: #0f5132;
        }

        @media (max-width: 1000px) {

            .cards {
                grid-template-columns: repeat(2, 1fr);
            }

        }

        @media (max-width: 700px) {

            .layout {
                flex-direction: column;
            }

            .sidebar {
                width: 100%;
            }

            .cards {
                grid-template-columns: 1fr;
            }

            .header {
                padding: 0 15px;
            }

            .user-info {
                display: none;
            }

            .content {
                padding: 20px;
            }

        }

    </style>

</head>

<body>


    <div class="header">

        <h2>
            🦷 Sunrise Dental Clinic
        </h2>

        <div class="header-right">

            <div class="user-info">

                Welcome,

                <strong>
                    <%= username != null ? username : "User" %>
                </strong>

                |

                <% if (isAdmin) { %>

                    <span class="role-badge admin-role">
                        ADMIN
                    </span>

                <% } else { %>

                    <span class="role-badge staff-role">
                        STAFF
                    </span>

                <% } %>

            </div>


            <a
                href="<%= request.getContextPath() %>/logout"
                class="logout">

                Logout

            </a>

        </div>

    </div>


    <div class="layout">


        <div class="sidebar">

            <div class="sidebar-title">
                Main Menu
            </div>


            <a
                href="<%= request.getContextPath() %>/dashboard"
                class="active">

                🏠 Dashboard

            </a>


            <a
                href="<%= request.getContextPath() %>/add-patient.jsp">

                👤 Register Patient

            </a>


            <a
                href="<%= request.getContextPath() %>/patients">

                📋 Patient List

            </a>


            <a
                href="<%= request.getContextPath() %>/add-appointment.jsp">

                📅 New Appointment

            </a>


            <a
                href="<%= request.getContextPath() %>/appointments">

                📋 Appointment List

            </a>


            <a
                href="<%= request.getContextPath() %>/add-treatment.jsp">

                🦷 Add Treatment

            </a>


            <a
                href="<%= request.getContextPath() %>/treatments">

                📋 Treatment List

            </a>


            <a
                href="<%= request.getContextPath() %>/addBill">

                💳 Create Bill

            </a>


            <a
                href="<%= request.getContextPath() %>/bills">

                📋 Bill List

            </a>


            <!-- HELP BUTTON -->

            <a
                href="<%= request.getContextPath() %>/help.jsp">

                📖 Help & User Guide

            </a>


            <% if (isAdmin) { %>

                <div
                    class="sidebar-title"
                    style="margin-top: 20px;">

                    Administration

                </div>


                <a
                    href="<%= request.getContextPath() %>/add-user.jsp">

                    👤 Add User

                </a>


                <a
                    href="<%= request.getContextPath() %>/users">

                    👥 User List

                </a>

            <% } %>

        </div>


        <div class="content">


            <% if ("true".equals(accessDenied)) { %>

                <div class="access-denied">

                    ⚠️ Access Denied!

                    <br>

                    You do not have permission
                    to access this page.

                </div>

            <% } %>


            <div class="welcome">

                <h1>
                    Welcome to the Dashboard
                </h1>

                <p>
                    Sunrise Dental Clinic Appointment &
                    Patient Management System
                </p>

            </div>


            <div class="cards">


                <div class="card">

                    <h3>
                        👤 Patients
                    </h3>

                    <div class="number">
                        <%= patientCount %>
                    </div>

                    <p>
                        Registered Patients
                    </p>

                    <a
                        href="<%= request.getContextPath() %>/patients"
                        class="card-link">

                        View Patients →

                    </a>

                </div>


                <div class="card">

                    <h3>
                        📅 Appointments
                    </h3>

                    <div class="number">
                        <%= appointmentCount %>
                    </div>

                    <p>
                        Scheduled Appointments
                    </p>

                    <a
                        href="<%= request.getContextPath() %>/appointments"
                        class="card-link">

                        View Appointments →

                    </a>

                </div>


                <div class="card">

                    <h3>
                        🦷 Treatments
                    </h3>

                    <div class="number">
                        <%= treatmentCount %>
                    </div>

                    <p>
                        Total Treatments
                    </p>

                    <a
                        href="<%= request.getContextPath() %>/treatments"
                        class="card-link">

                        View Treatments →

                    </a>

                </div>


                <div class="card">

                    <h3>
                        💳 Pending Bills
                    </h3>

                    <div class="number">
                        <%= pendingBillCount %>
                    </div>

                    <p>
                        Pending Payments
                    </p>

                    <a
                        href="<%= request.getContextPath() %>/bills"
                        class="card-link">

                        View Bills →

                    </a>

                </div>


            </div>


            <div class="quick-actions">

                <h2>
                    Quick Actions
                </h2>


                <div class="actions">


                    <a
                        href="<%= request.getContextPath() %>/add-patient.jsp"
                        class="action-btn">

                        + Register Patient

                    </a>


                    <a
                        href="<%= request.getContextPath() %>/add-appointment.jsp"
                        class="action-btn">

                        + New Appointment

                    </a>


                    <a
                        href="<%= request.getContextPath() %>/add-treatment.jsp"
                        class="action-btn">

                        + Add Treatment

                    </a>


                    <a
                        href="<%= request.getContextPath() %>/addBill"
                        class="action-btn">

                        + Create Bill

                    </a>


                    <!-- HELP QUICK ACTION -->

                    <a
                        href="<%= request.getContextPath() %>/help.jsp"
                        class="action-btn help-btn">

                        📖 Help & User Guide

                    </a>


                </div>

            </div>


        </div>

    </div>


</body>

</html>