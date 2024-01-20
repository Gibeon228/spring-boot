package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.IssueRequest;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import javax.naming.LimitExceededException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IssuerService {

    @Value("${application.max-allowed-books}")
    private int maxAllowedBooks;

    // спринг это все заинжектит
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public Issue issue(IssueRequest request) {
        if (bookRepository.getBookById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerRepository.getReaderById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }
        if (issueRepository.getIssues().stream()
                .filter(it -> Objects.equals(it.getReaderId(), request.getReaderId()))
                .filter(it -> it.getReturned_at() == null).count() >= maxAllowedBooks) {
            throw new RuntimeException("Достигнуто максимальное количество допустимых выдачей книг");
        }
        Issue issue = new Issue(request.getBookId(), request.getReaderId());
        issueRepository.save(issue);
        return issue;
    }

    public Issue closeIssue(IssueRequest request) {
        if (bookRepository.getBookById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerRepository.getReaderById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }

        for (Issue issue : issueRepository.getIssues()) {
            if (issue.getBookId() == request.getBookId() && issue.getReaderId() == request.getReaderId()) {
                if (issue.getReturned_at() == null) {
                    Issue newIssue = new Issue(request.getBookId(), request.getReaderId());
                    newIssue.setReturned_at(LocalDateTime.now());
                    issueRepository.closeIssue(newIssue);
                    return newIssue;
                }
                throw new RuntimeException("Данная книга уже сдана");
            }
        }
        throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\", который брал книгу с идентификатором \"" + request.getBookId() + "\"");
    }
}
