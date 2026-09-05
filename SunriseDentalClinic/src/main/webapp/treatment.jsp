<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Add Treatment - Sunrise Dental Clinic</title>

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
            width: 600px;
            max-width: 90%;
            margin: 40px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.12);
        }

        h2 {
            text-align: center;
            color: #0b6fa4;
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
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }

        textarea {
            height: 90px;
            resize: vertical;
        }

        .submit-btn {
            width: 100%;
            padding: 12px;
            background: #0b6fa4;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
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
        }

        .error {
            padding: 12px;
            margin-bottom: 20px;
            background: #f8d7da;
            color: #721c24;
            border-radius: 5px;
            text-align: center;
        }

        .links {
            text-align: center;
            margin-top: 20px;
        }

        .links a {
            display: block;
            margin-top: 10px;
            color: #0b6fa4;
            text-decoration: none;
        }

        .links a:hover {
            text-decoration: underline;
        }

    </style>

</head>

<body>

    <div class="header">

        <h1>🦷 Sunrise Dental Clinic</h1>

        <p>Treatment Management System</p>

    </div>


    <div class="container">

        <h2>Add New Treatment</h2>


        <%
            String success = request.getParameter("success");
            String error = request.getParameter("error");

            if ("true".equals(success)) {
        %>

            <div class="success">
                Treatment added successfully!
            </div>

        <%
            }

            if ("true".equals(error)) {
        %>

            <div class="error">
                Failed to add treatment.
                Please check the entered information.
            </div>

        <%
            }
        %>


        <form action="addTreatment" method="post">


            <!-- Patient ID -->

            <div class="form-group">

                <label for="patientId">
                    Patient ID
                </label>

                <input
                    type="number"
                    id="patientId"
                    name="patientId"
                    min="1"
                    placeholder="Enter patient ID"
                    required>

            </div>


            <!-- Treatment Name -->

            <div class="form-group">

                <label for="treatmentName">
                    Treatment Name
                </label>

                <input
                    type="text"
                    id="treatmentName"
                    name="treatmentName"
                    maxlength="100"
                    placeholder="Example: Dental Cleaning"
                    required>

            </div>


            <!-- Description -->

            <div class="form-group">

                <label for="description">
                    Description
                </label>

                <textarea
                    id="description"
                    name="description"
                    maxlength="500"
                    placeholder="Enter treatment description"></textarea>

            </div>


            <!-- Treatment Date -->

            <div class="form-group">

                <label for="treatmentDate">
                    Treatment Date
                </label>

                <input
                    type="date"
                    id="treatmentDate"
                    name="treatmentDate"
                    required>

            </div>


            <!-- Cost -->

            <div class="form-group">

                <label for="cost">
                    Treatment Cost (LKR)
                </label>

                <input
                    type="number"
                    id="cost"
                    name="cost"
                    min="0"
                    step="0.01"
                    placeholder="Enter treatment cost"
                    required>

            </div>


            <!-- Submit -->

            <button
                type="submit"
                class="submit-btn">

                🦷 Add Treatment

            </button>

        </form>


        <div class="links">

            <a href="treatments">
                View All Treatments →
            </a>

            <a href="dashboard">
                ← Back to Dashboard
            </a>

        </div>

    </div>

</body>
</html>