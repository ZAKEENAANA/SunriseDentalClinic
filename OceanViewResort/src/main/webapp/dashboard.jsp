<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Ocean View Resort Dashboard</title>
</head>
<body>

<h2>Dashboard</h2>
<%
String msg = request.getParameter("msg");
if("success".equals(msg)){
%>
    <p style="color:green; font-weight:bold;">
        Monthly PDF Report sent successfully to all customers!
    </p>
<%
}
%>

<div class="dashboard">
    <a href="addReservation.jsp">Add Reservation</a><br>
    <a href="viewReservations">View Reservations</a><br>
    <a href="revenue">View Total Revenue</a><br><br>
    <a href="help.jsp">Help</a><br>
    <a href="roomAvailability">Room Availability</a><br>
    
    <!-- EXIT SYSTEM -->
    <a href="logout" onclick="return confirm('Are you sure you want to exit the system?')">
        Exit System
    </a>
   
</div>

<hr>

<h3>Generate Monthly PDF Report</h3>
<form action="monthlyReport" method="get">
    <label for="month">Select Month (YYYY-MM):</label>
    <input type="month" id="month" name="month" required>
    <button type="submit">Download Monthly PDF</button>
</form>

<hr>

<h3>Send Monthly PDF Report via Email</h3>
<form action="sendMonthlyReportEmail" method="post">
    <label for="monthEmail">Select Month (YYYY-MM):</label>
    <input type="month" id="monthEmail" name="monthEmail" required>
    <button type="submit">Send Email to All Customers</button>
</form>

</body>
</html>