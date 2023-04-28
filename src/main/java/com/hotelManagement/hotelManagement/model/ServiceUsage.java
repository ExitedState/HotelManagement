package com.hotelManagement.hotelManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// This class represents the "ServiceUsage" entity and maps it to a database table using JPA.
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceUsage {

    // The primary key for the table, auto-generated with the IDENTITY strategy.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_usage_ID")
    private long serviceUsageID;

    // The time when the service usage starts, mapped to a column "time_in".
    @Column(name = "time_in")
    private LocalDateTime timeIn;

    // The time when the service usage ends, mapped to a column "time_out".
    @Column(name = "time_out")
    private LocalDateTime timeOut;

    // The guest associated with this service usage.
    // The relationship is defined as many-to-one, with a foreign key column "guest_ID".
    // JsonBackReference is used to avoid circular references during JSON serialization.
    @JsonBackReference(value = "guest-serviceUsage")
    @ManyToOne
    @JoinColumn(name = "guest_ID", referencedColumnName = "guest_ID")
    private Guest guest;

    // The service associated with this service usage.
    // The relationship is defined as many-to-one, with a foreign key column "service_ID".
    @ManyToOne
    @JoinColumn(name = "service_ID", referencedColumnName = "service_ID")
    private Services service;

    // The staff member who created the service usage record.
    // The relationship is defined as many-to-one, with a foreign key column "staff_ID".
    // JsonBackReference is used to avoid circular references during JSON serialization.
    @JsonBackReference(value = "reservation-serviceUsage")
    @ManyToOne
    @JoinColumn(name = "staff_ID", referencedColumnName = "staff_ID")
    private Staff staff;
}
