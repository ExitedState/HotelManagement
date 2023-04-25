package com.hotelManagement.hotelManagement.service;

import com.hotelManagement.hotelManagement.exception.ResourceNotFoundException;
import com.hotelManagement.hotelManagement.model.*;
import com.hotelManagement.hotelManagement.repository.GuestRepository;
import com.hotelManagement.hotelManagement.repository.StaffRepository;
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
    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ServicesRepository servicesRepository;

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

        Long serviceId = serviceUsage.getService().getServiceID();
        Long guestId = serviceUsage.getGuest().getGuestID();
        Long staffId = serviceUsage.getStaff().getStaffID();

        Services service = servicesRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service", "id", serviceId));
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new ResourceNotFoundException("Guest", "id", guestId));
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff", "id", staffId));
        //if service status is INACTIVE, throw exception
        if (service.getStatus().equals("INACTIVE")) {
            throw new IllegalStateException("The service is currently disabled");
        }
        serviceUsage.setService(service);
        serviceUsage.setGuest(guest);
        serviceUsage.setStaff(staff);

        ServiceUsage updatedServiceUsage = serviceUsageRepository.save(serviceUsage);
        // Get the guest related to this service usage
        guest = updatedServiceUsage.getGuest();
        // Update the total for the reservation linked to this guest
        Reservation reservation = guest.getReservation();
        if (reservation != null) {
            reservationService.recalculateTotalForReservation(reservation.getReservationID());
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
