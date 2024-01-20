package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.BookRequest;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;


import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;

  public Book book(BookRequest request) {
    Book book = new Book(request.getName());
    bookRepository.save(book);
    return book;
  }

}
