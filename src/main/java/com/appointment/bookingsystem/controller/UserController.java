package com.appointment.bookingsystem.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.appointment.bookingsystem.dto.request.UserRequestDTO;
import com.appointment.bookingsystem.dto.response.UserResponseDTO;
import com.appointment.bookingsystem.model.User;
import com.appointment.bookingsystem.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(
            @Valid @RequestBody UserRequestDTO request) {
        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(request.getPassword())
                .role(request.getRole())
                .build();

        User saved = userService.createUser(user);

        return ResponseEntity.ok(
                UserResponseDTO.builder()
                        .id(saved.getId())
                        .fullName(saved.getFullName())
                        .email(saved.getEmail())
                        .phone(saved.getPhone())
                        .role(saved.getRole())
                        .build());
    }

    @GetMapping("/providers")
    public ResponseEntity<List<UserResponseDTO>> getProviders() {
        return ResponseEntity.ok(
                userService.findProviders()
                        .stream()
                        .map(u -> UserResponseDTO.builder()
                                .id(u.getId())
                                .fullName(u.getFullName())
                                .email(u.getEmail())
                                .phone(u.getPhone())
                                .role(u.getRole())
                                .build())
                        .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);

        return ResponseEntity.ok(
                UserResponseDTO.builder()
                        .id(user.getId())
                        .fullName(user.getFullName())
                        .email(user.getEmail())
                        .phone(user.getPhone())
                        .role(user.getRole())
                        .build());
    }

}
