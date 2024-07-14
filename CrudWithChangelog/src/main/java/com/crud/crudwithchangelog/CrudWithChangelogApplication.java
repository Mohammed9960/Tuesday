package com.crud.crudwithchangelog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableKafka
public class CrudWithChangelogApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudWithChangelogApplication.class, args);
    }
//    @Bean
//    CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
//        return args -> {
//            kafkaTemplate.send("crud-with-changelog", "Hello World");
//        };
    }


