package controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/bill")
public class BillServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        double price = Double.parseDouble(request.getParameter("price"));
        int days = Integer.parseInt(request.getParameter("days"));

        double total = price * days;
        request.setAttribute("total", total);

        request.getRequestDispatcher("bill.jsp").forward(request, response);
    }
}