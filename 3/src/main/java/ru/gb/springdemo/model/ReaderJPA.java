package ru.gb.springdemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "readers")
@Data
public class ReaderJPA {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "issue")
    List<IssueJPA> issueList;
}
