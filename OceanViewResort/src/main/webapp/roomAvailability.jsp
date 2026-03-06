<%@ page import="java.sql.*,javax.servlet.*,javax.servlet.http.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Room Availability</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

<h2>Room Availability</h2>

<table border="1" cellpadding="10" cellspacing="0">
    <tr>
        <th>Room Type</th>
        <th>Booked Rooms</th>
        <th>Total Rooms</th>
        <th>Available Rooms</th>
    </tr>

<%
    ResultSet rs = (ResultSet) request.getAttribute("availabilityRS");
    // Hardcode total rooms per type here OR query room_types table if created
    java.util.Map<String, Integer> totalRooms = new java.util.HashMap<>();
    totalRooms.put("Single", 10);
    totalRooms.put("Double", 5);
    totalRooms.put("Suite", 3);

    while(rs.next()){
        String type = rs.getString("room_type");
        int booked = rs.getInt("booked");
        int total = totalRooms.getOrDefault(type, 0);
        int available = total - booked;
%>
    <tr>
        <td><%= type %></td>
        <td><%= booked %></td>
        <td><%= total %></td>
        <td><%= available %></td>
    </tr>
<%
    }
%>
</table>

<br>
<a href="dashboard.jsp">Back to Dashboard</a>

</body>
</html>