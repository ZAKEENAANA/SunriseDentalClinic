<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.sunrise.dental.model.Patient" %>

<%
    Patient patient =
        (Patient) request.getAttribute("patient");
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Edit Patient - Sunrise Dental Clinic</title>

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
            width: 600px;
            max-width: 90%;
            margin: 30px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.12);
        }

        h2 {
            color: #0b6fa4;
            text-align: center;
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

        input,
        select,
        textarea {
            width: 100%;
            padding: 11px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 15px;
        }

        textarea {
            height: 90px;
            resize: vertical;
        }

        .buttons {
            display: flex;
            gap: 10px;
            margin-top: 25px;
        }

        .btn {
            flex: 1;
            padding: 12px;
            border: none;
            border-radius: 5px;
            color: white;
            font-size: 15px;
            cursor: pointer;
            text-decoration: none;
            text-align: center;
        }

        .btn-update {
            background: #0b6fa4;
        }

        .btn-update:hover {
            background: #095b87;
        }

        .btn-cancel {
            background: #555;
        }

        .btn-cancel:hover {
            background: #333;
        }

        .patient-id {
            background: #eef5f8;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 20px;
            color: #555;
        }

    </style>

</head>


<body>


    <div class="header">

        <h1>Sunrise Dental Clinic</h1>

        <p>Patient Management System</p>

    </div>


    <div class="container">

        <h2>Edit Patient</h2>


        <div class="patient-id">

            Patient ID:
            <strong>
                <%= patient.getPatientId() %>
            </strong>

        </div>


        <form action="<%= request.getContextPath() %>/editPatient"
              method="post">


            <input type="hidden"
                   name="patientId"
                   value="<%= patient.getPatientId() %>">


            <div class="form-group">

                <label for="firstName">
                    First Name
                </label>

                <input type="text"
                       id="firstName"
                       name="firstName"
                       value="<%= patient.getFirstName() %>"
                       required>

            </div>


            <div class="form-group">

                <label for="lastName">
                    Last Name
                </label>

                <input type="text"
                       id="lastName"
                       name="lastName"
                       value="<%= patient.getLastName() %>"
                       required>

            </div>


            <div class="form-group">

                <label for="dateOfBirth">
                    Date of Birth
                </label>

                <input type="date"
                       id="dateOfBirth"
                       name="dateOfBirth"
                       value="<%= patient.getDateOfBirth() %>"
                       required>

            </div>


            <div class="form-group">

                <label for="gender">
                    Gender
                </label>

                <select id="gender"
                        name="gender"
                        required>

                    <option value="Male"
                        <%= "Male".equals(patient.getGender())
                            ? "selected" : "" %>>
                        Male
                    </option>

                    <option value="Female"
                        <%= "Female".equals(patient.getGender())
                            ? "selected" : "" %>>
                        Female
                    </option>

                    <option value="Other"
                        <%= "Other".equals(patient.getGender())
                            ? "selected" : "" %>>
                        Other
                    </option>

                </select>

            </div>


            <div class="form-group">

                <label for="phone">
                    Phone
                </label>

                <input type="tel"
                       id="phone"
                       name="phone"
                       value="<%= patient.getPhone() %>"
                       required>

            </div>


            <div class="form-group">

                <label for="email">
                    Email
                </label>

                <input type="email"
                       id="email"
                       name="email"
                       value="<%= patient.getEmail() %>">

            </div>


            <div class="form-group">

                <label for="address">
                    Address
                </label>

                <textarea id="address"
                          name="address"
                          required><%= patient.getAddress() %></textarea>

            </div>


            <div class="buttons">

                <button type="submit"
                        class="btn btn-update">
                    Update Patient
                </button>

                <a href="<%= request.getContextPath() %>/patients"
                   class="btn btn-cancel">
                    Cancel
                </a>

            </div>


        </form>

    </div>


</body>

</html>