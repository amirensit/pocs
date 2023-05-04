package com.example.hexagonal.hexademo.adapters.in;

import com.example.hexagonal.hexademo.adapters.out.StudentEntity;
import com.example.hexagonal.hexademo.adapters.out.StudentSpringRepository;
import com.example.hexagonal.hexademo.domain.Student;
import com.example.hexagonal.hexademo.domain.ports.in.StudentPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentAdapter implements StudentPort {

    private final StudentSpringRepository studentSpringRepository;

    public StudentAdapter(StudentSpringRepository studentSpringRepository) {
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
