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

@WebServlet("/editBill")
public class EditBillServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BillDAO billDAO;
    private PatientDAO patientDAO;
    private TreatmentDAO treatmentDAO;

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

        try {

            int billId = Integer.parseInt(
                    request.getParameter("id"));

            Bill bill = billDAO.getBillById(billId);

            if (bill == null) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/bills?error=true");

                return;
            }

            List<Patient> patients =
                    patientDAO.getAllPatients();

            List<Treatment> treatments =
                    treatmentDAO.getAllTreatments();

            request.setAttribute("bill", bill);
            request.setAttribute("patients", patients);
            request.setAttribute("treatments", treatments);

            request.getRequestDispatcher(
                    "/edit-bill.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/bills?error=true");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {

            int billId = Integer.parseInt(
                    request.getParameter("billId"));

            int patientId = Integer.parseInt(
                    request.getParameter("patientId"));

            int treatmentId = Integer.parseInt(
                    request.getParameter("treatmentId"));

            double amount = Double.parseDouble(
                    request.getParameter("amount"));

            String paymentStatus =
                    request.getParameter("paymentStatus");

            Bill bill = new Bill();

            bill.setBillId(billId);
            bill.setPatientId(patientId);
            bill.setTreatmentId(treatmentId);
            bill.setAmount(amount);
            bill.setPaymentStatus(paymentStatus);

            boolean success =
                    billDAO.updateBill(bill);

            if (success) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/bills?updated=true");

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/bills?error=true");
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/bills?error=true");
        }
    }
}