package com.example.hexagonal.hexademo.domain.ports.out;

import com.example.hexagonal.hexademo.adapters.out.StudentDatabaseAdapter;
import com.example.hexagonal.hexademo.adapters.out.StudentEntity;
import com.example.hexagonal.hexademo.adapters.out.StudentSpringRepository;
import com.example.hexagonal.hexademo.domain.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class StudentDatabasePortTest {

    @Mock
    private StudentSpringRepository studentSpringRepository;

    @Captor
    private ArgumentCaptor<StudentEntity> studentEntityArgumentCaptor;

    private StudentDatabasePort studentDatabasePort;

    @BeforeEach
    void setUp() {
        studentDatabasePort = new StudentDatabaseAdapter(studentSpringRepository);
    }

    @Test
    void getAllStudents() {
        // GIVEN
        BDDMockito.given(studentSpringRepository.findAll())
                .willReturn(
                        Arrays.asList(
                                StudentEntity
                                        .builder()
                                        .age(28L)
                                        .firstName("firstName-1")
                                        .lastName("lastName-1")
                                        .build(),
                                StudentEntity
                                        .builder()
                                        .age(28L)
                                        .firstName("firstName-2")
                                        .lastName("lastName-2")
                                        .build()
                        )
                );

        // WHEN
        List<Student> students = studentDatabasePort.getAllStudents();
        Assertions.assertThat(students).isNotNull();
        Assertions.assertThat(students).hasSize(2);
        Assertions
                .assertThat(students
                        .stream()
                        .map(Student::getAge)
                        .collect(Collectors.toList())
                )
                .isEqualTo(List.of(28L, 28L));
        Assertions
                .assertThat(students
                        .stream()
                        .map(Student::getFirstName)
                        .collect(Collectors.toList())
                )
                .isEqualTo(List.of("firstName-1", "firstName-2"));
        Assertions
                .assertThat(students
                        .stream()
                        .map(Student::getLastName)
                        .collect(Collectors.toList())
                )
                .isEqualTo(List.of("lastName-1", "lastName-2"));
    }

    @Test
    void save() {
        // GIVEN
        Student student = Student
                .builder()
                .firstName("firstName")
                .build();
        StudentEntity studentEntityInput = StudentEntity.toEntity(student);
        BDDMockito.given(studentSpringRepository.save(eq(studentEntityInput)))
                .willReturn(studentEntityInput);
        // WHEN
        student = studentDatabasePort.save(student);

        // THEN
        verify(studentSpringRepository, times(1))
                .save(studentEntityArgumentCaptor.capture());
        Assertions.assertThat(student).isNotNull();
        StudentEntity studentEntityCaptorValue = studentEntityArgumentCaptor.getValue();
        Assertions.assertThat(studentEntityCaptorValue.getFirstName()).isEqualTo("firstName");
    }
}