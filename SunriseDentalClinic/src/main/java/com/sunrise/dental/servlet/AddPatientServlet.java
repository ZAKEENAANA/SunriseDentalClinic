package com.sunrise.dental.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrise.dental.dao.PatientDAO;
import com.sunrise.dental.model.Patient;

@WebServlet("/addPatient")
public class AddPatientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private PatientDAO patientDAO;

    @Override
    public void init() throws ServletException {

        patientDAO = new PatientDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {

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

            patient.setFirstName(firstName);
            patient.setLastName(lastName);
            patient.setDateOfBirth(dateOfBirth);
            patient.setGender(gender);
            patient.setPhone(phone);
            patient.setEmail(email);
            patient.setAddress(address);


            boolean success =
                    patientDAO.addPatient(patient);


            if (success) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/patients?success=true");

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/add-patient.jsp?error=true");
            }


        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/add-patient.jsp?error=true");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(
                request.getContextPath()
                + "/add-patient.jsp");
    }
}