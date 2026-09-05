package com.sunrise.dental.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrise.dental.dao.TreatmentDAO;
import com.sunrise.dental.model.Treatment;

@WebServlet("/treatments")
public class TreatmentListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private TreatmentDAO treatmentDAO;


    @Override
    public void init() throws ServletException {

        treatmentDAO = new TreatmentDAO();
    }


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Treatment> treatments =
                treatmentDAO.getAllTreatments();


        request.setAttribute(
                "treatments",
                treatments);


        request.getRequestDispatcher(
                "treatment-list.jsp")
                .forward(request, response);
    }

}