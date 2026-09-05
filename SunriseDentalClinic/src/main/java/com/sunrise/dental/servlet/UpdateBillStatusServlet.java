package com.sunrise.dental.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrise.dental.dao.BillDAO;

@WebServlet("/updateBillStatus")
public class UpdateBillStatusServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BillDAO billDAO;

    @Override
    public void init() throws ServletException {
        billDAO = new BillDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String billIdValue =
                request.getParameter("billId");

        String paymentStatus =
                request.getParameter("paymentStatus");

        try {

            int billId =
                    Integer.parseInt(billIdValue);

            // Allow only valid statuses
            if (!"Pending".equals(paymentStatus)
                    && !"Paid".equals(paymentStatus)
                    && !"Cancelled".equals(paymentStatus)) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/bills?error=true"
                );

                return;
            }

            boolean success =
                    billDAO.updatePaymentStatus(
                            billId,
                            paymentStatus
                    );

            if (success) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/bills?success=true"
                );

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/bills?error=true"
                );
            }

        } catch (NumberFormatException e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/bills?error=true"
            );

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/bills?error=true"
            );
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(
                request.getContextPath()
                + "/bills"
        );
    }
}