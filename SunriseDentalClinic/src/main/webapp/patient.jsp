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

    <title>Patient Registration - Sunrise Dental Clinic</title>

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
            width: 650px;
            max-width: 95%;
            margin: 35px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.15);
        }

        h2 {
            text-align: center;
            color: #0b6fa4;
            margin-top: 0;
            margin-bottom: 25px;
        }

        .form-group {
            margin-bottom: 17px;
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
        select,
        textarea {
            width: 100%;
            padding: 11px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
            font-family: Arial, sans-serif;
        }

        input:focus,
        select:focus,
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
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
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
            margin-top: 20px;
            text-align: center;
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

        <p>Patient Management System</p>

    </div>


    <!-- Main Container -->

    <div class="container">

        <h2>Patient Registration</h2>


        <!-- Success Message -->

        <% if ("true".equals(success)) { %>

            <div class="success">
                Patient registered successfully!
            </div>

        <% } %>


        <!-- Error Message -->

        <% if ("true".equals(error)) { %>

            <div class="error">
                Failed to register patient.
                Please check the entered information and try again.
            </div>

        <% } %>


        <!-- Patient Form -->

        <form action="addPatient"
              method="post">


            <!-- First Name -->

            <div class="form-group">

                <label for="firstName">
                    First Name <span class="required">*</span>
                </label>

                <input type="text"
                       id="firstName"
                       name="firstName"
                       maxlength="50"
                       pattern="[A-Za-z ]+"
                       title="Please enter letters only"
                       required>

            </div>


            <!-- Last Name -->

            <div class="form-group">

                <label for="lastName">
                    Last Name <span class="required">*</span>
                </label>

                <input type="text"
                       id="lastName"
                       name="lastName"
                       maxlength="50"
                       pattern="[A-Za-z ]+"
                       title="Please enter letters only"
                       required>

            </div>


            <!-- Date of Birth -->

            <div class="form-group">

                <label for="dateOfBirth">
                    Date of Birth
                </label>

                <input type="date"
                       id="dateOfBirth"
                       name="dateOfBirth">

            </div>


            <!-- Gender -->

            <div class="form-group">

                <label for="gender">
                    Gender <span class="required">*</span>
                </label>

                <select id="gender"
                        name="gender"
                        required>

                    <option value="">
                        Select Gender
                    </option>

                    <option value="Male">
                        Male
                    </option>

                    <option value="Female">
                        Female
                    </option>

                    <option value="Other">
                        Other
                    </option>

                </select>

            </div>


            <!-- Phone -->

            <div class="form-group">

                <label for="phone">
                    Phone <span class="required">*</span>
                </label>

                <input type="tel"
                       id="phone"
                       name="phone"
                       maxlength="15"
                       pattern="[0-9+\- ]{9,15}"
                       title="Please enter a valid phone number"
                       placeholder="Example: 0771234567"
                       required>

            </div>


            <!-- Email -->

            <div class="form-group">

                <label for="email">
                    Email
                </label>

                <input type="email"
                       id="email"
                       name="email"
                       maxlength="100"
                       placeholder="Example: patient@email.com">

            </div>


            <!-- Address -->

            <div class="form-group">

                <label for="address">
                    Address <span class="required">*</span>
                </label>

                <textarea id="address"
                          name="address"
                          maxlength="255"
                          placeholder="Enter patient's full address"
                          required></textarea>

            </div>


            <!-- Submit -->

            <button type="submit"
                    class="submit-btn">

                Register Patient

            </button>


        </form>


        <!-- Navigation -->

        <a href="dashboard"
           class="back">

            ← Back to Dashboard

        </a>

    </div>

</body>

</html>