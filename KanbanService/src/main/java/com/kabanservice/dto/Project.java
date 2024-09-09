package com.kabanservice.dto;


import lombok.*;

import java.util.List;


@Data
@ToString

@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class Project {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;


    private Long userId; // Assuming one user can have multiple projects

////
//    @Transient
//    @JsonIgnore
    private List<Task> taskList;


//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public Long getUserId() {
//		return userId;
//	}
//
//	public void setUserId(Long userId) {
//		this.userId = userId;
//	}
//
//	public ResponseEntity<List<Task>> getTaskList() {
//		return taskList;
//	}
//
//	public void setTaskList(ResponseEntity<List<Task>> tasksByProjectId) {
//		this.taskList = tasksByProjectId;
//	}
//
//	@Override
//	public String toString() {
//		return "Project [id=" + id + ", name=" + name + ", description=" + description + ", userId=" + userId
//				+ ", taskList=" + taskList + "]";
//	}
//
//	public Project(Long id, String name, String description, Long userId, ResponseEntity<List<Task>> taskList) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.description = description;
//		this.userId = userId;
//		this.taskList = taskList;
//	}
//
//	public Project() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
    
    
}




	
