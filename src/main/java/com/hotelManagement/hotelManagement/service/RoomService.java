package com.hotelManagement.hotelManagement.service;

import com.hotelManagement.hotelManagement.exception.ResourceNotFoundException;
import com.hotelManagement.hotelManagement.model.Guest;
import com.hotelManagement.hotelManagement.model.Reservation;
import com.hotelManagement.hotelManagement.model.Room;
import com.hotelManagement.hotelManagement.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationService reservationService;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room room) {
        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
        existingRoom.setRoomNumber(room.getRoomNumber());
        existingRoom.setRoomStatus(room.getRoomStatus());
        existingRoom.setRoomType(room.getRoomType());
        existingRoom.setPpn(room.getPpn());
        existingRoom.setCapacity(room.getCapacity());
        Room updatedRoom = roomRepository.save(existingRoom);

        // Update the total for all reservations linked to this room
        for (Reservation reservation : updatedRoom.getReservations()) {
            reservationService.recalculateTotalForReservation(reservation.getReservationID());
        }
        return updatedRoom;
    }

    public void deleteRoom(long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
        roomRepository.delete(room);
    }

    public Guest getCurrentGuestForRoom(long roomId) {
        Room room = getRoomById(roomId);
        return reservationService.getCurrentGuestForRoom(room);
    }

}
