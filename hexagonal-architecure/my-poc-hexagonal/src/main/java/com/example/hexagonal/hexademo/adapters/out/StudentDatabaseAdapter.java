package com.example.hexagonal.hexademo.adapters.out;

import com.example.hexagonal.hexademo.domain.Student;
import com.example.hexagonal.hexademo.domain.ports.out.StudentDatabasePort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentDatabaseAdapter implements StudentDatabasePort {

    private final StudentSpringRepository studentSpringRepository;

    public StudentDatabaseAdapter(StudentSpringRepository studentSpringRepository) {
        this.studentSpringRepository = studentSpringRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentSpringRepository
                .findAll()
                .stream()
                .map(StudentEntity::toDomain)
                .collect(Collectors.toList());
    }
}
