package ru.gb.springdemo.api;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Dto;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;
import ru.gb.springdemo.service.IssuerService;

import javax.tools.DocumentationTool;
import java.util.List;
import java.util.Objects;

/**
 * 1. В предыдущий проект (где была библиотека с книгами, выдачами и читателями) добавить следующие рерурсы,
 * которые возвращают готовые HTML-страницы (т.е. это просто Controller'ы):
 * 1.1 /ui/books - на странице должен отобразиться список всех доступных книг в системе
 * 1.2 /ui/reader - аналогично 1.1
 * 1.3 /ui/issues - на странице отображается таблица, в которой есть столбцы (книга, читатель, когда взял, когда вернул (если не вернул - пустая ячейка)).
 * 1.4* /ui/reader/{id} - страница, где написано имя читателя с идентификатором id и перечислены книги, которые на руках у этого читателя
**/
@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller {

    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    @Autowired
    private IssuerService service;


    @GetMapping("ui/books")
    public String books(Model model) {
        List<Book> list = bookRepository.getAll();
        model.addAttribute("list", list);
        return "list";
    }

    @GetMapping("ui/readers")
    public String readers(Model model) {
        List<Reader> list = readerRepository.getAll();
        model.addAttribute("list", list);
        return "list";
    }

//    @GetMapping("ui/issue")
//    public String table(Model model) {
//        List<Dto> list = service.transformation();
//        model.addAttribute("list", list);
//        return "table";
//    }

    @GetMapping("ui/reader/{id}")
    public String booksFindReader(@PathVariable long id, Model model) {
        List<Issue> list = issueRepository.getIssues().stream().filter(it -> Objects.equals(it.getReaderId(), id)).toList();
        for (Issue issue : list) {
            System.out.println(issue);
        }
        model.addAttribute("list", list);
        return "list";
    }

}
