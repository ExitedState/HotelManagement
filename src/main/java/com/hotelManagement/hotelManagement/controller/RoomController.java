package com.hotelManagement.hotelManagement.controller;

import com.hotelManagement.hotelManagement.model.Room;
import com.hotelManagement.hotelManagement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("")
    public Room createRoom(@RequestBody Room room){
        return roomService.createRoom(room);
    }

    @GetMapping("")
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id){
        return roomService.getRoomById(id);
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody Room room){
        return roomService.updateRoom(id, room);
    }

    // can use string instead of void to display a message
    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id){
        roomService.deleteRoom(id);
    }
}
