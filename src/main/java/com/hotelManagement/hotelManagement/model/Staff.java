package com.hotelManagement.hotelManagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

// This class represents the "Staff" entity and maps it to a database table using JPA.
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff {

    // The primary key for the table, auto-generated with the IDENTITY strategy.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_ID")
    private long staffID;

    // The first name of the staff member, mapped to a column "firstname".
    @Column(name = "firstname")
    private String firstName;

    // The last name of the staff member, mapped to a column "lastname".
    @Column(name = "lastname")
    private String lastName;

    // The email of the staff member, mapped to a column "email".
    @Column(name = "email")
    private String email;

    // The phone number of the staff member, mapped to a column "phone".
    @Column(name = "phone")
    private String phone;

    // The position of the staff member, mapped to a column "position".
    @Column(name = "position")
    private String position;

    // The salary of the staff member, mapped to a column "salary".
    @Column(name = "salary")
    private double salary;

    // A list of reservations associated with this staff member.
    // The relationship is defined as one-to-many, using the mappedBy attribute to specify the owning side.
    @OneToMany(mappedBy = "staff")
    private List<Reservation> reservations;

    // A list of service usages created by this staff member.
    // The relationship is defined as one-to-many, using the mappedBy attribute to specify the owning side.
    @OneToMany(mappedBy = "staff")
    private List<ServiceUsage> serviceUsages;
}
