package com.elasticSearch.repository;

import com.elasticSearch.entity.IndexProject;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectEventRepository extends ElasticsearchRepository<IndexProject, Long> {



}
