package com.appointment.bookingsystem.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.appointment.bookingsystem.model.User;
import com.appointment.bookingsystem.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/providers")
    public ResponseEntity<List<User>> getProviders() {
        return ResponseEntity.ok(userService.findProviders());
    }

    @GetMapping("/{id}")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

}
