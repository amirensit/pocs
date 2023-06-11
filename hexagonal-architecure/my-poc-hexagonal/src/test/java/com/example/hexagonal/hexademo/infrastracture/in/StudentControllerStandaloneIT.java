package com.example.hexagonal.hexademo.infrastracture.in;

import com.example.hexagonal.hexademo.domain.usecases.StudentUseCase;
import com.example.hexagonal.hexademo.errors.infrastructure.PocExceptionHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class StudentControllerStandaloneIT {

    @Autowired
    private StudentUseCase studentUseCase;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @BeforeEach
    public void setup() {
        // Create an instance of your controller
        StudentController studentController = new StudentController(studentUseCase);

        // Set up MockMvc with standaloneSetup
        mockMvc = MockMvcBuilders
                .standaloneSetup(studentController)
                .setControllerAdvice(new PocExceptionHandler())
                .setMessageConverters(jacksonMessageConverter)
                .build();
    }

    @Test
    void givenNullAttribute_when_add_should_throw_exception() throws Exception {
        StudentDTO studentDTO = StudentDTO
                .builder()
                .firstName(null)
                .build();
        MvcResult mvcResult = mockMvc
                .perform(
                    post("/api/students/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(studentDTO))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andReturn();
        JsonNode resultJsonNode = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), JsonNode.class);
        Assertions.assertThat(resultJsonNode.get("errors"))
                .isEqualTo(objectMapper.createObjectNode().put("firstName", "firstName.null"));
    }

    @Test
    void givenEmptyAttribute_when_add_should_throw_exception() throws Exception {
        StudentDTO studentDTO = StudentDTO
                .builder()
                .firstName("")
                .build();
        MvcResult mvcResult = mockMvc
                .perform(
                        post("/api/students/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(studentDTO))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andReturn();
        JsonNode resultJsonNode = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), JsonNode.class);
        Assertions.assertThat(resultJsonNode.get("errors"))
                .isEqualTo(objectMapper.createObjectNode().put("firstName", "firstName.blank"));
    }

    @Test
    void givenWhiteSpacedAttribute_when_add_should_throw_exception() throws Exception {
        StudentDTO studentDTO = StudentDTO
                .builder()
                .firstName("  ")
                .build();
        MvcResult mvcResult = mockMvc
                .perform(
                        post("/api/students/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(studentDTO))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andReturn();
        JsonNode resultJsonNode = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), JsonNode.class);
        Assertions.assertThat(resultJsonNode.get("errors"))
                .isEqualTo(objectMapper.createObjectNode().put("firstName", "firstName.blank"));
    }

}