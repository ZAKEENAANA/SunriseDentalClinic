package com.sunrise.dental.webservice;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sunrise.dental.dao.AppointmentDAO;
import com.sunrise.dental.model.Appointment;

@Path("/appointments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppointmentWebService {

    private AppointmentDAO appointmentDAO =
            new AppointmentDAO();


    // ==========================================
    // GET ALL APPOINTMENTS
    // ==========================================

    @GET
    public Response getAllAppointments() {

        List<Appointment> appointments =
                appointmentDAO.getAllAppointments();

        return Response.ok(appointments).build();
    }


    // ==========================================
    // GET APPOINTMENT BY ID
    // ==========================================

    @GET
    @Path("/{id}")
    public Response getAppointmentById(
            @PathParam("id") int appointmentId) {

        if (appointmentId <= 0) {

            return Response.status(
                    Response.Status.BAD_REQUEST)
                    .entity(
                            "{\"error\":\"Appointment ID must be greater than 0\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        Appointment appointment =
                appointmentDAO.getAppointmentById(
                        appointmentId);

        if (appointment == null) {

            return Response.status(
                    Response.Status.NOT_FOUND)
                    .entity(
                            "{\"error\":\"Appointment not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        return Response.ok(appointment).build();
    }


    // ==========================================
    // POST - CREATE NEW APPOINTMENT
    // ==========================================

    @POST
    public Response createAppointment(
            Appointment appointment) {

        // Check empty request
        if (appointment == null) {

            return badRequest(
                    "Appointment data is required");
        }

        // Patient ID validation
        if (appointment.getPatientId() <= 0) {

            return badRequest(
                    "Valid patient ID is required");
        }

        // Dentist validation
        if (appointment.getDentistName() == null
                || appointment.getDentistName().trim().isEmpty()) {

            return badRequest(
                    "Dentist name is required");
        }

        // Date validation
        if (appointment.getAppointmentDate() == null
                || appointment.getAppointmentDate().trim().isEmpty()) {

            return badRequest(
                    "Appointment date is required");
        }

        try {

            LocalDate.parse(
                    appointment.getAppointmentDate());

        } catch (Exception e) {

            return badRequest(
                    "Invalid appointment date. Use YYYY-MM-DD");
        }


        // Time validation
        if (appointment.getAppointmentTime() == null
                || appointment.getAppointmentTime().trim().isEmpty()) {

            return badRequest(
                    "Appointment time is required");
        }

        try {

            String time =
                    appointment.getAppointmentTime();

            if (time.length() == 5) {
                time = time + ":00";
            }

            LocalTime.parse(time);

        } catch (Exception e) {

            return badRequest(
                    "Invalid appointment time. Use HH:mm");
        }


        // Reason validation
        if (appointment.getReason() == null
                || appointment.getReason().trim().isEmpty()) {

            return badRequest(
                    "Appointment reason is required");
        }


        // Status validation
        if (!isValidStatus(
                appointment.getStatus())) {

            return badRequest(
                    "Invalid status. Use Scheduled, Completed, Cancelled or Rescheduled");
        }


        // Save appointment
        boolean success =
                appointmentDAO.addAppointment(
                        appointment);

        if (success) {

            return Response.status(
                    Response.Status.CREATED)
                    .entity(
                            "{\"message\":\"Appointment created successfully\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        return Response.status(
                Response.Status.INTERNAL_SERVER_ERROR)
                .entity(
                        "{\"error\":\"Failed to create appointment\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }


    // ==========================================
    // PUT - UPDATE APPOINTMENT
    // ==========================================

    @PUT
    @Path("/{id}")
    public Response updateAppointment(
            @PathParam("id") int appointmentId,
            Appointment appointment) {

        if (appointmentId <= 0) {

            return badRequest(
                    "Appointment ID must be greater than 0");
        }

        if (appointment == null) {

            return badRequest(
                    "Appointment data is required");
        }

        Appointment existingAppointment =
                appointmentDAO.getAppointmentById(
                        appointmentId);

        if (existingAppointment == null) {

            return Response.status(
                    Response.Status.NOT_FOUND)
                    .entity(
                            "{\"error\":\"Appointment not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }


        // Date validation
        if (appointment.getAppointmentDate() == null
                || appointment.getAppointmentDate().trim().isEmpty()) {

            return badRequest(
                    "Appointment date is required");
        }

        try {

            LocalDate.parse(
                    appointment.getAppointmentDate());

        } catch (Exception e) {

            return badRequest(
                    "Invalid appointment date. Use YYYY-MM-DD");
        }


        // Time validation
        if (appointment.getAppointmentTime() == null
                || appointment.getAppointmentTime().trim().isEmpty()) {

            return badRequest(
                    "Appointment time is required");
        }

        try {

            String time =
                    appointment.getAppointmentTime();

            if (time.length() == 5) {
                time = time + ":00";
            }

            LocalTime.parse(time);

        } catch (Exception e) {

            return badRequest(
                    "Invalid appointment time. Use HH:mm");
        }


        // Status validation
        if (!isValidStatus(
                appointment.getStatus())) {

            return badRequest(
                    "Invalid status. Use Scheduled, Completed, Cancelled or Rescheduled");
        }


        boolean dateTimeUpdated =
                appointmentDAO.rescheduleAppointment(
                        appointmentId,
                        appointment.getAppointmentDate(),
                        appointment.getAppointmentTime());

        boolean statusUpdated =
                appointmentDAO.updateAppointmentStatus(
                        appointmentId,
                        appointment.getStatus());


        if (dateTimeUpdated || statusUpdated) {

            return Response.ok()
                    .entity(
                            "{\"message\":\"Appointment updated successfully\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        return Response.status(
                Response.Status.INTERNAL_SERVER_ERROR)
                .entity(
                        "{\"error\":\"Failed to update appointment\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }


    // ==========================================
    // DELETE - DELETE APPOINTMENT
    // ==========================================

    @DELETE
    @Path("/{id}")
    public Response deleteAppointment(
            @PathParam("id") int appointmentId) {

        if (appointmentId <= 0) {

            return badRequest(
                    "Appointment ID must be greater than 0");
        }

        Appointment appointment =
                appointmentDAO.getAppointmentById(
                        appointmentId);

        if (appointment == null) {

            return Response.status(
                    Response.Status.NOT_FOUND)
                    .entity(
                            "{\"error\":\"Appointment not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        boolean success =
                appointmentDAO.deleteAppointment(
                        appointmentId);

        if (success) {

            return Response.ok()
                    .entity(
                            "{\"message\":\"Appointment deleted successfully\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        return Response.status(
                Response.Status.INTERNAL_SERVER_ERROR)
                .entity(
                        "{\"error\":\"Failed to delete appointment\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }


    // ==========================================
    // VALIDATE STATUS
    // ==========================================

    private boolean isValidStatus(String status) {

        if (status == null) {
            return false;
        }

        return Arrays.asList(
                "Scheduled",
                "Completed",
                "Cancelled",
                "Rescheduled"
        ).contains(status);
    }


    // ==========================================
    // BAD REQUEST RESPONSE
    // ==========================================

    private Response badRequest(String message) {

        return Response.status(
                Response.Status.BAD_REQUEST)
                .entity(
                        "{\"error\":\"" + message + "\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}