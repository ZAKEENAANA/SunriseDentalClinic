package controller;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.DBConnection;

@WebServlet("/roomAvailability")
public class RoomAvailabilityServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Connection con = DBConnection.getConnection();

            // Count booked rooms per type
            PreparedStatement ps = con.prepareStatement(
                "SELECT room_type, COUNT(*) as booked FROM reservations GROUP BY room_type"
            );

            ResultSet rs = ps.executeQuery();

            request.setAttribute("availabilityRS", rs);
            RequestDispatcher rd = request.getRequestDispatcher("roomAvailability.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}