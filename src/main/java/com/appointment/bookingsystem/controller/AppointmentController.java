package com.appointment.bookingsystem.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.bookingsystem.dto.request.AppointmentRequestDTO;
import com.appointment.bookingsystem.dto.response.AppointmentResponseDTO;
import com.appointment.bookingsystem.model.Appointment;
import com.appointment.bookingsystem.service.AppointmentService;
import com.appointment.bookingsystem.service.ServiceCatalogService;
import com.appointment.bookingsystem.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

        private final AppointmentService appointmentService;
        private final UserService userService;
        private final ServiceCatalogService serviceCatalogService;

        @PostMapping
        public ResponseEntity<AppointmentResponseDTO> bookAppointment(
                        @Valid @RequestBody AppointmentRequestDTO request) {
                Appointment appointment = Appointment.builder()
                                .customer(userService.getUserById(request.getCustomerId()))
                                .provider(userService.getUserById(request.getProviderId()))
                                .service(serviceCatalogService.getServiceById(request.getServiceId()))
                                .appointmentDate(request.getAppointmentDate())
                                .appointmentTime(request.getAppointmentTime())
                                .priority(request.getPriority())
                                .build();

                Appointment saved = appointmentService.bookAppointment(appointment);

                return ResponseEntity.ok(
                                AppointmentResponseDTO.builder()
                                                .id(saved.getId())
                                                .customerName(saved.getCustomer().getFullName())
                                                .providerName(saved.getProvider().getFullName())
                                                .serviceName(saved.getService().getServiceName())
                                                .appointmentDate(saved.getAppointmentDate())
                                                .appointmentTime(saved.getAppointmentTime())
                                                .status(saved.getStatus())
                                                .priority(saved.getPriority())
                                                .build());
        }

        @PutMapping("/{id}/cancel")
        public ResponseEntity<Void> cancelAppointment(@PathVariable Long id) {
                appointmentService.cancelAppointment(id);
                return ResponseEntity.ok().build();
        }

        @PutMapping("/{id}/reschedule")
        public ResponseEntity<Appointment> rescheduleAppointment(
                        @PathVariable Long id,
                        @RequestParam LocalDate date,
                        @RequestParam LocalTime time) {
                return ResponseEntity.ok(
                                appointmentService.rescheduleAppointment(id, date, time));
        }

}
