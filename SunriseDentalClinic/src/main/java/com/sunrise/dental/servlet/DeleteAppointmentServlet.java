package com.sunrise.dental.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrise.dental.dao.AppointmentDAO;

@WebServlet("/deleteAppointment")
public class DeleteAppointmentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AppointmentDAO appointmentDAO;

    @Override
    public void init() throws ServletException {
        appointmentDAO = new AppointmentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String appointmentIdText =
                request.getParameter("appointmentId");

        if (appointmentIdText == null
                || appointmentIdText.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/appointments"
            );

            return;
        }

        try {

            int appointmentId =
                    Integer.parseInt(appointmentIdText);

            boolean success =
                    appointmentDAO.deleteAppointment(
                            appointmentId
                    );

            if (success) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/appointments?deleted=true"
                );

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/appointments?error=true"
                );
            }

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/appointments?error=invalid"
            );

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/appointments?error=true"
            );
        }
    }
}