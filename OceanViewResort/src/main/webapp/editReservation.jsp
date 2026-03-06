<%@ page import="java.sql.*, dao.DBConnection" %>
<%@ page import="model.Reservation" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Reservation</title>
<link rel="stylesheet" href="css/style.css">
 <!-- ✅ Browser Cache Prevent -->
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
<style>
    form { max-width: 500px; margin: auto; }
    label { display: block; margin-top: 10px; font-weight: bold; }
    input, select { width: 100%; padding: 8px; margin-top: 5px; }
    button { margin-top: 15px; padding: 10px 20px; }
</style>
</head>
<body>

<%
    String idStr = request.getParameter("id");
    if(idStr == null || idStr.trim().isEmpty()){
%>
    <p style="color:red;">Invalid reservation ID.</p>
<%
        return;
    }

    int id = Integer.parseInt(idStr);
    Reservation r = null;

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM reservations WHERE reservation_id=?")) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            r = new Reservation();
            r.setReservationId(rs.getInt("reservation_id"));
            r.setReservationNumber(rs.getString("reservation_number"));
            r.setGuestName(rs.getString("guest_name"));
            r.setAddress(rs.getString("address"));
            r.setContactNumber(rs.getString("contact_number"));
            r.setRoomType(rs.getString("room_type"));
            r.setPricePerNight(rs.getDouble("price_per_night"));
            r.setCheckIn(rs.getString("check_in"));
            r.setCheckOut(rs.getString("check_out"));
            r.setEmail(rs.getString("email"));
        } else {
%>
            <p style="color:red;">Reservation not found.</p>
<%
            return;
        }
    } catch(Exception e){
        e.printStackTrace();
    }
%>

<h2>Edit Reservation</h2>

<form action="updateReservation" method="post">
    <input type="hidden" name="id" value="<%= r.getReservationId() %>">

    <label>Reservation Number:</label>
    <input type="text" name="reservationNumber" required value="<%= r.getReservationNumber() %>">

    <label>Guest Name:</label>
    <input type="text" name="guestName" required value="<%= r.getGuestName() %>">

    <label>Email:</label>
    <input type="email" name="email" required value="<%= r.getEmail() %>">

    <label>Address:</label>
    <input type="text" name="address" required value="<%= r.getAddress() %>">

    <label>Contact Number:</label>
    <input type="text" name="contact" required value="<%= r.getContactNumber() %>">

    <label>Room Type:</label>
    <select name="roomType" required>
        <option value="Single" <%= "Single".equals(r.getRoomType()) ? "selected" : "" %>>Single</option>
        <option value="Double" <%= "Double".equals(r.getRoomType()) ? "selected" : "" %>>Double</option>
        <option value="Suite" <%= "Suite".equals(r.getRoomType()) ? "selected" : "" %>>Suite</option>
    </select>

    <label>Price per Night:</label>
    <input type="number" step="0.01" name="price" required value="<%= r.getPricePerNight() %>">

    <label>Check-In Date:</label>
    <input type="date" name="checkIn" required value="<%= r.getCheckIn() %>">

    <label>Check-Out Date:</label>
    <input type="date" name="checkOut" required value="<%= r.getCheckOut() %>">

    <button type="submit">Update Reservation</button>
</form>

<br>
<a href="viewReservations.jsp">Back to Reservations</a>

</body>
</html>