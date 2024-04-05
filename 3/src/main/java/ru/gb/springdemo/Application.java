package ru.gb.springdemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.web.config.SpringDataJacksonConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.StatementCallback;
import ru.gb.springdemo.model.BookJPA;
import ru.gb.springdemo.repository.BookRepositoryJPA;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Application {

	/*
	 * слои spring-приложения
	 *
	 * 1. controllers (api)
	 * 2. сервисный слой (services)
	 * 3. репозитории (repositories, dao (data access objects), ...)
	 * 4. jpa-сущности (entity, model, domain)
	 *
	 *
	 * Сервер, отвечающий за выдачу книг в библиотеке.
	 * Нужно напрограммировать ручку, которая либо выдает книгу читателями, либо отказывает в выдаче.
	 *
	 * /book/** - книга
	 * GET /book/25 - получить книгу с идентификатором 25
	 *
	 * /reader/** - читатель
	 * /issue/** - информация о выдаче
	 * POST /issue {"readerId": 25, "bookId": 57} - выдать читателю с идентификатором 25 книгу с идентификатором 57
	 *
	 *

	/*
			Tomcat - контейнер сервлетов (веб-сервер)

			/student/...
			spring-student.war -> tomcat
			/api/...
			spring-api.war -> tomcat

			spring-web.jar
	 */

    @SneakyThrows
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        BookRepositoryJPA bookRepositoryJPA = context.getBean(BookRepositoryJPA.class);

        BookJPA bookJPA = new BookJPA();
        bookJPA.setId(1L);
        bookJPA.setName("Name1");
        bookRepositoryJPA.save(bookJPA);

        System.out.println(bookRepositoryJPA.findAllByName("Name1"));

        Optional<BookJPA> foundBook = bookRepositoryJPA.findById(1L);
        foundBook.ifPresent(it -> System.out.println(it));

        bookRepositoryJPA.findById(2L).ifPresentOrElse(it -> System.out.println(it), () -> System.out.println("Не найден пользователь с идентификатором 2"));
    }
}
