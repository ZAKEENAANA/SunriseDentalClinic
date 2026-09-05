package com.sunrise.dental.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrise.dental.dao.TreatmentDAO;
import com.sunrise.dental.model.Treatment;

@WebServlet("/editTreatment")
public class EditTreatmentServlet extends HttpServlet {

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

        String idValue = request.getParameter("id");

        try {

            int treatmentId = Integer.parseInt(idValue);

            Treatment treatment =
                    treatmentDAO.getTreatmentById(treatmentId);

            if (treatment != null) {

                request.setAttribute(
                        "treatment",
                        treatment);

                request.getRequestDispatcher(
                        "edit-treatment.jsp")
                        .forward(request, response);

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/treatments?error=true");
            }

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/treatments?error=true");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {

            int treatmentId =
                    Integer.parseInt(
                            request.getParameter("treatmentId"));

            int patientId =
                    Integer.parseInt(
                            request.getParameter("patientId"));

            String treatmentName =
                    request.getParameter("treatmentName");

            String description =
                    request.getParameter("description");

            String treatmentDate =
                    request.getParameter("treatmentDate");

            double cost =
                    Double.parseDouble(
                            request.getParameter("cost"));

            Treatment treatment = new Treatment();

            treatment.setTreatmentId(treatmentId);
            treatment.setPatientId(patientId);
            treatment.setTreatmentName(treatmentName);
            treatment.setDescription(description);
            treatment.setTreatmentDate(treatmentDate);
            treatment.setCost(cost);

            boolean success =
                    treatmentDAO.updateTreatment(treatment);

            if (success) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/treatments?updated=true");

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/treatments?error=true");
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/treatments?error=true");
        }
    }
}