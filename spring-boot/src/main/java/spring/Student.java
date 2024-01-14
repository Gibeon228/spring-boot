package spring;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class Student {

    private static Long idCounter = 1L;
    private final Long id;
    private String name;
    private final String groupName;

    public Student(String name, String groupName) {
        this.id = idCounter++;
        this.name = name;
        this.groupName = groupName;
    }
}
