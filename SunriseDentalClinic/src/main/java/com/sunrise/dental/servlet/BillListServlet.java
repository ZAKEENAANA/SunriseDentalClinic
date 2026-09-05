package com.sunrise.dental.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrise.dental.dao.BillDAO;
import com.sunrise.dental.model.Bill;

@WebServlet("/bills")
public class BillListServlet extends HttpServlet {

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

        List<Bill> bills =
                billDAO.getAllBills();

        request.setAttribute(
                "bills",
                bills);

        request.getRequestDispatcher(
                "bills.jsp")
                .forward(request, response);
    }
}