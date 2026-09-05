package com.sunrise.dental.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrise.dental.dao.PatientDAO;

@WebServlet("/deletePatient")
public class DeletePatientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private PatientDAO patientDAO;

    @Override
    public void init() throws ServletException {

        patientDAO = new PatientDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int patientId = Integer.parseInt(
                    request.getParameter("id"));

            boolean success =
                    patientDAO.deletePatient(patientId);

            if (success) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/patients?success=deleted");

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/patients?error=true");
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/patients?error=true");
        }
    }
}