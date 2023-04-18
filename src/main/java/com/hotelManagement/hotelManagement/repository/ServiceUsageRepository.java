package com.hotelManagement.hotelManagement.repository;

import com.hotelManagement.hotelManagement.model.ServiceUsage;
import com.hotelManagement.hotelManagement.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceUsageRepository extends JpaRepository<ServiceUsage, Long> {
    List<ServiceUsage> findByService(Services service);
}
