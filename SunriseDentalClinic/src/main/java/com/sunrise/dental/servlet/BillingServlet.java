package com.sunrise.dental.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrise.dental.dao.BillDAO;
import com.sunrise.dental.dao.PatientDAO;
import com.sunrise.dental.dao.TreatmentDAO;
import com.sunrise.dental.model.Bill;
import com.sunrise.dental.model.Patient;
import com.sunrise.dental.model.Treatment;

@WebServlet("/addBill")
public class BillingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BillDAO billDAO;
    private PatientDAO patientDAO;
    private TreatmentDAO treatmentDAO;

    private static final double CONSULTATION_FEE = 1500.00;

    @Override
    public void init() throws ServletException {
        billDAO = new BillDAO();
        patientDAO = new PatientDAO();
        treatmentDAO = new TreatmentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        List<Patient> patients =
                patientDAO.getAllPatients();

        List<Treatment> treatments =
                treatmentDAO.getAllTreatments();

        request.setAttribute("patients", patients);
        request.setAttribute("treatments", treatments);
        request.setAttribute(
                "consultationFee",
                CONSULTATION_FEE
        );

        request.getRequestDispatcher(
                "/billing.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String patientIdText =
                request.getParameter("patientId");

        String treatmentIdText =
                request.getParameter("treatmentId");

        String paymentStatus =
                request.getParameter("paymentStatus");

        if (patientIdText == null
                || patientIdText.trim().isEmpty()
                || treatmentIdText == null
                || treatmentIdText.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/addBill?error=empty"
            );

            return;
        }

        try {

            int patientId =
                    Integer.parseInt(patientIdText);

            int treatmentId =
                    Integer.parseInt(treatmentIdText);

            Treatment treatment =
                    treatmentDAO.getTreatmentById(treatmentId);

            if (treatment == null) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/addBill?error=treatment"
                );

                return;
            }

            double treatmentCost =
                    treatment.getCost();

            double totalAmount =
                    treatmentCost + CONSULTATION_FEE;

            Bill bill = new Bill();

            bill.setPatientId(patientId);
            bill.setTreatmentId(treatmentId);
            bill.setAmount(totalAmount);

            if (paymentStatus == null
                    || paymentStatus.trim().isEmpty()) {

                bill.setPaymentStatus("Pending");

            } else {

                bill.setPaymentStatus(
                        paymentStatus.trim()
                );
            }

            boolean success =
                    billDAO.addBill(bill);

            if (success) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/bills?success=true"
                );

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/addBill?error=true"
                );
            }

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/addBill?error=invalid"
            );

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/addBill?error=true"
            );
        }
    }
}