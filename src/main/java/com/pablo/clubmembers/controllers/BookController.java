package com.pablo.clubmembers.controllers;

import com.pablo.clubmembers.models.Book;
import com.pablo.clubmembers.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookServ;

    @GetMapping("/")
    public String index(Model model) {
        // retrieve all books from DB
        List<Book> allBooks = bookServ.findAll();
        // Store list in model
        model.addAttribute("allBooks", allBooks);
        return "index.jsp";
    }
    // When adding User model, create a constructor for book
    @GetMapping("/book/new")
    // reserve CRUD words for the actual process of transferring data
    // this get route is only rendering the page to the user
    public String newBook(@ModelAttribute("newBook")Book newBook) {
        return "newBook.jsp";
    }
    @PostMapping("/book/new")
    public String createBook(@Valid @ModelAttribute("newBook")Book newBook, BindingResult result) {
        if (result.hasErrors()) {
            return "newBook.jsp";
        }
        bookServ.create(newBook);
        return "redirect:/";
    }

    @GetMapping("/book/{id}/delete")
    public String deleteBook(@PathVariable("id") Long id ) {
        bookServ.delete(id);
        return "redirect:/";
    }
    @GetMapping("/book/{id}")
    public String editBook(@PathVariable("id")Long id, Model model) {
        model.addAttribute("book", bookServ.findOneById(id));
        return "editBook.jsp";
    }
    @PutMapping("/book/{id}")
    // this has to come in this order valid, modelAtt etc...
    public String updateBook(
            @Valid @ModelAttribute ("book") Book book, BindingResult result, @PathVariable("id") Long id)  {
        if (result.hasErrors()) {
            return "editBook.jsp";
        }
        book.setId(id);
        bookServ.update(book);
        return "redirect:/";
    }
    @GetMapping("/book/{id}/return")
    public String returnBook(@PathVariable("id")Long id) {
        Book book  = bookServ.findOneById(id);
        book.setMember(null);
        bookServ.update(book);
        return "redirect:/member/{id}";
    }


}
