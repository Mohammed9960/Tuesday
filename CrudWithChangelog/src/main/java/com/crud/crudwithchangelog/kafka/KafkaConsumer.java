package com.crud.crudwithchangelog.kafka;


import com.crud.crudwithchangelog.author.Author;
import com.crud.crudwithchangelog.book.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;



@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "bookTopic",groupId = "hi")
    public void consume(Book book){
        log.info("Book: {}", book);
    }

    @KafkaListener(topics = "authorTopic", groupId = "hi")
    public void consume2(Author author){
        log.info("Author: {}", author);
    }


    }

