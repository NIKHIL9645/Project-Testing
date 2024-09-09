package com.elasticSearch.service;


import com.elasticSearch.dto.ProjectEvent;
import com.elasticSearch.entity.IndexProject;

public interface SearchService {

//    ProjectEvent getProjectEventById(String id);


    IndexProject getProjectById(Long id);


}
