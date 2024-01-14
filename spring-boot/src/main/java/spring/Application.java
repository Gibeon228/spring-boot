package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 3. Создать контроллер, обрабатывающий входящие запросы:
 * 3.3 GET /student/search?name='studentName' - получить список студентов, чье имя содержит подстроку studentName
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
