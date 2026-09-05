package com.sunrise.dental.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrise.dental.dao.DentistDAO;
import com.sunrise.dental.model.Dentist;

@WebServlet("/addDentist")
public class AddDentistServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private DentistDAO dentistDAO;

    @Override
    public void init() throws ServletException {
        dentistDAO = new DentistDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String specialization = request.getParameter("specialization");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        Dentist dentist = new Dentist();

        dentist.setFirstName(firstName);
        dentist.setLastName(lastName);
        dentist.setSpecialization(specialization);
        dentist.setPhone(phone);
        dentist.setEmail(email);

        boolean success = dentistDAO.addDentist(dentist);

        if (success) {
            response.sendRedirect("dentists?success=true");
        } else {
            response.sendRedirect("add-dentist.jsp?error=true");
        }
    }
}