package com.appointment.bookingsystem.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appointment.bookingsystem.model.Appointment;
import com.appointment.bookingsystem.model.User;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Prevent double booking
    boolean existsByProviderAndAppointmentDateAndAppointmentTime(
            User provider,
            LocalDate appointmentDate,
            LocalTime appointmentTime

    );

    // Providr dashboard
    List<Appointment> findByProviderAndAppointmentDate(
            User provider,
            LocalDate appointmenDate);

    // Customer history
    List<Appointment> findByCustomer(User customer);

    // Daily schedule
    List<Appointment> findByAppointmentDate(LocalDate date);

}
