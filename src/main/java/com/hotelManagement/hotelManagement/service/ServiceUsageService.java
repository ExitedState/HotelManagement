package com.hotelManagement.hotelManagement.service;

import com.hotelManagement.hotelManagement.exception.ResourceNotFoundException;
import com.hotelManagement.hotelManagement.model.Guest;
import com.hotelManagement.hotelManagement.model.Reservation;
import com.hotelManagement.hotelManagement.model.ServiceUsage;
import com.hotelManagement.hotelManagement.repository.ServiceUsageRepository;
import com.hotelManagement.hotelManagement.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ServiceUsageService {

    @Autowired
    private ServiceUsageRepository serviceUsageRepository;
    @Autowired
    private ReservationService reservationService;

    public ServiceUsage createServiceUsage(ServiceUsage serviceUsage) {
        // Set time_in to the current time if not provided
        if (serviceUsage.getTimeIn() == null) {
            LocalDateTime currentTime = LocalDateTime.now();
            serviceUsage.setTimeIn(currentTime);
        }
        // Set time_out to one hour later if not provided
        if (serviceUsage.getTimeOut() == null) {
            LocalDateTime oneHourLater = serviceUsage.getTimeIn().plus(1, ChronoUnit.HOURS);
            serviceUsage.setTimeOut(oneHourLater);
        }
        return serviceUsageRepository.save(serviceUsage);
    }

    public List<ServiceUsage> getAllServiceUsage() {
        return serviceUsageRepository.findAll();
    }

    public ServiceUsage getServiceUsageById(Long id) {
        return serviceUsageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service usage", "id", id));
    }

    public ServiceUsage updateServiceUsage(Long id, ServiceUsage serviceUsage) {
        ServiceUsage existingSU = serviceUsageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service usage", "id", id));
        existingSU.setService(serviceUsage.getService());
        existingSU.setTimeIn(serviceUsage.getTimeIn());
        existingSU.setTimeOut(serviceUsage.getTimeOut());

        ServiceUsage updatedServiceUsage = serviceUsageRepository.save(existingSU);

        // Get the guest related to this service usage
        Guest guest = updatedServiceUsage.getGuest();

        // Update the total for the reservation linked to this guest
        Reservation reservation = guest.getReservation();
        if (reservation != null) {
            reservationService.recalculateTotalForReservation(reservation.getReservationID());
        }

        return updatedServiceUsage;
    }


    public void deleteServiceUsage(Long id) {
        ServiceUsage serviceUsage = serviceUsageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service usage", "id", id));
        serviceUsageRepository.delete(serviceUsage);
    }

}
