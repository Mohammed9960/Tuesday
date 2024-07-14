package com.crud.crudwithchangelog.author;

import com.crud.crudwithchangelog.kafka.KafkaProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository repository;
    private final KafkaProducer kafkaProducer;

    public void addAuthor(Author author){
        repository.save(author);
       kafkaProducer.sendAuthor(author);

    }

    public List<Author> getAllAuthors(){
        return repository.findAll();

    }
    public Author getAuthorById(int id){
        return repository.findById(id).get();
    }
    public void deleteAuthorById(int id){
        repository.deleteById(id);
    }
    public List<Author> getAuthorByName(String name) {
        return repository.findByFirstNameContainsIgnoreCase(name);
    }
    }


