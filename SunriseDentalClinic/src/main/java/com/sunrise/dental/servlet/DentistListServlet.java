package com.sunrise.dental.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrise.dental.dao.DentistDAO;
import com.sunrise.dental.model.Dentist;

@WebServlet("/dentists")
public class DentistListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private DentistDAO dentistDAO;

    @Override
    public void init() throws ServletException {
        dentistDAO = new DentistDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Dentist> dentists = dentistDAO.getAllDentists();

        request.setAttribute("dentists", dentists);

        request.getRequestDispatcher("dentists.jsp")
               .forward(request, response);
    }
}