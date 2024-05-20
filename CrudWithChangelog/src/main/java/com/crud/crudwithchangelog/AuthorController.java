package com.crud.crudwithchangelog;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v2/author")
@AllArgsConstructor
public class AuthorController {
    private final AuthorService service;

    @PostMapping
    public ResponseEntity<Author> save(@RequestBody Author author) {

        service.addAuthor(author);
        return ResponseEntity.ok(author);
    }
    @GetMapping
    public List<Author> getAll() {
        return service.getAllAuthors();
    }
    @GetMapping("{id}")
    public Author getAuthorById(@PathVariable Integer id) {
        return service.getAuthorById(id);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Author> deleteAuthorById(@PathVariable Integer id) {
        service.deleteAuthorById(id);
        return ResponseEntity.ok(service.getAuthorById(id));

    }

}
