package com.hotelManagement.hotelManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

// This class represents the "Services" entity and maps it to a database table using JPA.
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Services {

    // The primary key for the table, auto-generated with the IDENTITY strategy.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_ID")
    private long serviceID;

    // The name of the service, mapped to a column "service_name".
    @Column(name = "service_name")
    private String serviceName;

    // The price of the service, mapped to a column "price".
    @Column(name = "price")
    private double price;

    // The description of the service, mapped to a column "description".
    @Column(name = "description")
    private String desc;

    // The status of the service, mapped to a column "status".
    @Column(name = "status")
    private String status;

    // A list of service usages associated with this service.
    // The relationship is defined as one-to-many, using the mappedBy attribute to specify the owning side.
    // JsonBackReference is used to avoid circular references during JSON serialization.
    @JsonBackReference(value = "serviceUsage-service")
    @OneToMany(mappedBy = "service")
    private List<ServiceUsage> serviceUsages;
}
