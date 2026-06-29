package com.app.controller;

import com.app.entity.Book;
import com.app.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bs;

    @PostMapping
    public Book addBook(
            @RequestBody Book book) {

        return bs.addBook(book);
    }

    @GetMapping
    public List<Book> showBooks() {

        return bs.showBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(
            @PathVariable Integer id) {

        return bs.getBookById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(
            @PathVariable Integer id,
            @RequestBody Book book) {

        return bs.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(
            @PathVariable Integer id) {

        bs.deleteBook(id);
    }
}