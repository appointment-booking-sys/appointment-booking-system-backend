package com.appointment.bookingsystem.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.bookingsystem.model.ServiceEntity;
import com.appointment.bookingsystem.service.ServiceCatalogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceCatalogService serviceCatalogService;

    @GetMapping
    public ResponseEntity<List<ServiceEntity>> getAllServices() {
        return ResponseEntity.ok(serviceCatalogService.getAllServices());
    }

    @PostMapping
    public ResponseEntity<ServiceEntity> createService(@RequestBody ServiceEntity service) {
        return ResponseEntity.ok(serviceCatalogService.createService(service));
    }
}
