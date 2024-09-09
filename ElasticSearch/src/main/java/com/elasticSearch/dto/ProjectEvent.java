package com.elasticSearch.dto;

import com.elasticSearch.entity.IndexProject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectEvent {

    private  String eventType;

    private IndexProject project;


}
