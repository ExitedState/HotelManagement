package com.hotelManagement.hotelManagement.service;

import com.hotelManagement.hotelManagement.exception.ResourceNotFoundException;
import com.hotelManagement.hotelManagement.model.Reservation;
import com.hotelManagement.hotelManagement.model.Room;
import com.hotelManagement.hotelManagement.repository.ReservationRepository;
import com.hotelManagement.hotelManagement.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;


    public Reservation createReservation(Reservation reservation){
        //can calculate and set total price or duration here
        //I have no idea so if u have one, u can  freely write
        reservation.setDuration(reservation.calculateDuration());
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservation(){
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id){
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation","id",id));
    }


    public Reservation updateReservation(Long id, Reservation reservation){
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation","id",id));
        existingReservation.setCheckInTime(reservation.getCheckInTime());
        existingReservation.setCheckOutTime(reservation.getCheckInTime());
        existingReservation.setDuration(reservation.calculateDuration());
        return reservationRepository.save(existingReservation);
    }

    public void deleteReservation(Long id){
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation","id",id));
        reservationRepository.delete(reservation);
    }


}
