package com.elasticSearch.service;

import com.elasticSearch.dto.ProjectEvent;
import com.elasticSearch.entity.IndexProject;
import com.elasticSearch.repository.ProjectEventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {


    private ProjectEventRepository projectEventRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public SearchServiceImpl(ProjectEventRepository projectEventRepository){
        this.projectEventRepository = projectEventRepository;
    }


    @Override
    public IndexProject getProjectById(Long id) {
        return projectEventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found for id: " + id));
    }





//
//    @KafkaListener(topics = "Project", groupId = "Search-group")
//    public void processProjectEvents(String projectEventstr) {
//
//        System.out.println(projectEventstr);
//
//        try {
//
//
//            ProjectEvent projectEvent = objectMapper.readValue(projectEventstr, ProjectEvent.class);
//
//            if (projectEvent.getEventType().equals("CreateEvent")) {
//                projectEventRepository.save(projectEvent.getIndexProject());
//            } else {
//                System.out.println("Update evevnt Logic writing remaining ");
//            }
//
//        } catch (Exception e) {
//            System.out.println("Exception Occurs " + e.getMessage());
//        }

    @KafkaListener(topics = "Project", groupId = "Search-group")
    public void processProjectEvents(String projectEventStr) {
        System.out.println(projectEventStr);

        try {
            // Deserialize the JSON string to ProjectEvent
            ProjectEvent projectEvent = objectMapper.readValue(projectEventStr, ProjectEvent.class);

            // Check for null before invoking methods on projectEvent
            if (projectEvent != null) {
                String eventType = projectEvent.getEventType();
                System.out.println("444444");
                System.out.println(eventType);
                if (eventType != null && eventType.equals("CreateEvent")) {
                    // Handle "CreateEvent" logic
                    System.out.println("pro");
                    projectEventRepository.save(projectEvent.getProject());
                } else {
                    // Handle other event types or log if eventType is null
                    System.out.println("Received an event with type: " + eventType);
                    System.out.println("Update event Logic writing remaining");
                }
            } else {
                System.out.println("Deserialized ProjectEvent is null");
            }

        } catch (Exception e) {
            // Log the exception with detailed information
            System.out.println("Exception Occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
//
//    public IndexProject serachProjectEventById(Long id){
//        return projectEventRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found !!!!! "));
//    }

}

