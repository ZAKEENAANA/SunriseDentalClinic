<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.sunrise.dental.model.Bill" %>
<%@ page import="com.sunrise.dental.model.Patient" %>
<%@ page import="com.sunrise.dental.model.Treatment" %>

<%
    Bill bill =
            (Bill) request.getAttribute("bill");

    Patient patient =
            (Patient) request.getAttribute("patient");

    Treatment treatment =
            (Treatment) request.getAttribute("treatment");

    if (bill == null) {

        response.sendRedirect(
                request.getContextPath()
                + "/bills"
        );

        return;
    }

    double consultationFee = 1500.00;

    double treatmentCost = 0.00;

    if (treatment != null) {
        treatmentCost = treatment.getCost();
    }

    double totalAmount = bill.getAmount();
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>
        Dental Bill Receipt
    </title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background: #f4f7f9;
            margin: 0;
            padding: 40px;
        }

        .receipt {
            width: 750px;
            max-width: 100%;
            margin: auto;
            background: white;
            padding: 40px;
            box-sizing: border-box;
            border: 1px solid #ddd;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }

        .header {
            text-align: center;
            border-bottom: 2px solid #1f4e79;
            padding-bottom: 20px;
        }

        .header h1 {
            margin: 0;
            color: #1f4e79;
        }

        .header p {
            margin: 6px 0;
            color: #555;
        }

        .bill-info {
            display: flex;
            justify-content: space-between;
            margin-top: 25px;
            margin-bottom: 25px;
        }

        .section-title {
            color: #1f4e79;
            font-size: 18px;
            font-weight: bold;
            margin-top: 25px;
            margin-bottom: 10px;
        }

        .details {
            border: 1px solid #ddd;
            border-radius: 6px;
            padding: 15px;
        }

        .row {
            display: flex;
            justify-content: space-between;
            gap: 20px;
            padding: 10px 0;
            border-bottom: 1px solid #eee;
        }

        .row:last-child {
            border-bottom: none;
        }

        .label {
            font-weight: bold;
            color: #555;
        }

        .value {
            color: #222;
            text-align: right;
        }

        .total {
            margin-top: 15px;
            border-top: 2px solid #1f4e79;
            padding-top: 15px;
            font-size: 20px;
            font-weight: bold;
            color: #1f4e79;
        }

        .status {
            text-align: center;
            margin-top: 20px;
            font-weight: bold;
            font-size: 16px;
        }

        .buttons {
            text-align: center;
            margin-top: 30px;
        }

        .print-btn,
        .back-btn {
            display: inline-block;
            padding: 12px 20px;
            margin: 5px;
            border-radius: 6px;
            text-decoration: none;
            border: none;
            cursor: pointer;
            font-size: 15px;
        }

        .print-btn {
            background: #1f4e79;
            color: white;
        }

        .back-btn {
            background: #777;
            color: white;
        }

        .footer {
            text-align: center;
            margin-top: 35px;
            padding-top: 15px;
            border-top: 1px solid #ddd;
            color: #777;
            font-size: 13px;
        }

        @media print {

            body {
                background: white;
                padding: 0;
            }

            .receipt {
                width: 100%;
                box-shadow: none;
                border: none;
            }

            .buttons {
                display: none;
            }

        }

    </style>

</head>

<body>

<div class="receipt">

    <div class="header">

        <h1>
            🦷 Sunrise Dental Clinic
        </h1>

        <p>
            Dental Care & Treatment Centre
        </p>

        <p>
            Colombo, Sri Lanka
        </p>

    </div>


    <div class="bill-info">

        <div>

            <strong>
                Bill No:
            </strong>

            <%= bill.getBillId() %>

        </div>

        <div>

            <strong>
                Date:
            </strong>

            <%= bill.getBillDate() != null
                ? bill.getBillDate()
                : "-" %>

        </div>

    </div>


    <!-- PATIENT INFORMATION -->

    <div class="section-title">
        Patient Information
    </div>

    <div class="details">

        <div class="row">

            <span class="label">
                Patient ID
            </span>

            <span class="value">
                <%= bill.getPatientId() %>
            </span>

        </div>


        <div class="row">

            <span class="label">
                Patient Name
            </span>

            <span class="value">

                <%
                    if (patient != null) {
                %>

                    <%= patient.getFirstName() %>
                    <%= patient.getLastName() %>

                <%
                    } else {
                %>

                    -

                <%
                    }
                %>

            </span>

        </div>


        <div class="row">

            <span class="label">
                Phone
            </span>

            <span class="value">

                <%= patient != null
                    && patient.getPhone() != null
                    ? patient.getPhone()
                    : "-" %>

            </span>

        </div>


        <div class="row">

            <span class="label">
                Email
            </span>

            <span class="value">

                <%= patient != null
                    && patient.getEmail() != null
                    ? patient.getEmail()
                    : "-" %>

            </span>

        </div>


        <div class="row">

            <span class="label">
                Address
            </span>

            <span class="value">

                <%= patient != null
                    && patient.getAddress() != null
                    ? patient.getAddress()
                    : "-" %>

            </span>

        </div>

    </div>


    <!-- TREATMENT INFORMATION -->

    <div class="section-title">
        Treatment Information
    </div>

    <div class="details">

        <div class="row">

            <span class="label">
                Treatment ID
            </span>

            <span class="value">

                <%= bill.getTreatmentId() > 0
                    ? bill.getTreatmentId()
                    : "-" %>

            </span>

        </div>


        <div class="row">

            <span class="label">
                Treatment Name
            </span>

            <span class="value">

                <%= treatment != null
                    && treatment.getTreatmentName() != null
                    ? treatment.getTreatmentName()
                    : "-" %>

            </span>

        </div>


        <div class="row">

            <span class="label">
                Description
            </span>

            <span class="value">

                <%= treatment != null
                    && treatment.getDescription() != null
                    ? treatment.getDescription()
                    : "-" %>

            </span>

        </div>


        <div class="row">

            <span class="label">
                Treatment Cost
            </span>

            <span class="value">

                Rs.

                <%= String.format(
                        "%.2f",
                        treatmentCost
                    ) %>

            </span>

        </div>


        <div class="row">

            <span class="label">
                Consultation Fee
            </span>

            <span class="value">

                Rs.

                <%= String.format(
                        "%.2f",
                        consultationFee
                    ) %>

            </span>

        </div>


        <div class="row total">

            <span>
                Total Amount
            </span>

            <span>

                Rs.

                <%= String.format(
                        "%.2f",
                        totalAmount
                    ) %>

            </span>

        </div>

    </div>


    <!-- PAYMENT STATUS -->

    <div class="status">

        Payment Status:

        <%= bill.getPaymentStatus() != null
            ? bill.getPaymentStatus()
            : "-" %>

    </div>


    <!-- BUTTONS -->

    <div class="buttons">

        <button
            type="button"
            class="print-btn"
            onclick="window.print()">

            🖨️ Print Receipt

        </button>


        <a
            href="<%= request.getContextPath() %>/bills"
            class="back-btn">

            ← Back to Bills

        </a>

    </div>


    <div class="footer">

        Thank you for choosing
        Sunrise Dental Clinic.

        <br>

        Please keep this receipt for your records.

    </div>

</div>

</body>

</html>