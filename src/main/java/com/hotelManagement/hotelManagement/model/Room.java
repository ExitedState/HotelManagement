package com.hotelManagement.hotelManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_ID")
    private Long roomID;

    @Column(name = "room_number")
    private int roomNumber;

    @Column(name = "room_status")
    private String roomStatus;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "price_per_night")
    private double ppn;

    @Column(name = "num_people")
    private int capacity;

    //    @JsonManagedReference(value = "reservation-room")
    @JsonBackReference(value = "reservation-room")
    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;
}
