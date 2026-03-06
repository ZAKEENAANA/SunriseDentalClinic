<%@ page import="java.sql.*,java.util.HashMap,java.util.Map,dao.DBConnection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Add Reservation</title>
</head>
<body>
<h2>Add Reservation</h2>

<%
    String error = request.getParameter("error");
    if("full".equals(error)){
%>
    <p style="color:red; font-weight:bold;">
        Sorry! Selected room type is fully booked. Please choose another.
    </p>
<%
    }
%>

<%
    // Calculate booked rooms per type
    Map<String,Integer> bookedMap = new HashMap<>();
    bookedMap.put("Single", 0);
    bookedMap.put("Double", 0);
    bookedMap.put("Suite", 0);

    try{
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT room_type, COUNT(*) AS booked FROM reservations GROUP BY room_type");
        while(rs.next()){
            bookedMap.put(rs.getString("room_type"), rs.getInt("booked"));
        }
    } catch(Exception e){
        e.printStackTrace();
    }

    // Total rooms per type
    Map<String,Integer> totalRooms = new HashMap<>();
    totalRooms.put("Single", 10);
    totalRooms.put("Double", 5);
    totalRooms.put("Suite", 3);
%>

<form action="addReservation" method="post">
    Guest Name:<br>
    <input type="text" name="guestName" required><br><br>

    Email:<br>
    <input type="email" name="email" required><br><br>

    Address:<br>
    <input type="text" name="address" required><br><br>

    Contact Number:<br>
    <input type="text" name="contact" required><br><br>

    Reservation Number:<br>
    <input type="text" name="reservationNumber" required><br><br>

    Room Type:<br>
    <select name="roomType" required>
        <option value="Single" <%= bookedMap.get("Single") >= totalRooms.get("Single") ? "disabled" : "" %>>
            Single (Available: <%= totalRooms.get("Single") - bookedMap.get("Single") %>)
        </option>
        <option value="Double" <%= bookedMap.get("Double") >= totalRooms.get("Double") ? "disabled" : "" %>>
            Double (Available: <%= totalRooms.get("Double") - bookedMap.get("Double") %>)
        </option>
        <option value="Suite" <%= bookedMap.get("Suite") >= totalRooms.get("Suite") ? "disabled" : "" %>>
            Suite (Available: <%= totalRooms.get("Suite") - bookedMap.get("Suite") %>)
        </option>
    </select><br><br>

    Price per Night:<br>
    <input type="number" name="price" required><br><br>

    Check-In:<br>
    <input type="date" name="checkIn" required><br><br>

    Check-Out:<br>
    <input type="date" name="checkOut" required><br><br>

    <button type="submit">Add Reservation</button>
</form>

<br>
<a href="dashboard.jsp">Back to Dashboard</a>
</body>
</html>