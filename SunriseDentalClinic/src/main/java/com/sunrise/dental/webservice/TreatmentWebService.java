package com.sunrise.dental.webservice;

import java.time.LocalDate;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sunrise.dental.dao.TreatmentDAO;
import com.sunrise.dental.model.Treatment;

@Path("/treatments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TreatmentWebService {

    private TreatmentDAO treatmentDAO = new TreatmentDAO();

    // GET ALL TREATMENTS
    @GET
    public Response getAllTreatments() {

        return Response.ok(
                treatmentDAO.getAllTreatments()
        ).build();
    }

    // GET TREATMENT BY ID
    @GET
    @Path("/{id}")
    public Response getTreatmentById(
            @PathParam("id") int treatmentId) {

        if (treatmentId <= 0) {
            return badRequest(
                    "Treatment ID must be greater than 0");
        }

        Treatment treatment =
                treatmentDAO.getTreatmentById(treatmentId);

        if (treatment == null) {
            return Response.status(
                    Response.Status.NOT_FOUND)
                    .entity(
                            "{\"error\":\"Treatment not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        return Response.ok(treatment).build();
    }

    // CREATE TREATMENT
    @POST
    public Response createTreatment(
            Treatment treatment) {

        if (treatment == null) {
            return badRequest(
                    "Treatment data is required");
        }

        if (treatment.getPatientId() <= 0) {
            return badRequest(
                    "Valid patient ID is required");
        }

        if (treatment.getTreatmentName() == null
                || treatment.getTreatmentName().trim().isEmpty()) {

            return badRequest(
                    "Treatment name is required");
        }

        if (treatment.getDescription() == null
                || treatment.getDescription().trim().isEmpty()) {

            return badRequest(
                    "Treatment description is required");
        }

        if (treatment.getTreatmentDate() == null
                || treatment.getTreatmentDate().trim().isEmpty()) {

            return badRequest(
                    "Treatment date is required");
        }

        try {

            LocalDate.parse(
                    treatment.getTreatmentDate());

        } catch (Exception e) {

            return badRequest(
                    "Invalid treatment date. Use YYYY-MM-DD");
        }

        if (treatment.getCost() <= 0) {
            return badRequest(
                    "Treatment cost must be greater than 0");
        }

        boolean success =
                treatmentDAO.addTreatment(treatment);

        if (success) {

            return Response.status(
                    Response.Status.CREATED)
                    .entity(
                            "{\"message\":\"Treatment created successfully\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        return Response.status(
                Response.Status.INTERNAL_SERVER_ERROR)
                .entity(
                        "{\"error\":\"Failed to create treatment\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    // UPDATE TREATMENT
    @PUT
    @Path("/{id}")
    public Response updateTreatment(
            @PathParam("id") int treatmentId,
            Treatment treatment) {

        if (treatmentId <= 0) {
            return badRequest(
                    "Treatment ID must be greater than 0");
        }

        if (treatment == null) {
            return badRequest(
                    "Treatment data is required");
        }

        Treatment existingTreatment =
                treatmentDAO.getTreatmentById(
                        treatmentId);

        if (existingTreatment == null) {

            return Response.status(
                    Response.Status.NOT_FOUND)
                    .entity(
                            "{\"error\":\"Treatment not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        if (treatment.getPatientId() <= 0) {
            return badRequest(
                    "Valid patient ID is required");
        }

        if (treatment.getTreatmentName() == null
                || treatment.getTreatmentName().trim().isEmpty()) {

            return badRequest(
                    "Treatment name is required");
        }

        if (treatment.getDescription() == null
                || treatment.getDescription().trim().isEmpty()) {

            return badRequest(
                    "Treatment description is required");
        }

        if (treatment.getTreatmentDate() == null
                || treatment.getTreatmentDate().trim().isEmpty()) {

            return badRequest(
                    "Treatment date is required");
        }

        try {

            LocalDate.parse(
                    treatment.getTreatmentDate());

        } catch (Exception e) {

            return badRequest(
                    "Invalid treatment date. Use YYYY-MM-DD");
        }

        if (treatment.getCost() <= 0) {
            return badRequest(
                    "Treatment cost must be greater than 0");
        }

        treatment.setTreatmentId(treatmentId);

        boolean success =
                treatmentDAO.updateTreatment(treatment);

        if (success) {

            return Response.ok()
                    .entity(
                            "{\"message\":\"Treatment updated successfully\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        return Response.status(
                Response.Status.INTERNAL_SERVER_ERROR)
                .entity(
                        "{\"error\":\"Failed to update treatment\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    // DELETE TREATMENT
    @DELETE
    @Path("/{id}")
    public Response deleteTreatment(
            @PathParam("id") int treatmentId) {

        if (treatmentId <= 0) {
            return badRequest(
                    "Treatment ID must be greater than 0");
        }

        Treatment treatment =
                treatmentDAO.getTreatmentById(
                        treatmentId);

        if (treatment == null) {

            return Response.status(
                    Response.Status.NOT_FOUND)
                    .entity(
                            "{\"error\":\"Treatment not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        boolean success =
                treatmentDAO.deleteTreatment(
                        treatmentId);

        if (success) {

            return Response.ok()
                    .entity(
                            "{\"message\":\"Treatment deleted successfully\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        return Response.status(
                Response.Status.INTERNAL_SERVER_ERROR)
                .entity(
                        "{\"error\":\"Failed to delete treatment\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    // BAD REQUEST RESPONSE
    private Response badRequest(String message) {

        return Response.status(
                Response.Status.BAD_REQUEST)
                .entity(
                        "{\"error\":\"" + message + "\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}