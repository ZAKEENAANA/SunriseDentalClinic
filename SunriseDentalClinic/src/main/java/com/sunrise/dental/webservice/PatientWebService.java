package com.sunrise.dental.webservice;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

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

import com.sunrise.dental.dao.PatientDAO;
import com.sunrise.dental.model.Patient;

@Path("/patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientWebService {

    private PatientDAO patientDAO = new PatientDAO();


    // =====================================================
    // GET ALL PATIENTS
    // =====================================================

    @GET
    public Response getAllPatients() {

        List<Patient> patients =
                patientDAO.getAllPatients();

        return Response.ok(patients).build();
    }


    // =====================================================
    // GET PATIENT BY ID
    // =====================================================

    @GET
    @Path("/{id}")
    public Response getPatientById(
            @PathParam("id") int patientId) {

        if (patientId <= 0) {

            return badRequest(
                    "Patient ID must be greater than 0");
        }

        Patient patient =
                patientDAO.getPatientById(patientId);

        if (patient == null) {

            return Response.status(
                    Response.Status.NOT_FOUND)
                    .entity(
                            "{\"error\":\"Patient not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        return Response.ok(patient).build();
    }


    // =====================================================
    // CREATE / REGISTER PATIENT
    // =====================================================

    @POST
    public Response createPatient(
            Patient patient) {

        // Check request body
        if (patient == null) {

            return badRequest(
                    "Patient data is required");
        }


        // First name
        if (patient.getFirstName() == null
                || patient.getFirstName().trim().isEmpty()) {

            return badRequest(
                    "First name is required");
        }

        if (!isValidName(patient.getFirstName())) {

            return badRequest(
                    "First name must contain letters only");
        }


        // Last name
        if (patient.getLastName() == null
                || patient.getLastName().trim().isEmpty()) {

            return badRequest(
                    "Last name is required");
        }

        if (!isValidName(patient.getLastName())) {

            return badRequest(
                    "Last name must contain letters only");
        }


        // Date of birth
        if (patient.getDateOfBirth() == null
                || patient.getDateOfBirth().trim().isEmpty()) {

            return badRequest(
                    "Date of birth is required");
        }

        try {

            LocalDate.parse(
                    patient.getDateOfBirth());

        } catch (Exception e) {

            return badRequest(
                    "Invalid date of birth. Use YYYY-MM-DD");
        }


        // Gender
        if (patient.getGender() == null
                || patient.getGender().trim().isEmpty()) {

            return badRequest(
                    "Gender is required");
        }

        if (!isValidGender(patient.getGender())) {

            return badRequest(
                    "Invalid gender. Use Male, Female or Other");
        }


        // Phone
        if (patient.getPhone() == null
                || patient.getPhone().trim().isEmpty()) {

            return badRequest(
                    "Phone number is required");
        }

        if (!isValidPhone(patient.getPhone())) {

            return badRequest(
                    "Invalid phone number");
        }


        // Email
        if (patient.getEmail() == null
                || patient.getEmail().trim().isEmpty()) {

            return badRequest(
                    "Email is required");
        }

        if (!isValidEmail(patient.getEmail())) {

            return badRequest(
                    "Invalid email address");
        }


        // Address
        if (patient.getAddress() == null
                || patient.getAddress().trim().isEmpty()) {

            return badRequest(
                    "Address is required");
        }


        // Add patient to database
        boolean success =
                patientDAO.addPatient(patient);

        if (success) {

            return Response.status(
                    Response.Status.CREATED)
                    .entity(
                            "{\"message\":\"Patient registered successfully\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }


        return Response.status(
                Response.Status.INTERNAL_SERVER_ERROR)
                .entity(
                        "{\"error\":\"Failed to register patient\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }


    // =====================================================
    // UPDATE PATIENT
    // =====================================================

    @PUT
    @Path("/{id}")
    public Response updatePatient(
            @PathParam("id") int patientId,
            Patient patient) {

        if (patientId <= 0) {

            return badRequest(
                    "Patient ID must be greater than 0");
        }


        if (patient == null) {

            return badRequest(
                    "Patient data is required");
        }


        // Check whether patient exists
        Patient existingPatient =
                patientDAO.getPatientById(patientId);

        if (existingPatient == null) {

            return Response.status(
                    Response.Status.NOT_FOUND)
                    .entity(
                            "{\"error\":\"Patient not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }


        // Set URL ID
        patient.setPatientId(patientId);


        // Validate first name
        if (patient.getFirstName() == null
                || patient.getFirstName().trim().isEmpty()) {

            return badRequest(
                    "First name is required");
        }

        if (!isValidName(patient.getFirstName())) {

            return badRequest(
                    "First name must contain letters only");
        }


        // Validate last name
        if (patient.getLastName() == null
                || patient.getLastName().trim().isEmpty()) {

            return badRequest(
                    "Last name is required");
        }

        if (!isValidName(patient.getLastName())) {

            return badRequest(
                    "Last name must contain letters only");
        }


        // Validate DOB
        if (patient.getDateOfBirth() == null
                || patient.getDateOfBirth().trim().isEmpty()) {

            return badRequest(
                    "Date of birth is required");
        }

        try {

            LocalDate.parse(
                    patient.getDateOfBirth());

        } catch (Exception e) {

            return badRequest(
                    "Invalid date of birth. Use YYYY-MM-DD");
        }


        // Validate gender
        if (patient.getGender() == null
                || patient.getGender().trim().isEmpty()) {

            return badRequest(
                    "Gender is required");
        }

        if (!isValidGender(patient.getGender())) {

            return badRequest(
                    "Invalid gender. Use Male, Female or Other");
        }


        // Validate phone
        if (patient.getPhone() == null
                || patient.getPhone().trim().isEmpty()) {

            return badRequest(
                    "Phone number is required");
        }

        if (!isValidPhone(patient.getPhone())) {

            return badRequest(
                    "Invalid phone number");
        }


        // Validate email
        if (patient.getEmail() == null
                || patient.getEmail().trim().isEmpty()) {

            return badRequest(
                    "Email is required");
        }

        if (!isValidEmail(patient.getEmail())) {

            return badRequest(
                    "Invalid email address");
        }


        // Validate address
        if (patient.getAddress() == null
                || patient.getAddress().trim().isEmpty()) {

            return badRequest(
                    "Address is required");
        }


        // Update database
        boolean success =
                patientDAO.updatePatient(patient);

        if (success) {

            return Response.ok()
                    .entity(
                            "{\"message\":\"Patient updated successfully\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }


        return Response.status(
                Response.Status.INTERNAL_SERVER_ERROR)
                .entity(
                        "{\"error\":\"Failed to update patient\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }


    // =====================================================
    // DELETE PATIENT
    // =====================================================

    @DELETE
    @Path("/{id}")
    public Response deletePatient(
            @PathParam("id") int patientId) {

        if (patientId <= 0) {

            return badRequest(
                    "Patient ID must be greater than 0");
        }


        Patient patient =
                patientDAO.getPatientById(patientId);

        if (patient == null) {

            return Response.status(
                    Response.Status.NOT_FOUND)
                    .entity(
                            "{\"error\":\"Patient not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }


        boolean success =
                patientDAO.deletePatient(patientId);

        if (success) {

            return Response.ok()
                    .entity(
                            "{\"message\":\"Patient deleted successfully\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }


        return Response.status(
                Response.Status.INTERNAL_SERVER_ERROR)
                .entity(
                        "{\"error\":\"Failed to delete patient\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }


    // =====================================================
    // VALIDATION METHODS
    // =====================================================

    private boolean isValidName(String name) {

        return Pattern
                .matches("[a-zA-Z ]+", name.trim());
    }


    private boolean isValidGender(String gender) {

        return Arrays.asList(
                "Male",
                "Female",
                "Other"
        ).contains(gender);
    }


    private boolean isValidPhone(String phone) {

        return Pattern
                .matches("^[0-9]{10}$",
                        phone.trim());
    }


    private boolean isValidEmail(String email) {

        return Pattern
                .matches(
                        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
                        email.trim());
    }


    private Response badRequest(String message) {

        return Response.status(
                Response.Status.BAD_REQUEST)
                .entity(
                        "{\"error\":\"" + message + "\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}