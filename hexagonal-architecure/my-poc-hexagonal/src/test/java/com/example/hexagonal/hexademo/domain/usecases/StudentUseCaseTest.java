package com.example.hexagonal.hexademo.domain.usecases;

import com.example.hexagonal.hexademo.application.StudentUseCaseImpl;
import com.example.hexagonal.hexademo.domain.Student;
import com.example.hexagonal.hexademo.domain.ports.in.StudentPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class StudentUseCaseTest {

    @Mock
    private StudentPort studentPort;

    @Captor
    ArgumentCaptor<Student> studentCaptor;

    private StudentUseCase studentUseCase;

    @BeforeEach
    void setUp() {
        studentUseCase = new StudentUseCaseImpl(studentPort);
    }

    @Test
    void getAllStudentsTest() {
        // GIVEN
        BDDMockito
                .given(studentPort.getAllStudents())
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
        List<Student> students = studentUseCase.getAllStudents();

        // THEN
        verify(studentPort, times(1)).getAllStudents();
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
    void saveStudentTest() {
        // GIVEN
        Student studentInput = Student
                .builder()
                .firstName("firstName")
                .build();
        BDDMockito.given(studentPort.save(eq(studentInput)))
                .willReturn(studentInput);
        // WHEN
        Student student = studentUseCase.save(studentInput);

        // THEN
        verify(studentPort, times(1)).save(studentCaptor.capture());
        Assertions.assertThat(student).isNotNull();
        Student studentCaptorValue = studentCaptor.getValue();
        Assertions.assertThat(studentCaptorValue.getFirstName()).isEqualTo("firstName");

    }
}