package com.appointment.bookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appointment.bookingsystem.model.ServiceEntity;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

}
