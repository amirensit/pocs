package com.example.hexagonal.hexademo.domain.ports.in;

import com.example.hexagonal.hexademo.domain.Student;

import java.util.List;

public interface StudentPort {
    List<Student> getAllStudents();

    Student save(Student student);
}
