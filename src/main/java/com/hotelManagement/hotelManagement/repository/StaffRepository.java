package com.hotelManagement.hotelManagement.repository;

import com.hotelManagement.hotelManagement.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}
