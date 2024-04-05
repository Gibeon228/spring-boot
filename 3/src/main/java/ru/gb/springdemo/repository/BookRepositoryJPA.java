package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.BookJPA;

import java.util.List;
import java.util.Objects;

public interface BookRepositoryJPA extends JpaRepository<BookJPA, Long> {

    List<BookJPA> findAllByName(String name);

    BookJPA findBookJPAById(Long id);

    Void deleteBookById(Long id);

    BookJPA save(BookJPA bookJPA);
}
