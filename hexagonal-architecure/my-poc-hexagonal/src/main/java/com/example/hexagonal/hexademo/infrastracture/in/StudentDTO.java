package com.example.hexagonal.hexademo.infrastracture.in;

import com.example.hexagonal.hexademo.domain.Student;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
public class StudentDTO {

    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final Long age;

    public static Student toDomain(StudentDTO studentDTO) {
        return Student
                .builder()
                .id(studentDTO.getId())
                .firstName(studentDTO.getFirstName())
                .lastName(studentDTO.getLastName())
                .age(studentDTO.getAge())
                .build();
    }

    public static StudentDTO toDTO(Student student) {
        return StudentDTO
                .builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .age(student.getAge())
                .build();
    }

    public static List<Student> toDomains(List<StudentDTO> studentDTOList) {
        return studentDTOList
                .stream()
                .map(StudentDTO::toDomain)
                .collect(Collectors.toList());
    }

    public static List<StudentDTO> ToDTOs(List<Student> studentDTOList) {
        return studentDTOList
                .stream()
                .map(StudentDTO::toDTO)
                .collect(Collectors.toList());
    }
}
