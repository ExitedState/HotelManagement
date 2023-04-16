package com.hotelManagement.hotelManagement.model;

import com.fasterxml.jackson.annotation.JacksonInject;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "guest_ID")
    private long guestID;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

//    @JsonManagedReference(value = "guest-reservation")
    @OneToOne(mappedBy = "guest")
    private Reservation reservation;

//    @JsonManagedReference(value = "guest-serviceUsage")
    @OneToMany(mappedBy = "guest")
    private List<ServiceUsage> serviceUsages = new ArrayList<>();
}
