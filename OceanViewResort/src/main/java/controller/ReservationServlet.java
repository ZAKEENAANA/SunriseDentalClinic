package controller;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.DBConnection;
import model.Reservation;

@WebServlet("/addReservation")
public class ReservationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String roomType = request.getParameter("roomType");

        try {
            Connection con = DBConnection.getConnection();

            // Count booked rooms of this type
            PreparedStatement ps = con.prepareStatement(
                "SELECT COUNT(*) FROM reservations WHERE room_type=?"
            );
            ps.setString(1, roomType);
            ResultSet rs = ps.executeQuery();
            int booked = 0;
            if(rs.next()) booked = rs.getInt(1);

            // Total rooms hardcoded (or query from room_types table)
            int totalRooms = 0;
            switch(roomType){
                case "Single": totalRooms = 10; break;
                case "Double": totalRooms = 5; break;
                case "Suite": totalRooms = 3; break;
            }

            if(booked >= totalRooms){
                // Room type fully booked
                response.sendRedirect("addReservation.jsp?error=full");
                return;
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        // Continue with reservation if available
        Reservation r = new Reservation();
        r.setReservationNumber(request.getParameter("reservationNumber"));
        r.setGuestName(request.getParameter("guestName"));
        r.setEmail(request.getParameter("email"));
        r.setAddress(request.getParameter("address"));
        r.setContactNumber(request.getParameter("contact"));
        r.setRoomType(roomType);
        r.setPricePerNight(Double.parseDouble(request.getParameter("price")));
        r.setCheckIn(request.getParameter("checkIn"));
        r.setCheckOut(request.getParameter("checkOut"));

        new dao.ReservationDAO().addReservation(r);

        response.sendRedirect("dashboard.jsp?success=1");
    }
}