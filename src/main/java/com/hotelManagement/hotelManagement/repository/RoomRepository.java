package com.hotelManagement.hotelManagement.repository;

import com.hotelManagement.hotelManagement.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
