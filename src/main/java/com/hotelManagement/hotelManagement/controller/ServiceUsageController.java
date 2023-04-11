package com.hotelManagement.hotelManagement.controller;

import com.hotelManagement.hotelManagement.model.ServiceUsage;
import com.hotelManagement.hotelManagement.service.ServiceUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/serviceUsage")
public class ServiceUsageController {

    @Autowired
    private ServiceUsageService serviceUsageService;

    @PostMapping("")
    public ServiceUsage createServiceUsage(@RequestBody ServiceUsage serviceUsage){
        return serviceUsageService.createServiceUsage(serviceUsage);
    }

    @GetMapping("")
    public List<ServiceUsage> getAllServiceUsage(){
        return serviceUsageService.getAllServiceUsage();
    }

    @GetMapping("/{id}")
    public ServiceUsage getServiceUsageById(@PathVariable Long id) {
        return serviceUsageService.getServiceUsageById(id);
    }

    @PutMapping("/{id}")
    public ServiceUsage updateServiceUsage(@PathVariable Long id, @RequestBody ServiceUsage serviceUsage){
        return serviceUsageService.updateServiceUsage(id, serviceUsage);
    }


    // can use string instead of void to display a message
    @DeleteMapping("/{id}")
    public void deleteServiceUsage(@PathVariable Long id){
        serviceUsageService.deleteServiceUsage(id);
    }
}
