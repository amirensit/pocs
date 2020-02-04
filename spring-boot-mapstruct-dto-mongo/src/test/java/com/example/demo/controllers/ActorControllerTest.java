package com.example.demo.controllers;

import com.example.demo.domain.Actor;
import com.example.demo.services.dto.ActorDTO;
import com.example.demo.services.mapper.ActorMapper;
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

    @Autowired
    private ActorMapper actorMapper;

   /* @Autowired
    private ActorService actorService;

    @Autowired
    private ErrorHandlingControllerAdvice errorHandlingControllerAdvice;*/

    /*@BeforeEach
    public void setup() {

        final ActorController actorController = new ActorController(actorService);
        mockMvc = MockMvcBuilders.standaloneSetup(actorController)
                .setControllerAdvice(errorHandlingControllerAdvice).build();

    }*/

    /*
    this test is used in controllers level.
     */
    @Test
    public void whenActorCodeInvalid_thenReturnStatus400() throws Exception {
        Actor actor = ActorMapperTest.createActor();
        actor.setActorCode("ae");

        ActorDTO actorDTO = actorMapper.toDTO(actor);

        String body = objectMapper.writeValueAsString(actorDTO);

        mockMvc.perform(post("/actors/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                        .andExpect(status().isBadRequest());
    }

    @Test
    public void whenIpAddressInvalid_thenReturnStatus400() throws Exception {
        /*Throwable exception = assertThrows(Exception.class, () -> {
            Actor actor = ActorMapperTest.createActor();
            actor.setIpAddress("333.333.333..2251");
            String body = objectMapper.writeValueAsString(actor);
            mockMvc.perform(post("/actors/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                    .andExpect(status().isBadRequest());
        });
        assertEquals(exception.getMessage(), "IpAddress.invalid");*/

        Actor actor = ActorMapperTest.createActor();
        actor.setIpAddress("333.333.333..2251");
        String body = objectMapper.writeValueAsString(actor);
        mockMvc.perform(post("/actors/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest());

    }

}
