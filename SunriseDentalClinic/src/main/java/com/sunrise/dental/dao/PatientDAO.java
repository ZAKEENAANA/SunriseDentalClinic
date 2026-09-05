package com.sunrise.dental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunrise.dental.model.Patient;
import com.sunrise.dental.util.DatabaseConnection;

public class PatientDAO {

    // Add a new patient
    public boolean addPatient(Patient patient) {

        String sql = "INSERT INTO patients "
                   + "(first_name, last_name, date_of_birth, gender, phone, email, address) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getLastName());
            statement.setString(3, patient.getDateOfBirth());
            statement.setString(4, patient.getGender());
            statement.setString(5, patient.getPhone());
            statement.setString(6, patient.getEmail());
            statement.setString(7, patient.getAddress());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // Get all patients
    public List<Patient> getAllPatients() {

        List<Patient> patients = new ArrayList<>();

        String sql = "SELECT * FROM patients ORDER BY patient_id DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Patient patient = new Patient();

                patient.setPatientId(
                        resultSet.getInt("patient_id"));

                patient.setFirstName(
                        resultSet.getString("first_name"));

                patient.setLastName(
                        resultSet.getString("last_name"));

                patient.setDateOfBirth(
                        resultSet.getString("date_of_birth"));

                patient.setGender(
                        resultSet.getString("gender"));

                patient.setPhone(
                        resultSet.getString("phone"));

                patient.setEmail(
                        resultSet.getString("email"));

                patient.setAddress(
                        resultSet.getString("address"));

                patients.add(patient);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return patients;
    }


    // Get total patient count
    public int getPatientCount() {

        String sql = "SELECT COUNT(*) FROM patients";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("ERROR GETTING PATIENT COUNT:");
            e.printStackTrace();
        }

        return 0;
    }
    
    // Get patient by ID
    public Patient getPatientById(int patientId) {

        Patient patient = null;

        String sql = "SELECT * FROM patients WHERE patient_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, patientId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    patient = new Patient();

                    patient.setPatientId(
                            resultSet.getInt("patient_id"));

                    patient.setFirstName(
                            resultSet.getString("first_name"));

                    patient.setLastName(
                            resultSet.getString("last_name"));

                    patient.setDateOfBirth(
                            resultSet.getString("date_of_birth"));

                    patient.setGender(
                            resultSet.getString("gender"));

                    patient.setPhone(
                            resultSet.getString("phone"));

                    patient.setEmail(
                            resultSet.getString("email"));

                    patient.setAddress(
                            resultSet.getString("address"));
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return patient;
    }


    // Update patient
    public boolean updatePatient(Patient patient) {

        String sql = "UPDATE patients SET "
                   + "first_name = ?, "
                   + "last_name = ?, "
                   + "date_of_birth = ?, "
                   + "gender = ?, "
                   + "phone = ?, "
                   + "email = ?, "
                   + "address = ? "
                   + "WHERE patient_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getLastName());
            statement.setString(3, patient.getDateOfBirth());
            statement.setString(4, patient.getGender());
            statement.setString(5, patient.getPhone());
            statement.setString(6, patient.getEmail());
            statement.setString(7, patient.getAddress());
            statement.setInt(8, patient.getPatientId());

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
    
    // Delete patient
    public boolean deletePatient(int patientId) {

        String sql = "DELETE FROM patients WHERE patient_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, patientId);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
    
}