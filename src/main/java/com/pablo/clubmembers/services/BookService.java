package com.pablo.clubmembers.services;


import com.pablo.clubmembers.models.Book;
import com.pablo.clubmembers.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    //    private final BookRepository bookRepo;
//    public BookService(BookRepository bookRepo) {
//        this.bookRepo = bookRepo;
//    }
    @Autowired
    private BookRepository bookRepo;

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public Book create(Book b) {
        return bookRepo.save(b);
    }

    public void delete(Long id) {
        bookRepo.deleteById(id);
    }

    public Book findOneById(Long id) {
        // optional is all about SQL
        Optional<Book> optionalBook = bookRepo.findById(id);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        }
        else {
            return null;
        }
    }
    // same functionality as create method
    public Book update(Book b){
        return bookRepo.save(b);
    }
}

