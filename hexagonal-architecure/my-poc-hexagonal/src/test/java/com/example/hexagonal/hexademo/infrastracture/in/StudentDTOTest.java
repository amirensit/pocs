package com.example.hexagonal.hexademo.infrastracture.in;

import com.example.hexagonal.hexademo.domain.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

class StudentDTOTest {

    @Test
    void toDomain() {
        StudentDTO studentDTO = StudentDTO
                .builder()
                .firstName("firstName")
                .lastName("lastName")
                .age(28L)
                .build();
        Student student = StudentDTO.toDomain(studentDTO);
        Assertions.assertThat(student).isNotNull();
        Assertions.assertThat(student.getFirstName()).isEqualTo("firstName");
        Assertions.assertThat(student.getLastName()).isEqualTo("lastName");
        Assertions.assertThat(student.getAge()).isEqualTo(28L);
    }

    @Test
    void toDTO() {
        Student student = Student
                .builder()
                .firstName("firstName")
                .lastName("lastName")
                .age(28L)
                .build();
        StudentDTO studentDTO = StudentDTO.toDTO(student);
        Assertions.assertThat(studentDTO).isNotNull();
        Assertions.assertThat(studentDTO.getFirstName()).isEqualTo("firstName");
        Assertions.assertThat(studentDTO.getLastName()).isEqualTo("lastName");
        Assertions.assertThat(studentDTO.getAge()).isEqualTo(28L);
    }

    @Test
    void toDomains() {
        var studentDTOs = Arrays.asList(
                StudentDTO
                    .builder()
                    .firstName("firstName1")
                    .lastName("lastName1")
                    .age(28L)
                    .build(),
                StudentDTO
                    .builder()
                    .firstName("firstName2")
                    .lastName("lastName2")
                    .age(28L)
                    .build()
        );
        var students = StudentDTO.toDomains(studentDTOs);
        Assertions.assertThat(students).isNotNull();
        Assertions.assertThat(students).hasSize(2);
        Assertions.assertThat(
                students.stream().map(Student::getFirstName).collect(Collectors.toList())
        ).isEqualTo(
                Arrays.asList("firstName1", "firstName2")
        );
        Assertions.assertThat(
                students.stream().map(Student::getLastName).collect(Collectors.toList())
        ).isEqualTo(
                Arrays.asList("lastName1", "lastName2")
        );
    }

    @Test
    void toDTOs() {
        var students = Arrays.asList(
                Student
                        .builder()
                        .firstName("firstName1")
                        .lastName("lastName1")
                        .age(28L)
                        .build(),
                Student
                        .builder()
                        .firstName("firstName2")
                        .lastName("lastName2")
                        .age(28L)
                        .build()
        );
        var studentDTOs = StudentDTO.ToDTOs(students);
        Assertions.assertThat(studentDTOs).isNotNull();
        Assertions.assertThat(studentDTOs).hasSize(2);
        Assertions.assertThat(
                studentDTOs.stream().map(StudentDTO::getFirstName).collect(Collectors.toList())
        ).isEqualTo(
                Arrays.asList("firstName1", "firstName2")
        );
        Assertions.assertThat(
                studentDTOs.stream().map(StudentDTO::getLastName).collect(Collectors.toList())
        ).isEqualTo(
                Arrays.asList("lastName1", "lastName2")
        );
    }
}