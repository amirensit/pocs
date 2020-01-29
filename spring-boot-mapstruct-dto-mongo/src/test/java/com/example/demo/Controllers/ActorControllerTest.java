package com.example.demo.Controllers;

import com.example.demo.domain.Actor;
import com.example.demo.services.mapper.ActorMapperTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc // need this in Spring Boot test
public class ActorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /*
    this test is used in controllers level.
     */
    @Test
    public void whenActorCodeInvalid_thenReturnStatus400() throws Exception {
        Actor actor = ActorMapperTest.createActor();
        actor.setActorCode("ae");
        String body = objectMapper.writeValueAsString(actor);

        mockMvc.perform(post("/actors/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                        .andExpect(status().isBadRequest());
    }

}
