package com.hotelManagement.hotelManagement.controller;

import com.hotelManagement.hotelManagement.model.Services;
import com.hotelManagement.hotelManagement.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServicesController {

    @Autowired
    private ServicesService servicesService;

    @PostMapping("")
    public Services createService(@RequestBody Services service){
        return servicesService.createService(service);
    }

    @GetMapping("")
    public List<Services> getAllServices(){
        return servicesService.getAllServices();
    }

    @GetMapping("/{id}")
    public Services getServiceById(@PathVariable Long id){
        return servicesService.getServiceById(id);
    }

    @PutMapping("/{id}")
    public Services updateService(@PathVariable Long id, @RequestBody Services service){
        return servicesService.updateService(id, service);
    }

    // can use string instead of void to display a message
    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id){
        servicesService.deleteService(id);
    }
}
