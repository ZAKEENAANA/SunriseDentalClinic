package com.sunrise.dental.webservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sunrise.dental.model.Appointment;

import javax.ws.rs.core.Response;

public class AppointmentWebServiceTest {

    // Test 1: Invalid Patient ID
    @Test
    void testInvalidPatientId() {

        Appointment appointment = new Appointment();

        appointment.setPatientId(0);
        appointment.setDentistName("");
        appointment.setAppointmentDate("");
        appointment.setAppointmentTime("");
        appointment.setReason("");
        appointment.setStatus("");

        AppointmentWebService service =
                new AppointmentWebService();

        Response response =
                service.createAppointment(appointment);

        assertEquals(
                400,
                response.getStatus()
        );

        response.close();
    }


    // Test 2: Empty Dentist Name
    @Test
    void testEmptyDentistName() {

        Appointment appointment = new Appointment();

        appointment.setPatientId(2);
        appointment.setDentistName("");
        appointment.setAppointmentDate("");
        appointment.setAppointmentTime("");
        appointment.setReason("");
        appointment.setStatus("");

        AppointmentWebService service =
                new AppointmentWebService();

        Response response =
                service.createAppointment(appointment);

        assertEquals(
                400,
                response.getStatus()
        );

        response.close();
    }


    // Test 3: Empty Appointment Date
    @Test
    void testEmptyAppointmentDate() {

        Appointment appointment = new Appointment();

        appointment.setPatientId(2);
        appointment.setDentistName("Dr.Priyanga");
        appointment.setAppointmentDate("");
        appointment.setAppointmentTime("");
        appointment.setReason("");
        appointment.setStatus("");

        AppointmentWebService service =
                new AppointmentWebService();

        Response response =
                service.createAppointment(appointment);

        assertEquals(
                400,
                response.getStatus()
        );

        response.close();
    }


    // Test 4: Invalid Appointment Date
    @Test
    void testInvalidAppointmentDate() {

        Appointment appointment = new Appointment();

        appointment.setPatientId(2);
        appointment.setDentistName("Dr.Priyanga");
        appointment.setAppointmentDate("2026-99-99");
        appointment.setAppointmentTime("");
        appointment.setReason("");
        appointment.setStatus("");

        AppointmentWebService service =
                new AppointmentWebService();

        Response response =
                service.createAppointment(appointment);

        assertEquals(
                400,
                response.getStatus()
        );

        response.close();
    }


    // Test 5: Empty Appointment Time
    @Test
    void testEmptyAppointmentTime() {

        Appointment appointment = new Appointment();

        appointment.setPatientId(2);
        appointment.setDentistName("Dr.Priyanga");
        appointment.setAppointmentDate("2026-12-20");
        appointment.setAppointmentTime("");
        appointment.setReason("");
        appointment.setStatus("");

        AppointmentWebService service =
                new AppointmentWebService();

        Response response =
                service.createAppointment(appointment);

        assertEquals(
                400,
                response.getStatus()
        );

        response.close();
    }


    // Test 6: Invalid Appointment Time
    @Test
    void testInvalidAppointmentTime() {

        Appointment appointment = new Appointment();

        appointment.setPatientId(2);
        appointment.setDentistName("Dr.Priyanga");
        appointment.setAppointmentDate("2026-12-20");
        appointment.setAppointmentTime("25:99");
        appointment.setReason("");
        appointment.setStatus("");

        AppointmentWebService service =
                new AppointmentWebService();

        Response response =
                service.createAppointment(appointment);

        assertEquals(
                400,
                response.getStatus()
        );

        response.close();
    }


    // Test 7: Empty Appointment Reason
    @Test
    void testEmptyAppointmentReason() {

        Appointment appointment = new Appointment();

        appointment.setPatientId(2);
        appointment.setDentistName("Dr.Priyanga");
        appointment.setAppointmentDate("2026-12-20");
        appointment.setAppointmentTime("10:30");
        appointment.setReason("");
        appointment.setStatus("");

        AppointmentWebService service =
                new AppointmentWebService();

        Response response =
                service.createAppointment(appointment);

        assertEquals(
                400,
                response.getStatus()
        );

        response.close();
    }


    // Test 8: Invalid Appointment Status
    @Test
    void testInvalidAppointmentStatus() {

        Appointment appointment = new Appointment();

        appointment.setPatientId(2);
        appointment.setDentistName("Dr.Priyanga");
        appointment.setAppointmentDate("2026-12-20");
        appointment.setAppointmentTime("10:30");
        appointment.setReason("Dental check up");
        appointment.setStatus("Pending");

        AppointmentWebService service =
                new AppointmentWebService();

        Response response =
                service.createAppointment(appointment);

        assertEquals(
                400,
                response.getStatus()
        );

        response.close();
    }
}