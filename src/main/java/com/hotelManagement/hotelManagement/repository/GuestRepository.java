package com.hotelManagement.hotelManagement.repository;

import com.hotelManagement.hotelManagement.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
