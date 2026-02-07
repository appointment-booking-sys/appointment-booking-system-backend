package com.appointment.bookingsystem.dto.response;

import com.appointment.bookingsystem.model.enums.Role;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDTO {

    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private Role role;

}
