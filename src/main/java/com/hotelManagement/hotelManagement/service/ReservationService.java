package com.hotelManagement.hotelManagement.service;

import com.hotelManagement.hotelManagement.exception.ResourceNotFoundException;
import com.hotelManagement.hotelManagement.model.Guest;
import com.hotelManagement.hotelManagement.model.Reservation;
import com.hotelManagement.hotelManagement.model.Room;
import com.hotelManagement.hotelManagement.model.ServiceUsage;
import com.hotelManagement.hotelManagement.repository.GuestRepository;
import com.hotelManagement.hotelManagement.repository.ReservationRepository;
import com.hotelManagement.hotelManagement.repository.RoomRepository;
import com.hotelManagement.hotelManagement.repository.ServiceUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GuestRepository guestRepository;


    public Reservation createReservation(Reservation reservation) {
        reservation.setDuration(reservation.calculateDuration());

        // Fetch the room from the database
        Long roomId = reservation.getRoom().getRoomID();
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", roomId));
        // Fetch the guest from the database
        Long guestId = reservation.getGuest().getGuestID();
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new ResourceNotFoundException("Guest", "id", guestId));

        // Calculate the total price
        double totalPrice = room.getPpn() * reservation.getDuration();
        totalPrice += getServiceUsageTotalForGuest(guest);
        reservation.setTotal(totalPrice);

        return reservationRepository.save(reservation);
    }

    private double getServiceUsageTotalForGuest(Guest guest) {
        double serviceUsageTotal = 0;
        for (ServiceUsage serviceUsage : guest.getServiceUsages()) {
            serviceUsageTotal += serviceUsage.getService().getPrice();
        }
        return serviceUsageTotal;
    }


    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));
    }


    public Reservation updateReservation(Long id, Reservation reservation) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));
        existingReservation.setCheckInTime(reservation.getCheckInTime());
        existingReservation.setCheckOutTime(reservation.getCheckOutTime());
        existingReservation.setDuration(reservation.calculateDuration());

        // Update the total price
        double totalPrice = existingReservation.getRoom().getPpn() * existingReservation.getDuration();
        totalPrice += getServiceUsageTotalForGuest(existingReservation.getGuest());
        existingReservation.setTotal(totalPrice);

        return reservationRepository.save(existingReservation);
    }

    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));
        reservationRepository.delete(reservation);
    }

    public void recalculateTotalForReservation(Long reservationId) {
        Reservation reservation = getReservationById(reservationId);
        double totalPrice = reservation.getRoom().getPpn() * reservation.getDuration();
        totalPrice += getServiceUsageTotalForGuest(reservation.getGuest());
        reservation.setTotal(totalPrice);
        reservationRepository.save(reservation);
    }

}
