package com.kabanservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Task {


	private Long id;

	private String title;
	private String description;
	private TaskStatus status ;// Default value is NOT_STARTED
	private LocalTime startTime;
	private LocalDate Date;

	private Long userId; // User associated with the task

	private Long projectId; // User associated with the task

	
	

}
