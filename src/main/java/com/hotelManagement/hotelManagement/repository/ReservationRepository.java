package com.hotelManagement.hotelManagement.repository;

import com.hotelManagement.hotelManagement.model.Reservation;
import com.hotelManagement.hotelManagement.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByRoom(Room room);
}
