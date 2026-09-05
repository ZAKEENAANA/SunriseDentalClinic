package com.sunrise.dental.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrise.dental.dao.AppointmentDAO;
import com.sunrise.dental.model.Appointment;

@WebServlet("/appointment")
public class AppointmentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AppointmentDAO appointmentDAO;

    @Override
    public void init() throws ServletException {
        appointmentDAO = new AppointmentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {

            int patientId = Integer.parseInt(
                    request.getParameter("patientId"));

            String appointmentDate =
                    request.getParameter("appointmentDate");

            String appointmentTime =
                    request.getParameter("appointmentTime");

            String reason =
                    request.getParameter("reason");

            String status =
                    request.getParameter("status");

            if (status == null || status.trim().isEmpty()) {
                status = "Scheduled";
            }

            if (appointmentTime != null
                    && appointmentTime.length() == 5) {

                appointmentTime = appointmentTime + ":00";
            }

            Appointment appointment =
                    new Appointment();

            appointment.setPatientId(patientId);
            appointment.setAppointmentDate(appointmentDate);
            appointment.setAppointmentTime(appointmentTime);
            appointment.setReason(reason);
            appointment.setStatus(status);

            boolean success =
                    appointmentDAO.addAppointment(appointment);

            if (success) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/appointments?success=true");

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/appointment.jsp?error=true");
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/appointment.jsp?error=true");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(
                request.getContextPath()
                + "/appointment.jsp");
    }
}