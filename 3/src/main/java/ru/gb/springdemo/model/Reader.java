package ru.gb.springdemo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Reader {

  public static long sequence = 1L;

  List<Issue> issueList;
  private final long id;
  private final String name;

  public Reader(String name) {
    this(sequence++, name);
  }

}
