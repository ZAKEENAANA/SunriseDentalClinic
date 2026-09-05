package com.sunrise.dental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunrise.dental.model.User;
import com.sunrise.dental.util.DatabaseConnection;

public class UserDAO {

    // =========================================================
    // ADD USER
    // =========================================================
    public boolean addUser(User user) {

        String sql = "INSERT INTO users "
                   + "(username, password, role) "
                   + "VALUES (?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());

            int rowsInserted =
                    statement.executeUpdate();

            return rowsInserted > 0;

        } catch (java.sql.SQLIntegrityConstraintViolationException e) {

            System.out.println(
                    "Username already exists.");

            return false;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // GET ALL USERS
    // =========================================================
    public List<User> getAllUsers() {

        List<User> users =
                new ArrayList<>();

        String sql =
                "SELECT * FROM users "
              + "ORDER BY id DESC";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                User user =
                        new User();

                user.setId(
                        resultSet.getInt("id"));

                user.setUsername(
                        resultSet.getString("username"));

                user.setPassword(
                        resultSet.getString("password"));

                user.setRole(
                        resultSet.getString("role"));

                users.add(user);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return users;
    }


    // =========================================================
    // GET USER BY ID
    // =========================================================
    public User getUserById(int id) {

        User user = null;

        String sql =
                "SELECT * FROM users "
              + "WHERE id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    user =
                            new User();

                    user.setId(
                            resultSet.getInt("id"));

                    user.setUsername(
                            resultSet.getString("username"));

                    user.setPassword(
                            resultSet.getString("password"));

                    user.setRole(
                            resultSet.getString("role"));
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return user;
    }


    // =========================================================
    // LOGIN
    // =========================================================
    public User login(
            String username,
            String password) {

        User user = null;

        String sql =
                "SELECT * FROM users "
              + "WHERE username = ? "
              + "AND password = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    user =
                            new User();

                    user.setId(
                            resultSet.getInt("id"));

                    user.setUsername(
                            resultSet.getString("username"));

                    user.setPassword(
                            resultSet.getString("password"));

                    user.setRole(
                            resultSet.getString("role"));
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return user;
    }


    // =========================================================
    // GET USER BY USERNAME
    // =========================================================
    public User getUserByUsername(
            String username) {

        User user = null;

        String sql =
                "SELECT * FROM users "
              + "WHERE username = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    user =
                            new User();

                    user.setId(
                            resultSet.getInt("id"));

                    user.setUsername(
                            resultSet.getString("username"));

                    user.setPassword(
                            resultSet.getString("password"));

                    user.setRole(
                            resultSet.getString("role"));
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return user;
    }


    // =========================================================
    // UPDATE USER
    // =========================================================
    public boolean updateUser(User user) {

        String sql =
                "UPDATE users SET "
              + "username = ?, "
              + "password = ?, "
              + "role = ? "
              + "WHERE id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    user.getUsername());

            statement.setString(
                    2,
                    user.getPassword());

            statement.setString(
                    3,
                    user.getRole());

            statement.setInt(
                    4,
                    user.getId());

            int rowsUpdated =
                    statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (java.sql.SQLIntegrityConstraintViolationException e) {

            System.out.println(
                    "Username already exists.");

            return false;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // DELETE USER
    // =========================================================
    public boolean deleteUser(int id) {

        String sql =
                "DELETE FROM users "
              + "WHERE id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsDeleted =
                    statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // GET USER COUNT
    // =========================================================
    public int getUserCount() {

        String sql =
                "SELECT COUNT(*) FROM users";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            if (resultSet.next()) {

                return resultSet.getInt(1);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }
}