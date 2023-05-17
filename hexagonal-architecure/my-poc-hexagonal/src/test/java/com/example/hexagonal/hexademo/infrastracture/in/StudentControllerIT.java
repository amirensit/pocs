package com.example.hexagonal.hexademo.infrastracture.in;

import com.example.hexagonal.hexademo.domain.Student;
import com.example.hexagonal.hexademo.domain.usecases.StudentUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class StudentControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    StudentUseCase studentUseCase;

    @Test
    void should_return_all_students() throws Exception {
        var student = Student
                .builder()
                .firstName("firstName")
                .lastName("lastName")
                .age(28L)
                .build();

        studentUseCase.save(student);
        mockMvc
                .perform(get("/api/students/get-all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].firstName").value("firstName"))
                .andExpect(jsonPath("$[0].lastName").value("lastName"))
                .andExpect(jsonPath("$[0].age").value(28L));
    }

    @Test
    void should_add_student() throws Exception {
        Student student = Student
                .builder()
                .firstName("firstName")
                .lastName("lastName")
                .build();

        mockMvc
                .perform(
                        post("/api/students/add")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("firstName"))
                .andExpect(jsonPath("$.lastName").value("lastName"));
        Assertions.assertThat(studentUseCase.getAllStudents()).hasSize(1);
    }

    @Test
    void edit() {
    }
}