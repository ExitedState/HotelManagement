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

import java.time.LocalDateTime;
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
        // Check if the guest already has a reservation
        Long guestId = reservation.getGuest().getGuestID();
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new ResourceNotFoundException("Guest", "id", guestId));
        if (guest.getReservation() != null) {
            System.out.println("Guest with ID " + guestId + " already has a reservation.");
            throw new IllegalStateException("Guest with ID " + guestId + " already has a reservation.");
        }
        reservation.setDuration(reservation.calculateDuration());

        // Fetch the room from the database
        Long roomId = reservation.getRoom().getRoomID();
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", roomId));
        // Fetch the guest from the database
//        guestId = reservation.getGuest().getGuestID();
//        guest = guestRepository.findById(guestId)
//                .orElseThrow(() -> new ResourceNotFoundException("Guest", "id", guestId));
        if (!isRoomAvailable(room, reservation.getCheckInTime(), reservation.getCheckOutTime())) {
            throw new IllegalStateException("The room is not available for the specified time period.");
        }
        if(room.getCapacity()<reservation.getPerson()){
            throw new IllegalStateException("The room is not available for the specified number of guests.");
        }
        // Calculate the total price
        double totalPrice = room.getPpn() * reservation.getDuration();
        totalPrice += getServiceUsageTotalForGuest(guest);
        reservation.setTotal(totalPrice);
        reservation.setRoom(room);
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
        if (!isRoomAvailable(existingReservation.getRoom(), reservation.getCheckInTime(), reservation.getCheckOutTime()) &&
        !existingReservation.getReservationID().equals(id)) {
            throw new IllegalStateException("The room is not available for the specified time period.");
        }
        existingReservation.setCheckInTime(reservation.getCheckInTime());
        existingReservation.setCheckOutTime(reservation.getCheckOutTime());
        existingReservation.setPerson(reservation.getPerson());
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

    private boolean isRoomAvailable(Room room, LocalDateTime checkInTime, LocalDateTime checkOutTime) {

        List<Reservation> reservations = reservationRepository.findByRoom(room);

        for (Reservation reservation : reservations) {
            if (checkInTime.isBefore(reservation.getCheckOutTime()) && checkOutTime.isAfter(reservation.getCheckInTime())) {
                return false;
            }
        }

        return true;
    }

    public Guest getCurrentGuestForRoom(Room room) {
        LocalDateTime now = LocalDateTime.now();
        for (Reservation reservation : room.getReservations()) {
            if (reservation.getCheckInTime().isBefore(now) && reservation.getCheckOutTime().isAfter(now)) {
                return reservation.getGuest();
            }
        }
        return null;
    }



}
