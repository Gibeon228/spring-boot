package spring;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class StudentRepository {

    private final List<Student> students;

    public StudentRepository(){
        this.students = new ArrayList<>();
        students.add(new Student("student1", "group"));
        students.add(new Student("student2", "group"));
        students.add(new Student("student3", "group"));
        students.add(new Student("student4", "group4"));
        students.add(new Student("student5", "group5"));
    }


    public Student addStudent(Student student) {
        students.add(student);
        return student;
    }

    public void deleteStudent(Long id) {
        students.removeIf(it -> it.getId().equals(id));
    }
    public List<Student> getAll() {
        return List.copyOf(students);
    }

    public List<Student> getByGroup(String groupName) {
        return students.stream().filter(it -> Objects.equals(it.getGroupName(), groupName)).toList();
    }


    public Student getById(Long id) {
        return students.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public Student getByName(String name) {
        return students.stream().filter(it -> Objects.equals(it.getName(), name))
                .findFirst()
                .orElse(null);
    }

    public List<Student> getSearchByName(String name) {
        return students.stream().filter(it -> Objects.equals(it.getName(), name)).toList();
    }
}
