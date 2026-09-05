package com.sunrise.dental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunrise.dental.model.Bill;
import com.sunrise.dental.util.DatabaseConnection;

public class BillDAO {

    // =========================================================
    // ADD NEW BILL
    // =========================================================
    public boolean addBill(Bill bill) {

        String sql = "INSERT INTO bills "
                   + "(patient_id, treatment_id, amount, payment_status, bill_date) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            // Patient ID
            statement.setInt(1, bill.getPatientId());

            // Treatment ID
            // Treatment ID is optional
            if (bill.getTreatmentId() > 0) {

                statement.setInt(
                        2,
                        bill.getTreatmentId());

            } else {

                statement.setNull(
                        2,
                        java.sql.Types.INTEGER);
            }

            // Amount
            statement.setDouble(
                    3,
                    bill.getAmount());

            // Payment Status
            statement.setString(
                    4,
                    bill.getPaymentStatus());

            // Bill Date
            statement.setString(
                    5,
                    bill.getBillDate());

            int rowsInserted =
                    statement.executeUpdate();

            return rowsInserted > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }


    // =========================================================
    // GET ALL BILLS
    // =========================================================
    public List<Bill> getAllBills() {

        List<Bill> bills =
                new ArrayList<>();

        String sql =
                "SELECT * FROM bills "
              + "ORDER BY bill_id DESC";

        try (Connection connection =
                     DatabaseConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql);

             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                Bill bill =
                        new Bill();

                // Bill ID
                bill.setBillId(
                        resultSet.getInt(
                                "bill_id"));

                // Patient ID
                bill.setPatientId(
                        resultSet.getInt(
                                "patient_id"));

                // Treatment ID
                bill.setTreatmentId(
                        resultSet.getInt(
                                "treatment_id"));

                // Amount
                bill.setAmount(
                        resultSet.getDouble(
                                "amount"));

                // Payment Status
                bill.setPaymentStatus(
                        resultSet.getString(
                                "payment_status"));

                // Bill Date
                if (resultSet.getDate(
                        "bill_date") != null) {

                    bill.setBillDate(
                            resultSet.getDate(
                                    "bill_date")
                                    .toString());
                }

                bills.add(bill);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return bills;
    }


    // =========================================================
    // GET BILL BY ID
    // =========================================================
    public Bill getBillById(int billId) {

        Bill bill = null;

        String sql =
                "SELECT * FROM bills "
              + "WHERE bill_id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(
                    1,
                    billId);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    bill =
                            new Bill();

                    // Bill ID
                    bill.setBillId(
                            resultSet.getInt(
                                    "bill_id"));

                    // Patient ID
                    bill.setPatientId(
                            resultSet.getInt(
                                    "patient_id"));

                    // Treatment ID
                    bill.setTreatmentId(
                            resultSet.getInt(
                                    "treatment_id"));

                    // Amount
                    bill.setAmount(
                            resultSet.getDouble(
                                    "amount"));

                    // Payment Status
                    bill.setPaymentStatus(
                            resultSet.getString(
                                    "payment_status"));

                    // Bill Date
                    if (resultSet.getDate(
                            "bill_date") != null) {

                        bill.setBillDate(
                                resultSet.getDate(
                                        "bill_date")
                                        .toString());
                    }
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return bill;
    }


    // =========================================================
    // GET COMPLETE BILL DETAILS
    // =========================================================
    public Bill getBillDetails(int billId) {

        Bill bill = null;

        String sql =
                "SELECT b.bill_id, "
              + "b.patient_id, "
              + "b.treatment_id, "
              + "b.amount, "
              + "b.payment_status, "
              + "b.bill_date, "
              + "p.first_name, "
              + "p.last_name, "
              + "p.phone, "
              + "p.email, "
              + "p.address, "
              + "t.treatment_name, "
              + "t.cost "
              + "FROM bills b "
              + "JOIN patients p "
              + "ON b.patient_id = p.patient_id "
              + "LEFT JOIN treatments t "
              + "ON b.treatment_id = t.treatment_id "
              + "WHERE b.bill_id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(
                    1,
                    billId);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    bill =
                            new Bill();

                    // Bill ID
                    bill.setBillId(
                            resultSet.getInt(
                                    "bill_id"));

                    // Patient ID
                    bill.setPatientId(
                            resultSet.getInt(
                                    "patient_id"));

                    // Treatment ID
                    bill.setTreatmentId(
                            resultSet.getInt(
                                    "treatment_id"));

                    // Amount
                    bill.setAmount(
                            resultSet.getDouble(
                                    "amount"));

                    // Payment Status
                    bill.setPaymentStatus(
                            resultSet.getString(
                                    "payment_status"));

                    // Bill Date
                    if (resultSet.getDate(
                            "bill_date") != null) {

                        bill.setBillDate(
                                resultSet.getDate(
                                        "bill_date")
                                        .toString());
                    }
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return bill;
    }


    // =========================================================
    // UPDATE BILL
    // =========================================================
    public boolean updateBill(Bill bill) {

        String sql =
                "UPDATE bills SET "
              + "patient_id = ?, "
              + "treatment_id = ?, "
              + "amount = ?, "
              + "payment_status = ?, "
              + "bill_date = ? "
              + "WHERE bill_id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            // Patient ID
            statement.setInt(
                    1,
                    bill.getPatientId());

            // Treatment ID
            if (bill.getTreatmentId() > 0) {

                statement.setInt(
                        2,
                        bill.getTreatmentId());

            } else {

                statement.setNull(
                        2,
                        java.sql.Types.INTEGER);
            }

            // Amount
            statement.setDouble(
                    3,
                    bill.getAmount());

            // Payment Status
            statement.setString(
                    4,
                    bill.getPaymentStatus());

            // Bill Date
            statement.setString(
                    5,
                    bill.getBillDate());

            // Bill ID
            statement.setInt(
                    6,
                    bill.getBillId());

            int rowsUpdated =
                    statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }


    // =========================================================
    // DELETE BILL
    // =========================================================
    public boolean deleteBill(int billId) {

        String sql =
                "DELETE FROM bills "
              + "WHERE bill_id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(
                    1,
                    billId);

            int rowsDeleted =
                    statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }


    // =========================================================
    // COUNT ALL BILLS
    // =========================================================
    public int getBillCount() {

        String sql =
                "SELECT COUNT(*) FROM bills";

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


    // =========================================================
    // COUNT PENDING BILLS
    // =========================================================
    public int getPendingBillCount() {

        String sql =
                "SELECT COUNT(*) FROM bills "
              + "WHERE payment_status = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    "Pending");

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    return resultSet.getInt(1);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }


    // =========================================================
    // UPDATE PAYMENT STATUS
    // =========================================================
    public boolean updatePaymentStatus(
            int billId,
            String paymentStatus) {

        String sql =
                "UPDATE bills SET "
              + "payment_status = ? "
              + "WHERE bill_id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    paymentStatus);

            statement.setInt(
                    2,
                    billId);

            int rowsUpdated =
                    statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }
}