package com.taskservice.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Task {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String title;
	    private String description;
	    private TaskStatus status = TaskStatus.NOT_STARTED; // Default value is NOT_STARTED
	    private LocalTime startTime;
	    private LocalDate Date;

	    @JoinColumn(name = "user_id", referencedColumnName = "id")
	    private Long userId; // User associated with the task

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public TaskStatus getStatus() {
			return status;
		}

		public void setStatus(TaskStatus status) {
			this.status = status;
		}

		public LocalTime getStartTime() {
			return startTime;
		}

		public void setStartTime(LocalTime startTime) {
			this.startTime = startTime;
		}

		public LocalDate getDate() {
			return Date;
		}

		public void setDate(LocalDate date) {
			Date = date;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		@Override
		public String toString() {
			return "Task [id=" + id + ", title=" + title + ", description=" + description + ", status=" + status
					+ ", startTime=" + startTime + ", Date=" + Date + ", userId=" + userId + "]";
		}

		public Task(Long id, String title, String description, TaskStatus status, LocalTime startTime, LocalDate date,
				Long userId) {
			super();
			this.id = id;
			this.title = title;
			this.description = description;
			this.status = status;
			this.startTime = startTime;
			Date = date;
			this.userId = userId;
		}

		public Task() {
			super();
			// TODO Auto-generated constructor stub
		}

	

	
	    
	    
    
	
    
}
