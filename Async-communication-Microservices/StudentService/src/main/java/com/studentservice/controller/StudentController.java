package com.studentservice.controller;


import com.studentservice.entity.Student;
import com.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


//    public StudentController(StudentService studentService) {
//        this.studentService = studentService;
//    }


    //using feign client
    @GetMapping("/{studentId}")
    public CompletableFuture<Student> getStudent(@PathVariable String studentId) {
        return studentService.getStudentAsync(studentId);
    }



//    //using web client
//    @GetMapping("/web/{studentId}")
//    public Mono<Student> getStudent1(@PathVariable String studentId) {
//        return studentService.getStudentAsync1(studentId);
//    }

}
