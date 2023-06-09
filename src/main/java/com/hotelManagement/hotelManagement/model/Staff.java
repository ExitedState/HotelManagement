package com.hotelManagement.hotelManagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    private String phone;

    @Column(name = "position")
    private String position;

    @Column(name = "salary")
    private double salary;

//    @JsonManagedReference(value = "staff-reservation")
    @OneToMany(mappedBy = "staff")
    private List<Reservation> reservations;

//    @JsonManagedReference(value = "staff-serviceUsage")
    @OneToMany(mappedBy = "staff")
    private List<ServiceUsage> serviceUsages;
}
