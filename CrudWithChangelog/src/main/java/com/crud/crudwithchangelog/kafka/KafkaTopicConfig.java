package com.crud.crudwithchangelog.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;



@Configuration
public class KafkaTopicConfig {


    @Bean
    public NewTopic mohammedTopic(){
        return TopicBuilder
                .name("bookTopic")
                .build();
    }

    @Bean
    public NewTopic authorTopic(){
        return TopicBuilder
                .name("authorTopic")
                .build();
    }

    }



