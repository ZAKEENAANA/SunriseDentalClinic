<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.sunrise.dental.model.User" %>

<%
    if (session.getAttribute("user") == null) {

        response.sendRedirect(
            request.getContextPath() + "/login.jsp"
        );

        return;
    }

    User loggedInUser =
        (User) session.getAttribute("user");

    String username =
        loggedInUser.getUsername();

    String role =
        loggedInUser.getRole();
%>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>
        Help & User Guide - Sunrise Dental Clinic
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
            background: #0b6fa4;
            color: white;
            padding: 20px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-wrap: wrap;
            gap: 15px;
        }

        .header h1 {
            margin: 0;
        }

        .user-info {
            font-size: 14px;
        }

        .container {
            width: 90%;
            max-width: 1100px;
            margin: 30px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.12);
        }

        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            gap: 15px;
            flex-wrap: wrap;
            margin-bottom: 25px;
        }

        .top-bar h2 {
            color: #0b6fa4;
            margin: 0;
        }

        .button {
            background: #0b6fa4;
            color: white;
            padding: 10px 16px;
            text-decoration: none;
            border-radius: 5px;
            display: inline-block;
        }

        .button:hover {
            background: #095b87;
        }

        .logout {
            background: #dc3545;
        }

        .logout:hover {
            background: #b02a37;
        }

        .section {
            margin-bottom: 25px;
            padding: 20px;
            border-left: 5px solid #0b6fa4;
            background: #f8fbfd;
            border-radius: 6px;
        }

        .section h3 {
            margin-top: 0;
            color: #0b6fa4;
        }

        .section p {
            line-height: 1.7;
            color: #444;
        }

        .section ol,
        .section ul {
            line-height: 1.8;
            color: #444;
        }

        .step-title {
            font-weight: bold;
            color: #333;
        }

        .important {
            background: #fff3cd;
            color: #664d03;
            border: 1px solid #ffecb5;
            padding: 15px;
            border-radius: 6px;
            margin-top: 10px;
        }

        .footer {
            text-align: center;
            margin-top: 30px;
            padding-top: 20px;
            border-top: 1px solid #ddd;
            color: #777;
        }

        @media (max-width: 700px) {

            .container {
                width: 95%;
                padding: 20px;
            }

            .header {
                padding: 20px;
            }

            .top-bar {
                flex-direction: column;
                align-items: stretch;
            }

            .button {
                text-align: center;
            }

        }

    </style>

</head>

