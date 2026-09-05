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

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {

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

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(
                    request.getContextPath() + "/login.jsp"
            );
            return;
        }

        String role = (String) session.getAttribute("role");

        if (!"ADMIN".equalsIgnoreCase(role)) {
            response.sendRedirect(
                    request.getContextPath()
                    + "/dashboard?accessDenied=true"
            );
            return;
        }

        request.getRequestDispatcher("/add-user.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(
                    request.getContextPath() + "/login.jsp"
            );
            return;
        }

        String role = (String) session.getAttribute("role");

        if (!"ADMIN".equalsIgnoreCase(role)) {
            response.sendRedirect(
                    request.getContextPath()
                    + "/dashboard?accessDenied=true"
            );
            return;
        }

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userRole = request.getParameter("role");

        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()
                || userRole == null || userRole.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/addUser?error=empty"
            );

            return;
        }

        username = username.trim();
        userRole = userRole.trim().toUpperCase();

        if (username.length() < 3) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/addUser?error=username"
            );

            return;
        }

        if (password.length() < 4) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/addUser?error=password"
            );

            return;
        }

        if (!userRole.equals("ADMIN")
                && !userRole.equals("STAFF")) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/addUser?error=role"
            );

            return;
        }
        
        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setRole(userRole);

        boolean success = userDAO.addUser(user);

        if (success) {
            response.sendRedirect(
                    request.getContextPath()
                    + "/users?success=true"
            );
        } else {
            response.sendRedirect(
                    request.getContextPath()
                    + "/addUser?error=true"
            );
        }
    }
}