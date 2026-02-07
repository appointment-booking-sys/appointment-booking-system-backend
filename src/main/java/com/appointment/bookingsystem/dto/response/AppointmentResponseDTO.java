package com.appointment.bookingsystem.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

import com.appointment.bookingsystem.model.enums.AppointmentStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AppointmentResponseDTO {

    private Long id;
    private String customerName;
    private String providerName;
    private String serviceName;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private AppointmentStatus status;
    private Boolean priority;

}
