package controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.ReservationDAO;

@WebServlet("/viewReservations")
public class ViewReservationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("list", new ReservationDAO().getAllReservations());
        request.getRequestDispatcher("viewReservation.jsp").forward(request, response);
    }
}