package com.hotelManagement.hotelManagement.service;

import com.hotelManagement.hotelManagement.exception.ResourceNotFoundException;
import com.hotelManagement.hotelManagement.model.Room;
import com.hotelManagement.hotelManagement.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;


    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public Room getRoomById(long id){
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room","id",id));
    }

    public Room createRoom(Room room){
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room room){
        Room existingRoom = roomRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Room","id",id));
        existingRoom.setRoomNumber(room.getRoomNumber());
        existingRoom.setRoomStatus(room.getRoomStatus());
        existingRoom.setRoomType(room.getRoomType());
        existingRoom.setPpn(room.getPpn());
        existingRoom.setCapacity(room.getCapacity());
        return roomRepository.save(existingRoom);
    }

    public void deleteRoom(long id){
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room","id",id));
        roomRepository.delete(room);
    }

}
