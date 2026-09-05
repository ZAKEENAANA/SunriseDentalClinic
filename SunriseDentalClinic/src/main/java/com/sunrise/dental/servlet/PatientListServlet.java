package com.sunrise.dental.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrise.dental.dao.PatientDAO;
import com.sunrise.dental.model.Patient;

@WebServlet("/patients")
public class PatientListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private PatientDAO patientDAO;

    @Override
    public void init() {
        patientDAO = new PatientDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        List<Patient> patients = patientDAO.getAllPatients();

        request.setAttribute("patients", patients);

        request.getRequestDispatcher("patient-list.jsp")
               .forward(request, response);
    }
}