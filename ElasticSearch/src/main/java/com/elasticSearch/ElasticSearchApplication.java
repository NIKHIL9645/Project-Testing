package com.elasticSearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ElasticSearchApplication {


	public static void main(String[] args) {
		SpringApplication.run(ElasticSearchApplication.class, args);
	}

}
