package com.sunrise.dental.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunrise.dental.dao.UserDAO;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Check login
        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/login.jsp"
            );

            return;
        }

        // Check ADMIN role
        String role =
                (String) session.getAttribute("role");

        if (!"ADMIN".equalsIgnoreCase(role)) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/dashboard?accessDenied=true"
            );

            return;
        }

        String idParameter =
                request.getParameter("id");

        if (idParameter == null ||
            idParameter.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/users?error=true"
            );

            return;
        }

        try {

            int id = Integer.parseInt(idParameter);

            boolean success =
                    userDAO.deleteUser(id);

            if (success) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/users?deleted=true"
                );

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/users?error=true"
                );
            }

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/users?error=true"
            );
        }
    }
}