package com.kabanservice.controller;

import com.kabanservice.dto.Project;
import com.kabanservice.dto.Task;
import com.kabanservice.service.KanbanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kanban")
public class KanbanController {

    @Autowired
    private KanbanService service;

    @GetMapping("/{projectId}")
    public Project getProjecByProjectId(@PathVariable Long projectId){
   Project project= service.getProjectbyPId(projectId);

        return  project;
    }

    @PutMapping("/{id}")
    public  String updateTaskStatusByById(@PathVariable Long id ,@RequestBody Task task){

        String s = service.uppdattaskStatus(id, task);
        return  s;

    }
}
