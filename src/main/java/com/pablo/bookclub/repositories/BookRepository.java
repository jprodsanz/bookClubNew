package com.pablo.bookclub.repositories;

import com.pablo.bookclub.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

    // using override will help you with typos or misspells
    // safety check
    @Override
    List<Book> findAll();

}
