package com.kafka.consumer;

import com.kafka.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {

    Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(topics = "kafka-demo", groupId = "kafka-group")
    public void listen(Customer customer) {
        // Process the received Customer message
        System.out.println("Received customer: " + customer);
    }

//    @KafkaListener(topics = "kafka-demo", groupId = "jt-group")
//    public void consume(Customer customer) {
//
//        logger.info("consumer consumes the events {}", customer.toString());

//    }
//    @KafkaListener(topics = "kafka-demo" , groupId = "jt-group-1")
//    public  void consume(String message){
//
//        logger.info("consumer consumes the messages {}",message);
//
//    }

//    }
}
