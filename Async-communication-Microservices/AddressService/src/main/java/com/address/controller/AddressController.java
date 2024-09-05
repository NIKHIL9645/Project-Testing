package com.address.controller;


import com.address.entity.Address;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @GetMapping("/{studentId}")
    public Address getAddress(@PathVariable String studentId) {
        // Dummy data
        return new Address(studentId, "123 Main St", "Springfield", "IL");
    }

}
