package com.appointment.bookingsystem.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.bookingsystem.dto.request.ServiceRequestDTO;
import com.appointment.bookingsystem.dto.response.ServiceResponseDTO;
import com.appointment.bookingsystem.model.ServiceEntity;
import com.appointment.bookingsystem.service.ServiceCatalogService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceCatalogService serviceCatalogService;

    @GetMapping
    public ResponseEntity<List<ServiceResponseDTO>> getAllServices() {
        return ResponseEntity.ok(
                serviceCatalogService.getAllServices()
                        .stream()
                        .map(s -> ServiceResponseDTO.builder()
                                .id(s.getId())
                                .serviceName(s.getServiceName())
                                .durationMinutes(s.getDurationMinutes())
                                .price(s.getPrice())
                                .build())
                        .toList());
    }

    @PostMapping
    public ResponseEntity<ServiceResponseDTO> createService(
            @Valid @RequestBody ServiceRequestDTO request) {

        ServiceEntity service = ServiceEntity.builder()
                .serviceName(request.getServiceName())
                .durationMinutes(request.getDurationMinutes())
                .price(request.getPrice())
                .build();

        ServiceEntity saved = serviceCatalogService.createService(service);

        return ResponseEntity.ok(
                ServiceResponseDTO.builder()
                        .id(saved.getId())
                        .serviceName(saved.getServiceName())
                        .durationMinutes(saved.getDurationMinutes())
                        .price(saved.getPrice())
                        .build());
    }
}
