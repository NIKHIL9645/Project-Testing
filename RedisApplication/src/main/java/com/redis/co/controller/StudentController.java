package com.redis.co.controller;


import com.redis.co.entity.Student;
import com.redis.co.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping("/save")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable  Long id) {
        return studentService.getStudentById(id);
    }



    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable  Long id)
    {
        studentService.deleteStudent(id);
    }



    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }


    @PatchMapping("/{id}")
    public Student UpdatePartially(@PathVariable  Long id,@RequestBody Map<String, Object> update) throws NoSuchFieldException {
        return studentService.UpdatePartially(id, update);
    }
}

