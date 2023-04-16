package com.hotelManagement.hotelManagement.service;

import com.hotelManagement.hotelManagement.exception.ResourceNotFoundException;
import com.hotelManagement.hotelManagement.model.Guest;
import com.hotelManagement.hotelManagement.model.Reservation;
import com.hotelManagement.hotelManagement.model.ServiceUsage;
import com.hotelManagement.hotelManagement.model.Services;
import com.hotelManagement.hotelManagement.repository.ServiceUsageRepository;
import com.hotelManagement.hotelManagement.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesService {

    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ServiceUsageRepository serviceUsageRepository;

    public List<Services> getAllServices() {
        return servicesRepository.findAll();
    }

    public Services getServiceById(Long id) {
        return servicesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Services", "id", id));
    }

    public Services createService(Services services) {
        return servicesRepository.save(services);
    }

    public Services updateService(Long id, Services services) {
        Services existingService = servicesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Services", "id", id));
        existingService.setServiceName(services.getServiceName());
        existingService.setStatus(services.getStatus());
        existingService.setDesc(services.getDesc());
        existingService.setPrice(services.getPrice());
        Services updatedService = servicesRepository.save(existingService);

        // Get all the service usages related to this service
        List<ServiceUsage> serviceUsages = serviceUsageRepository.findByService(updatedService);

        // Update the total for the reservation linked to each service usage
        for (ServiceUsage serviceUsage : serviceUsages) {
            Guest guest = serviceUsage.getGuest();
            Reservation reservation = guest.getReservation();
            if (reservation != null) {
                reservationService.recalculateTotalForReservation(reservation.getReservationID());
            }
        }

        return updatedService;
    }

    public void deleteService(Long id) {
        Services service = servicesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Services", "id", id));
        servicesRepository.delete(service);
    }
}
