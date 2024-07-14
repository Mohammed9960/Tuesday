package com.crud.crudwithchangelog.book;

import com.crud.crudwithchangelog.author.Author;
import com.crud.crudwithchangelog.author.AuthorRepository;
import com.crud.crudwithchangelog.kafka.KafkaProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    public final KafkaProducer kafkaProducer;

    public void saveBook(Book book){
        repository.save(book);
        kafkaProducer.sendMessage(book);
    }
    public List<Book> getAllBooks(){
        return repository.findAll();

    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();

    }

    public void addAuthor(Author author){
         authorRepository.save(author);
    }

    public Book getBookById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public Author getAuthorById(Integer id){
        return authorRepository.findById(id).orElse(null);

    }

    public List<Author> getBooksByAuthorName(String  name){

        return authorRepository.findByFirstNameContainsIgnoreCase(name);

    }

    public void deleteBookById(Integer id){
        repository.deleteById(id);
    }

    public void deleteAuthorById(Integer id){
        authorRepository.deleteById(id);
    }

    public void updateBookVersion(Integer id ,Book book){
        Book updatededBook = repository.findById(id).orElse(null);
        if(updatededBook != null){

            updatededBook.setBookVersion(book.getBookVersion());
            repository.save(updatededBook);

        }

    }





    }





