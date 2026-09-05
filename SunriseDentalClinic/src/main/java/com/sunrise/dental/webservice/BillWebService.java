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

import com.sunrise.dental.dao.BillDAO;
import com.sunrise.dental.model.Bill;

@Path("/bills")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BillWebService {

    private BillDAO billDAO = new BillDAO();

    // GET ALL BILLS
    @GET
    public Response getAllBills() {

        return Response.ok(
                billDAO.getAllBills()
        ).build();
    }

    // GET BILL BY ID
    @GET
    @Path("/{id}")
    public Response getBillById(
            @PathParam("id") int billId) {

        if (billId <= 0) {
            return badRequest(
                    "Bill ID must be greater than 0");
        }

        Bill bill = billDAO.getBillById(billId);

        if (bill == null) {
            return Response.status(
                    Response.Status.NOT_FOUND)
                    .entity(
                            "{\"error\":\"Bill not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        return Response.ok(bill).build();
    }

    // CREATE BILL
    @POST
    public Response createBill(Bill bill) {

        if (bill == null) {
            return badRequest(
                    "Bill data is required");
        }

        // Patient ID validation
        if (bill.getPatientId() <= 0) {
            return badRequest(
                    "Valid patient ID is required");
        }

        // Treatment ID validation
        // Treatment ID is optional because DAO supports NULL
        if (bill.getTreatmentId() < 0) {
            return badRequest(
                    "Treatment ID cannot be negative");
        }

        // Amount validation
        if (bill.getAmount() <= 0) {
            return badRequest(
                    "Bill amount must be greater than 0");
        }

        // Payment status validation
        if (bill.getPaymentStatus() == null
                || bill.getPaymentStatus().trim().isEmpty()) {

            return badRequest(
                    "Payment status is required");
        }

        if (!bill.getPaymentStatus().equals("Pending")
                && !bill.getPaymentStatus().equals("Paid")
                && !bill.getPaymentStatus().equals("Cancelled")) {

            return badRequest(
                    "Invalid payment status. Use Pending, Paid or Cancelled");
        }

        // Bill date validation
        if (bill.getBillDate() == null
                || bill.getBillDate().trim().isEmpty()) {

            return badRequest(
                    "Bill date is required");
        }

        try {

            LocalDate.parse(
                    bill.getBillDate());

        } catch (Exception e) {

            return badRequest(
                    "Invalid bill date. Use YYYY-MM-DD");
        }

        boolean success =
                billDAO.addBill(bill);

        if (success) {

            return Response.status(
                    Response.Status.CREATED)
                    .entity(
                            "{\"message\":\"Bill created successfully\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        return Response.status(
                Response.Status.INTERNAL_SERVER_ERROR)
                .entity(
                        "{\"error\":\"Failed to create bill\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    // UPDATE BILL
    @PUT
    @Path("/{id}")
    public Response updateBill(
            @PathParam("id") int billId,
            Bill bill) {

        if (billId <= 0) {
            return badRequest(
                    "Bill ID must be greater than 0");
        }

        if (bill == null) {
            return badRequest(
                    "Bill data is required");
        }

        Bill existingBill =
                billDAO.getBillById(billId);

        if (existingBill == null) {

            return Response.status(
                    Response.Status.NOT_FOUND)
                    .entity(
                            "{\"error\":\"Bill not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        // Patient ID validation
        if (bill.getPatientId() <= 0) {
            return badRequest(
                    "Valid patient ID is required");
        }

        // Treatment ID validation
        if (bill.getTreatmentId() < 0) {
            return badRequest(
                    "Treatment ID cannot be negative");
        }

        // Amount validation
        if (bill.getAmount() <= 0) {
            return badRequest(
                    "Bill amount must be greater than 0");
        }

        // Payment status validation
        if (bill.getPaymentStatus() == null
                || bill.getPaymentStatus().trim().isEmpty()) {

            return badRequest(
                    "Payment status is required");
        }

        if (!bill.getPaymentStatus().equals("Pending")
                && !bill.getPaymentStatus().equals("Paid")
                && !bill.getPaymentStatus().equals("Cancelled")) {

            return badRequest(
                    "Invalid payment status. Use Pending, Paid or Cancelled");
        }

        // Bill date validation
        if (bill.getBillDate() == null
                || bill.getBillDate().trim().isEmpty()) {

            return badRequest(
                    "Bill date is required");
        }

        try {

            LocalDate.parse(
                    bill.getBillDate());

        } catch (Exception e) {

            return badRequest(
                    "Invalid bill date. Use YYYY-MM-DD");
        }

        bill.setBillId(billId);

        boolean success =
                billDAO.updateBill(bill);

        if (success) {

            return Response.ok()
                    .entity(
                            "{\"message\":\"Bill updated successfully\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        return Response.status(
                Response.Status.INTERNAL_SERVER_ERROR)
                .entity(
                        "{\"error\":\"Failed to update bill\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    // DELETE BILL
    @DELETE
    @Path("/{id}")
    public Response deleteBill(
            @PathParam("id") int billId) {

        if (billId <= 0) {
            return badRequest(
                    "Bill ID must be greater than 0");
        }

        Bill bill =
                billDAO.getBillById(billId);

        if (bill == null) {

            return Response.status(
                    Response.Status.NOT_FOUND)
                    .entity(
                            "{\"error\":\"Bill not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        boolean success =
                billDAO.deleteBill(billId);

        if (success) {

            return Response.ok()
                    .entity(
                            "{\"message\":\"Bill deleted successfully\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        return Response.status(
                Response.Status.INTERNAL_SERVER_ERROR)
                .entity(
                        "{\"error\":\"Failed to delete bill\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    // BAD REQUEST HELPER
    private Response badRequest(String message) {

        return Response.status(
                Response.Status.BAD_REQUEST)
                .entity(
                        "{\"error\":\"" + message + "\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}