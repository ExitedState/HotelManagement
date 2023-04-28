// Package declaration
package com.hotelManagement.hotelManagement.model;

// Imports
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

// Entity annotation indicates this class is mapped to a database table
@Entity
// Lombok annotations to generate boilerplate code (getters, setters, constructors, etc.)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guest {

    // Id annotation indicates this field is the primary key in the table
    // GeneratedValue annotation specifies the primary key generation strategy
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // Column annotation to specify the column name in the database table
    @Column(name = "guest_ID")
    private long guestID;

    // Column annotation for the firstname column
    @Column(name = "firstname")
    private String firstName;

    // Column annotation for the lastname column
    @Column(name = "lastname")
    private String lastName;

    // Column annotation for the email column
    @Column(name = "email")
    private String email;

    // Column annotation for the phone column
    @Column(name = "phone")
    private String phone;

    // Column annotation for the address column
    @Column(name = "address")
    private String address;

    // One-to-one relationship between Guest and Reservation entities
    // mappedBy indicates that the relationship is managed by the 'guest' field in the Reservation entity
    @OneToOne(mappedBy = "guest")
    private Reservation reservation;

    // One-to-many relationship between Guest and ServiceUsage entities
    // mappedBy indicates that the relationship is managed by the 'guest' field in the ServiceUsage entity
    @OneToMany(mappedBy = "guest")
    private List<ServiceUsage> serviceUsages = new ArrayList<>();
}
