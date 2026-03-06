package dao;



import java.sql.*;

import java.util.ArrayList;

import java.util.List;

import model.Reservation;



public class ReservationDAO {



    // Add new reservation

    public void addReservation(Reservation r) {

        String sql = "INSERT INTO reservations(reservation_number, guest_name, address, contact_number, room_type, price_per_night, check_in, check_out, email, payment_status) "

                   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";



        try (Connection con = DBConnection.getConnection();

             PreparedStatement ps = con.prepareStatement(sql)) {



            ps.setString(1, r.getReservationNumber());

            ps.setString(2, r.getGuestName());

            ps.setString(3, r.getAddress());

            ps.setString(4, r.getContactNumber());

            ps.setString(5, r.getRoomType());

            ps.setDouble(6, r.getPricePerNight());

            ps.setString(7, r.getCheckIn());

            ps.setString(8, r.getCheckOut());

            ps.setString(9, r.getEmail());

            ps.setString(10, "Unpaid"); // default



            ps.executeUpdate();



        } catch (Exception e) {

            e.printStackTrace();

        }

    }



    // Update existing reservation

    public void updateReservation(Reservation r) {

        String sql = "UPDATE reservations SET reservation_number=?, guest_name=?, address=?, contact_number=?, room_type=?, price_per_night=?, check_in=?, check_out=?, email=? "

                   + "WHERE reservation_id=?";



        try (Connection con = DBConnection.getConnection();

             PreparedStatement ps = con.prepareStatement(sql)) {



            ps.setString(1, r.getReservationNumber());

            ps.setString(2, r.getGuestName());

            ps.setString(3, r.getAddress());

            ps.setString(4, r.getContactNumber());

            ps.setString(5, r.getRoomType());

            ps.setDouble(6, r.getPricePerNight());

            ps.setString(7, r.getCheckIn());

            ps.setString(8, r.getCheckOut());

            ps.setString(9, r.getEmail());

            ps.setInt(10, r.getReservationId());



            ps.executeUpdate();



        } catch (Exception e) {

            e.printStackTrace();

        }

    }



    // Delete reservation by ID

    public void deleteReservation(int id) {

        String sql = "DELETE FROM reservations WHERE reservation_id=?";

        try (Connection con = DBConnection.getConnection();

             PreparedStatement ps = con.prepareStatement(sql)) {



            ps.setInt(1, id);

            ps.executeUpdate();



        } catch (Exception e) {

            e.printStackTrace();

        }

    }



    // Get reservation by ID

    public Reservation getReservationById(int id) {

        Reservation r = null;

        String sql = "SELECT * FROM reservations WHERE reservation_id=?";

        try (Connection con = DBConnection.getConnection();

             PreparedStatement ps = con.prepareStatement(sql)) {



            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();



            if (rs.next()) {

                r = new Reservation();

                r.setReservationId(rs.getInt("reservation_id"));

                r.setReservationNumber(rs.getString("reservation_number"));

                r.setGuestName(rs.getString("guest_name"));

                r.setAddress(rs.getString("address"));

                r.setContactNumber(rs.getString("contact_number"));

                r.setRoomType(rs.getString("room_type"));

                r.setPricePerNight(rs.getDouble("price_per_night"));

                r.setCheckIn(rs.getString("check_in"));

                r.setCheckOut(rs.getString("check_out"));

                r.setEmail(rs.getString("email"));

            }



        } catch (Exception e) {

            e.printStackTrace();

        }

        return r;

    }



    // Get all reservations

    public List<Reservation> getAllReservations() {

        List<Reservation> list = new ArrayList<>();

        String sql = "SELECT * FROM reservations ORDER BY reservation_id DESC";



        try (Connection con = DBConnection.getConnection();

             PreparedStatement ps = con.prepareStatement(sql);

             ResultSet rs = ps.executeQuery()) {



            while (rs.next()) {

                Reservation r = new Reservation();

                r.setReservationId(rs.getInt("reservation_id"));

                r.setReservationNumber(rs.getString("reservation_number"));

                r.setGuestName(rs.getString("guest_name"));

                r.setAddress(rs.getString("address"));

                r.setContactNumber(rs.getString("contact_number"));

                r.setRoomType(rs.getString("room_type"));

                r.setPricePerNight(rs.getDouble("price_per_night"));

                r.setCheckIn(rs.getString("check_in"));

                r.setCheckOut(rs.getString("check_out"));

                r.setEmail(rs.getString("email"));

                list.add(r);

            }



        } catch (Exception e) {

            e.printStackTrace();

        }



        return list;

    }



    // Optional: Update payment status

    public void updatePaymentStatus(int id, String status) {

        String sql = "UPDATE reservations SET payment_status=? WHERE reservation_id=?";

        try (Connection con = DBConnection.getConnection();

             PreparedStatement ps = con.prepareStatement(sql)) {



            ps.setString(1, status);

            ps.setInt(2, id);

            ps.executeUpdate();



        } catch (Exception e) {

            e.printStackTrace();

        }

    }

   
}

