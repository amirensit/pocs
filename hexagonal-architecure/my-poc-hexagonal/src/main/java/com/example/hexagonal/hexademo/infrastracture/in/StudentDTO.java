package com.example.hexagonal.hexademo.infrastracture.in;

import com.example.hexagonal.hexademo.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class StudentDTO {

    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final Long age;

    public static Student to(StudentDTO studentDTO) {
        return Student
                .builder()
                .id(studentDTO.getId())
                .firstName(studentDTO.getFirstName())
                .lastName(studentDTO.getLastName())
                .age(studentDTO.getAge())
                .build();
    }

    public static StudentDTO from(Student student) {
        return StudentDTO
                .builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .age(student.getAge())
                .build();
    }

    public static List<Student> to(List<StudentDTO> studentDTOList) {
        return studentDTOList
                .stream()
                .map(StudentDTO::to)
                .collect(Collectors.toList());
    }

    public static List<StudentDTO> from(List<Student> studentDTOList) {
        return studentDTOList
                .stream()
                .map(StudentDTO::from)
                .collect(Collectors.toList());
    }
}
