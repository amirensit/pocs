package com.example.hexagonal.hexademo.infrastracture.in;

import com.example.hexagonal.hexademo.domain.Student;
import com.example.hexagonal.hexademo.domain.usecases.StudentUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentUseCase studentUseCase;

    public StudentController(StudentUseCase studentUseCase) {
        this.studentUseCase = studentUseCase;
    }

    @RequestMapping("/get-all")
    public ResponseEntity<List<StudentDTO>> getAll() {
        List<Student> studentList = studentUseCase.getAllStudents();
        return ResponseEntity.ok().body(StudentDTO.from(studentList));
    }
}
