// This is the Reservation class representing a reservation in the hotel management system.
// It maps to the Reservation table in the database using JPA annotations.
package com.hotelManagement.hotelManagement.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import com.fasterxml.jackson.annotation.JsonBackReference;

// Define the class as an entity and enable Lombok annotations for boilerplate code generation.
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    // Define the primary key with auto-generated values.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Reservation_ID")
    private Long reservationID;

    // Define columns for check-in and check-out times.
    @Column(name = "check_in_time")
    private LocalDateTime checkInTime;

    @Column(name = "check_out_time")
    private LocalDateTime checkOutTime;

    // Define columns for the reservation duration and total cost.
    @Column(name = "duration")
    private int duration;

    @Column(name = "total")
    private double total;

    // Define a column for the number of persons in the reservation.
    @Column(name = "person")
    private int person;

    // Define a one-to-one relationship between the Reservation and Guest entities.
    @JsonBackReference(value = "reservation-guest")
    @OneToOne
    @JoinColumn(name = "guest_ID", referencedColumnName = "guest_ID")
    private Guest guest;

    // Define a many-to-one relationship between the Reservation and Staff entities.
    @JsonBackReference(value = "reservation-staff")
    @ManyToOne
    @JoinColumn(name = "staff_ID", referencedColumnName = "staff_ID")
    private Staff staff;

    // Define a many-to-one relationship between the Reservation and Room entities.
    @ManyToOne
    @JoinColumn(name = "room_ID", referencedColumnName = "room_ID")
    private Room room;

    // Automatically update the reservation duration before persisting or updating the record.
    @PrePersist
    @PreUpdate
    private void updateDuration() {
        duration = calculateDuration();
    }

    // Calculate the duration of the reservation in days based on check-in and check-out times.
    public int calculateDuration() {
        if (checkInTime != null && checkOutTime != null) {
            return (int) ChronoUnit.DAYS.between(checkInTime.toLocalDate(), checkOutTime.toLocalDate());
        }
        return 0;
    }

}
