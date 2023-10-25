package com.example.studentserver.Config;

import com.example.studentserver.Entity.Student;
import com.example.studentserver.StudentServer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
@ConditionalOnProperty("app.init")
public class StudentConfig {

    @Bean
    public StudentServer server() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(UUID.randomUUID().toString(), "Jhon", "F", 23));
        students.add(new Student(UUID.randomUUID().toString(), "Vie", "M", 25));
        students.add(new Student(UUID.randomUUID().toString(), "Alice", "Djonson", 30));
        students.add(new Student(UUID.randomUUID().toString(), "Sten", "A", 27));
        return new StudentServer(students);
    }
}
