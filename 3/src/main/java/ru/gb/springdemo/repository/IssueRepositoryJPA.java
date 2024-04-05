package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springdemo.model.BookJPA;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.IssueJPA;

import java.util.List;

public interface IssueRepositoryJPA extends JpaRepository<BookJPA, Long> {

    void closeIssue(IssueJPA issue);

    IssueJPA findIssueJPAById(Long id);

    IssueJPA save(IssueJPA issue);
}
