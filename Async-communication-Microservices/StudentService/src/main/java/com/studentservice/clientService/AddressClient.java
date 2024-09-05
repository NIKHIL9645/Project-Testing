package com.studentservice.clientService;

import com.studentservice.entity.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




//@Service
@FeignClient(name = "AddressService", url = "http://localhost:8081")
public interface AddressClient {


    @GetMapping("/api/address/{studentId}")
    Address getAddress(@PathVariable("studentId") String studentId);
}


