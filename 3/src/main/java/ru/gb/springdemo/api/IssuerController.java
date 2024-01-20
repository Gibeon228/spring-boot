package ru.gb.springdemo.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.service.IssuerService;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/issue")
public class IssuerController {

    private final IssueRepository repository;
    @Autowired
    private IssuerService service;

//  @PutMapping
//  public void returnBook(long issueId) {
//    // найти в репозитории выдачу и проставить ей returned_at
//  }

    @GetMapping("/{id}")
    public Issue getIssue(@PathVariable Long id) {
        return repository.getIssueById(id);
    }


    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
        log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

        final Issue issue;
        try {
            issue = service.issue(request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(issue);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Issue> closeIssue(@RequestBody IssueRequest request) {
        LocalDateTime timeClose = LocalDateTime.now();
        log.info("Получен запрос на сдачу книги: readerId = {}, bookId = {}, returned_at = {}", request.getReaderId(), request.getBookId(), timeClose);

        final Issue issue;
        try {
            issue = service.closeIssue(request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(issue);
    }

}


