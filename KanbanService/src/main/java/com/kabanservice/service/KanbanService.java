package com.kabanservice.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kabanservice.dto.Project;
import com.kabanservice.dto.Task;
import com.kabanservice.dto.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class KanbanService {

    @Autowired
    private  ProjectService projectService;
    @Autowired
    private  TaskClient taskClient;


    public Project getProjectbyPId(Long projectId)  {
           Object project = projectService.getProjectById(projectId).getBody();
        System.out.println(project);
           Project project1= (Project) project;
        System.out.println(project1);
        System.out.println("adsffs"+project1);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
//            Project project2 = objectMapper.readValue((JsonParser) project, Project.class);
            System.out.println(project);
//            System.out.println(project2);
        }
        catch (Exception e)
        {
            System.out.println("sfaaaafsfsffafa");
        }

//           Project project1 = new Project();



//        System.out.println(task);
//        System.out.println(project1);
        System.out.println(project);
//
//        Task task = new Task();
//        System.out.println(task);

//           Project foundProject = (Project) project;

        return  project1;
//        return foundProject;
    }


    public  String  uppdattaskStatus(Long id, Task status){

        Task task = taskClient.getTaskById(id).getBody();
        TaskStatus priviousStatus=task.getStatus();
        TaskStatus updateingStatus=status.getStatus();


        if(priviousStatus==TaskStatus.NOT_STARTED && updateingStatus==TaskStatus.IN_PROGRESS) {

        taskClient.updateTaskbyTaskStatus(id,status);
        return " task update from "+priviousStatus+" to "+updateingStatus;
        }
        if(priviousStatus==TaskStatus.IN_PROGRESS && updateingStatus==TaskStatus.COMPLETED
        ) {

            taskClient.updateTaskbyTaskStatus(id,status);
            return " task update from "+priviousStatus+" to "+updateingStatus;
        }

        return  " invalidate status";
    }
}
