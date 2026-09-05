<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.sunrise.dental.model.Treatment" %>

<%
    Treatment treatment =
        (Treatment) request.getAttribute("treatment");

    if (treatment == null) {
        response.sendRedirect(
            request.getContextPath() + "/treatments"
        );
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Edit Treatment - Sunrise Dental Clinic</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7f9;
            margin: 0;
            padding: 40px;
        }

        .container {
            width: 600px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
        }

        label {
            display: block;
            margin-top: 15px;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input,
        textarea {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        textarea {
            height: 100px;
            resize: vertical;
        }

        .buttons {
            margin-top: 25px;
            display: flex;
            gap: 10px;
        }

        button,
        a {
            padding: 10px 18px;
            border-radius: 5px;
            text-decoration: none;
            border: none;
            cursor: pointer;
        }

        button {
            background-color: #198754;
            color: white;
        }

        a {
            background-color: #6c757d;
            color: white;
        }

    </style>

</head>

<body>

<div class="container">

    <h2>Edit Treatment</h2>

    <form action="<%= request.getContextPath() %>/editTreatment"
          method="post">

        <input type="hidden"
               name="treatmentId"
               value="<%= treatment.getTreatmentId() %>">

        <label>Patient ID</label>

        <input type="number"
               name="patientId"
               value="<%= treatment.getPatientId() %>"
               required>


        <label>Treatment Name</label>

        <input type="text"
               name="treatmentName"
               value="<%= treatment.getTreatmentName() %>"
               required>


        <label>Description</label>

        <textarea name="description"><%= treatment.getDescription() == null ? "" : treatment.getDescription() %></textarea>


        <label>Treatment Date</label>

        <input type="date"
               name="treatmentDate"
               value="<%= treatment.getTreatmentDate() == null ? "" : treatment.getTreatmentDate() %>">


        <label>Cost (LKR)</label>

        <input type="number"
               name="cost"
               step="0.01"
               min="0"
               value="<%= treatment.getCost() %>"
               required>


        <div class="buttons">

            <button type="submit">
                Update Treatment
            </button>

            <a href="<%= request.getContextPath() %>/treatments">
                Cancel
            </a>

        </div>

    </form>

</div>

</body>
</html>