package com.hotelManagement.hotelManagement.service;

import com.hotelManagement.hotelManagement.exception.ResourceNotFoundException;
import com.hotelManagement.hotelManagement.model.Staff;
import com.hotelManagement.hotelManagement.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public List<Staff> getAllStaffs(){
        return staffRepository.findAll();
    }

    public Staff getStaffById(Long id){
        return staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff","id",id));
    }

    public Staff createStaff(Staff staff){
        String phone = staff.getPhone();
        String sanitizedPhone = phone.replaceAll("\\D", "");
        staff.setPhone(sanitizedPhone);
        return staffRepository.save(staff);
    }

    public Staff updateStaff(Long id, Staff staff){
        Staff existingStaff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff","id",id));
        existingStaff.setFirstName(staff.getFirstName());
        existingStaff.setLastName(staff.getLastName());
        existingStaff.setEmail(staff.getEmail());
        existingStaff.setPhone(staff.getPhone());
        existingStaff.setPosition(staff.getPosition());
        existingStaff.setSalary(staff.getSalary());
        return staffRepository.save(existingStaff);
    }

    public void deleteStaff(Long id){
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff","id",id));
        staffRepository.delete(staff);
    }
}
