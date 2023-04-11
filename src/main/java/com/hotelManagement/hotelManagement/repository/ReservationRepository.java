package com.hotelManagement.hotelManagement.repository;

import com.hotelManagement.hotelManagement.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
