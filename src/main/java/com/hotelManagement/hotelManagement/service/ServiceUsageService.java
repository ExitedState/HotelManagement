package com.hotelManagement.hotelManagement.service;

import com.hotelManagement.hotelManagement.exception.ResourceNotFoundException;
import com.hotelManagement.hotelManagement.model.ServiceUsage;
import com.hotelManagement.hotelManagement.repository.ServiceUsageRepository;
import com.hotelManagement.hotelManagement.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUsageService {

    @Autowired
    private ServiceUsageRepository serviceUsageRepository;

    public ServiceUsage createServiceUsage(ServiceUsage serviceUsage) {
        //can set time_in or time_out here before return
        //I have no idea so if u have one, u can  freely write it
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
        return serviceUsageRepository.save(existingSU);
    }


    public void deleteServiceUsage(Long id) {
        ServiceUsage serviceUsage = serviceUsageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service usage", "id", id));
        serviceUsageRepository.delete(serviceUsage);
    }

}
