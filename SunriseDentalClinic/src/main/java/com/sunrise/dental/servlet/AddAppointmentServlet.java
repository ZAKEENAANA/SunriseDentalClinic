package com.sunrise.dental.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrise.dental.dao.AppointmentDAO;
import com.sunrise.dental.model.Appointment;

@WebServlet("/addAppointment")
public class AddAppointmentServlet extends HttpServlet {

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

        String patientId = request.getParameter("patientId");
        String dentistName = request.getParameter("dentistName");
        String appointmentDate = request.getParameter("appointmentDate");
        String appointmentTime = request.getParameter("appointmentTime");
        String reason = request.getParameter("reason");

        if (patientId == null || patientId.trim().isEmpty()
                || dentistName == null || dentistName.trim().isEmpty()
                || appointmentDate == null || appointmentDate.trim().isEmpty()
                || appointmentTime == null || appointmentTime.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/add-appointment.jsp?error=empty"
            );

            return;
        }

        try {

            Appointment appointment = new Appointment();

            appointment.setPatientId(Integer.parseInt(patientId));
            appointment.setDentistName(dentistName.trim());
            appointment.setAppointmentDate(appointmentDate);
            appointment.setAppointmentTime(appointmentTime);
            appointment.setReason(reason);
            appointment.setStatus("Scheduled");

            boolean success = appointmentDAO.addAppointment(appointment);

            if (success) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/appointments?success=true"
                );

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/add-appointment.jsp?error=true"
                );
            }

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/add-appointment.jsp?error=invalid"
            );

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/add-appointment.jsp?error=true"
            );
        }
    }
}