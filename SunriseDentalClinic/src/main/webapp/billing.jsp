<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.sunrise.dental.model.Patient" %>
<%@ page import="com.sunrise.dental.model.Treatment" %>

<%
    List<Patient> patients =
            (List<Patient>) request.getAttribute("patients");

    List<Treatment> treatments =
            (List<Treatment>) request.getAttribute("treatments");

    Double consultationFee =
            (Double) request.getAttribute("consultationFee");

    if (consultationFee == null) {
        consultationFee = 1500.00;
    }
%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Generate Bill - Sunrise Dental Clinic</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background: #f4f7f9;
            margin: 0;
            padding: 40px;
        }

        .container {
            max-width: 750px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }

        h1 {
            text-align: center;
            color: #1f4e79;
            margin-bottom: 30px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 8px;
            color: #333;
        }

        select {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 15px;
        }

        .calculation-box {
            margin-top: 25px;
            padding: 20px;
            background: #f8fafc;
            border: 1px solid #dfe5ea;
            border-radius: 8px;
        }

        .calculation-row {
            display: flex;
            justify-content: space-between;
            padding: 10px 0;
            font-size: 16px;
        }

        .total-row {
            border-top: 2px solid #1f4e79;
            margin-top: 10px;
            padding-top: 15px;
            font-size: 20px;
            font-weight: bold;
            color: #1f4e79;
        }

        .submit-btn {
            width: 100%;
            padding: 14px;
            margin-top: 25px;
            background: #1f4e79;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
        }

        .submit-btn:hover {
            opacity: 0.9;
        }

        .back-btn {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #1f4e79;
            text-decoration: none;
        }

    </style>

</head>

<body>

<div class="container">

    <h1>🧾 Generate Dental Bill</h1>

    <form
        action="<%= request.getContextPath() %>/addBill"
        method="post">

        <div class="form-group">

            <label for="patientId">
                Select Patient
            </label>

            <select
                id="patientId"
                name="patientId"
                required>

                <option value="">
                    -- Select Patient --
                </option>

                <%
                    if (patients != null) {

                        for (Patient patient : patients) {
                %>

                    <option value="<%= patient.getPatientId() %>">

                        <%= patient.getPatientId() %>
                        -
                        <%= patient.getFirstName() %>
                        <%= patient.getLastName() %>

                    </option>

                <%
                        }
                    }
                %>

            </select>

        </div>


        <div class="form-group">

            <label for="treatmentId">
                Select Treatment
            </label>

            <select
                id="treatmentId"
                name="treatmentId"
                required
                onchange="calculateBill()">

                <option value="">
                    -- Select Treatment --
                </option>

                <%
                    if (treatments != null) {

                        for (Treatment treatment : treatments) {
                %>

                    <option
                        value="<%= treatment.getTreatmentId() %>"
                        data-cost="<%= treatment.getCost() %>">

                        <%= treatment.getTreatmentName() %>
                        -
                        Rs.
                        <%= String.format("%.2f", treatment.getCost()) %>

                    </option>

                <%
                        }
                    }
                %>

            </select>

        </div>


        <div class="calculation-box">

            <div class="calculation-row">

                <span>
                    Treatment Cost
                </span>

                <span>
                    Rs.
                    <span id="treatmentCost">
                        0.00
                    </span>
                </span>

            </div>


            <div class="calculation-row">

                <span>
                    Consultation Fee
                </span>

                <span>
                    Rs.
                    <span id="consultationFee">
                        <%= String.format("%.2f", consultationFee) %>
                    </span>
                </span>

            </div>


            <div class="calculation-row total-row">

                <span>
                    Total Amount
                </span>

                <span>
                    Rs.
                    <span id="totalAmount">
                        0.00
                    </span>
                </span>

            </div>

        </div>


        <div class="form-group">

            <label for="paymentStatus">
                Payment Status
            </label>

            <select
                id="paymentStatus"
                name="paymentStatus">

                <option value="Pending">
                    Pending
                </option>

                <option value="Paid">
                    Paid
                </option>

            </select>

        </div>


        <button
            type="submit"
            class="submit-btn">

            💰 Generate Bill

        </button>

    </form>


    <a
        href="<%= request.getContextPath() %>/bills"
        class="back-btn">

        ← Back to Bill List

    </a>

</div>


<script>

    function calculateBill() {

        const treatmentSelect =
            document.getElementById("treatmentId");

        const selectedOption =
            treatmentSelect.options[
                treatmentSelect.selectedIndex
            ];

        let treatmentCost =
            parseFloat(
                selectedOption.getAttribute("data-cost")
            );

        if (isNaN(treatmentCost)) {
            treatmentCost = 0;
        }

        const consultationFee =
            parseFloat(
                document.getElementById(
                    "consultationFee"
                ).innerText
            );

        const totalAmount =
            treatmentCost + consultationFee;

        document.getElementById(
            "treatmentCost"
        ).innerText =
            treatmentCost.toFixed(2);

        document.getElementById(
            "totalAmount"
        ).innerText =
            totalAmount.toFixed(2);
    }

</script>

</body>
</html>