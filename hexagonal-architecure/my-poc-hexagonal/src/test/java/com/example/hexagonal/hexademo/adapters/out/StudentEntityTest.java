package com.example.hexagonal.hexademo.adapters.out;

import com.example.hexagonal.hexademo.domain.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentEntityTest {

    @Test
    void toEntity() {
        Student student = Student
                .builder()
                .firstName("firstName")
                .lastName("lastName")
                .age(28L)
                .build();
        StudentEntity studentEntity = StudentEntity.toEntity(student);
        Assertions.assertThat(studentEntity).isNotNull();
        Assertions.assertThat(studentEntity.getFirstName()).isEqualTo("firstName");
        Assertions.assertThat(studentEntity.getLastName()).isEqualTo("lastName");
        Assertions.assertThat(studentEntity.getAge()).isEqualTo(28L);
    }

    @Test
    void toDomain() {
        StudentEntity studentEntity = StudentEntity
                .builder()
                .firstName("firstName")
                .lastName("lastName")
                .age(28L)
                .build();
        Student student = StudentEntity.toDomain(studentEntity);
        Assertions.assertThat(student).isNotNull();
        Assertions.assertThat(student.getFirstName()).isEqualTo("firstName");
        Assertions.assertThat(student.getLastName()).isEqualTo("lastName");
        Assertions.assertThat(student.getAge()).isEqualTo(28L);
    }
}