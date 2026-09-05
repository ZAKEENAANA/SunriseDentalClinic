package com.sunrise.dental.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sunrise.dental.dao.UserDAO;
import com.sunrise.dental.model.User;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserWebService {

    private UserDAO userDAO = new UserDAO();

    // =========================================================
    // GET ALL USERS
    // GET /api/users
    // =========================================================
    @GET
    public Response getAllUsers() {

        List<User> users = userDAO.getAllUsers();

        List<Map<String, Object>> responseList = new ArrayList<>();

        for (User user : users) {
            responseList.add(toSafeUser(user));
        }

        return Response.ok(responseList).build();
    }

    // =========================================================
    // GET USER BY ID
    // GET /api/users/{id}
    // =========================================================
    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") int userId) {

        if (userId <= 0) {
            return badRequest("User ID must be greater than 0");
        }

        User user = userDAO.getUserById(userId);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"User not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        return Response.ok(toSafeUser(user)).build();
    }

    // =========================================================
    // CREATE USER
    // POST /api/users
    // =========================================================
    @POST
    public Response createUser(User user) {

        // Check user object
        if (user == null) {
            return badRequest("User data is required");
        }

        // Username validation
        if (user.getUsername() == null
                || user.getUsername().trim().isEmpty()) {

            return badRequest("Username is required");
        }

        String username = user.getUsername().trim();

        if (username.length() < 3) {
            return badRequest(
                    "Username must contain at least 3 characters");
        }

        // Password validation
        if (user.getPassword() == null
                || user.getPassword().trim().isEmpty()) {

            return badRequest("Password is required");
        }

        if (user.getPassword().length() < 6) {
            return badRequest(
                    "Password must contain at least 6 characters");
        }

        // Role validation
        if (user.getRole() == null
                || user.getRole().trim().isEmpty()) {

            return badRequest("Role is required");
        }

        String role = user.getRole().trim().toUpperCase();

        if (!role.equals("ADMIN")
                && !role.equals("STAFF")) {

            return badRequest(
                    "Invalid role. Use ADMIN or STAFF");
        }

        // Check duplicate username
        User existingUser =
                userDAO.getUserByUsername(username);

        if (existingUser != null) {

            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"error\":\"Username already exists\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        // Set cleaned values
        user.setUsername(username);
        user.setRole(role);

        // Add user
        boolean success = userDAO.addUser(user);

        if (success) {

            return Response.status(Response.Status.CREATED)
                    .entity("{\"message\":\"User created successfully\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        return Response.status(
                Response.Status.INTERNAL_SERVER_ERROR)
                .entity("{\"error\":\"Failed to create user\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    // =========================================================
    // UPDATE USER
    // PUT /api/users/{id}
    // =========================================================
    @PUT
    @Path("/{id}")
    public Response updateUser(
            @PathParam("id") int userId,
            User user) {

        // Validate ID
        if (userId <= 0) {
            return badRequest("User ID must be greater than 0");
        }

        // Validate object
        if (user == null) {
            return badRequest("User data is required");
        }

        // Check existing user
        User existingUser =
                userDAO.getUserById(userId);

        if (existingUser == null) {

            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"User not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        // Username validation
        if (user.getUsername() == null
                || user.getUsername().trim().isEmpty()) {

            return badRequest("Username is required");
        }

        String username = user.getUsername().trim();

        if (username.length() < 3) {

            return badRequest(
                    "Username must contain at least 3 characters");
        }

        // Password validation
        if (user.getPassword() == null
                || user.getPassword().trim().isEmpty()) {

            return badRequest("Password is required");
        }

        if (user.getPassword().length() < 6) {

            return badRequest(
                    "Password must contain at least 6 characters");
        }

        // Role validation
        if (user.getRole() == null
                || user.getRole().trim().isEmpty()) {

            return badRequest("Role is required");
        }

        String role = user.getRole().trim().toUpperCase();

        if (!role.equals("ADMIN")
                && !role.equals("STAFF")) {

            return badRequest(
                    "Invalid role. Use ADMIN or STAFF");
        }

        // Check duplicate username
        User usernameOwner =
                userDAO.getUserByUsername(username);

        if (usernameOwner != null
                && usernameOwner.getId() != userId) {

            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"error\":\"Username already exists\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        // Set ID and cleaned values
        user.setId(userId);
        user.setUsername(username);
        user.setRole(role);

        // Update user
        boolean success = userDAO.updateUser(user);

        if (success) {

            return Response.ok()
                    .entity("{\"message\":\"User updated successfully\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        return Response.status(
                Response.Status.INTERNAL_SERVER_ERROR)
                .entity("{\"error\":\"Failed to update user\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    // =========================================================
    // DELETE USER
    // DELETE /api/users/{id}
    // =========================================================
    @DELETE
    @Path("/{id}")
    public Response deleteUser(
            @PathParam("id") int userId) {

        // Validate ID
        if (userId <= 0) {
            return badRequest("User ID must be greater than 0");
        }

        // Check existing user
        User existingUser =
                userDAO.getUserById(userId);

        if (existingUser == null) {

            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"User not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        // Delete user
        boolean success =
                userDAO.deleteUser(userId);

        if (success) {

            return Response.ok()
                    .entity("{\"message\":\"User deleted successfully\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        return Response.status(
                Response.Status.INTERNAL_SERVER_ERROR)
                .entity("{\"error\":\"Failed to delete user\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    // =========================================================
    // LOGIN
    // POST /api/users/login
    // =========================================================
    @POST
    @Path("/login")
    public Response login(User user) {

        // Check user object
        if (user == null) {
            return badRequest("Login data is required");
        }

        // Username validation
        if (user.getUsername() == null
                || user.getUsername().trim().isEmpty()) {

            return badRequest("Username is required");
        }

        // Password validation
        if (user.getPassword() == null
                || user.getPassword().trim().isEmpty()) {

            return badRequest("Password is required");
        }

        String username = user.getUsername().trim();

        // Authenticate user
        User loggedUser =
                userDAO.login(username, user.getPassword());

        if (loggedUser == null) {

            return Response.status(
                    Response.Status.UNAUTHORIZED)
                    .entity("{\"error\":\"Invalid username or password\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        // Successful login
        Map<String, Object> response =
                new HashMap<>();

        response.put("message", "Login successful");
        response.put("id", loggedUser.getId());
        response.put("username", loggedUser.getUsername());
        response.put("role", loggedUser.getRole());

        return Response.ok(response).build();
    }

    // =========================================================
    // SAFE USER RESPONSE
    // Password is intentionally NOT returned
    // =========================================================
    private Map<String, Object> toSafeUser(User user) {

        Map<String, Object> safeUser =
                new HashMap<>();

        safeUser.put("id", user.getId());
        safeUser.put("username", user.getUsername());
        safeUser.put("role", user.getRole());

        return safeUser;
    }

    // =========================================================
    // BAD REQUEST HELPER
    // =========================================================
    private Response badRequest(String message) {

        return Response.status(
                Response.Status.BAD_REQUEST)
                .entity("{\"error\":\"" + message + "\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}