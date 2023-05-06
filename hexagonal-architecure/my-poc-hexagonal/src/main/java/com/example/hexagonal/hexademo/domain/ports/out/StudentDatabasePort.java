package com.example.hexagonal.hexademo.domain.ports.out;

import com.example.hexagonal.hexademo.domain.Student;

import java.util.List;

public interface StudentDatabasePort {

    List<Student> getAllStudents();
}
