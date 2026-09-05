package com.sunrise.dental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunrise.dental.model.Treatment;
import com.sunrise.dental.util.DatabaseConnection;

public class TreatmentDAO {

    // Add Treatment
    public boolean addTreatment(Treatment treatment) {

        String sql = "INSERT INTO treatments "
                   + "(patient_id, treatment_name, description, treatment_date, cost) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, treatment.getPatientId());

            statement.setString(2, treatment.getTreatmentName());

            statement.setString(3, treatment.getDescription());

            statement.setString(4, treatment.getTreatmentDate());

            statement.setDouble(5, treatment.getCost());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }


    // Get All Treatments
    public List<Treatment> getAllTreatments() {

        List<Treatment> treatments = new ArrayList<>();

        String sql = "SELECT * FROM treatments "
                   + "ORDER BY treatment_id DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                Treatment treatment = new Treatment();

                treatment.setTreatmentId(
                        resultSet.getInt("treatment_id"));

                treatment.setPatientId(
                        resultSet.getInt("patient_id"));

                treatment.setTreatmentName(
                        resultSet.getString("treatment_name"));

                treatment.setDescription(
                        resultSet.getString("description"));

                if (resultSet.getDate("treatment_date") != null) {

                    treatment.setTreatmentDate(
                            resultSet.getDate("treatment_date")
                                    .toString());
                }

                treatment.setCost(
                        resultSet.getDouble("cost"));

                treatments.add(treatment);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return treatments;
    }


    // Get Treatment by ID
    public Treatment getTreatmentById(int treatmentId) {

        Treatment treatment = null;

        String sql = "SELECT * FROM treatments "
                   + "WHERE treatment_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, treatmentId);

            try (ResultSet resultSet =
                    statement.executeQuery()) {

                if (resultSet.next()) {

                    treatment = new Treatment();

                    treatment.setTreatmentId(
                            resultSet.getInt("treatment_id"));

                    treatment.setPatientId(
                            resultSet.getInt("patient_id"));

                    treatment.setTreatmentName(
                            resultSet.getString("treatment_name"));

                    treatment.setDescription(
                            resultSet.getString("description"));

                    if (resultSet.getDate("treatment_date") != null) {

                        treatment.setTreatmentDate(
                                resultSet.getDate("treatment_date")
                                        .toString());
                    }

                    treatment.setCost(
                            resultSet.getDouble("cost"));
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return treatment;
    }


    // Update Treatment
    public boolean updateTreatment(Treatment treatment) {

        String sql = "UPDATE treatments SET "
                   + "patient_id = ?, "
                   + "treatment_name = ?, "
                   + "description = ?, "
                   + "treatment_date = ?, "
                   + "cost = ? "
                   + "WHERE treatment_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, treatment.getPatientId());

            statement.setString(2, treatment.getTreatmentName());

            statement.setString(3, treatment.getDescription());

            statement.setString(4, treatment.getTreatmentDate());

            statement.setDouble(5, treatment.getCost());

            statement.setInt(6, treatment.getTreatmentId());

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }


    // Delete Treatment
    public boolean deleteTreatment(int treatmentId) {

        String sql = "DELETE FROM treatments "
                   + "WHERE treatment_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, treatmentId);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }


    // Count Treatments
    public int getTreatmentCount() {

        String sql = "SELECT COUNT(*) FROM treatments";

        try (Connection connection = DatabaseConnection.getConnection();
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