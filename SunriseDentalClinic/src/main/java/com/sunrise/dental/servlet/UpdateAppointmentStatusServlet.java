package com.sunrise.dental.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrise.dental.dao.AppointmentDAO;

@WebServlet("/updateAppointmentStatus")
public class UpdateAppointmentStatusServlet extends HttpServlet {

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

        String appointmentIdValue =
                request.getParameter("appointmentId");

        String status =
                request.getParameter("status");

        String appointmentDate =
                request.getParameter("appointmentDate");

        String appointmentTime =
                request.getParameter("appointmentTime");

        try {

            // Validate appointment ID
            if (appointmentIdValue == null
                    || appointmentIdValue.trim().isEmpty()) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/appointments?error=true"
                );

                return;
            }

            // Validate status
            if (status == null
                    || status.trim().isEmpty()) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/appointments?error=true"
                );

                return;
            }

            int appointmentId =
                    Integer.parseInt(appointmentIdValue);

            status = status.trim();


            // Allow only valid appointment statuses
            if (!"Scheduled".equals(status)
                    && !"Completed".equals(status)
                    && !"Cancelled".equals(status)
                    && !"Rescheduled".equals(status)) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/appointments?error=true"
                );

                return;
            }


            /*
             * RESCHEDULE
             *
             * If status is Rescheduled,
             * new date and time are required.
             */

            if ("Rescheduled".equals(status)) {

                if (appointmentDate == null
                        || appointmentDate.trim().isEmpty()
                        || appointmentTime == null
                        || appointmentTime.trim().isEmpty()) {

                    response.sendRedirect(
                            request.getContextPath()
                            + "/appointments?error=true"
                    );

                    return;
                }


                boolean success =
                        appointmentDAO.rescheduleAppointment(
                                appointmentId,
                                appointmentDate,
                                appointmentTime
                        );


                if (success) {

                    response.sendRedirect(
                            request.getContextPath()
                            + "/appointments?statusUpdated=true"
                    );

                } else {

                    response.sendRedirect(
                            request.getContextPath()
                            + "/appointments?error=true"
                    );
                }

                return;
            }


            /*
             * NORMAL STATUS UPDATE
             *
             * Scheduled / Completed / Cancelled
             */

            boolean success =
                    appointmentDAO.updateAppointmentStatus(
                            appointmentId,
                            status
                    );


            if (success) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/appointments?statusUpdated=true"
                );

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/appointments?error=true"
                );
            }


        } catch (NumberFormatException e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/appointments?error=true"
            );

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/appointments?error=true"
            );
        }
    }


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(
                request.getContextPath()
                + "/appointments"
        );
    }
}