package com.app.service;

import com.app.entity.Book;
import com.app.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository br;

    public Book addBook(Book book) {

        book.setStatus("AVAILABLE");

        return br.save(book);
    }

    public List<Book> showBooks() {

        return br.findAll();
    }

    public Book getBookById(Integer id) {

        return br.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Book Not Found"));
    }

    public void deleteBook(Integer id) {

        br.deleteById(id);
    }

    public Book updateBook(
            Integer id,
            Book newBook) {

        Book oldBook = getBookById(id);

        oldBook.setTitle(newBook.getTitle());
        oldBook.setAuthor(newBook.getAuthor());
        oldBook.setPrice(newBook.getPrice());

        return br.save(oldBook);
    }

    public List<Book> searchBook(String keyword) {

        return br.findByTitleContainingIgnoreCase(keyword);
    }

    public Book issueBook(Integer id) {

        Book book = getBookById(id);

        book.setStatus("ISSUED");

        return br.save(book);
    }

    public Book returnBook(Integer id) {

        Book book = getBookById(id);

        book.setStatus("AVAILABLE");

        return br.save(book);
    }
}