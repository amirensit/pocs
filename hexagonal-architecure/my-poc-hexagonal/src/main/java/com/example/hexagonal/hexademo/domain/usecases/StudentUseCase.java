package com.example.hexagonal.hexademo.domain.usecases;

import com.example.hexagonal.hexademo.domain.Student;

import java.util.List;

public interface StudentUseCase {

    public List<Student> getAllStudents();
}
