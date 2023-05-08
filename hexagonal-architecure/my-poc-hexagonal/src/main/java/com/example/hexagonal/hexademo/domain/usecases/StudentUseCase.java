package com.example.hexagonal.hexademo.domain.usecases;

import com.example.hexagonal.hexademo.domain.Student;
import com.example.hexagonal.hexademo.infrastracture.in.StudentDTO;

import java.util.List;

public interface StudentUseCase {

    List<Student> getAllStudents();

    Student save(Student student);
}
