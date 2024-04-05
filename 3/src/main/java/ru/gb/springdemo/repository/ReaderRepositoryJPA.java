package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springdemo.model.BookJPA;
import ru.gb.springdemo.model.ReaderJPA;

import java.util.List;

public interface ReaderRepositoryJPA extends JpaRepository<BookJPA, Long> {

    List<ReaderJPA> findAllByName(String name);

    ReaderJPA findReaderJPAById(Long id);

    Void deleteReaderById(Long id);

    ReaderJPA save(ReaderJPA reader);
}
