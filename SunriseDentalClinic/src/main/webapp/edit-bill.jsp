<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.sunrise.dental.model.Bill"%>
<%@ page import="com.sunrise.dental.model.Patient"%>
<%@ page import="com.sunrise.dental.model.Treatment"%>

<%
    Bill bill = (Bill) request.getAttribute("bill");

    List<Patient> patients =
        (List<Patient>) request.getAttribute("patients");

    List<Treatment> treatments =
        (List<Treatment>) request.getAttribute("treatments");
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Edit Bill - Sunrise Dental Clinic</title>

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

        .bill-id {
            background: #eaf5fb;
            padding: 12px;
            border-radius: 5px;
            margin-bottom: 20px;
            text-align: center;
            font-weight: bold;
            color: #095b87;
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
        select {
            width: 100%;
            padding: 11px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }

        input:focus,
        select:focus {
            outline: none;
            border-color: #0b6fa4;
        }

        .update-btn {
            width: 100%;
            padding: 12px;
            background: #198754;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        .update-btn:hover {
            background: #157347;
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

    <p>Billing Management System</p>

</div>


<div class="container">

    <h2>Edit Bill</h2>


    <div class="bill-id">

        Bill ID:
        <%= bill.getBillId() %>

    </div>


    <form action="<%= request.getContextPath() %>/editBill"
          method="post">


        <!-- Bill ID -->

        <input
            type="hidden"
            name="billId"
            value="<%= bill.getBillId() %>">


        <!-- Patient -->

        <div class="form-group">

            <label for="patientId">
                Patient
            </label>

            <select
                id="patientId"
                name="patientId"
                required>

                <option value="">
                    Select Patient
                </option>

                <%

                    if (patients != null) {

                        for (Patient patient : patients) {

                %>

                    <option
                        value="<%= patient.getPatientId() %>"
                        <%= patient.getPatientId()
                            == bill.getPatientId()
                            ? "selected" : "" %>>

                        ID:
                        <%= patient.getPatientId() %>
                        -
                        <%= patient.getFirstName() %>
                        <%= patient.getLastName() %>
                        -
                        <%= patient.getPhone() %>

                    </option>

                <%

                        }

                    }

                %>

            </select>

        </div>


        <!-- Treatment -->

        <div class="form-group">

            <label for="treatmentId">
                Treatment
            </label>

            <select
                id="treatmentId"
                name="treatmentId"
                required>

                <option value="">
                    Select Treatment
                </option>

                <%

                    if (treatments != null) {

                        for (Treatment treatment : treatments) {

                %>

                    <option
                        value="<%= treatment.getTreatmentId() %>"
                        data-cost="<%= treatment.getCost() %>"
                        <%= treatment.getTreatmentId()
                            == bill.getTreatmentId()
                            ? "selected" : "" %>>

                        ID:
                        <%= treatment.getTreatmentId() %>
                        -
                        <%= treatment.getTreatmentName() %>
                        -
                        Patient ID:
                        <%= treatment.getPatientId() %>
                        -
                        LKR
                        <%= String.format("%.2f",
                            treatment.getCost()) %>

                    </option>

                <%

                        }

                    }

                %>

            </select>

        </div>


        <!-- Amount -->

        <div class="form-group">

            <label for="amount">
                Amount (LKR)
            </label>

            <input
                type="number"
                id="amount"
                name="amount"
                min="0"
                step="0.01"
                value="<%= bill.getAmount() %>"
                required>

        </div>


        <!-- Payment Status -->

        <div class="form-group">

            <label for="paymentStatus">
                Payment Status
            </label>

            <select
                id="paymentStatus"
                name="paymentStatus"
                required>

                <option value="Pending"
                    <%= "Pending".equals(
                        bill.getPaymentStatus())
                        ? "selected" : "" %>>

                    Pending

                </option>

                <option value="Paid"
                    <%= "Paid".equals(
                        bill.getPaymentStatus())
                        ? "selected" : "" %>>

                    Paid

                </option>

                <option value="Cancelled"
                    <%= "Cancelled".equals(
                        bill.getPaymentStatus())
                        ? "selected" : "" %>>

                    Cancelled

                </option>

            </select>

        </div>


        <!-- Update Button -->

        <button
            type="submit"
            class="update-btn">

            💾 Update Bill

        </button>

    </form>


    <div class="links">

        <a href="<%= request.getContextPath() %>/bills">

            ← Back to Bill List

        </a>

        <a href="<%= request.getContextPath() %>/dashboard.jsp">

            ← Back to Dashboard

        </a>

    </div>

</div>


<script>

    const treatmentSelect =
        document.getElementById("treatmentId");

    const amountInput =
        document.getElementById("amount");


    treatmentSelect.addEventListener(
        "change",
        function() {

            const selectedOption =
                this.options[this.selectedIndex];

            const cost =
                selectedOption.getAttribute("data-cost");

            if (cost) {

                amountInput.value =
                    parseFloat(cost).toFixed(2);

            }

        }
    );

</script>


</body>

</html>