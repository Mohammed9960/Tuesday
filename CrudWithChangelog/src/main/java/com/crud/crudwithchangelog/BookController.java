package com.crud.crudwithchangelog;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/v1/library")
@AllArgsConstructor
public class BookController {

    private final BookService service;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        service.saveBook(book);
        return ResponseEntity.ok(book);

    }
    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id){
        service.getBookById(id);
        return ResponseEntity.ok(service.getBookById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable("id") Integer id) {
        service.deleteBookById(id);
        return ResponseEntity.ok(service.getBookById(id));
    }





    @PutMapping("{id}/update")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Integer id,@RequestBody Book book) {

        service.updateBookVersion(id,book);
        return ResponseEntity.ok(service.getBookById(id));
    }

}
