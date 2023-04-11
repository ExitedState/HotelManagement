package com.hotelManagement.hotelManagement.controller;

import com.hotelManagement.hotelManagement.model.Staff;
import com.hotelManagement.hotelManagement.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("")
    public Staff createStaff(@RequestBody Staff staff){
        return staffService.createStaff(staff);
    }

    @GetMapping("")
    public List<Staff> getAllStaffs(){
        return staffService.getAllStaffs();
    }

    @GetMapping("/{id}")
    public Staff getStaffById(@PathVariable Long id){
        return staffService.getStaffById(id);
    }

    @PutMapping("/{id}")
    public Staff updateStaff(@PathVariable Long id, @RequestBody Staff staff){
        return staffService.updateStaff(id,staff);
    }

    // can use string instead of void to display a message
    @DeleteMapping("/{id}")
    public void deleteStaff(@PathVariable Long id){
        staffService.deleteStaff(id);
    }
}
