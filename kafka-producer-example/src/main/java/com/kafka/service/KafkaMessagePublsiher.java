package com.kafka.service;

import com.kafka.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublsiher {

    @Autowired
    private KafkaTemplate<String, Customer> template;


//    public void sendMessageToTopic(String message){
//        CompletableFuture<SendResult<String, Object>> future = template.send("kafka-demo3", message);
////        CompletableFuture<SendResult<String, Object>> future = template.send("kafka-demo1", message);
//        future.whenComplete((result, ex ) -> {
//            if(ex == null){
//                System.out.println("sent message =["+message+"]with offeset "+result.getRecordMetadata().offset()+"]");
//            }else{
//                System.out.println("Unable to send message = [" + message + "] due to :" +ex.getMessage());
//            }
//        });
//    }

    public void sendEventsToTopic(Customer customer) {
        System.out.println("----------------");
        try {
            System.out.println(customer);
            CompletableFuture<SendResult<String, Customer>> future = template.send("kafka-demo", customer);
            System.out.println(future.isDone());
//        CompletableFuture<SendResult<String, Object>> future = template.send("kafka-demo1", message);
//            future.whenComplete((result, ex) -> {
//                if (ex == null) {
//                    System.out.println("sent message =[" + customer.toString() + "]with offeset " + result.getRecordMetadata().offset() + "]");
//                } else {
//                    System.out.println("Unable to send message = [" + customer.toString() + "] due to :" + ex.getMessage());
//                }
//            });
        } catch (Exception e) {
            System.out.println("error occurs......");
            System.out.println(e.getMessage());
        }
    }

}
