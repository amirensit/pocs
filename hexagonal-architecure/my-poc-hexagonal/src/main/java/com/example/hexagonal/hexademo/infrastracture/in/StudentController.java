package com.example.hexagonal.hexademo.infrastracture.in;

import com.example.hexagonal.hexademo.domain.Student;
import com.example.hexagonal.hexademo.domain.usecases.StudentUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentUseCase studentUseCase;

    public StudentController(StudentUseCase studentUseCase) {
        this.studentUseCase = studentUseCase;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<StudentDTO>> getAll() {
        List<Student> studentList = studentUseCase.getAllStudents();
        return ResponseEntity.ok().body(StudentDTO.ToDTOs(studentList));
    }


    @PostMapping("/add")
    public ResponseEntity<StudentDTO> add(@RequestBody StudentDTO studentDTO) {
        Student student = StudentDTO.toDomain(studentDTO);
        Student result = studentUseCase.save(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(StudentDTO.ToDTO(result));
    }
     @PutMapping("/edit/{id}")
    public ResponseEntity<StudentDTO> edit(@PathVariable("id")UUID id, @RequestBody StudentDTO studentDTO) {
        // TODO: exception handling will be treated soon.
        Student student = StudentDTO.toDomain(studentDTO);
        Student result = studentUseCase.save(student);
         return ResponseEntity
                 .ok()
                 .body(StudentDTO.ToDTO(result));
     }
}
