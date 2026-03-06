package controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.ReservationDAO;

@WebServlet("/deleteReservation")
public class DeleteReservationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        if (idStr == null || idStr.trim().isEmpty()) {
            // No ID provided
            response.sendRedirect("viewReservations.jsp?error=missingId");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            ReservationDAO dao = new ReservationDAO();
            dao.deleteReservation(id);

            response.sendRedirect("viewReservations.jsp?deleted=1");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("viewReservations.jsp?error=invalidId");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("viewReservations.jsp?error=serverError");
        }
    }
}