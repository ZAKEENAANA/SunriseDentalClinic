package com.sunrise.dental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunrise.dental.model.Dentist;
import com.sunrise.dental.util.DatabaseConnection;

public class DentistDAO {

    // Add Dentist
    public boolean addDentist(Dentist dentist) {

        String sql = "INSERT INTO dentists "
                + "(first_name, last_name, specialization, phone, email) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, dentist.getFirstName());
            statement.setString(2, dentist.getLastName());
            statement.setString(3, dentist.getSpecialization());
            statement.setString(4, dentist.getPhone());
            statement.setString(5, dentist.getEmail());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Get All Dentists
    public List<Dentist> getAllDentists() {

        List<Dentist> dentists = new ArrayList<>();

        String sql = "SELECT * FROM dentists ORDER BY dentist_id DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Dentist dentist = new Dentist();

                dentist.setDentistId(resultSet.getInt("dentist_id"));
                dentist.setFirstName(resultSet.getString("first_name"));
                dentist.setLastName(resultSet.getString("last_name"));
                dentist.setSpecialization(resultSet.getString("specialization"));
                dentist.setPhone(resultSet.getString("phone"));
                dentist.setEmail(resultSet.getString("email"));

                dentists.add(dentist);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dentists;
    }

    // Get Dentist Count
    public int getDentistCount() {

        String sql = "SELECT COUNT(*) FROM dentists";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}