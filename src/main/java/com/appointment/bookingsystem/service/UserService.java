package com.appointment.bookingsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.appointment.bookingsystem.exception.ResourceNotFoundException;
import com.appointment.bookingsystem.model.User;
import com.appointment.bookingsystem.model.enums.Role;
import com.appointment.bookingsystem.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findProviders() {
        return userRepository.findByRole(Role.PROVIDER);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

}
