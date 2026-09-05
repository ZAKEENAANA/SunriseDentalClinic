package com.sunrise.dental.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrise.dental.dao.BillDAO;

@WebServlet("/deleteBill")
public class DeleteBillServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BillDAO billDAO;

    @Override
    public void init() throws ServletException {

        billDAO = new BillDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int billId = Integer.parseInt(
                    request.getParameter("id"));

            boolean success =
                    billDAO.deleteBill(billId);

            if (success) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/bills?deleted=true");

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/bills?error=true");
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/bills?error=true");
        }
    }
}