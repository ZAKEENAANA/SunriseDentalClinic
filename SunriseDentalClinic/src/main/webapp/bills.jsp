<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.sunrise.dental.model.Bill" %>

<%
    if (session.getAttribute("user") == null) {

        response.sendRedirect(
            request.getContextPath() + "/login.jsp"
        );

        return;
    }

    List<Bill> bills =
        (List<Bill>) request.getAttribute("bills");
%>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Bill List - Sunrise Dental Clinic</title>

    <style>

        * {
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7f9;
            margin: 0;
            padding: 30px;
        }

        .container {
            width: 95%;
            margin: auto;
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #1f4e79;
        }

        .top-bar {
            margin-bottom: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            gap: 10px;
            flex-wrap: wrap;
        }

        .left-buttons {
            display: flex;
            gap: 10px;
            flex-wrap: wrap;
        }

        .btn {
            display: inline-block;
            padding: 10px 18px;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .dashboard-btn {
            background-color: #0b6fa4;
        }

        .dashboard-btn:hover {
            background-color: #095b87;
        }

        .add-btn {
            background-color: #198754;
        }

        .add-btn:hover {
            background-color: #146c43;
        }

        .message {
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 5px;
            background-color: #d1e7dd;
            color: #0f5132;
        }

        .error {
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 5px;
            background-color: #f8d7da;
            color: #842029;
        }

        .table-wrapper {
            overflow-x: auto;
            background: white;
            border-radius: 8px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            min-width: 900px;
        }

        th,
        td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #343a40;
            color: white;
        }

        tr:hover {
            background-color: #f8f9fa;
        }

        .edit {
            color: #0d6efd;
            text-decoration: none;
            margin-right: 10px;
        }

        .delete {
            color: #dc3545;
            text-decoration: none;
            margin-right: 10px;
        }

        .print {
            color: #198754;
            text-decoration: none;
            font-weight: bold;
        }

        .status {
            font-weight: bold;
        }

        .empty {
            text-align: center;
            padding: 30px;
            background: white;
        }

        @media (max-width: 700px) {

            body {
                padding: 15px;
            }

            .container {
                width: 100%;
            }

            .top-bar {
                flex-direction: column;
                align-items: stretch;
            }

            .left-buttons {
                flex-direction: column;
            }

            .btn {
                text-align: center;
            }
        }

    </style>

</head>

<body>

<div class="container">

    <h2>
        🧾 Bill List
    </h2>


    <%
        String success =
            request.getParameter("success");

        String updated =
            request.getParameter("updated");

        String deleted =
            request.getParameter("deleted");

        String error =
            request.getParameter("error");
    %>


    <% if ("true".equals(success)) { %>

        <div class="message">
            ✅ Bill added successfully!
        </div>

    <% } %>


    <% if ("true".equals(updated)) { %>

        <div class="message">
            ✅ Bill updated successfully!
        </div>

    <% } %>


    <% if ("true".equals(deleted)) { %>

        <div class="message">
            ✅ Bill deleted successfully!
        </div>

    <% } %>


    <% if ("true".equals(error)) { %>

        <div class="error">
            ❌ Unable to process the bill.
        </div>

    <% } %>


    <div class="top-bar">

        <div class="left-buttons">

            <!-- Dashboard Button -->
            <a
                class="btn dashboard-btn"
                href="<%= request.getContextPath() %>/dashboard">

                🏠 Dashboard

            </a>


            <!-- Add New Bill Button -->
            <a
                class="btn add-btn"
                href="<%= request.getContextPath() %>/addBill">

                + Add New Bill

            </a>

        </div>

    </div>


    <div class="table-wrapper">

        <%
            if (bills != null && !bills.isEmpty()) {
        %>

        <table>

            <thead>

                <tr>

                    <th>Bill ID</th>

                    <th>Patient ID</th>

                    <th>Treatment ID</th>

                    <th>Amount (LKR)</th>

                    <th>Payment Status</th>

                    <th>Bill Date</th>

                    <th>Actions</th>

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

                        <%= bill.getTreatmentId() == 0
                            ? "-"
                            : bill.getTreatmentId() %>

                    </td>


                    <td>

                        Rs.

                        <%= String.format(
                            "%.2f",
                            bill.getAmount()
                        ) %>

                    </td>


                    <td class="status">

                        <%= bill.getPaymentStatus() == null
                            ? "-"
                            : bill.getPaymentStatus() %>

                    </td>


                    <td>

                        <%= bill.getBillDate() == null
                            ? "-"
                            : bill.getBillDate() %>

                    </td>


                    <td>

                        <a
                            class="edit"
                            href="<%= request.getContextPath() %>/editBill?id=<%= bill.getBillId() %>">

                            Edit

                        </a>


                        <a
                            class="delete"
                            href="<%= request.getContextPath() %>/deleteBill?id=<%= bill.getBillId() %>"
                            onclick="return confirm('Are you sure you want to delete this bill?');">

                            Delete

                        </a>


                        <a
                            class="print"
                            href="<%= request.getContextPath() %>/printBill?billId=<%= bill.getBillId() %>"
                            target="_blank">

                            🖨️ Print Receipt

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

            <div class="empty">

                📭 No bills found.

            </div>

        <%
            }
        %>

    </div>

</div>

</body>

</html>