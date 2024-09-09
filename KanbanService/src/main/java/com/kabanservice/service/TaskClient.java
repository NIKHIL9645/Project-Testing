package com.kabanservice.service;

import com.kabanservice.dto.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(name = "taskservice")
public interface TaskClient {

    @PutMapping("/taskservice/status/{id}")
    public  String updateTaskbyTaskStatus(@PathVariable Long id ,@RequestBody Task taskStatus);


    @GetMapping("/taskservice/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId);

}
