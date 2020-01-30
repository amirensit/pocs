package com.example.demo.services;

import com.example.demo.services.dto.ActorDTO;
import com.example.demo.services.mapper.ActorMapperTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ActorServiceTest {

    @Autowired
    private ActorService actorService;

    @Test
    public void whenActorInvalid_thenThrowsException() {
        ActorDTO actorDTO = ActorMapperTest.createActorDTO();
        actorDTO.setIpAddress("222.333.222.65"); // setting invalid ip address.
        Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
            actorService.validateAndSave(actorDTO);
        });
        assertEquals("{IpAddress.invalid}", ((ConstraintViolationException)exception).getConstraintViolations().iterator().next().getMessageTemplate());
    }

    @Test
    void givenInjectedValidator_whenActorIsInvalid_thenThrowsException(){
        ActorDTO actorDTO = ActorMapperTest.createActorDTO();
        actorDTO.setIpAddress("222.333.222.65"); // setting invalid ip address.

        assertThrows(ConstraintViolationException.class, () -> {
            actorService.programmaticallyValidate(actorDTO);
        });
    }

}
