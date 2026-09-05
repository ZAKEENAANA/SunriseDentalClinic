package com.sunrise.dental.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrise.dental.dao.TreatmentDAO;
import com.sunrise.dental.model.Treatment;

@WebServlet("/addTreatment")
public class TreatmentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private TreatmentDAO treatmentDAO;


    @Override
    public void init() throws ServletException {

        treatmentDAO = new TreatmentDAO();
    }


    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");


        String patientIdValue =
                request.getParameter("patientId");

        String treatmentName =
                request.getParameter("treatmentName");

        String description =
                request.getParameter("description");

        String treatmentDate =
                request.getParameter("treatmentDate");

        String costValue =
                request.getParameter("cost");


        try {

            // Convert Patient ID
            int patientId =
                    Integer.parseInt(patientIdValue);


            // Convert treatment cost
            BigDecimal cost = BigDecimal.ZERO;

            if (costValue != null
                    && !costValue.trim().isEmpty()) {

                cost = new BigDecimal(costValue);
            }


            // Create Treatment object
            Treatment treatment =
                    new Treatment();


            treatment.setPatientId(patientId);

            treatment.setTreatmentName(
                    treatmentName);

            treatment.setDescription(
                    description);

            treatment.setTreatmentDate(
                    treatmentDate);


            // BigDecimal → double
            treatment.setCost(
                    cost.doubleValue()
            );


            // Save treatment
            boolean success =
                    treatmentDAO.addTreatment(
                            treatment);


            if (success) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/treatments?success=true"
                );

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/treatment.jsp?error=true"
                );
            }


        } catch (NumberFormatException e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/treatment.jsp?error=true"
            );


        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/treatment.jsp?error=true"
            );
        }
    }


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(
                request.getContextPath()
                + "/treatment.jsp"
        );
    }

}