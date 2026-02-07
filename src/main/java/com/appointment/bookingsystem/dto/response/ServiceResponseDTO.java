package com.appointment.bookingsystem.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ServiceResponseDTO {

    private Long id;
    private String serviceName;
    private Integer durationMinutes;
    private Double price;

}
