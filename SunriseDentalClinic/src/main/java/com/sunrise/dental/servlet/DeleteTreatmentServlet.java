package com.sunrise.dental.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrise.dental.dao.TreatmentDAO;

@WebServlet("/deleteTreatment")
public class DeleteTreatmentServlet extends HttpServlet {

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

            int treatmentId =
                    Integer.parseInt(idValue);

            boolean success =
                    treatmentDAO.deleteTreatment(treatmentId);

            if (success) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/treatments?deleted=true");

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/treatments?error=true");
            }

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/treatments?error=true");

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/treatments?error=true");
        }
    }
}