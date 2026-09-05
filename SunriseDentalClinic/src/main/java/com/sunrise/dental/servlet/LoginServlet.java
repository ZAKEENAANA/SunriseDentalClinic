package com.sunrise.dental.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunrise.dental.dao.UserDAO;
import com.sunrise.dental.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {

        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username =
                request.getParameter("username");

        String password =
                request.getParameter("password");


        // Check empty fields
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/login.jsp?error=true"
            );

            return;
        }


        // Verify login
        User user = userDAO.login(
                username.trim(),
                password
        );


        // Login successful
        if (user != null) {

            HttpSession session =
                    request.getSession();

            // Store complete User object
            session.setAttribute(
                    "user",
                    user
            );

            // Store username
            session.setAttribute(
                    "username",
                    user.getUsername()
            );

            // Store role
            session.setAttribute(
                    "role",
                    user.getRole()
            );


            // Go through DashboardServlet
            response.sendRedirect(
                    request.getContextPath()
                    + "/dashboard"
            );

        } else {

            // Login failed
            response.sendRedirect(
                    request.getContextPath()
                    + "/login.jsp?error=true"
            );
        }
    }
}