package com.appointment.bookingsystem.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentRequestDTO {
    @NotNull
    private Long customerId;

    @NotNull
    private Long providerId;

    @NotNull
    private Long serviceId;

    @NotNull
    private LocalDate appointmentDate;

    @NotNull
    private LocalTime appointmentTime;

    private Boolean priority;

}
