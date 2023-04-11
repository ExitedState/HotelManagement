package com.hotelManagement.hotelManagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Reservation_ID")
    private Long reservationID;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

    private int duration;

    private double total;

    private int person;

    @OneToOne
    @JoinColumn(name = "guest_ID", referencedColumnName = "guest_ID")
    private Guest guest;

    @OneToOne
    @JoinColumn(name = "staff_ID", referencedColumnName = "staff_ID")
    private Staff staff;

    @OneToMany(mappedBy = "reservation")
    private List<Room> rooms;

    public int calculateDuration() {
        if (checkInTime != null && checkOutTime != null) {
            return (int) ChronoUnit.DAYS.between(checkInTime.toLocalDate(), checkOutTime.toLocalDate());
        }
        return 0;
    }

}
