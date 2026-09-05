package com.sunrise.dental.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrise.dental.dao.PatientDAO;
import com.sunrise.dental.dao.AppointmentDAO;
import com.sunrise.dental.dao.TreatmentDAO;
import com.sunrise.dental.dao.BillDAO;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private PatientDAO patientDAO;
    private AppointmentDAO appointmentDAO;
    private TreatmentDAO treatmentDAO;
    private BillDAO billDAO;

    @Override
    public void init() throws ServletException {

        patientDAO = new PatientDAO();
        appointmentDAO = new AppointmentDAO();
        treatmentDAO = new TreatmentDAO();
        billDAO = new BillDAO();

        System.out.println("====================================");
        System.out.println("DashboardServlet initialized");
        System.out.println("====================================");
    }

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {

            // =========================================
            // Get Dashboard Statistics
            // =========================================

            int patientCount =
                    patientDAO.getPatientCount();

            System.out.println(
                    "DEBUG Patient Count = " + patientCount
            );


            int appointmentCount =
                    appointmentDAO.getAppointmentCount();

            System.out.println(
                    "DEBUG Appointment Count = " + appointmentCount
            );


            int treatmentCount =
                    treatmentDAO.getTreatmentCount();

            System.out.println(
                    "DEBUG Treatment Count = " + treatmentCount
            );


            int pendingBillCount =
                    billDAO.getPendingBillCount();

            System.out.println(
                    "DEBUG Pending Bill Count = " + pendingBillCount
            );


            // =========================================
            // Send Statistics to dashboard.jsp
            // =========================================

            request.setAttribute(
                    "patientCount",
                    patientCount
            );

            request.setAttribute(
                    "appointmentCount",
                    appointmentCount
            );

            request.setAttribute(
                    "treatmentCount",
                    treatmentCount
            );

            request.setAttribute(
                    "pendingBillCount",
                    pendingBillCount
            );


            // =========================================
            // Open Dashboard
            // =========================================

            request.getRequestDispatcher(
                    "/dashboard.jsp"
            ).forward(request, response);


        } catch (Exception e) {

            System.out.println(
                    "ERROR: DashboardServlet failed!"
            );

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/login.jsp"
            );
        }
    }
}