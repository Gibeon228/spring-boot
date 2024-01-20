package ru.gb.springdemo.api;

import lombok.Data;

@Data
public class BookRequest {

    /**
     * Название книги
     */
    private String name;
}
