package controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.ReservationDAO;
import model.Reservation;

@WebServlet("/updateReservation")
public class UpdateReservationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	// Ensure proper encoding
        request.setCharacterEncoding("UTF-8");

        try {

            String idStr = request.getParameter("id");
            if (idStr == null || idStr.isEmpty()) {
            	response.sendRedirect("viewReservations.jsp?updated=1");
                return;
            }
    	
        Reservation r = new Reservation();

        r.setReservationId(Integer.parseInt(request.getParameter("id")));
        r.setReservationNumber(request.getParameter("reservationNumber"));
        r.setGuestName(request.getParameter("guestName"));
        r.setAddress(request.getParameter("address"));
        r.setContactNumber(request.getParameter("contact"));
        r.setRoomType(request.getParameter("roomType"));
        r.setPricePerNight(Double.parseDouble(request.getParameter("price")));
        r.setCheckIn(request.getParameter("checkIn"));
        r.setCheckOut(request.getParameter("checkOut"));
        r.setEmail(request.getParameter("email"));

     // Safe price parsing
        String priceStr = request.getParameter("price");
        double price = 0.0;
        if (priceStr != null && !priceStr.trim().isEmpty()) {
            price = Double.parseDouble(priceStr);
        }
        r.setPricePerNight(price);

        ReservationDAO dao = new ReservationDAO();
        dao.updateReservation(r);

        response.sendRedirect("viewReservations.jsp?updated=1");

    } catch (NumberFormatException e) {
        e.printStackTrace();
        response.sendRedirect("viewReservations.jsp?error=invalidNumber");

    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("viewReservations.jsp?error=serverError");
    }
}
}

        