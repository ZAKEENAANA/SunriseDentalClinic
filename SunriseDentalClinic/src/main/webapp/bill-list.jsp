<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.sunrise.dental.model.Bill" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Bill List - Sunrise Dental Clinic</title>

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
            width: 95%;
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
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
        }

        .button:hover {
            background: #095b87;
        }

        table {
            width: 100%;
            border-collapse: collapse;
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

        .amount {
            font-weight: bold;
        }

        .status-form {
            display: flex;
            align-items: center;
            gap: 5px;
        }

        .status-select {
            padding: 6px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .update-btn {
            padding: 6px 10px;
            background: #0b6fa4;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .update-btn:hover {
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

        .no-data {
            text-align: center;
            padding: 30px;
            color: #777;
        }

        .back {
            display: block;
            margin-top: 20px;
            text-align: center;
            color: #0b6fa4;
            text-decoration: none;
        }

    </style>

</head>


<body>

    <div class="header">

        <h1>🦷 Sunrise Dental Clinic</h1>

        <p>Billing Management System</p>

    </div>


    <div class="container">

        <div class="top-bar">

            <h2>Bill List</h2>

            <a href="billing.jsp" class="button">
                + Create New Bill
            </a>

        </div>


        <%
            String success = request.getParameter("success");
            String error = request.getParameter("error");

            if ("true".equals(success)) {
        %>

            <div class="success">
                Payment status updated successfully!
            </div>

        <%
            }

            if ("true".equals(error)) {
        %>

            <div class="error">
                Failed to update payment status.
            </div>

        <%
            }
        %>


        <%
            List<Bill> bills =
                (List<Bill>) request.getAttribute("bills");

            if (bills != null && !bills.isEmpty()) {
        %>


        <table>

            <thead>

                <tr>

                    <th>Bill ID</th>

                    <th>Patient ID</th>

                    <th>Treatment ID</th>

                    <th>Amount</th>

                    <th>Payment Status</th>

                    <th>Bill Date</th>

                </tr>

            </thead>


            <tbody>

            <%
                for (Bill bill : bills) {
            %>

                <tr>

                    <td>
                        <%= bill.getBillId() %>
                    </td>


                    <td>
                        <%= bill.getPatientId() %>
                    </td>


                    <td>
                        <%= bill.getTreatmentId() %>
                    </td>


                    <td class="amount">
                        <%= String.format("%.2f", bill.getAmount()) %>
                    </td>


                    <td>

                        <form action="updatePaymentStatus"
                              method="post"
                              class="status-form">

                            <input type="hidden"
                                   name="billId"
                                   value="<%= bill.getBillId() %>">


                            <select name="paymentStatus"
                                    class="status-select">

                                <option value="Pending"
                                    <%= "Pending".equals(bill.getPaymentStatus())
                                        ? "selected" : "" %>>
                                    Pending
                                </option>


                                <option value="Paid"
                                    <%= "Paid".equals(bill.getPaymentStatus())
                                        ? "selected" : "" %>>
                                    Paid
                                </option>

                            </select>


                            <button type="submit"
                                    class="update-btn">

                                Update

                            </button>

                        </form>

                    </td>


                    <td>
                        <%= bill.getBillDate() %>
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

                No bills found.

            </div>

        <%
            }
        %>


        <a href="dashboard.jsp" class="back">
            ← Back to Dashboard
        </a>

    </div>

</body>

</html>