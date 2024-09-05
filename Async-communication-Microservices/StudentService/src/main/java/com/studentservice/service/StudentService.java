package com.studentservice.service;

//import com.studentservice.clientService.AddressClient;

import com.studentservice.clientService.AddressClient;
import com.studentservice.entity.Address;
import com.studentservice.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class StudentService {


    //Executor Service
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);



//    using open feign
    @Autowired
    private AddressClient addressClient;

//
//    //using web client
//    private final WebClient webClient;
//
//    @Autowired
//    public StudentService(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build(); // AddressService URL
//    }


    //using feign client
    @Async
    public CompletableFuture<Student> getStudentAsync(String studentId) {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate fetching student data
            Address address = addressClient.getAddress(studentId);
            // Dummy student data
            return new Student(studentId, "John Doe", address);
        }, executorService);
    }


//    using web client
//    public Mono<Student> getStudentAsync1(String studentId) {
//        return webClient.get()
//                .uri("/api/address/{studentId}", studentId)
//                .retrieve()
//                .bodyToMono(Address.class)
//
//                //Synchronous communication using block() ,if u want to make it asynchronous comm then avoid use of  block()
//                  .block()
//                .map(address -> new Student(studentId, "John Doe", address));
//    }
}

