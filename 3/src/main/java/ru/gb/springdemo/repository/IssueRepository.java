package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@Data
public class IssueRepository {

    private final List<Issue> issues;

    public IssueRepository() {
        this.issues = new ArrayList<>();
    }

    @PostConstruct
    public void generateData() {
        issues.addAll(List.of(
                new Issue(1, 1),
                new Issue(2, 1),
                new Issue(1, 2)
        ));
    }

    public Issue getIssueById(long id) {
        return issues.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }


    public void save(Issue issue) {
        // insert into ....
        issues.add(issue);
    }

    public void closeIssue(Issue issue) {
        for (Issue item : issues) {
            if (item.getBookId() == issue.getBookId() && item.getReaderId() == issue.getReaderId()) {
                item.setReturned_at(issue.getReturned_at());
            }
        }
    }

}
