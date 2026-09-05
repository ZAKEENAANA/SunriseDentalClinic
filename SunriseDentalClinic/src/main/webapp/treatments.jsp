<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.sunrise.dental.model.Treatment" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Treatment List - Sunrise Dental Clinic</title>

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
            max-width: 1200px;
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
        }

        h2 {
            color: #0b6fa4;
            margin: 0;
        }

        .button {
            background: #0b6fa4;
            color: white;
            padding: 10px 16px;
            text-decoration: none;
            border-radius: 5px;
        }

        .button:hover {
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

        .table-container {
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            min-width: 800px;
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

        .cost {
            font-weight: bold;
            color: #198754;
        }

        .no-data {
            text-align: center;
            padding: 40px;
            color: #777;
            font-size: 16px;
        }

        .back {
            display: block;
            margin-top: 20px;
            text-align: center;
            color: #0b6fa4;
            text-decoration: none;
        }

        .back:hover {
            text-decoration: underline;
        }

        @media (max-width: 700px) {

            .top-bar {
                flex-direction: column;
                gap: 15px;
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

        <h1>🦷 Sunrise Dental Clinic</h1>

        <p>Treatment Management System</p>

    </div>


    <div class="container">

        <div class="top-bar">

            <h2>Treatment List</h2>

            <a href="treatment.jsp" class="button">
                + Add New Treatment
            </a>

        </div>


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
                Failed to process treatment.
            </div>

        <%
            }
        %>


        <%
            List<Treatment> treatments =
                (List<Treatment>) request.getAttribute("treatments");

            if (treatments != null && !treatments.isEmpty()) {
        %>


        <div class="table-container">

            <table>

                <thead>

                    <tr>

                        <th>Treatment ID</th>

                        <th>Patient ID</th>

                        <th>Treatment Name</th>

                        <th>Description</th>

                        <th>Treatment Date</th>

                        <th>Cost (LKR)</th>

                    </tr>

                </thead>


                <tbody>

                <%
                    for (Treatment treatment : treatments) {
                %>

                    <tr>

                        <td>
                            <%= treatment.getTreatmentId() %>
                        </td>

                        <td>
                            <%= treatment.getPatientId() %>
                        </td>

                        <td>
                            <%= treatment.getTreatmentName() %>
                        </td>

                        <td>
                            <%= treatment.getDescription() != null
                                && !treatment.getDescription().trim().isEmpty()
                                ? treatment.getDescription()
                                : "-" %>
                        </td>

                        <td>
                            <%= treatment.getTreatmentDate() != null
                                ? treatment.getTreatmentDate()
                                : "-" %>
                        </td>

                        <td class="cost">

                            <%= String.format("%.2f", treatment.getCost()) %>

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

                No treatments found.

                <br><br>

                <a href="treatment.jsp" class="button">
                    Add First Treatment
                </a>

            </div>

        <%
            }
        %>


        <a href="dashboard" class="back">
            ← Back to Dashboard
        </a>

    </div>

</body>

</html>