package com.appointment.bookingsystem.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.appointment.bookingsystem.exception.BadRequestException;
import com.appointment.bookingsystem.exception.ResourceNotFoundException;
import com.appointment.bookingsystem.model.Appointment;
import com.appointment.bookingsystem.model.User;
import com.appointment.bookingsystem.model.enums.AppointmentStatus;
import com.appointment.bookingsystem.repository.AppointmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    // BOOK APPOINTMENT
    public Appointment bookAppointment(Appointment appointment) {

        boolean alreadyBooked = appointmentRepository.existsByProviderAndAppointmentDateAndAppointmentTime(
                appointment.getProvider(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime()

        );
        if (alreadyBooked) {
            // throw new RuntimeException("This time slot is already booked");
            throw new BadRequestException("This time slot is already booked");

        }
        appointment.setStatus(AppointmentStatus.BOOKED);
        return appointmentRepository.save(appointment);
    }

    // CANCEL APPOINTMENT
    public void cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);
    }

    // RESCHEDULE
    public Appointment rescheduleAppointment(
            Long appointmentId,
            LocalDate newDate,
            LocalTime newTime) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        appointment.setAppointmentDate(newDate);
        appointment.setAppointmentTime(newTime);
        appointment.setStatus(AppointmentStatus.RESCHEDULED);

        return appointmentRepository.save(appointment);

    }

    // PROVIDER DASHBOARD

    public List<Appointment> getProviderAppointments(User provider, LocalDate date) {
        return appointmentRepository.findByProviderAndAppointmentDate(provider, date);
    }

}
