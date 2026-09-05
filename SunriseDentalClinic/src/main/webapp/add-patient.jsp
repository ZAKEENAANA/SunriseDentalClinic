<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Add Patient - Sunrise Dental Clinic</title>

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

        .btn-submit {
            background: #0b6fa4;
        }

        .btn-submit:hover {
            background: #095b87;
        }

        .btn-list {
            background: #555;
        }

        .btn-list:hover {
            background: #333;
        }

        .error {
            background: #f8d7da;
            color: #842029;
            padding: 12px;
            border-radius: 5px;
            margin-bottom: 20px;
            text-align: center;
        }

    </style>

</head>


<body>


    <div class="header">

        <h1>Sunrise Dental Clinic</h1>

        <p>Patient Management System</p>

    </div>


    <div class="container">

        <h2>Register New Patient</h2>


        <%

            String error = request.getParameter("error");

            if ("true".equals(error)) {

        %>

            <div class="error">
                Failed to register patient. Please try again.
            </div>

        <%

            }

        %>


        <form action="<%= request.getContextPath() %>/addPatient"
              method="post">


            <div class="form-group">

                <label for="firstName">
                    First Name
                </label>

                <input type="text"
                       id="firstName"
                       name="firstName"
                       required>

            </div>


            <div class="form-group">

                <label for="lastName">
                    Last Name
                </label>

                <input type="text"
                       id="lastName"
                       name="lastName"
                       required>

            </div>


            <div class="form-group">

                <label for="dateOfBirth">
                    Date of Birth
                </label>

                <input type="date"
                       id="dateOfBirth"
                       name="dateOfBirth"
                       required>

            </div>


            <div class="form-group">

                <label for="gender">
                    Gender
                </label>

                <select id="gender"
                        name="gender"
                        required>

                    <option value="">
                        -- Select Gender --
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


            <div class="form-group">

                <label for="phone">
                    Phone
                </label>

                <input type="tel"
                       id="phone"
                       name="phone"
                       required>

            </div>


            <div class="form-group">

                <label for="email">
                    Email
                </label>

                <input type="email"
                       id="email"
                       name="email">

            </div>


            <div class="form-group">

                <label for="address">
                    Address
                </label>

                <textarea id="address"
                          name="address"
                          required></textarea>

            </div>


            <div class="buttons">

                <button type="submit"
                        class="btn btn-submit">
                    Register Patient
                </button>

                <a href="patients"
                   class="btn btn-list">
                    View Patients
                </a>

            </div>


        </form>

    </div>


</body>

</html>