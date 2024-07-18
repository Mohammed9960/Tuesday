package com.crud.crudwithchangelog.author;

import com.crud.crudwithchangelog.kafka.KafkaProducer;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository repository;
    private final KafkaProducer kafkaProducer;
    private final RedisTemplate<String , Author> redisTemplate;
    private final static String KEY = "author";

    public void addAuthor(Author author){
        repository.save(author);
       redisTemplate.opsForHash().put(KEY, author.getId(), author);
       kafkaProducer.sendAuthor(author);

    }

    public List<Author> getAllAuthors(){
        return repository.findAll();

    }
    public Author getAuthorById(int id) {

        Author author = (Author) redisTemplate.opsForHash().get(KEY, id);
        if (author != null) {
            return author;
        }

        author = repository.findById(id).orElse(null);
        if (author != null) {
            redisTemplate.opsForHash().put(KEY, id, author);
        }
        return author;
    }
    public void deleteAuthorById(int id){
        repository.deleteById(id);
        redisTemplate.opsForHash().delete(KEY, id);
    }
    public List<Author> getAuthorByName(String name) {
        return repository.findByFirstNameContainsIgnoreCase(name);
    }
    }


