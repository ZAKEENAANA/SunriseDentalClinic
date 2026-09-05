package com.sunrise.dental.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrise.dental.dao.PatientDAO;
import com.sunrise.dental.model.Patient;

@WebServlet("/editPatient")
public class EditPatientServlet extends HttpServlet {

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

            Patient patient =
                    patientDAO.getPatientById(patientId);

            if (patient != null) {

                request.setAttribute("patient", patient);

                request.getRequestDispatcher(
                        "edit-patient.jsp")
                        .forward(request, response);

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/patients?error=notfound");
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/patients?error=true");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {

            int patientId = Integer.parseInt(
                    request.getParameter("patientId"));

            String firstName =
                    request.getParameter("firstName");

            String lastName =
                    request.getParameter("lastName");

            String dateOfBirth =
                    request.getParameter("dateOfBirth");

            String gender =
                    request.getParameter("gender");

            String phone =
                    request.getParameter("phone");

            String email =
                    request.getParameter("email");

            String address =
                    request.getParameter("address");


            Patient patient = new Patient();

            patient.setPatientId(patientId);
            patient.setFirstName(firstName);
            patient.setLastName(lastName);
            patient.setDateOfBirth(dateOfBirth);
            patient.setGender(gender);
            patient.setPhone(phone);
            patient.setEmail(email);
            patient.setAddress(address);


            boolean success =
                    patientDAO.updatePatient(patient);


            if (success) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/patients?success=updated");

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/editPatient?id="
                        + patientId
                        + "&error=true");
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/patients?error=true");
        }
    }
}