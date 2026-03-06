package controller;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.DBConnection;

@WebServlet("/revenue")
public class RevenueServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        double totalRevenue = 0;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT price_per_night, check_in, check_out FROM reservations";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){

                double price = rs.getDouble("price_per_night");

                Date checkIn = rs.getDate("check_in");
                Date checkOut = rs.getDate("check_out");

                long diff = (checkOut.getTime() - checkIn.getTime()) / (1000*60*60*24);

                totalRevenue += price * diff;

            }

        } catch(Exception e){
            e.printStackTrace();
        }

        request.setAttribute("revenue", totalRevenue);
        RequestDispatcher rd = request.getRequestDispatcher("revenue.jsp");
        rd.forward(request, response);
    }
}