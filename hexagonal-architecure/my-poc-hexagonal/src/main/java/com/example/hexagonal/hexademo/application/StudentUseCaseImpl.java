package com.example.hexagonal.hexademo.application;

import com.example.hexagonal.hexademo.domain.Student;
import com.example.hexagonal.hexademo.domain.ports.in.StudentPort;
import com.example.hexagonal.hexademo.domain.usecases.StudentUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentUseCaseImpl implements StudentUseCase {

    private final StudentPort studentPort;

    public StudentUseCaseImpl(StudentPort studentPort) {
        this.studentPort = studentPort;
    }
    @Override
    public List<Student> getAllStudents() {
        return studentPort.getAllStudents();
    }
}
