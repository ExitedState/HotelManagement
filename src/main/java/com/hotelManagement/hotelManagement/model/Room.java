package com.hotelManagement.hotelManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

// The Room class represents a room entity in the hotel management system.
// This class is used as a model to create a database table using JPA (Java Persistence API).
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    // The roomID field represents the primary key of the Room table.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_ID")
    private Long roomID;

    // The roomNumber field represents the room number in the hotel.
    @Column(name = "room_number")
    private int roomNumber;

    // The roomStatus field represents the current status of the room (e.g., "available", "occupied", "cleaning").
    @Column(name = "room_status")
    private String roomStatus;

    // The roomType field represents the type of room (e.g., "single", "double", "suite").
    @Column(name = "room_type")
    private String roomType;

    // The ppn (price per night) field represents the cost of renting the room for one night.
    @Column(name = "price_per_night")
    private double ppn;

    // The capacity field represents the maximum number of people that can stay in the room.
    @Column(name = "num_people")
    private int capacity;

    // The reservations field represents the list of reservations associated with the room.
    // This creates a one-to-many relationship between Room and Reservation.
    @JsonBackReference(value = "reservation-room")
    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;
}
