package com.sunrise.dental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.util.DatabaseConnection;

public class AppointmentDAO {

    // Add a new appointment
    public boolean addAppointment(Appointment appointment) {

        String sql =
                "INSERT INTO appointments "
                + "(patient_id, dentist_name, appointment_date, "
                + "appointment_time, reason, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(
                    1,
                    appointment.getPatientId()
            );

            statement.setString(
                    2,
                    appointment.getDentistName()
            );

            statement.setDate(
                    3,
                    java.sql.Date.valueOf(
                            appointment.getAppointmentDate()
                    )
            );

            String timeText =
                    appointment.getAppointmentTime();

            // HTML time input gives HH:mm
            // SQL Time requires HH:mm:ss
            if (timeText != null
                    && timeText.length() == 5) {

                timeText = timeText + ":00";
            }

            statement.setTime(
                    4,
                    java.sql.Time.valueOf(timeText)
            );

            statement.setString(
                    5,
                    appointment.getReason()
            );

            statement.setString(
                    6,
                    appointment.getStatus()
            );

            int rowsInserted =
                    statement.executeUpdate();

            return rowsInserted > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }


    // Get all appointments
    public List<Appointment> getAllAppointments() {

        List<Appointment> appointments =
                new ArrayList<>();

        String sql =
                "SELECT * FROM appointments "
                + "ORDER BY appointment_date DESC, "
                + "appointment_time DESC";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                Appointment appointment =
                        new Appointment();

                appointment.setAppointmentId(
                        resultSet.getInt(
                                "appointment_id"
                        )
                );

                appointment.setPatientId(
                        resultSet.getInt(
                                "patient_id"
                        )
                );

                appointment.setDentistName(
                        resultSet.getString(
                                "dentist_name"
                        )
                );

                if (resultSet.getDate(
                        "appointment_date"
                ) != null) {

                    appointment.setAppointmentDate(
                            resultSet.getDate(
                                    "appointment_date"
                            ).toString()
                    );
                }

                if (resultSet.getTime(
                        "appointment_time"
                ) != null) {

                    appointment.setAppointmentTime(
                            resultSet.getTime(
                                    "appointment_time"
                            ).toString()
                    );
                }

                appointment.setReason(
                        resultSet.getString(
                                "reason"
                        )
                );

                appointment.setStatus(
                        resultSet.getString(
                                "status"
                        )
                );

                appointments.add(appointment);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return appointments;
    }


    // Get appointment by ID
    public Appointment getAppointmentById(
            int appointmentId) {

        Appointment appointment = null;

        String sql =
                "SELECT * FROM appointments "
                + "WHERE appointment_id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(
                    1,
                    appointmentId
            );

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    appointment =
                            new Appointment();

                    appointment.setAppointmentId(
                            resultSet.getInt(
                                    "appointment_id"
                            )
                    );

                    appointment.setPatientId(
                            resultSet.getInt(
                                    "patient_id"
                            )
                    );

                    appointment.setDentistName(
                            resultSet.getString(
                                    "dentist_name"
                            )
                    );

                    if (resultSet.getDate(
                            "appointment_date"
                    ) != null) {

                        appointment.setAppointmentDate(
                                resultSet.getDate(
                                        "appointment_date"
                                ).toString()
                        );
                    }

                    if (resultSet.getTime(
                            "appointment_time"
                    ) != null) {

                        appointment.setAppointmentTime(
                                resultSet.getTime(
                                        "appointment_time"
                                ).toString()
                        );
                    }

                    appointment.setReason(
                            resultSet.getString(
                                    "reason"
                            )
                    );

                    appointment.setStatus(
                            resultSet.getString(
                                    "status"
                            )
                    );
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return appointment;
    }


    // Count appointments
    public int getAppointmentCount() {

        String sql =
                "SELECT COUNT(*) FROM appointments";

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


    // Update appointment status
    public boolean updateAppointmentStatus(
            int appointmentId,
            String status) {

        String sql =
                "UPDATE appointments "
                + "SET status = ? "
                + "WHERE appointment_id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    status
            );

            statement.setInt(
                    2,
                    appointmentId
            );

            int rowsUpdated =
                    statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }


    // Reschedule appointment
    // Updates date, time and status
    public boolean rescheduleAppointment(
            int appointmentId,
            String appointmentDate,
            String appointmentTime) {

        String sql =
                "UPDATE appointments "
                + "SET appointment_date = ?, "
                + "appointment_time = ?, "
                + "status = 'Rescheduled' "
                + "WHERE appointment_id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            // Set new appointment date
            statement.setDate(
                    1,
                    java.sql.Date.valueOf(
                            appointmentDate
                    )
            );

            String timeText =
                    appointmentTime;

            // HTML time input gives HH:mm
            // SQL Time requires HH:mm:ss
            if (timeText != null
                    && timeText.length() == 5) {

                timeText = timeText + ":00";
            }

            // Set new appointment time
            statement.setTime(
                    2,
                    java.sql.Time.valueOf(
                            timeText
                    )
            );

            // Set appointment ID
            statement.setInt(
                    3,
                    appointmentId
            );

            int rowsUpdated =
                    statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }


    // Delete appointment
    public boolean deleteAppointment(
            int appointmentId) {

        String sql =
                "DELETE FROM appointments "
                + "WHERE appointment_id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(
                    1,
                    appointmentId
            );

            int rowsDeleted =
                    statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

}