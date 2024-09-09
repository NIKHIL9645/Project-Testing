package com.elasticSearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(indexName = "projectindex")
public class IndexProject {

    @Id
    private Long id;

    private String name;

    private String description;


    private Long userId; // Assuming one user can have multiple projects


}
