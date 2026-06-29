package com.app.repository;

import com.app.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository
        extends JpaRepository<Book, Integer> {

    List<Book> findByTitleContainingIgnoreCase(
            String keyword);
}