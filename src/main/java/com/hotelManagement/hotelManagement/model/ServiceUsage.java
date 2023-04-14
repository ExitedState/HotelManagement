package com.hotelManagement.hotelManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_usage_ID")
    private long serviceUsageID;

    @Column(name = "time_in")
    private LocalDateTime timeIn;

    @Column(name = "time_out")
    private LocalDateTime timeOut;

    @ManyToOne
    @JoinColumn(name = "guest_ID", referencedColumnName = "guest_ID")
    private Guest guest;

    @OneToOne
    @JoinColumn(name = "service_ID", referencedColumnName = "service_ID")
    private Services service;

    @ManyToOne
    @JoinColumn(name = "staff_ID", referencedColumnName = "staff_ID")
    private Staff staff;
}
