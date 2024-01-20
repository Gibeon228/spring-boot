package ru.gb.springdemo.api;

import lombok.Data;

@Data
public class ReaderRequest {

    /**
     * Имя читателя
     */
    private String name;
}
