package com.hotelManagement.hotelManagement.controller;

import com.hotelManagement.hotelManagement.model.Reservation;
import com.hotelManagement.hotelManagement.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;


    @PostMapping("")
    public Reservation createReservation(@RequestBody Reservation reservation){
        return reservationService.createReservation(reservation);
    }

    @GetMapping("")
    public List<Reservation> getAllReservations(){
        return reservationService.getAllReservation();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable Long id){
        return reservationService.getReservationById(id);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable Long id, @RequestBody Reservation reservation){
        return reservationService.updateReservation(id, reservation);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id){
        reservationService.deleteReservation(id);
    }
}
