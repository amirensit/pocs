package com.example.hexagonal.hexademo.adapters.out;

import com.example.hexagonal.hexademo.domain.Student;
import com.example.hexagonal.hexademo.domain.ports.out.StudentDatabasePort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
class StudentDatabaseAdapterIT {
    
    @Autowired
    StudentSpringRepository studentSpringRepository;
    
    @Autowired
    StudentDatabasePort studentDatabasePort;

    @Test
    @Sql("classpath:config/insert-data-student.sql")
    void should_return_all_students() {
        Assertions.assertThat(studentSpringRepository.findAll()).hasSize(1);
        var students = studentDatabasePort.getAllStudents();
        Assertions.assertThat(students).isNotNull();
        Assertions.assertThat(students).hasSize(1);
        Assertions.assertThat(students.get(0).getAge()).isEqualTo(28L);
        Assertions.assertThat(students.get(0).getFirstName()).isEqualTo("amir");
        Assertions.assertThat(students.get(0).getLastName()).isEqualTo("choubani");
    }

    @Test
    void should_save_student() {
        Assertions.assertThat(studentSpringRepository.findAll()).hasSize(0);
        var student = studentDatabasePort.save(
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
    public static class StudentDatabaseAdapterConfig {

        @Bean
        StudentDatabasePort studentDatabasePort(StudentSpringRepository studentSpringRepository) {
            return new StudentDatabaseAdapter(studentSpringRepository);
        }

    }
}