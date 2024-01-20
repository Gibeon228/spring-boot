package ru.gb.springdemo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;



@RequiredArgsConstructor
@Data
public class Dto {

    private String nameBook;
    private String nameReader;
    private LocalDateTime issued_at;
    private LocalDateTime returned_at;


    public Dto(String nameBook, String nameReader, LocalDateTime issued_at, LocalDateTime returned_at) {
        this.nameBook = nameBook;
        this.nameReader = nameReader;
        this.issued_at = issued_at;
        this.returned_at = returned_at;
    }
}
