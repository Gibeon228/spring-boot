package ru.gb.springdemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "issues")
@Data
public class IssueJPA {

    @Id
    private Long id;

    @Column(name = "bookId")
    private Long bookId;

    @Column(name = "readerId")
    private Long readerId;

    @Column(name = "name")
    private String name;

    @Column(name = "issued_at")
    private LocalDateTime issue_at;

    @Column(name = "return_at")
    private LocalDateTime returned_at;
}