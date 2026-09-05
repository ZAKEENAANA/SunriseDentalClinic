package com.sunrise.dental.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunrise.dental.dao.UserDAO;
import com.sunrise.dental.model.User;

@WebServlet("/users")
public class UserListServlet extends HttpServlet {

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

        HttpSession session =
                request.getSession(false);

        // Check login
        if (session == null
                || session.getAttribute("user") == null) {

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

        try {

            List<User> users =
                    userDAO.getAllUsers();

            request.setAttribute(
                    "users",
                    users
            );

            request.getRequestDispatcher(
                    "/users.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/dashboard?error=true"
            );
        }
    }
}