package com.elasticSearch.controller;

import com.elasticSearch.entity.IndexProject;
import com.elasticSearch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elasticsearch")
public class SearchController {

    @Autowired
    private SearchService searchService;


    @GetMapping("/{id}")
    public IndexProject getProjectById(@PathVariable Long id) {
        return searchService.getProjectById(id);
    }

}
