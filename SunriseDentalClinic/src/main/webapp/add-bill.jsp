<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Add Bill - Sunrise Dental Clinic</title>

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
        select {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
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

    <h2>Add New Bill</h2>

    <form action="<%= request.getContextPath() %>/addBill"
          method="post">

        <label>Patient ID</label>

        <input type="number"
               name="patientId"
               min="1"
               required>


        <label>Treatment ID</label>

        <input type="number"
               name="treatmentId"
               min="1"
               placeholder="Optional">


        <label>Amount (LKR)</label>

        <input type="number"
               name="amount"
               step="0.01"
               min="0"
               required>


        <label>Payment Status</label>

        <select name="paymentStatus" required>

            <option value="Pending">Pending</option>

            <option value="Paid">Paid</option>

            <option value="Cancelled">Cancelled</option>

        </select>


        <div class="buttons">

            <button type="submit">
                Add Bill
            </button>

            <a href="<%= request.getContextPath() %>/bills">
                View Bills
            </a>

        </div>

    </form>

</div>

</body>
</html>