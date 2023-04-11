package com.hotelManagement.hotelManagement.controller;

import com.hotelManagement.hotelManagement.model.Guest;
import com.hotelManagement.hotelManagement.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guests")
public class GuestController {
    @Autowired
    private GuestService guestService;

    @PostMapping("")
    public Guest createGuest(@RequestBody Guest guest){
        return guestService.createGuest(guest);
    }

    @GetMapping("")
    public List<Guest> getAllGuests(){
        return guestService.getGuests();
    }

    @GetMapping("/{id}")
    public Guest getGuestById(@PathVariable Long id){
        return guestService.getGuestsById(id);
    }

    @PutMapping("/{id}")
    public Guest updateGuest(@PathVariable Long id,@RequestBody Guest guest){
        return guestService.updateGuest(id, guest);
    }

    // can use string instead of void to display a message
    @DeleteMapping("/{id}")
    public void deleteGuest(@PathVariable Long id){
        guestService.deleteGuest(id);
    }

}
