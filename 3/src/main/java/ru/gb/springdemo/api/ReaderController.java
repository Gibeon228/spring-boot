package ru.gb.springdemo.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/reader")
public class ReaderController {

    private final ReaderRepository repository;
    private final IssueRepository issueRepository;
    @Autowired
    private ReaderService service;

    @GetMapping("/{id}")
    public Reader getReader(@PathVariable Long id) {
        return repository.getReaderById(id);
    }

    @GetMapping("/{id}/issue")
    public List<Issue> getReaderIssue(@PathVariable Long id) {
        return issueRepository.getIssues().stream().filter(it -> Objects.equals(it.getReaderId(), id)).toList();
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        repository.deleteReaderById(id);
    }

    @PostMapping
    public Reader reader(@RequestBody ReaderRequest request) {
        log.info("Получен запрос на добавлении читателя: name = {}", request.getName());
        return service.reader(request);
    }
}
