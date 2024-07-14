package com.crud.crudwithchangelog.kafka;


import com.crud.crudwithchangelog.author.Author;
import com.crud.crudwithchangelog.book.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class KafkaProducer {


    private final KafkaTemplate<String, Book> kafkaTemplate;
    private final KafkaTemplate<String, Author> kafkaTemplateAuthor;

    public void sendMessage(Book book) {
        Message<Book> bookMessage = MessageBuilder
                .withPayload(book)
                .setHeader(KafkaHeaders.TOPIC, "bookTopic").
                build();
        kafkaTemplate.send(bookMessage);

    }
  public void sendAuthor(Author author) {
      Message<Author> authorMessage = MessageBuilder
                .withPayload(author)
                .setHeader(KafkaHeaders.TOPIC ,"authorTopic").
                build();
       kafkaTemplateAuthor.send(authorMessage);


    }
}