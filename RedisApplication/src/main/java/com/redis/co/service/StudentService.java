package com.redis.co.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.redis.co.entity.Student;
import com.redis.co.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    @Autowired
    private RedisTemplate<String, Student>  redisTemplate;


    private static  final String STUDENT_CACHE_KEY_PREFIX = "student:";



    @Autowired
    private ObjectMapper objectMapper;

    public Student saveStudent(Student student){

        //save to db
        Student save = studentRepository.save(student);

        //save to redis
        redisTemplate.opsForValue().set(STUDENT_CACHE_KEY_PREFIX + save.getId(), save);

        return save;

    }


    public Optional<Student> getStudentById(Long id){

        //get first from cache
        Student student = redisTemplate.opsForValue().get(STUDENT_CACHE_KEY_PREFIX + id);


        if (student != null){

            return Optional.of(student);
        }


        // if not available then go to db
        student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Boss Data Not Found "));



        //save to redis cache if not found
        if (student !=null){
            System.out.println("from ");
            redisTemplate.opsForValue().set(STUDENT_CACHE_KEY_PREFIX + id, student);

        }

    
        return  Optional.ofNullable(student);

    }

    public void deleteStudent(Long id){

        //delete from db
        studentRepository.deleteById(id);

        //delete from db
        redisTemplate.delete(STUDENT_CACHE_KEY_PREFIX + id);

    }


    public  Student updateStudent(Long id,Student student){

        // Find the existing student
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with given id"));

        // Update the existing student's properties with the new values from the `student` parameter
        existingStudent.setName(student.getName());  // Update name
        existingStudent.setAge(student.getAge());    // Update age

        // Save the updated student to the database
        Student updatedStudent = studentRepository.save(existingStudent);

        // Update the cache with the new student data
        redisTemplate.opsForValue().set(STUDENT_CACHE_KEY_PREFIX + updatedStudent.getId(), updatedStudent);

        return updatedStudent;


    }

    public Student UpdatePartially(Long id , Map<String , Object> update) throws NoSuchFieldException {
        // Find the existing student
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with given id"));


        if (update != null && !update.isEmpty()){
            for(Map.Entry<String,Object> entry : update.entrySet()){

                String fieldName = entry.getKey();
                Object value = entry.getValue();

                try{
                    Field field = existingStudent.getClass().getDeclaredField(fieldName);
                    field.setAccessible(true);
                    field.set(existingStudent,value);

                } catch ( NoSuchFieldException | IllegalAccessException e) {

                    throw new RuntimeException("Failed to update : => "+fieldName,e);


                }


            }
        }

        //update to db
        Student updated = studentRepository.save(existingStudent);

        //update in redis cache
        redisTemplate.opsForValue().set(STUDENT_CACHE_KEY_PREFIX + updated.getId() ,  updated);

        return  updated;
    }
}