<body>


    <div class="header">

        <h1>
            🦷 Sunrise Dental Clinic
        </h1>

        <div class="user-info">

            Welcome,

            <strong>
                <%= username %>
            </strong>

            |

            <strong>
                <%= role %>
            </strong>

        </div>

    </div>


    <div class="container">


        <div class="top-bar">

            <h2>
                📖 Help & User Guide
            </h2>

            <div>

                <a
                    href="<%= request.getContextPath() %>/dashboard"
                    class="button">

                    🏠 Dashboard

                </a>

                <a
                    href="<%= request.getContextPath() %>/logout"
                    class="button logout">

                    Logout

                </a>

            </div>

        </div>


        <!-- 1. LOGIN -->

        <div class="section">

            <h3>
                1️⃣ Login to the System
            </h3>

            <p>
                <span class="step-title">Step 1:</span>
                Open the Sunrise Dental Clinic system.
            </p>

            <p>
                <span class="step-title">Step 2:</span>
                Enter your username and password.
            </p>

            <p>
                <span class="step-title">Step 3:</span>
                Click the <strong>Login</strong> button.
            </p>

            <p>
                After successful login, the system will
                display the Dashboard.
            </p>

        </div>


        <!-- 2. PATIENT -->

        <div class="section">

            <h3>
                2️⃣ Register a New Patient
            </h3>

            <ol>

                <li>
                    Go to <strong>Register Patient</strong>.
                </li>

                <li>
                    Enter the patient's first name
                    and last name.
                </li>

                <li>
                    Enter date of birth and gender.
                </li>

                <li>
                    Enter phone number and email.
                </li>

                <li>
                    Enter the patient's address.
                </li>

                <li>
                    Click <strong>Save Patient</strong>.
                </li>

            </ol>

            <div class="important">

                ⚠️ Make sure the patient information
                is entered correctly before saving.

            </div>

        </div>


        <!-- 3. APPOINTMENT -->

        <div class="section">

            <h3>
                3️⃣ Register a New Appointment
            </h3>

            <ol>

                <li>
                    Open <strong>New Appointment</strong>.
                </li>

                <li>
                    Select the patient.
                </li>

                <li>
                    Enter the dentist name.
                </li>

                <li>
                    Select the appointment date.
                </li>

                <li>
                    Select the appointment time.
                </li>

                <li>
                    Enter the reason or treatment.
                </li>

                <li>
                    Save the appointment.
                </li>

            </ol>

        </div>


        <!-- 4. SEARCH -->

        <div class="section">

            <h3>
                4️⃣ Search Appointment Details
            </h3>

            <ol>

                <li>
                    Open <strong>Appointment List</strong>.
                </li>

                <li>
                    Click <strong>Search Appointment</strong>.
                </li>

                <li>
                    Enter the Appointment Number.
                </li>

                <li>
                    Click <strong>Search</strong>.
                </li>

            </ol>

            <p>
                The system displays the complete patient
                and appointment information.
            </p>

        </div>


        <!-- 5. UPDATE STATUS -->

        <div class="section">

            <h3>
                5️⃣ Update Appointment Status
            </h3>

            <p>
                The appointment status can be changed to:
            </p>

            <ul>

                <li>Scheduled</li>
                <li>Completed</li>
                <li>Cancelled</li>
                <li>Rescheduled</li>

            </ul>

            <p>
                If <strong>Rescheduled</strong> is selected,
                enter the new appointment date and time.
            </p>

        </div>


        <!-- 6. TREATMENT -->

        <div class="section">

            <h3>
                6️⃣ Manage Treatments
            </h3>

            <ol>

                <li>
                    Open <strong>Add Treatment</strong>.
                </li>

                <li>
                    Select the patient.
                </li>

                <li>
                    Enter the treatment name.
                </li>

                <li>
                    Enter the treatment description.
                </li>

                <li>
                    Enter the treatment date.
                </li>

                <li>
                    Enter the treatment cost.
                </li>

                <li>
                    Click <strong>Save Treatment</strong>.
                </li>

            </ol>

        </div>


        <!-- 7. BILL -->

        <div class="section">

            <h3>
                7️⃣ Create a Bill
            </h3>

            <ol>

                <li>
                    Open <strong>Create Bill</strong>.
                </li>

                <li>
                    Select the patient.
                </li>

                <li>
                    Select the treatment.
                </li>

                <li>
                    The system calculates the treatment
                    cost automatically.
                </li>

                <li>
                    The consultation fee is added.
                </li>

                <li>
                    The system calculates the total bill.
                </li>

                <li>
                    Click <strong>Create Bill</strong>.
                </li>

            </ol>

            <div class="important">

                💳 Total Bill = Treatment Cost +
                Consultation Fee

            </div>

        </div>


        <!-- 8. PRINT -->

        <div class="section">

            <h3>
                8️⃣ Print Bill / Receipt
            </h3>

            <ol>

                <li>
                    Open <strong>Bill List</strong>.
                </li>

                <li>
                    Find the required bill.
                </li>

                <li>
                    Click <strong>Print Receipt</strong>.
                </li>

                <li>
                    Check the bill details.
                </li>

                <li>
                    Click the <strong>Print</strong> button.
                </li>

            </ol>

        </div>


        <!-- 9. USER MANAGEMENT -->

        <div class="section">

            <h3>
                9️⃣ User Management
            </h3>

            <p>
                User management is available only for
                <strong>ADMIN</strong> users.
            </p>

            <ol>

                <li>
                    Open <strong>User Management</strong>.
                </li>

                <li>
                    Add a new system user.
                </li>

                <li>
                    Select the user role.
                </li>

                <li>
                    View existing users.
                </li>

                <li>
                    Delete users when necessary.
                </li>

            </ol>

        </div>


        <!-- 10. LOGOUT -->

        <div class="section">

            <h3>
                🔟 Logout
            </h3>

            <p>
                Always click the <strong>Logout</strong>
                button after completing your work.
            </p>

            <p>
                The system will safely terminate the
                current user session and return to
                the login page.
            </p>

        </div>


        <!-- TROUBLESHOOTING -->

        <div class="section">

            <h3>
                🛠️ Troubleshooting
            </h3>

            <ul>

                <li>
                    If login fails, check your username
                    and password.
                </li>

                <li>
                    If a record is not displayed, refresh
                    the page and check the database entry.
                </li>

                <li>
                    If an appointment cannot be rescheduled,
                    make sure both date and time are entered.
                </li>

                <li>
                    If a bill cannot be created, make sure
                    a valid patient and treatment are selected.
                </li>

                <li>
                    If an unexpected error occurs, check
                    the Eclipse/Tomcat console for details.
                </li>

            </ul>

        </div>


        <div class="footer">

            <p>
                🦷 Sunrise Dental Clinic
            </p>

            <p>
                Appointment & Patient Management System
            </p>

            <p>
                Help & User Guide
            </p>

        </div>


    </div>


</body>

</html>