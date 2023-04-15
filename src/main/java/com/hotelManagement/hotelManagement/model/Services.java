package com.hotelManagement.hotelManagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_ID")
    private long serviceID;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String desc;

    @Column(name = "status")
    private String status;

//    @JsonManagedReference(value = "service-serviceUsage")
    @OneToOne(mappedBy = "service")
    private ServiceUsage serviceUsage;
}
