package ru.gb.springdemo.api;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Запрос на выдачу
 */
@Data
public class IssueRequest {

  /**
   * Идентификатор читателя
   */
  private long readerId;

  /**
   * Идентификатор книги
   */
  private long bookId;


  private LocalDateTime returned_at;



}
