package com.example.hexagonal.hexademo.domain.ports.in;

import com.example.hexagonal.hexademo.adapters.in.StudentAdapter;
import com.example.hexagonal.hexademo.domain.Student;
import com.example.hexagonal.hexademo.domain.ports.out.StudentDatabasePort;
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
class StudentPortTest {

    @Mock
    private StudentDatabasePort studentDatabasePort;

    @Captor
    ArgumentCaptor<Student> studentCaptor;

    private StudentPort studentPort;

    @BeforeEach
    void setUp() {
        studentPort = new StudentAdapter(studentDatabasePort);
    }

    @Test
    void getAllStudents() {
        // GIVEN
        BDDMockito.given(studentDatabasePort.getAllStudents())
                .willReturn(
                        Arrays.asList(
                                Student
                                        .builder()
                                        .firstName("firstName-1")
                                        .lastName("lastName-1")
                                        .build(),
                                Student
                                        .builder()
                                        .firstName("firstName-2")
                                        .lastName("lastName-2")
                                        .build()
                        )
                );

        // WHEN
        List<Student> students = studentPort.getAllStudents();

        // THEN
        verify(studentDatabasePort, times(1)).getAllStudents();
        Assertions.assertThat(students).hasSize(2);
        Assertions
                .assertThat(students
                        .stream()
                        .map(Student::getFirstName)
                        .collect(Collectors.toList())
                )
                .containsExactlyInAnyOrder("firstName-1", "firstName-2");
        Assertions
                .assertThat(students
                        .stream()
                        .map(Student::getLastName)
                        .collect(Collectors.toList())
                )
                .containsExactlyInAnyOrder("lastName-1", "lastName-2");
    }

    @Test
    void save() {
        // GIVEN
        Student studentInput = Student
                .builder()
                .firstName("firstName")
                .build();
        BDDMockito.given(studentDatabasePort.save(eq(studentInput)))
                .willReturn(studentInput);
        // WHEN
        Student student = studentPort.save(studentInput);

        // THEN
        verify(studentDatabasePort, times(1)).save(studentCaptor.capture());
        Assertions.assertThat(student).isNotNull();
        Student studentCaptorValue = studentCaptor.getValue();
        Assertions.assertThat(studentCaptorValue.getFirstName()).isEqualTo("firstName");
    }
}