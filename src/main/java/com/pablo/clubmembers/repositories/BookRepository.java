package com.pablo.clubmembers.repositories;

import com.pablo.clubmembers.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    // using override will help you with typos or misspells
    // safety check
    @Override
    List<Book> findAll();

}
