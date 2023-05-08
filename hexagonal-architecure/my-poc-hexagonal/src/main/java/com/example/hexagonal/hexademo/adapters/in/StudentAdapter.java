package com.example.hexagonal.hexademo.adapters.in;

import com.example.hexagonal.hexademo.adapters.out.StudentEntity;
import com.example.hexagonal.hexademo.adapters.out.StudentSpringRepository;
import com.example.hexagonal.hexademo.domain.Student;
import com.example.hexagonal.hexademo.domain.ports.in.StudentPort;
import com.example.hexagonal.hexademo.domain.ports.out.StudentDatabasePort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentAdapter implements StudentPort {

    private final StudentDatabasePort studentDatabasePort;

    public StudentAdapter(StudentDatabasePort studentDatabasePort) {
        this.studentDatabasePort = studentDatabasePort;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDatabasePort.getAllStudents();
    }

    @Override
    public Student save(Student student) {
        return studentDatabasePort.save(student);
    }
}
