package com.example.hexagonal.hexademo.adapters.in;

import com.example.hexagonal.hexademo.adapters.out.StudentDatabaseAdapter;
import com.example.hexagonal.hexademo.adapters.out.StudentEntity;
import com.example.hexagonal.hexademo.adapters.out.StudentSpringRepository;
import com.example.hexagonal.hexademo.domain.Student;
import com.example.hexagonal.hexademo.domain.ports.in.StudentPort;
import com.example.hexagonal.hexademo.domain.ports.out.StudentDatabasePort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
class StudentAdapterIT {

    @Autowired
    StudentSpringRepository studentSpringRepository;

    @Autowired
    StudentDatabasePort studentDatabasePort;

    @Autowired
    StudentPort studentPort;

    @Test
    @Sql("classpath:config/insert-data-student.sql")
    void getAllStudents() {
        Assertions.assertThat(studentSpringRepository.findAll()).hasSize(1);
        var students = studentPort.getAllStudents();
        Assertions.assertThat(students).isNotNull();
        Assertions.assertThat(students).hasSize(1);
        Assertions.assertThat(students.get(0).getAge()).isEqualTo(28L);
        Assertions.assertThat(students.get(0).getFirstName()).isEqualTo("amir");
        Assertions.assertThat(students.get(0).getLastName()).isEqualTo("choubani");
    }

    @Test
    void should_save_Student() {
        Assertions.assertThat(studentSpringRepository.findAll()).hasSize(0);
        var student = studentPort.save(
                Student.builder()
                        .firstName("amir")
                        .lastName("choubani")
                        .age(28L)
                        .build()
        );
        Assertions.assertThat(studentSpringRepository.findAll()).hasSize(1);
        var expected = new StudentEntity(student.getId(), "amir", "choubani", 28L);
        var actualOptional = studentSpringRepository.findById(student.getId());
        Assertions.assertThat(actualOptional.isPresent()).isTrue();
        var actual = actualOptional.get();
        Assertions.assertThat(expected).isEqualTo(actual);
    }

    @TestConfiguration
    public static class StudentAdapterConfig {

        @Bean
        StudentDatabasePort studentDatabasePort(StudentSpringRepository studentSpringRepository) {
            return new StudentDatabaseAdapter(studentSpringRepository);
        }

        @Bean
        StudentPort studentPort(StudentDatabasePort studentDatabasePort) {
            return new StudentAdapter(studentDatabasePort);
        }

    }
}