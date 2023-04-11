package com.hotelManagement.hotelManagement.service;

import com.hotelManagement.hotelManagement.exception.ResourceNotFoundException;
import com.hotelManagement.hotelManagement.model.Guest;
import com.hotelManagement.hotelManagement.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService {
    @Autowired
    private GuestRepository guestRepository;


    public Guest createGuest(Guest guest){
        return guestRepository.save(guest);
    }

    public List<Guest> getGuests(){
        return guestRepository.findAll();
    }

    public Guest getGuestsById(long id){
        return guestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guest","id",id));
    }

    public void deleteGuest(long id){
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guest", "id", id));
        guestRepository.delete(guest);
    }

    public Guest updateGuest(Long id, Guest guest){
        Guest existingGuest = guestRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Guest", "id", id));
        existingGuest.setFirstName(guest.getFirstName());
        existingGuest.setLastName(guest.getLastName());
        existingGuest.setEmail(guest.getEmail());
        existingGuest.setPhone(guest.getPhone());
        existingGuest.setAddress(guest.getAddress());
        return guestRepository.save(existingGuest);
    }

}
