package com.redis.co.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.redis.co.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {


    @Bean
    public RedisTemplate<String , Student> redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String,Student> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);




        // Create and configure Jackson2JsonRedisSerializer
        Jackson2JsonRedisSerializer<Student> serializer = new Jackson2JsonRedisSerializer<>(Student.class);
        // Configure ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // Set the ObjectMapper in the Jackson2JsonRedisSerializer
        serializer.setObjectMapper(objectMapper);


        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

//        //key and value serialization
//        template.setKeySerializer(new StringRedisSerializer());
////        template.setValueSerializer(new StringRedisSerializer());
//        template.setValueSerializer(new GenericToStringSerializer<>(Student.class));


        return  template;
    }


}
