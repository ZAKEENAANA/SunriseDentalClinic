<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Add Treatment - Sunrise Dental Clinic</title>

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

        .header h1 {
            margin: 0 0 8px 0;
        }

        .header p {
            margin: 0;
        }

        .container {
            width: 600px;
            max-width: 90%;
            margin: 30px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.12);
            box-sizing: border-box;
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
        textarea {
            width: 100%;
            padding: 11px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 15px;
        }

        textarea {
            height: 100px;
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

        <p>Treatment Management System</p>

    </div>


    <div class="container">


        <h2>Add New Treatment</h2>


        <%

            String error =
                    request.getParameter("error");

            if ("true".equals(error)) {

        %>

            <div class="error">

                Failed to add treatment.
                Please check the details and try again.

            </div>

        <%

            }

        %>


        <form action="<%= request.getContextPath() %>/addTreatment"
              method="post">


            <div class="form-group">

                <label for="patientId">
                    Patient ID
                </label>

                <input type="number"
                       id="patientId"
                       name="patientId"
                       min="1"
                       required>

            </div>


            <div class="form-group">

                <label for="treatmentName">
                    Treatment Name
                </label>

                <input type="text"
                       id="treatmentName"
                       name="treatmentName"
                       maxlength="100"
                       placeholder="e.g. Dental Cleaning"
                       required>

            </div>


            <div class="form-group">

                <label for="description">
                    Description
                </label>

                <textarea id="description"
                          name="description"
                          maxlength="255"
                          placeholder="Enter treatment details"></textarea>

            </div>


            <div class="form-group">

                <label for="treatmentDate">
                    Treatment Date
                </label>

                <input type="date"
                       id="treatmentDate"
                       name="treatmentDate">

            </div>


            <div class="form-group">

                <label for="cost">
                    Cost (LKR)
                </label>

                <input type="number"
                       id="cost"
                       name="cost"
                       min="0"
                       step="0.01"
                       placeholder="0.00">

            </div>


            <div class="buttons">


                <button type="submit"
                        class="btn btn-submit">

                    Add Treatment

                </button>


                <a href="<%= request.getContextPath() %>/treatments"
                   class="btn btn-list">

                    View Treatments

                </a>


            </div>


        </form>


    </div>


</body>

</html>