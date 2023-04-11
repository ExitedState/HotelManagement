package com.hotelManagement.hotelManagement.repository;

import com.hotelManagement.hotelManagement.model.ServiceUsage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceUsageRepository extends JpaRepository<ServiceUsage, Long> {
}
