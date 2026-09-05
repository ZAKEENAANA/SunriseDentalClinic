package com.sunrise.dental.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrise.dental.dao.AppointmentDAO;
import com.sunrise.dental.dao.PatientDAO;
import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.model.Patient;

@WebServlet("/searchAppointment")
public class SearchAppointmentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AppointmentDAO appointmentDAO;
    private PatientDAO patientDAO;

    @Override
    public void init() throws ServletException {
        appointmentDAO = new AppointmentDAO();
        patientDAO = new PatientDAO();
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
                    + "/search-appointment.jsp"
            );

            return;
        }

        try {

            int appointmentId =
                    Integer.parseInt(appointmentIdText);

            Appointment appointment =
                    appointmentDAO.getAppointmentById(appointmentId);

            if (appointment != null) {

                Patient patient =
                        patientDAO.getPatientById(
                                appointment.getPatientId());

                request.setAttribute(
                        "appointment",
                        appointment);

                request.setAttribute(
                        "patient",
                        patient);

                request.getRequestDispatcher(
                        "/appointment-details.jsp")
                        .forward(request, response);

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/search-appointment.jsp?error=notfound"
                );
            }

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/search-appointment.jsp?error=invalid"
            );

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/search-appointment.jsp?error=true"
            );
        }
    }
}