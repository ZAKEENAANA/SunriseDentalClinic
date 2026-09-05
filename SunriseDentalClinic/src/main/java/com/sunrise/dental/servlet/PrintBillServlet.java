package com.sunrise.dental.servlet;

import java.io.IOException;

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

@WebServlet("/printBill")
public class PrintBillServlet extends HttpServlet {

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

        String billIdText =
                request.getParameter("billId");

        if (billIdText == null
                || billIdText.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/bills"
            );

            return;
        }

        try {

            int billId =
                    Integer.parseInt(billIdText);

            Bill bill =
                    billDAO.getBillById(billId);

            if (bill != null) {

                Patient patient =
                        patientDAO.getPatientById(
                                bill.getPatientId()
                        );

                Treatment treatment = null;

                if (bill.getTreatmentId() > 0) {

                    treatment =
                            treatmentDAO.getTreatmentById(
                                    bill.getTreatmentId()
                            );
                }

                request.setAttribute(
                        "bill",
                        bill
                );

                request.setAttribute(
                        "patient",
                        patient
                );

                request.setAttribute(
                        "treatment",
                        treatment
                );

                request.getRequestDispatcher(
                        "/print-bill.jsp"
                ).forward(request, response);

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/bills?error=notfound"
                );
            }

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/bills?error=invalid"
            );

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/bills?error=true"
            );
        }
    }
}