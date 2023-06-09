package com.hotelManagement.hotelManagement.model;

import com.hotelManagement.hotelManagement.exception.ResourceNotFoundException;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Reservation_ID")
    private Long reservationID;

    @Column(name = "check_in_time")
    private LocalDateTime checkInTime;

    @Column(name = "check_out_time")
    private LocalDateTime checkOutTime;

    @Column(name = "duration")
    private int duration;

    @Column(name = "total")
    private double total;

    @Column(name = "person")
    private int person;

    @JsonBackReference(value = "reservation-guest")
    @OneToOne
    @JoinColumn(name = "guest_ID", referencedColumnName = "guest_ID")
    private Guest guest;

    @JsonBackReference(value = "reservation-staff")
    @ManyToOne
    @JoinColumn(name = "staff_ID", referencedColumnName = "staff_ID")
    private Staff staff;


    @ManyToOne
    @JoinColumn(name = "room_ID", referencedColumnName = "room_ID")
    private Room room;

    @PrePersist
    @PreUpdate
    private void updateDuration() {
        duration = calculateDuration();
    }


    public int calculateDuration() {
        if (checkInTime != null && checkOutTime != null) {
            return (int) ChronoUnit.DAYS.between(checkInTime.toLocalDate(), checkOutTime.toLocalDate());
        }
        return 0;
    }


}
