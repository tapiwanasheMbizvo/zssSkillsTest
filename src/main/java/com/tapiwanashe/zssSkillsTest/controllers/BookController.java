package com.tapiwanashe.zssSkillsTest.controllers;

import com.tapiwanashe.zssSkillsTest.models.Book;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.tapiwanashe.zssSkillsTest.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired

    private BookRepository bookRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<Book> getAllBooks (){

        return  bookRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Optional<Book> getOneBook(@PathVariable (name = "id") Long id){


        return  bookRepository.findById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    private Book saveBook(@RequestBody Book book){

        return  bookRepository.save(book);

    }
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Book update (@PathVariable("id") Long id, @RequestBody Book book){

        return bookRepository.findById(id)
                .map(theBook->{

                    theBook.setPrice(book.getPrice());
                    theBook.setDescription(book.getDescription());
                    theBook.setCategory(book.getCategory());
                    return  bookRepository.save(book);

                }).orElseGet(()->{

                    book.setId(id);

                    return  bookRepository.save(book);

                });
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    private void delete(@PathVariable (name = "id") Long id){


        bookRepository.deleteById(id);

    }
}
