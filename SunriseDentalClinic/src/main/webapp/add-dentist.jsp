<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Dentist - Sunrise Dental Clinic</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f9;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 600px;
            margin: 50px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 25px;
        }

        .form-group {
            margin-bottom: 18px;
        }

        label {
            display: block;
            margin-bottom: 7px;
            font-weight: bold;
            color: #34495e;
        }

        input {
            width: 100%;
            padding: 11px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 14px;
        }

        input:focus {
            border-color: #3498db;
            outline: none;
        }

        .buttons {
            margin-top: 25px;
            display: flex;
            justify-content: space-between;
        }

        .btn {
            padding: 11px 20px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            cursor: pointer;
            font-size: 14px;
        }

        .btn-submit {
            background-color: #3498db;
            color: white;
        }

        .btn-submit:hover {
            background-color: #2980b9;
        }

        .btn-back {
            background-color: #7f8c8d;
            color: white;
        }

        .btn-back:hover {
            background-color: #636e72;
        }

        .error {
            background-color: #f8d7da;
            color: #721c24;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 20px;
            text-align: center;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>Add New Dentist</h2>

    <% 
        String error = request.getParameter("error");

        if ("true".equals(error)) {
    %>
        <div class="error">
            Failed to add dentist. Please try again.
        </div>
    <% 
        }
    %>

    <form action="addDentist" method="post">

        <div class="form-group">
            <label for="firstName">First Name</label>
            <input type="text"
                   id="firstName"
                   name="firstName"
                   required>
        </div>

        <div class="form-group">
            <label for="lastName">Last Name</label>
            <input type="text"
                   id="lastName"
                   name="lastName"
                   required>
        </div>

        <div class="form-group">
            <label for="specialization">Specialization</label>
            <input type="text"
                   id="specialization"
                   name="specialization"
                   placeholder="e.g. Orthodontist">
        </div>

        <div class="form-group">
            <label for="phone">Phone</label>
            <input type="text"
                   id="phone"
                   name="phone"
                   placeholder="Enter phone number">
        </div>

        <div class="form-group">
            <label for="email">Email</label>
            <input type="email"
                   id="email"
                   name="email"
                   placeholder="Enter email address">
        </div>

        <div class="buttons">

            <a href="dentists" class="btn btn-back">
                Back
            </a>

            <button type="submit" class="btn btn-submit">
                Add Dentist
            </button>

        </div>

    </form>

</div>

</body>
</html>