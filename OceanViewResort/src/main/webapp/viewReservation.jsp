<%@ page import="java.sql.*, dao.DBConnection" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Reservations</title>
<link rel="stylesheet" href="css/style.css">
 <!-- ✅ Browser Cache Prevent -->
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
<style>
    table { border-collapse: collapse; width: 100%; }
    th, td { padding: 8px; text-align: left; border: 1px solid #ddd; }
    th { background-color: #f2f2f2; }
    .paid { background-color: #c8e6c9; }  /* green */
    .unpaid { background-color: #ffcdd2; } /* red */
    a { text-decoration: none; color: blue; }
    a:hover { text-decoration: underline; }
    button { cursor: pointer; }
</style>
</head>
<body>

<h2>Reservations List</h2>

<%-- Feedback messages --%>
<%
    String updatedMsg = request.getParameter("updated");
    String deletedMsg = request.getParameter("deleted");
    String errorMsg = request.getParameter("error");

    if("1".equals(updatedMsg)){
%>
    <p style="color:green; font-weight:bold;">Reservation updated successfully!</p>
<%
    }
    if("1".equals(deletedMsg)){
%>
    <p style="color:green; font-weight:bold;">Reservation deleted successfully!</p>
<%
    }
    if(errorMsg != null){
%>
    <p style="color:red; font-weight:bold;">Error: <%= errorMsg %></p>
<%
    }
%>

<%
    List<Map<String,Object>> reservations = new ArrayList<>();
    try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reservations ORDER BY reservation_id DESC");
            ResultSet rs = ps.executeQuery()) {

        while(rs.next()){
            Map<String,Object> r = new HashMap<>();
            r.put("id", rs.getInt("reservation_id"));
            r.put("number", rs.getString("reservation_number"));
            r.put("guest", rs.getString("guest_name"));
            r.put("email", rs.getString("email"));
            r.put("room", rs.getString("room_type"));
            r.put("checkin", rs.getDate("check_in"));
            r.put("checkout", rs.getDate("check_out"));
            r.put("status", rs.getString("payment_status"));
            reservations.add(r);
        }
    } catch(Exception e){
        e.printStackTrace();
    }
%>

<table>
    <tr>
        <th>ID</th>
        <th>Reservation Number</th>
        <th>Guest Name</th>
        <th>Email</th>
        <th>Room Type</th>
        <th>Check In</th>
        <th>Check Out</th>
        <th>Payment Status</th>
        <th>Actions</th>
        <th>Invoice PDF</th>
    </tr>

<%
    for(Map<String,Object> r : reservations){
%>
    <tr>
        <td><%= r.get("id") %></td>
        <td><%= r.get("number") %></td>
        <td><%= r.get("guest") %></td>
        <td><%= r.get("email") %></td>
        <td><%= r.get("room") %></td>
        <td><%= r.get("checkin") %></td>
        <td><%= r.get("checkout") %></td>
        <td class="<%= "Paid".equalsIgnoreCase((String)r.get("status")) ? "paid" : "unpaid" %>">
            <%= r.get("status") %>
        </td>
        <td>
            <a href="editReservation.jsp?id=<%= r.get("id") %>">Edit</a> |
            <a href="deleteReservation?id=<%= r.get("id") %>" 
               onclick="return confirm('Are you sure you want to delete this reservation?')">Delete</a>
        </td>
        <td>
            <form action="generatePDF" method="get" style="margin:0;">
                <input type="hidden" name="id" value="<%= r.get("id") %>">
                <button type="submit">Download PDF</button>
            </form>
        </td>
    </tr>
<%
    }
%>
</table>

<br>
<a href="dashboard.jsp">Back to Dashboard</a>

</body>
</html>