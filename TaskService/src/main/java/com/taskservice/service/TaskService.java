package com.taskservice.service;

import com.taskservice.entity.Task;
import com.taskservice.entity.TaskStatus;

import java.util.List;

public interface TaskService {

    Task createTask(Task task);

    Task getTaskById(Long taskId);

    Task updateTask(Long taskId, Task taskDetails);

    void deleteTask(Long taskId);

    List<Task> getTasksByUserId(Long userId);

    List<Task> getTasksByStatus(TaskStatus status); // Updated to use TaskStatus enum
}
