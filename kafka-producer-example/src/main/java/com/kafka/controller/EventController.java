package com.kafka.controller;

import com.kafka.dto.Customer;
import com.kafka.service.KafkaMessagePublsiher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    private KafkaMessagePublsiher publsiher;
//
//    @GetMapping("/publish/{message}")
//    public ResponseEntity<?> publishMessage(@PathVariable  String message){
//
//        try {
//            for (int i = 1; i <=1000 ; i++) {
//
//        publsiher.sendMessageToTopic(message +" "+i );
//            }
//        return ResponseEntity.ok("Message  published succesfully");
//
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//
//    }


    @PostMapping
    public void sendCustomer(@RequestBody Customer customer){
        publsiher.sendEventsToTopic(customer);

    }
}
