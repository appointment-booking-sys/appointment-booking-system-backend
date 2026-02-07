package com.appointment.bookingsystem.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequestDTO {

    @NotBlank
    private String serviceName;

    @NotNull
    @Min(5)
    private Integer durationMinutes;

    @NotNull
    @Min(0)
    private Double price;

}
