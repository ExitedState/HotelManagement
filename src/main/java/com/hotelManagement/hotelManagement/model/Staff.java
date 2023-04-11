package com.hotelManagement.hotelManagement.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_ID")
    private long staffID;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private long phone;

    @Column(name = "position")
    private String position;

    @Column(name = "salary")
    private double salary;

    @OneToOne(mappedBy = "staff")
    private Reservation reservation;

    @OneToOne(mappedBy = "staff")
    private ServiceUsage serviceUsage;
}
