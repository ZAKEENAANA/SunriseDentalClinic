<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.sunrise.dental.model.Treatment" %>

<%
    if (session.getAttribute("user") == null) {

        response.sendRedirect(
            request.getContextPath() + "/login.jsp"
        );

        return;
    }

    List<Treatment> treatments =
        (List<Treatment>) request.getAttribute("treatments");
%>

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
            margin: 0 0 8px 0;
        }

        .header p {
            margin: 0;
        }

        .container {
            width: 95%;
            margin: 30px auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.12);
            overflow-x: auto;
        }

        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            gap: 10px;
            flex-wrap: wrap;
        }

        h2 {
            color: #0b6fa4;
            margin: 0;
        }

        .top-buttons {
            display: flex;
            gap: 10px;
            flex-wrap: wrap;
        }

        .button {
            background: #0b6fa4;
            color: white;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
            display: inline-block;
        }

        .button:hover {
            background: #095b87;
        }

        .dashboard-button {
            background: #0b6fa4;
        }

        .dashboard-button:hover {
            background: #095b87;
        }

        .edit-button {
            background: #198754;
            color: white;
            padding: 7px 12px;
            text-decoration: none;
            border-radius: 5px;
            display: inline-block;
            margin-right: 5px;
        }

        .edit-button:hover {
            background: #146c43;
        }

        .delete-button {
            background: #dc3545;
            color: white;
            padding: 7px 12px;
            text-decoration: none;
            border-radius: 5px;
            display: inline-block;
        }

        .delete-button:hover {
            background: #bb2d3b;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            min-width: 900px;
        }

        th {
            background: #0b6fa4;
            color: white;
            padding: 12px;
            text-align: left;
        }

        td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        tr:hover {
            background: #f1f7fa;
        }

        .no-data {
            text-align: center;
            padding: 30px;
            color: #777;
        }

        .success-message {
            background: #d1e7dd;
            color: #0f5132;
            padding: 12px;
            border-radius: 5px;
            margin-bottom: 20px;
            text-align: center;
        }

        .error-message {
            background: #f8d7da;
            color: #842029;
            padding: 12px;
            border-radius: 5px;
            margin-bottom: 20px;
            text-align: center;
        }

        .cost {
            text-align: right;
            font-weight: bold;
        }

        @media (max-width: 800px) {

            .top-bar {
                flex-direction: column;
                align-items: stretch;
            }

            .top-buttons {
                flex-direction: column;
            }

            .button {
                text-align: center;
            }

            th,
            td {
                padding: 8px;
                font-size: 13px;
            }

        }

    </style>

</head>

<body>


    <div class="header">

        <h1>
            🦷 Sunrise Dental Clinic
        </h1>

        <p>
            Treatment Management System
        </p>

    </div>


    <div class="container">


        <div class="top-bar">


            <h2>
                Registered Treatments
            </h2>


            <div class="top-buttons">


                <!-- Dashboard Button -->
                <a
                    href="<%= request.getContextPath() %>/dashboard"
                    class="button dashboard-button">

                    🏠 Dashboard

                </a>


                <!-- Add New Treatment -->
                <a
                    href="<%= request.getContextPath() %>/add-treatment.jsp"
                    class="button">

                    + Add New Treatment

                </a>


            </div>


        </div>


        <%

            String success =
                request.getParameter("success");

            String error =
                request.getParameter("error");


            if ("true".equals(success)) {

        %>


            <div class="success-message">

                ✅ Treatment added successfully!

            </div>


        <%

            } else if ("updated".equals(success)) {

        %>


            <div class="success-message">

                ✅ Treatment updated successfully!

            </div>


        <%

            } else if ("deleted".equals(success)) {

        %>


            <div class="success-message">

                ✅ Treatment deleted successfully!

            </div>


        <%

            } else if ("true".equals(error)) {

        %>


            <div class="error-message">

                ❌ An error occurred.
                Please try again.

            </div>


        <%

            }

        %>


        <%

            if (treatments != null
                    && !treatments.isEmpty()) {

        %>


        <table>


            <thead>

                <tr>

                    <th>
                        Treatment ID
                    </th>

                    <th>
                        Patient ID
                    </th>

                    <th>
                        Treatment Name
                    </th>

                    <th>
                        Description
                    </th>

                    <th>
                        Treatment Date
                    </th>

                    <th>
                        Cost (LKR)
                    </th>

                    <th>
                        Actions
                    </th>

                </tr>

            </thead>


            <tbody>


            <%

                for (Treatment treatment :
                        treatments) {

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
                            && !treatment.getDescription()
                                    .trim().isEmpty()
                            ? treatment.getDescription()
                            : "-" %>

                    </td>


                    <td>

                        <%= treatment.getTreatmentDate() != null
                            ? treatment.getTreatmentDate()
                            : "-" %>

                    </td>


                    <td class="cost">

                        <%= String.format(
                                "%.2f",
                                treatment.getCost()) %>

                    </td>


                    <td>


                        <a
                            href="<%= request.getContextPath() %>/editTreatment?id=<%= treatment.getTreatmentId() %>"
                            class="edit-button">

                            Edit

                        </a>


                        <a
                            href="<%= request.getContextPath() %>/deleteTreatment?id=<%= treatment.getTreatmentId() %>"
                            class="delete-button"
                            onclick="return confirm('Are you sure you want to delete this treatment?');">

                            Delete

                        </a>


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

                📭 No treatments registered yet.

            </div>


        <%

            }

        %>


    </div>


</body>

</html>