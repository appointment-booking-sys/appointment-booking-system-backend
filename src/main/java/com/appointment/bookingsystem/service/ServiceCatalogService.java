package com.appointment.bookingsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.appointment.bookingsystem.exception.ResourceNotFoundException;
import com.appointment.bookingsystem.model.ServiceEntity;
import com.appointment.bookingsystem.repository.ServiceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceCatalogService {

    private final ServiceRepository serviceRepository;

    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }

    public ServiceEntity createService(ServiceEntity service) {
        return serviceRepository.save(service);
    }

    public ServiceEntity getServiceById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));
    }

}
