package spring;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository repository;

    @GetMapping("/student/all")
    public List<Student> getStudents() {
        return repository.getAll();
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable Long id) {
        return repository.getById(id);
    }

    @GetMapping
    public Student getStudentByName(@RequestParam String name) {
        return repository.getByName(name);
    }

    @GetMapping("/group/{groupName}/student")
    public List<Student> getStudentsByGroup(@PathVariable String groupName) {
        return repository.getByGroup(groupName);
    }

    @GetMapping("/student/search/%{name}%")
    public List<Student> getSearchStudents(@RequestParam String name) {
        return repository.getSearchByName(name);
    }

    @PostMapping("/student/student/{id}")
    public Student createStudent(@PathVariable Long id, Student student) {
        Student existsStudent = repository.getById(id);
        if(existsStudent != null) {
            throw new IllegalArgumentException();
        }
        repository.addStudent(student);
        return student;
    }

    @DeleteMapping("/delStudent/{id}")
    public void deleteStudent(@PathVariable Long id) {
        Student existsStudent = repository.getById(id);
        if(existsStudent == null) {
            throw new IllegalArgumentException();
        }
        repository.deleteStudent(id);
    }

}
