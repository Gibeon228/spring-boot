package ru.gb.springdemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "readers")
@Data
public class ReaderJPA {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

//    @Column(name = "issueList")
//    List<IssueJPA> issueList;
}
