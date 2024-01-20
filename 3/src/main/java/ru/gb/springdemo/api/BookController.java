package ru.gb.springdemo.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.service.BookService;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookRepository repository;
    @Autowired
    private BookService service;

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return repository.getBookById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        repository.deleteBookById(id);
    }

    @PostMapping
    public Book book(@RequestBody BookRequest request) {
        log.info("Получен запрос на добавлении книги: name = {}", request.getName());
        return service.book(request);
}
}
