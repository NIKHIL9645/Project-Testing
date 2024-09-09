package com.kabanservice.service;

import com.kabanservice.dto.Project;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "projectservice")
public interface ProjectService {

    @GetMapping("/projects/{projectId}")
     ResponseEntity<Project> getProjectById(@PathVariable Long projectId);
}
