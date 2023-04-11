package com.hotelManagement.hotelManagement.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @Column(name = "room_number")
    private Long roomNum;

    @Column(name = "room_status")
    private String roomStatus;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "price_per_night")
    private double ppn;

    @Column(name = "num_people")
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "Reservation_ID")
    private Reservation reservation;
}
